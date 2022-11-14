package com.sjiwon.studyholic.web.api.authenticate.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EmailAuthenticationRequest {
    @ApiModelProperty(value = "인증 대상 자원 [회원가입 = join / 아이디 찾기 = id / 비밀번호찾기 = password]", example = "join", required = true)
    private String resource;

    @ApiModelProperty(value = "인증받을 이메일 (실제 메일 발송)", required = true)
    private String email;
}
