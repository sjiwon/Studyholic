package com.sjiwon.studyholic.domain.entity.user.service;

import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.UserRepository;
import com.sjiwon.studyholic.domain.entity.user.service.dto.response.MyPageInformation;
import com.sjiwon.studyholic.domain.etc.file.FileUploadService;
import com.sjiwon.studyholic.domain.etc.login.dto.response.UserSession;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.sjiwon.studyholic.common.VariableFactory.SESSION_KEY;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    // repository
    private final UserRepository userRepository;

    // service
    private final FileUploadService fileUploadService;

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
        Long currentUserId = getCurrentUserSession(request).getId();
        isIllegalRequestByAnonymousUser(requestUserId, currentUserId);

        User user = userRepository.findById(requestUserId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        fileUploadService.uploadProfileImage(profile, user);
    }

    @Transactional
    public void changeUserProfileImageToDefault(Long requestUserId, HttpServletRequest request) {
        Long currentUserId = getCurrentUserSession(request).getId();
        isIllegalRequestByAnonymousUser(requestUserId, currentUserId);

        User user = userRepository.findById(requestUserId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        user.applyDefaultImage();
    }

    private UserSession getCurrentUserSession(HttpServletRequest request) {
        return (UserSession) request.getSession(false).getAttribute(SESSION_KEY);
    }

    private void isIllegalRequestByAnonymousUser(Long requestUserId, Long currentUserId) {
        if (!Objects.equals(requestUserId, currentUserId)) {
            throw StudyholicException.type(ILLEGAL_REQUEST_BY_ANONYMOUS);
        }
    }

    /**
     * View를 위한 Service Logic
     */
    public MyPageInformation getUserDetailInformation(Long userId) {
        return new MyPageInformation(
                userRepository.getBasicUserInformation(userId)
                        .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND))
        );
    }
}
