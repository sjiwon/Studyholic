package com.sjiwon.studyholic.domain.entity.study.service.dto;

import com.sjiwon.studyholic.domain.entity.user.User;
import lombok.Getter;

@Getter
public class StudyLeaderDto {
    private final Long id; // 스터디 리더 ID (PK)
    private final String name; // 스터디 리더 이름
    private final String nickname; // 스터디 리더 닉네임
    private final String storageName; // 스티더 리더 이미지 (서버 저장 이름)

    public StudyLeaderDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickName();
        this.storageName = user.getStorageName();
    }
}
