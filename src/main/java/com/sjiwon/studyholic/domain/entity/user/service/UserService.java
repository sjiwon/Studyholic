package com.sjiwon.studyholic.domain.entity.user.service;

import com.sjiwon.studyholic.domain.entity.study.repository.StudyRepository;
import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import com.sjiwon.studyholic.domain.entity.study.service.dto.StudyLeaderDto;
import com.sjiwon.studyholic.domain.entity.studytag.StudyTag;
import com.sjiwon.studyholic.domain.entity.studytag.repository.StudyTagRepository;
import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.UserRepository;
import com.sjiwon.studyholic.domain.entity.user.service.dto.response.MyPageInformation;
import com.sjiwon.studyholic.domain.entity.user.service.dto.response.ParticipateStudyInformation;
import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.UserStudyRepository;
import com.sjiwon.studyholic.domain.etc.file.FileUploadService;
import com.sjiwon.studyholic.domain.etc.session.SessionRefreshService;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    // repository
    private final UserRepository userRepository;
    private final UserStudyRepository userStudyRepository;
    private final StudyRepository studyRepository;
    private final StudyTagRepository studyTagRepository;

    // service
    private final FileUploadService fileUploadService;
    private final SessionRefreshService sessionRefreshService;

    @Transactional
    public Long saveUser(User user, @Nullable MultipartFile profile) {
        if (Objects.isNull(profile)) {
            user.applyDefaultImage();
        } else {
            fileUploadService.uploadProfileImage(profile, user);
        }

        return userRepository.save(user).getId();
    }

    public void hasDuplicateNickname(String nickname) {
        if (userRepository.existsByNickName(nickname)) {
            throw StudyholicException.type(DUPLICATE_USER_NICKNAME);
        }
    }

    public void hasDuplicateLoginId(String loginId) {
        if (userRepository.existsByLoginId(loginId)) {
            throw StudyholicException.type(DUPLICATE_USER_LOGIN_ID);
        }
    }

    @Transactional
    public void changeUserProfileImage(Long requestUserId, MultipartFile profile, HttpServletRequest request) {
        Long currentUserId = sessionRefreshService.getCurrentUserSession(request).getId();
        isIllegalRequestByAnonymousUser(requestUserId, currentUserId);

        User user = userRepository.findById(requestUserId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        fileUploadService.uploadProfileImage(profile, user);
    }

    @Transactional
    public void changeUserProfileImageToDefault(Long requestUserId, HttpServletRequest request) {
        Long currentUserId = sessionRefreshService.getCurrentUserSession(request).getId();
        isIllegalRequestByAnonymousUser(requestUserId, currentUserId);

        User user = userRepository.findById(requestUserId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        user.applyDefaultImage();
    }

    private void isIllegalRequestByAnonymousUser(Long requestUserId, Long currentUserId) {
        if (!Objects.equals(requestUserId, currentUserId)) {
            throw StudyholicException.type(ILLEGAL_REQUEST_BY_ANONYMOUS);
        }
    }

    @Transactional
    public void changeUserNickname(Long userId, String updateNickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        isSameNicknameAsBefore(user.getNickName(), updateNickname);
        isDuplicateNickname(userId, updateNickname);
        user.changeNickname(updateNickname);
    }

    private void isSameNicknameAsBefore(String beforeNickname, String afterNickname) {
        if (Objects.equals(beforeNickname, afterNickname)) {
            throw StudyholicException.type(SAME_USER_NICKNAME_AS_BEFORE);
        }
    }

    private void isDuplicateNickname(Long userId, String nickname) {
        if (userRepository.existsByIdNotAndNickName(userId, nickname)) {
            throw StudyholicException.type(DUPLICATE_USER_NICKNAME);
        }
    }

    public String findLoginId(String name, String email) {
        return userRepository.findByNameAndEmail(name, email)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND))
                .getLoginId();
    }

    /**
     * 마이페이지 정보
     */
    public MyPageInformation getUserDetailInformation(Long userId) {
        return new MyPageInformation(
                userRepository.getBasicUserInformation(userId)
                        .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND))
        );
    }

    /**
     * 참여중인 스터디 정보
     */
    public List<ParticipateStudyInformation> getUserParticipateStudyInformation(Long userId) {
        List<BasicStudy> basicStudyInformations = studyRepository.getUserParticipateStudyInformation(userId);
        List<StudyTag> studyTagList = studyTagRepository.findAllWithFetchStudy();
        List<UserStudy> userStudyList = userStudyRepository.findAllWithFetchUserAndStudy();

        return basicStudyInformations.stream()
                .map(basicStudyInformation -> new ParticipateStudyInformation(
                        basicStudyInformation,
                        getStudyTagList(studyTagList, basicStudyInformation),
                        getStudyLeaderInformation(userStudyList, basicStudyInformation.getId())
                ))
                .collect(Collectors.toList());
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
}
