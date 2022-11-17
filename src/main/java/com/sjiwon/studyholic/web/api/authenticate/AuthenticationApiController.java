package com.sjiwon.studyholic.web.api.authenticate;

import com.sjiwon.studyholic.domain.etc.login.LoginService;
import com.sjiwon.studyholic.domain.etc.mail.MailService;
import com.sjiwon.studyholic.web.api.authenticate.dto.EmailAuthenticationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = {"이메일 인증 API"})
public class AuthenticationApiController {
    private final LoginService loginService;
    private final MailService mailService;

    @PostMapping("/email/authenticate") // 이메일 인증 (회원가입[join] / 아이디 찾기[id] / 비밀번호 찾기[password])
    @ApiOperation(value = "이메일 인증 API", notes = "회원가입/아이디 찾기/비밀번호 찾기 프로세스에서 이메일 인증을 위한 API")
    public String emailVerificationProcess(@RequestBody EmailAuthenticationRequest authenticationRequest) {
        return mailService.sendEmailAuthenticationNonce(authenticationRequest.getResource(), authenticationRequest.getEmail());
    }
}
