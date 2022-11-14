package com.sjiwon.studyholic.web.api.study.dto.request;

import com.sjiwon.studyholic.domain.entity.study.Study;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StudyCreateRequest {
    @ApiModelProperty(value = "스터디 만드는 사용자 ID(PK)", example = "1", required = true)
    private Long userId;

    @ApiModelProperty(value = "스터디 이름", example = "스터디", required = true)
    private String name;

    @ApiModelProperty(value = "스터디 최대 인원", example = "2", required = true)
    private Integer maxMember;

    @ApiModelProperty(value = "스터디 간단 설명", example = "간단한 설명...", required = true)
    private String briefDescription;

    @ApiModelProperty(value = "스터디 설명", example = "자세한 설명...", required = true)
    private String description;

    @ApiModelProperty(value = "스터디 모집 마감일", example = "2022-12-31", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recruitDeadline;

    @ApiModelProperty(value = "스터디 태그 리스트")
    private List<String> tagList;

    public Study toEntity() {
        return Study.createStudy(name, maxMember, briefDescription, description, recruitDeadline);
    }
}
