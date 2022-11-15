package com.sjiwon.studyholic.web.api.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChangePasswordRequest {
    @ApiModelProperty(value = "사용자 ID(PK)", example = "1", required = true)
    private Long userId;

    @ApiModelProperty(value = "원래 비밀번호", example = "1234", required = true)
    private String currentPassword;

    @ApiModelProperty(value = "변경할 비밀번호", example = "!hello1234", required = true)
    private String changePassword;
}
