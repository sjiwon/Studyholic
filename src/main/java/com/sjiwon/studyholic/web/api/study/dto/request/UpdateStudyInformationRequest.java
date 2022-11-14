package com.sjiwon.studyholic.web.api.study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateStudyInformationRequest {
    @ApiModelProperty(value = "변경할 스터디명", example = "테스트", required = true)
    private String name;

    @ApiModelProperty(value = "변경할 스터디 간단 설명", example = "간단한 스터디 설명입니다", required = true)
    private String briefDescription;

    @ApiModelProperty(value = "변경할 스터디 설명", example = "자세한 스터디 설명입니다", required = true)
    private String description;

    @ApiModelProperty(value = "변경할 스터디 모집마감일", example = "2022-12-31", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recruitDeadline;

    @ApiModelProperty(value = "변경할 스터디 최대 모집 인원수", example = "2", required = true)
    private Integer maxMember;
}
