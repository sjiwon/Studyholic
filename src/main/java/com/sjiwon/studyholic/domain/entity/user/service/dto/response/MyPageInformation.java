package com.sjiwon.studyholic.domain.entity.user.service.dto.response;

import com.sjiwon.studyholic.common.CommonDateTranslator;
import com.sjiwon.studyholic.domain.entity.user.repository.dto.BasicUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MyPageInformation {
    private Long userId;
    private String userName;
    private String userNickname;
    private String userLoginId;
    private String userLoginPassword;
    private String userBirth;
    private String userEmail;
    private String userJoinDate;
    private String userProfileImage;
    private String userLastModifiedDate;

    public MyPageInformation(BasicUser user) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.userNickname = user.getNickname();
        this.userLoginId = user.getLoginId();
        this.userLoginPassword = user.getLoginPassword();
        this.userBirth = CommonDateTranslator.translateLocalDateToString(user.getBirth());
        this.userEmail = user.getEmail();
        this.userJoinDate = CommonDateTranslator.translateLocalDateTimeToStringVersion1(user.getJoinDate());
        this.userProfileImage = user.getProfileImage();
        this.userLastModifiedDate = CommonDateTranslator.translateLocalDateTimeToStringVersion2(user.getLastModifiedDate());
    }
}
