package com.sjiwon.studyholic.domain.etc.session;

import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.UserRepository;
import com.sjiwon.studyholic.domain.etc.login.dto.response.UserSession;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.sjiwon.studyholic.common.VariableFactory.SESSION_KEY;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.USER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SessionRefreshService {
    private final UserRepository userRepository;

    @Transactional
    public void refreshSession(Long userId, HttpServletRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY, UserSession.of(user));
    }
}
