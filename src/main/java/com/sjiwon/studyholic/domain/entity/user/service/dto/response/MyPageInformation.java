package com.sjiwon.studyholic.domain.entity.user.service.dto.response;

import com.sjiwon.studyholic.domain.entity.user.repository.dto.BasicUser;
import com.sjiwon.studyholic.domain.entity.user.service.dto.BasicUserDto;
import lombok.Getter;

@Getter
public class MyPageInformation {
    private final BasicUserDto basicUser;

    public MyPageInformation(BasicUser user) {
        this.basicUser = new BasicUserDto(user);
    }
}
