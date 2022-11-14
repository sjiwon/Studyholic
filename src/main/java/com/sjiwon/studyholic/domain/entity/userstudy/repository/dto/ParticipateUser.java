package com.sjiwon.studyholic.domain.entity.userstudy.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipateUser {
    private Long userId;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userProfileImage;
    private boolean teamLeader;

    @QueryProjection
    public ParticipateUser(Long userId, String userName, String userNickname, String userEmail, String userProfileImage, boolean teamLeader) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userProfileImage = userProfileImage;
        this.teamLeader = teamLeader;
    }
}
