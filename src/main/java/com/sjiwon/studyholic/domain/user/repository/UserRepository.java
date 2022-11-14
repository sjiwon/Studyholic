package com.sjiwon.studyholic.domain.user.repository;

import com.sjiwon.studyholic.domain.user.User;
import com.sjiwon.studyholic.domain.user.repository.dsl.UserQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserQueryDslRepository {
    Optional<User> findByLoginId(String loginId);
}
