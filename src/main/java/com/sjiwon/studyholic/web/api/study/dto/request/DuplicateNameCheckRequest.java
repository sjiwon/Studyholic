package com.sjiwon.studyholic.web.api.study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DuplicateNameCheckRequest {
    @ApiModelProperty(value = "중복체크 대상 자원 [name]", example = "name", required = true)
    private String resource;

    @ApiModelProperty(value = "중복체크 대상 자원의 값", example = "코틀린 스터디", required = true)
    private String value;
}
