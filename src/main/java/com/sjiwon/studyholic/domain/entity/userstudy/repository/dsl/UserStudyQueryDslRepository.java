package com.sjiwon.studyholic.domain.entity.userstudy.repository.dsl;

import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;

import java.util.List;

public interface UserStudyQueryDslRepository {
    List<UserStudy> findAllWithFetchUserAndStudy();
    Long deleteInBatchByStudyId(Long studyId);
}
