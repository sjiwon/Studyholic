package com.sjiwon.studyholic.domain.entity.user.service.dto.response;

import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import com.sjiwon.studyholic.domain.entity.study.service.dto.BasicStudyDto;
import com.sjiwon.studyholic.domain.entity.study.service.dto.StudyLeaderDto;
import lombok.Getter;

import java.util.List;
import java.util.Locale;

@Getter
public class ParticipateStudyInformation {
    private final BasicStudyDto basicStudy;
    private final List<String> studyTagList;
    private final StudyLeaderDto studyLeader;

    public ParticipateStudyInformation(BasicStudy study, List<String> studyTagList, StudyLeaderDto studyLeader, Locale locale) {
        this.basicStudy = new BasicStudyDto(study, locale);
        this.studyTagList = studyTagList;
        this.studyLeader = studyLeader;
    }
}
