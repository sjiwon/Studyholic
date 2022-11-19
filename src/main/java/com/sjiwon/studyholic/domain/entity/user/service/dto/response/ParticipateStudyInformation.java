package com.sjiwon.studyholic.domain.entity.user.service.dto.response;

import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import com.sjiwon.studyholic.domain.entity.study.service.dto.BasicStudyDto;
import com.sjiwon.studyholic.domain.entity.study.service.dto.StudyLeaderDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ParticipateStudyInformation {
    private final BasicStudyDto basicStudy;
    private final List<String> studyTagList;
    private final StudyLeaderDto studyLeader;

    public ParticipateStudyInformation(BasicStudy study, List<String> studyTagList, StudyLeaderDto studyLeader) {
        this.basicStudy = new BasicStudyDto(study);
        this.studyTagList = studyTagList;
        this.studyLeader = studyLeader;
    }
}
