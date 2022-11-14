package com.sjiwon.studyholic.domain.entity.user.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicUser {
    private Long id;
    private String name;
    private String nickname;
    private String loginId;
    private String loginPassword;
    private LocalDate birth;
    private String email;
    private LocalDateTime joinDate;
    private String profileImage;
    private LocalDateTime lastModifiedDate;

    @QueryProjection
    public BasicUser(Long id, String name, String nickname, String loginId, String loginPassword, String email, LocalDate birth, LocalDateTime joinDate, String profileImage, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.birth = birth;
        this.email = email;
        this.joinDate = joinDate;
        this.profileImage = profileImage;
        this.lastModifiedDate = lastModifiedDate;
    }
}
