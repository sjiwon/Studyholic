package com.sjiwon.studyholic.domain.entity.study.service;

import com.sjiwon.studyholic.domain.entity.study.Study;
import com.sjiwon.studyholic.domain.entity.study.repository.StudyRepository;
import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import com.sjiwon.studyholic.domain.entity.study.service.dto.StudyLeaderDto;
import com.sjiwon.studyholic.domain.entity.study.service.dto.request.UpdateStudyInformationRequestDto;
import com.sjiwon.studyholic.domain.entity.study.service.dto.response.StudyDetailInformation;
import com.sjiwon.studyholic.domain.entity.study.service.dto.response.StudySimpleInformation;
import com.sjiwon.studyholic.domain.entity.studytag.StudyTag;
import com.sjiwon.studyholic.domain.entity.studytag.repository.StudyTagRepository;
import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.UserRepository;
import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.UserStudyRepository;
import com.sjiwon.studyholic.domain.etc.session.SessionRefreshService;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {
    // repository
    private final StudyRepository studyRepository;
    private final StudyTagRepository studyTagRepository;
    private final UserStudyRepository userStudyRepository;
    private final UserRepository userRepository;

    // service
    private final SessionRefreshService sessionRefreshService;

    @Transactional
    public Long createNewStudy(Long userId, Study study, List<String> tagList) {
        User user = userRepository.findAllByIdWithFetchUserStudy(userId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));

        tagList.forEach(tag -> study.getStudyTagList().add(StudyTag.addTagInStudy(study, tag)));
        Study createStudy = studyRepository.save(study); // cascade StudyTag
        userStudyRepository.save(UserStudy.addUserInStudy(user, createStudy, Boolean.TRUE));

        return createStudy.getId();
    }

    @Transactional
    public void changeInformation(HttpServletRequest request, Long studyId, UpdateStudyInformationRequestDto updateRequest) {
        Long currentUserId = sessionRefreshService.getCurrentUserSession(request).getId();
        isStudyLeaderUpdateRequest(studyId, currentUserId); // 스터디 리더의 업데이트 요청인지 검증

        Study study = studyRepository.findByStudyIdWithFetchStudyTag(studyId)
                .orElseThrow(() -> StudyholicException.type(STUDY_NOT_FOUND));

        if (StringUtils.hasText(updateRequest.getName())) {
            checkDuplicateStudyName(study.getId(), updateRequest.getName());
            study.changeName(updateRequest.getName());
        }
        if (StringUtils.hasText(updateRequest.getBriefDescription())) {
            study.changeBriefDescription(updateRequest.getBriefDescription());
        }
        if (StringUtils.hasText(updateRequest.getDescription())) {
            study.changeDescription(updateRequest.getDescription());
        }
        if (Objects.nonNull(updateRequest.getRecruitDeadline())) {
            study.changeRecruitDeadLine(updateRequest.getRecruitDeadline());
        }
        if (Objects.nonNull(updateRequest.getMaxMember())) {
            study.changeMaxMember(updateRequest.getMaxMember());
        }

        List<String> tagList = updateRequest.getTagList();
        if (!CollectionUtils.isEmpty(tagList) && !isTagListExactlySameAsBefore(study, tagList)) {
            // 1) StudyTagRepository에서 studyId에 해당되는 모든 Instance -> BatchDelete
            studyTagRepository.deleteInBatchByStudyId(studyId);

            // 2) tagList에 존재하는 tag들을 다시 StudyTagRepository에 saveAll
            List<StudyTag> studyTagListForBatchInsert = new ArrayList<>();
            tagList.forEach(tag -> studyTagListForBatchInsert.add(StudyTag.addTagInStudy(study, tag)));
            studyTagRepository.saveAll(studyTagListForBatchInsert);
        }
    }

    private boolean isTagListExactlySameAsBefore(Study study, List<String> tagList) {
        List<String> originTagList = study.getStudyTagList()
                .stream()
                .map(StudyTag::getTag)
                .collect(Collectors.toList());
        return new HashSet<>(tagList).containsAll(originTagList);
    }

    private void isStudyLeaderUpdateRequest(Long studyId, Long userId) {
        if (userStudyRepository.existsByStudyIdAndUserIdAndTeamLeaderIsFalse(studyId, userId)) {
            throw StudyholicException.type(BAD_UPDATE_REQUEST_FROM_ANONYMOUS_USER);
        }
    }

    private void checkDuplicateStudyName(Long studyId, String name) {
        if (studyRepository.existsByIdNotAndName(studyId, name)) {
            throw StudyholicException.type(DUPLICATE_STUDY_NAME);
        }
    }

    @Transactional
    public void deleteStudy(Long studyId, HttpServletRequest request) {
        Long currentUserId = sessionRefreshService.getCurrentUserSession(request).getId();
        isStudyLeaderDeleteRequest(studyId, currentUserId); // 스터디 리더의 삭제 요청인지 검증
        userStudyRepository.deleteInBatchByStudyId(studyId); // 1) delete UserStudy (batch)
        studyTagRepository.deleteInBatchByStudyId(studyId); // 2) delete StudyTag (batch)
        studyRepository.deleteByStudyId(studyId); // 3) delete Study
    }

    private void isStudyLeaderDeleteRequest(Long studyId, Long userId) {
        if (userStudyRepository.existsByStudyIdAndUserIdAndTeamLeaderIsFalse(studyId, userId)) {
            throw StudyholicException.type(BAD_DELETE_REQUEST_FROM_ANONYMOUS_USER);
        }
    }

    public void hasDuplicateName(String name) {
        if (studyRepository.existsByName(name)) {
            throw StudyholicException.type(DUPLICATE_STUDY_NAME);
        }
    }

    /**
     * 메인 페이지 스터디 리스트
     */
    public Page<StudySimpleInformation> getMainPageStudyList(Pageable pageable, String sort, @Nullable String keyword) {
        Page<BasicStudy> pagingStudyList = isRequestContainsKeyword(keyword)
                ? studyRepository.getMainPageStudyListWithKeyword(pageable, sort, keyword)
                : studyRepository.getMainPageStudyList(pageable, sort);

        List<StudyTag> studyTagList = studyTagRepository.findAllWithFetchStudy();
        List<UserStudy> userStudyList = userStudyRepository.findAllWithFetchUserAndStudy();
        List<StudySimpleInformation> studySimpleInformationList = pagingStudyList
                .stream()
                .map(basicStudyInformation -> new StudySimpleInformation(
                        basicStudyInformation,
                        getStudyTagList(studyTagList, basicStudyInformation),
                        getStudyLeaderInformation(userStudyList, basicStudyInformation.getId())
                ))
                .collect(Collectors.toList());

        return PageableExecutionUtils.getPage(studySimpleInformationList, pageable, pagingStudyList::getTotalElements);
    }

    private boolean isRequestContainsKeyword(String keyword) {
        return Objects.nonNull(keyword) && StringUtils.hasText(keyword);
    }

    private List<String> getStudyTagList(List<StudyTag> studyTagList, BasicStudy basicStudy) {
        return studyTagList
                .stream()
                .filter(studyTag -> studyTag.getStudy().getId().equals(basicStudy.getId()))
                .map(StudyTag::getTag)
                .collect(Collectors.toList());
    }

    private StudyLeaderDto getStudyLeaderInformation(List<UserStudy> userStudyList, Long studyId) {
        return userStudyList
                .stream()
                .filter(userStudy -> userStudy.getStudy().getId().equals(studyId) && userStudy.isTeamLeader())
                .map(userStudy -> new StudyLeaderDto(userStudy.getUser()))
                .findFirst()
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
    }

    /**
     * 특정 스터디 상세 정보
     */
    public StudyDetailInformation getStudyDetailInformation(Long studyId) {
        return new StudyDetailInformation(
                studyRepository.getBasicStudyInformation(studyId)
                        .orElseThrow(() -> StudyholicException.type(STUDY_NOT_FOUND)),
                userStudyRepository.findStudyLeaderIdByStudyId(studyId),
                studyTagRepository.findTagListByStudyId(studyId),
                userStudyRepository.findParticipateUserListByStudyId(studyId)
        );
    }
}
