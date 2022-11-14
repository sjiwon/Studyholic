package com.sjiwon.studyholic.domain.entity.userstudy.repository;

import com.sjiwon.studyholic.domain.entity.study.Study;
import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.dsl.UserStudyQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyRepository extends JpaRepository<UserStudy, Long>, UserStudyQueryDslRepository {
    boolean existsByStudyIdAndUserIdAndTeamLeaderIsFalse(Long studyId, Long userId);
    boolean existsByStudyAndUser(Study study, User user);
}
