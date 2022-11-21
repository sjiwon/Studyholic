package com.sjiwon.studyholic.domain.entity.study.service.dto.response;

import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StudyDetailToEditInformation {
    private Long studyId;
    private String studyName;
    private String studyBriefDescription;
    private String studyDescription;
    private Integer studyMaxMemberCount;
    private LocalDateTime studyRecruitDeadLine;
    private String studyTagList;

    public StudyDetailToEditInformation(BasicStudy information, List<String> studyTagList) {
        this.studyId = information.getId();
        this.studyName = information.getName();
        this.studyBriefDescription = information.getBriefDescription();
        this.studyDescription = information.getDescription();
        this.studyMaxMemberCount = information.getMaxMember();
        this.studyRecruitDeadLine = information.getRecruitDeadLine();
        this.studyTagList = studyTagList.stream().collect(Collectors.joining(", "));
    }
}
