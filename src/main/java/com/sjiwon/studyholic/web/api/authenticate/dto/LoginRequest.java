package com.sjiwon.studyholic.web.api.authenticate.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginRequest {
    @ApiModelProperty(value = "로그인 아이디", example = "admin", required = true)
    private String loginId;

    @ApiModelProperty(value = "로그인 비밀번호", example = "1234", required = true)
    private String loginPassword;
}
