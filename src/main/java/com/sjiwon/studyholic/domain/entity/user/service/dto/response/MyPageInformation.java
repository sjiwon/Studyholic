package com.sjiwon.studyholic.domain.entity.user.service.dto.response;

import com.sjiwon.studyholic.domain.entity.user.repository.dto.BasicUser;
import com.sjiwon.studyholic.domain.entity.user.service.dto.BasicUserDto;
import lombok.Getter;

import java.util.Locale;

@Getter
public class MyPageInformation {
    private final BasicUserDto basicUser;

    public MyPageInformation(BasicUser user, Locale locale) {
        this.basicUser = new BasicUserDto(user, locale);
    }
}
