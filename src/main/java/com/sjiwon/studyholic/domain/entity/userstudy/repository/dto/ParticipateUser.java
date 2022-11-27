package com.sjiwon.studyholic.domain.entity.userstudy.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipateUser {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String profileImage;
    private boolean teamLeader;

    @QueryProjection
    public ParticipateUser(Long id, String name, String nickname, String email, String profileImage, boolean teamLeader) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.teamLeader = teamLeader;
    }
}
