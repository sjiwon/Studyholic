package com.sjiwon.studyholic.web.api.authenticate;

import com.sjiwon.studyholic.domain.login.LoginService;
import com.sjiwon.studyholic.domain.login.dto.response.UserSession;
import com.sjiwon.studyholic.web.api.authenticate.dto.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.sjiwon.studyholic.common.VariableFactory.SESSION_KEY;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = {"인증(로그인/로그아웃/이메일) API"})
public class AuthenticationApiController {
    private final LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "로그인 API", notes = "로그인을 진행하는 API")
    public ResponseEntity<Void> loginProcess(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        UserSession userSession = loginService.login(loginRequest.getLoginId(), loginRequest.getLoginPassword());

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY, userSession);

        return ResponseEntity.noContent().build();
    }
}
