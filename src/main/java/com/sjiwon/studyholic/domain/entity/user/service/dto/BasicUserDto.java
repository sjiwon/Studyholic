package com.sjiwon.studyholic.domain.entity.user.service.dto;

import com.sjiwon.studyholic.common.CommonDateTranslator;
import com.sjiwon.studyholic.domain.entity.user.repository.dto.BasicUser;
import lombok.Getter;

@Getter
public class BasicUserDto {
    private final Long id;
    private final String name;
    private final String nickname;
    private final String loginId;
    private final String birth;
    private final String email;
    private final String joinDate;
    private final String profileImage;
    private final String lastModifiedDate;

    public BasicUserDto(BasicUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.loginId = user.getLoginId();
        this.birth = CommonDateTranslator.translateLocalDateToString(user.getBirth());
        this.email = user.getEmail();
        this.joinDate = CommonDateTranslator.translateLocalDateTimeToStringVersion1(user.getJoinDate());
        this.profileImage = user.getProfileImage();
        this.lastModifiedDate = CommonDateTranslator.translateLocalDateTimeToStringVersion2(user.getLastModifiedDate());
    }
}
