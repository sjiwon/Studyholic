package com.sjiwon.studyholic.web.api.userstudy.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserStudyParticipateRequest {
    @ApiModelProperty(value = "스터디 ID(PK)", example = "1", required = true)
    private Long studyId;

    @ApiModelProperty(value = "스터디에 참여하는 사용자 ID(PK)", example = "1", required = true)
    private Long userId;
}
