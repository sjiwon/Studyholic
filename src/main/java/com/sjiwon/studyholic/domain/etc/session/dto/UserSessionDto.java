package com.sjiwon.studyholic.domain.etc.session.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User 정보에서 변경되는 값들을 응답해주는 Dto
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSessionDto {
    private String nickname;
    private String storageName;
}
