package com.sjiwon.studyholic.web.api.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserDuplicateCheckRequest {
    @ApiModelProperty(value = "중복체크 대상 자원 [nickname, id]", example = "nickname", required = true)
    private String resource;

    @ApiModelProperty(value = "중복체크 대상 자원의 값", example = "User1", required = true)
    private String value;
}
