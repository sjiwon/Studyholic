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
    @ApiModelProperty(value = "스터디 ID(PK) - 스터디 수정 시 중복체크 API에서 필요한 필드", example = "1")
    private Long studyId;

    @ApiModelProperty(value = "중복체크할 스터디 이름", example = "코틀린 스터디", required = true)
    private String name;
}
