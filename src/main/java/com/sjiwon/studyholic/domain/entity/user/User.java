package com.sjiwon.studyholic.domain.entity.user;

import com.sjiwon.studyholic.domain.entity.user.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.sjiwon.studyholic.common.VariableFactory.DEFAULT_IMAGE_NAME;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "nickname", length = 30, nullable = false, unique = true)
    private String nickName;

    @Column(name = "login_id", length = 30, nullable = false, unique = true)
    private String loginId;

    @Column(name = "login_password", length = 100, nullable = false)
    private String loginPassword;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @CreatedDate
    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "upload_name", length = 100, nullable = false)
    private String uploadName;

    @Column(name = "storage_name", length = 40, nullable = false)
    private String storageName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    private User(String name, String nickName, String loginId, String loginPassword, LocalDate birth, String email, Role role) {
        this.name = name;
        this.nickName = nickName;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.birth = birth;
        this.email = email;
        this.role = role;
    }

    public static User createUser(String name, String nickName, String loginId, String loginPassword, LocalDate birth, String email, Role role) {
        return new User(name, nickName, loginId, loginPassword, birth, email, role);
    }

    public void encodePassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void applyUploadImage(String uploadName, String storageName) {
        this.uploadName = uploadName;
        this.storageName = storageName;
    }

    public void applyDefaultImage() {
        this.uploadName = DEFAULT_IMAGE_NAME;
        this.storageName = DEFAULT_IMAGE_NAME;
    }

    public void changeNickname(String nickname) {
        this.nickName = nickname;
    }

    public void changePassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
