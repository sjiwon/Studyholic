package com.sjiwon.studyholic.domain.entity.study.service.dto;

import com.sjiwon.studyholic.common.CommonDateTranslator;
import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import lombok.Getter;

import java.util.Locale;

@Getter
public class BasicStudyDto {
    private final Long id; // 스터디 ID (PK)
    private final String name; // 스터디 이름
    private final String briefDescription; // 스터디 간단 요약 설명
    private final String description; // 스터디 설명
    private final Integer maxMemberCount; // 스터디 최대 정원
    private final Integer currentMemberCount; // 스터디 현재 참가 인원 수
    private final String registerDate; // 스터디 등록 날짜
    private final String registerDateFromCurrentDate; // 현재 날짜로부터 얼마 전에 올렸는지
    private final String randomSequence; // 스터디 UUID (조회를 위한 Sequence)
    private final String recruitDeadline; // 스티더 모집 마감일
    private final String lastModifiedDate;

    public BasicStudyDto(BasicStudy study, Locale locale) {
        this.id = study.getId();
        this.name = study.getName();
        this.briefDescription = study.getBriefDescription();
        this.description = study.getDescription();
        this.maxMemberCount = study.getMaxMember();
        this.currentMemberCount = study.getCurrentMemberCount();
        this.registerDate = CommonDateTranslator.translateLocalDateTimeToStringVersion1(study.getRegisterDate(), locale);
        this.registerDateFromCurrentDate = CommonDateTranslator.translateRegisterDateFromCurrentDate(study.getRegisterDate(), locale);
        this.randomSequence = study.getRandomSequence();
        this.recruitDeadline = CommonDateTranslator.translateLocalDateTimeToStringVersion2(study.getRecruitDeadLine(), locale);
        this.lastModifiedDate = CommonDateTranslator.translateLocalDateTimeToStringVersion2(study.getLastModifiedDate(), locale);
    }
}
