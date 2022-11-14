package com.sjiwon.studyholic.domain.user.service;

import com.sjiwon.studyholic.domain.file.FileUploadService;
import com.sjiwon.studyholic.domain.user.User;
import com.sjiwon.studyholic.domain.user.repository.UserRepository;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.DUPLICATE_USER_LOGIN_ID;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.DUPLICATE_USER_NICKNAME;

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
}
