package com.sjiwon.studyholic.domain.userstudy.repository;

import com.sjiwon.studyholic.domain.userstudy.UserStudy;
import com.sjiwon.studyholic.domain.userstudy.repository.dsl.UserStudyQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyRepository extends JpaRepository<UserStudy, Long>, UserStudyQueryDslRepository {
}
