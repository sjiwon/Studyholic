package com.sjiwon.studyholic.domain.entity.userstudy.repository.dsl;

import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.dto.ParticipateUser;

import java.util.List;

public interface UserStudyQueryDslRepository {
    List<UserStudy> findByStudyIdWithFetchUserAndStudy(Long studyId);
    List<UserStudy> findAllWithFetchUserAndStudy();
    Long deleteInBatchByStudyId(Long studyId);
    Long findStudyLeaderIdByStudyRandomSequence(String randomSequence);
    List<ParticipateUser> findParticipateUserListByStudyRandomSequence(String randomSequence);
}
