package com.sjiwon.studyholic.domain.entity.study.service.dto;

import com.sjiwon.studyholic.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StudyLeaderDto {
    private Long studyLeaderId; // 스터디 리더 ID (PK)
    private String studyLeaderName; // 스터디 리더 이름
    private String studyLeaderNickname; // 스터디 리더 닉네임
    private String studyLeaderImage; // 스티더 리더 이미지 (서버 저장 이름)

    public StudyLeaderDto(User user) {
        this.studyLeaderId = user.getId();
        this.studyLeaderName = user.getName();
        this.studyLeaderNickname = user.getNickName();
        this.studyLeaderImage = user.getStorageName();
    }
}
