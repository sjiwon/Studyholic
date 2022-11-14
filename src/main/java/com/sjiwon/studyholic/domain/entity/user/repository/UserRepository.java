package com.sjiwon.studyholic.domain.entity.user.repository;

import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.dsl.UserQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserQueryDslRepository {
    Optional<User> findByLoginId(String loginId);
    boolean existsByNickName(String nickName);
    boolean existsByLoginId(String loginId);
    boolean existsByEmail(String Email);
}
