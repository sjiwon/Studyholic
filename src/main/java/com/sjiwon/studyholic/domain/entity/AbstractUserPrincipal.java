package com.sjiwon.studyholic.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AbstractUserPrincipal {
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private List<String> roles;
}
