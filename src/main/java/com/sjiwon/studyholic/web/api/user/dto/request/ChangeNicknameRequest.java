package com.sjiwon.studyholic.web.api.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChangeNicknameRequest {
    @ApiModelProperty(value = "정보 변경할 사용자 ID(PK)", example = "1", required = true)
    private Long userId;

    @ApiModelProperty(value = "변경할 닉네임", example = "Hello World", required = true)
    private String nickname;
}
