package com.sjiwon.studyholic.domain.user.service;

import com.sjiwon.studyholic.domain.user.repository.UserRepository;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.DUPLICATE_USER_LOGIN_ID;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.DUPLICATE_USER_NICKNAME;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

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
