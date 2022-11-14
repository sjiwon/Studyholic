package com.sjiwon.studyholic.domain.entity.study.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjiwon.studyholic.common.CommonDateTranslator;
import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import com.sjiwon.studyholic.domain.entity.study.service.dto.StudyLeaderDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class StudySimpleInformation {
    private final Long studyId; // 스터디 ID (PK)
    private final String studyName; // 스터디 이름
    private final String studyBriefDescription; // 스터디 간단 요약 설명
    private final String studyDescription; // 스터디 설명
    private final Integer studyMaxMemberCount; // 스터디 최대 정원
    private final Integer studyCurrentMemberCount; // 스터디 현재 참가 인원 수
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분")
    private final LocalDateTime studyRegisterDate; // 스터디 등록 날짜
    private final String registerDateFromCurrentDate; // 현재 날짜로부터 얼마 전에 올렸는지
    private final String studyRecruitDeadLine; // 스티더 모집 마감일
    private final List<String> studyTagList; // 스터디 태그 리스트
    private final Long studyLeaderId; // 스터디 리더 ID (PK)
    private final String studyLeaderName; // 스터디 리더 이름
    private final String studyLeaderNickname; // 스터디 리더 닉네임
    private final String studyLeaderImage; // 스터디 리더 프로필 이미지 서버 저장 이름

    public StudySimpleInformation(BasicStudy study, List<String> studyTagList, StudyLeaderDto studyLeader) {
        this.studyId = study.getId();
        this.studyName = study.getName();
        this.studyBriefDescription = study.getBriefDescription();
        this.studyDescription = study.getDescription();
        this.studyMaxMemberCount = study.getMaxMember();
        this.studyCurrentMemberCount = study.getCurrentMemberCount();
        this.studyRegisterDate = study.getRegisterDate();
        this.registerDateFromCurrentDate = CommonDateTranslator.translateRegisterDateFromCurrentDate(study.getRegisterDate());
        this.studyRecruitDeadLine = CommonDateTranslator.translateLocalDateToString(study.getRecruitDeadLine());
        this.studyTagList = studyTagList;
        this.studyLeaderId = studyLeader.getStudyLeaderId();
        this.studyLeaderName = studyLeader.getStudyLeaderName();
        this.studyLeaderNickname = studyLeader.getStudyLeaderNickname();
        this.studyLeaderImage = studyLeader.getStudyLeaderImage();
    }
}
