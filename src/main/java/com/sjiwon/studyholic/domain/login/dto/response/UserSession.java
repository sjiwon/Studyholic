package com.sjiwon.studyholic.domain.login.dto.response;

import com.sjiwon.studyholic.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSession {
    private Long id;
    private String name;
    private String nickName;
    private String email;
    private String uploadName;
    private String storageName;

    public static UserSession of(User user) {
        return new UserSession(
                user.getId(),
                user.getName(),
                user.getNickName(),
                user.getEmail(),
                user.getUploadName(),
                user.getStorageName()
        );
    }
}
