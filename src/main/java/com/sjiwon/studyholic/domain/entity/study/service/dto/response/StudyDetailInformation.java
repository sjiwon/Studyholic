package com.sjiwon.studyholic.domain.entity.study.service.dto.response;

import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import com.sjiwon.studyholic.domain.entity.study.service.dto.BasicStudyDto;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.dto.ParticipateUser;
import lombok.Getter;

import java.util.List;

@Getter
public class StudyDetailInformation {
    private final BasicStudyDto basicStudy;
    private final Long studyLeaderId;
    private final List<String> studyTagList;
    private final List<ParticipateUser> participateUserList;

    public StudyDetailInformation(BasicStudy study, Long studyLeaderId, List<String> studyTagList, List<ParticipateUser> participateUserList) {
        this.basicStudy = new BasicStudyDto(study);
        this.studyLeaderId = studyLeaderId;
        this.studyTagList = studyTagList;
        this.participateUserList = participateUserList;
    }
}
