package com.sjiwon.studyholic.domain.entity;

import com.sjiwon.studyholic.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AbstractUserPrincipal {
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private String role;

    public AbstractUserPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.loginId = user.getLoginId();
        this.password = user.getLoginPassword();
        this.role = user.getRole().getAuthority();
    }
}
