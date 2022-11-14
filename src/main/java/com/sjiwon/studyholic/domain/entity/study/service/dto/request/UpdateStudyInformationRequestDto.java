package com.sjiwon.studyholic.domain.entity.study.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateStudyInformationRequestDto {
    private String name;
    private String briefDescription;
    private String description;
    private LocalDate recruitDeadline;
    private Integer maxMember;
    private List<String> tagList;
}
