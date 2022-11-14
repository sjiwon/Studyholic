package com.sjiwon.studyholic.domain.entity.study.service.dto.response;

import com.sjiwon.studyholic.common.CommonDateTranslator;
import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.dto.ParticipateUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StudyDetailInformation {
    private Long studyId;
    private Long studyLeaderId;
    private String studyName;
    private String studyBriefDescription;
    private String studyDescription;
    private Integer studyMaxMemberCount;
    private Integer studyCurrentMemberCount;
    private String studyRecruitDeadLine;
    private String studyRegisterDate;
    private String studyLastModifiedDate;
    private List<String> studyTagList;
    private List<ParticipateUser> participateUserList;

    public StudyDetailInformation(BasicStudy information, Long studyLeaderId, List<String> studyTagList, List<ParticipateUser> participateUserList) {
        this.studyId = information.getId();
        this.studyLeaderId = studyLeaderId;
        this.studyName = information.getName();
        this.studyBriefDescription = information.getBriefDescription();
        this.studyDescription = information.getDescription();
        this.studyMaxMemberCount = information.getMaxMember();
        this.studyCurrentMemberCount = information.getCurrentMemberCount();
        this.studyRecruitDeadLine = CommonDateTranslator.translateLocalDateToString(information.getRecruitDeadLine());
        this.studyRegisterDate = CommonDateTranslator.translateLocalDateTimeToStringVersion1(information.getRegisterDate());
        this.studyLastModifiedDate = CommonDateTranslator.translateLocalDateTimeToStringVersion2(information.getLastModifiedDate());
        this.studyTagList = studyTagList;
        this.participateUserList = participateUserList;
    }
}
