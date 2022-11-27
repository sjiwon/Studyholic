package com.sjiwon.studyholic.domain.entity.user.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "회원")
    ;

    private String authority;
    private String description;

    Role(String authority, String description) {
        this.authority = authority;
        this.description = description;
    }
}
