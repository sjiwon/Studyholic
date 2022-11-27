package com.sjiwon.studyholic.web.api.study.dto.request;

import com.sjiwon.studyholic.domain.entity.study.service.dto.request.UpdateStudyInformationRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateStudyInformationRequest {
    @ApiModelProperty(value = "변경할 스터디명", example = "테스트")
    private String name;

    @ApiModelProperty(value = "변경할 스터디 간단 설명", example = "간단한 스터디 설명입니다")
    private String briefDescription;

    @ApiModelProperty(value = "변경할 스터디 설명", example = "자세한 스터디 설명입니다")
    private String description;

    @ApiModelProperty(value = "변경할 스터디 모집마감일", example = "2022-12-31 09:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recruitDeadline;

    @ApiModelProperty(value = "변경할 스터디 최대 모집 인원수", example = "2")
    private Integer maxMember;

    @ApiModelProperty(value = "업데이트될 스터디 태그 리스트")
    private List<String> tagList;

    public UpdateStudyInformationRequestDto toServiceDto() {
        return new UpdateStudyInformationRequestDto(name, briefDescription, description, recruitDeadline, maxMember, tagList);
    }
}
