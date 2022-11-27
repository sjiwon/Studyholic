package com.sjiwon.studyholic.web.api.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApplyRandomPasswordRequest {
    @ApiModelProperty(value = "이름", example = "User1", required = true)
    private String name;

    @ApiModelProperty(value = "아이디", example = "user1", required = true)
    private String loginId;

    @ApiModelProperty(value = "이메일 (실제 메일 발송)", required = true)
    private String email;
}
