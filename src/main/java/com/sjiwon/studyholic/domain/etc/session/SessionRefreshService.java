package com.sjiwon.studyholic.domain.etc.session;

import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.UserRepository;
import com.sjiwon.studyholic.domain.etc.session.dto.UserSessionDto;
import com.sjiwon.studyholic.exception.StudyholicException;
import com.sjiwon.studyholic.security.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.sjiwon.studyholic.common.VariableFactory.SESSION_KEY;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.UNAUTHENTICATED_USER;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SessionRefreshService {
    private final UserRepository userRepository;

    public void refreshSession(HttpServletRequest request, UserPrincipal userPrincipal) {
        if (Objects.isNull(userPrincipal)) {
            throw StudyholicException.type(UNAUTHENTICATED_USER);
        }

        HttpSession session = request.getSession();
        User user = userRepository.findById(userPrincipal.getUser().getId())
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        session.setAttribute(SESSION_KEY, new UserSessionDto(user.getNickName(), user.getStorageName()));
    }
}
