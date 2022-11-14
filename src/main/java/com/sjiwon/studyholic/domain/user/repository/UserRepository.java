package com.sjiwon.studyholic.domain.user.repository;

import com.sjiwon.studyholic.domain.user.User;
import com.sjiwon.studyholic.domain.user.repository.dsl.UserQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserQueryDslRepository {
}
