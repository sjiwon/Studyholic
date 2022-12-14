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
    private String randomSequence; // 스터디 UUID (조회를 위한 Sequence)
    private String studyTagList;

    public StudyDetailToEditInformation(BasicStudy study, List<String> studyTagList) {
        this.studyId = study.getId();
        this.studyName = study.getName();
        this.studyBriefDescription = study.getBriefDescription();
        this.studyDescription = study.getDescription();
        this.studyMaxMemberCount = study.getMaxMember();
        this.studyRecruitDeadLine = study.getRecruitDeadLine();
        this.randomSequence = study.getRandomSequence();
        this.studyTagList = studyTagList.stream().collect(Collectors.joining(", "));
    }
}
