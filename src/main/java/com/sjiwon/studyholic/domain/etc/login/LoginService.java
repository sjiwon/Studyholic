package com.sjiwon.studyholic.domain.etc.login;

import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.UserRepository;
import com.sjiwon.studyholic.domain.etc.login.dto.response.UserSession;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.USER_NOT_FOUND;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.WRONG_PASSWORD;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final UserRepository userRepository;

    public UserSession login(String loginId, String loginPassword) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        isCorrectPassword(user, loginPassword);
        return UserSession.of(user);
    }

    private void isCorrectPassword(User user, String loginPassword) {
        if (!user.getLoginPassword().equals(loginPassword)) {
            throw StudyholicException.type(WRONG_PASSWORD);
        }
    }
}
