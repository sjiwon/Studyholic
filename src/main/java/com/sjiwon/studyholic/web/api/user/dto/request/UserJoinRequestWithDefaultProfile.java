package com.sjiwon.studyholic.web.api.user.dto.request;

import com.sjiwon.studyholic.domain.entity.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserJoinRequestWithDefaultProfile {
    private String name;
    private String nickname;
    private String loginId;
    private String loginPassword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String email;

    public User toEntity() {
        return User.createUser(this.name, this.nickname, this.loginId, this.loginPassword, this.birth, this.email);
    }
}
