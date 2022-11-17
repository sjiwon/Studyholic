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
import com.sjiwon.studyholic.domain.etc.mail.MailService;
import com.sjiwon.studyholic.exception.StudyholicException;
import com.sjiwon.studyholic.security.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long saveUser(User user, @Nullable MultipartFile profile) {
        if (Objects.isNull(profile)) {
            user.applyDefaultImage();
        } else {
            fileUploadService.uploadProfileImage(profile, user);
        }
        user.encodePassword(passwordEncoder.encode(user.getLoginPassword())); // Encoding

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
    public void changeUserProfileImage(Long requestUserId, MultipartFile profile, UserPrincipal userPrincipal) {
        isIllegalRequestByAnonymousUser(requestUserId, userPrincipal); // 타인의 악의적인 API 요청 판별

        User user = userRepository.findById(requestUserId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        fileUploadService.uploadProfileImage(profile, user);
    }

    @Transactional
    public void changeUserProfileImageToDefault(Long requestUserId, UserPrincipal userPrincipal) {
        isIllegalRequestByAnonymousUser(requestUserId, userPrincipal); // 타인의 악의적인 API 요청 판별

        User user = userRepository.findById(requestUserId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        user.applyDefaultImage();
    }

    @Transactional
    public void changeUserNickname(Long requestUserId, String updateNickname, UserPrincipal userPrincipal) {
        isIllegalRequestByAnonymousUser(requestUserId, userPrincipal); // 타인의 악의적인 API 요청 판별

        User user = userRepository.findById(requestUserId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        isSameNicknameAsBefore(user.getNickName(), updateNickname);
        isDuplicateNickname(requestUserId, updateNickname);
        user.changeNickname(updateNickname);
    }

    private void isIllegalRequestByAnonymousUser(Long requestUserId, UserPrincipal userPrincipal) {
        if (Objects.isNull(userPrincipal)) {
            throw StudyholicException.type(UNAUTHENTICATED_USER);
        } else if (!Objects.equals(userPrincipal.getUser().getId(), requestUserId)) {
            throw StudyholicException.type(ILLEGAL_API_REQUEST);
        }
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

    @Transactional
    public void applyRandomPassword(String name, String loginId, String email) {
        User user = userRepository.findByNameAndLoginIdAndEmail(name, loginId, email)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        String randomPassword = mailService.sendEmailAuthenticationNonce("randomPassword", email);
        user.changePassword(passwordEncoder.encode(randomPassword));
    }

    @Transactional
    public void changePassword(Long userId, String currentPassword, String changePassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        isCorrectPassword(user.getLoginPassword(), currentPassword);
        user.changePassword(passwordEncoder.encode(changePassword));
    }

    private void isCorrectPassword(String originPassword, String requestPassword) {
        if (!passwordEncoder.matches(requestPassword, originPassword)) {
            throw StudyholicException.type(WRONG_PASSWORD_WITH_RESET_PASSWORD_VERIFICATION);
        }
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
