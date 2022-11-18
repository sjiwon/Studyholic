package com.sjiwon.studyholic.domain.entity.userstudy.repository.dsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.dto.ParticipateUser;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.dto.QParticipateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.sjiwon.studyholic.domain.entity.study.QStudy.study;
import static com.sjiwon.studyholic.domain.entity.user.QUser.user;
import static com.sjiwon.studyholic.domain.entity.userstudy.QUserStudy.userStudy;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserStudyQueryDslRepositoryImpl implements UserStudyQueryDslRepository {
    private final JPAQueryFactory query;

    @Override
    public List<UserStudy> findAllWithFetchUserAndStudy() {
        return query
                .select(userStudy)
                .from(userStudy)
                .innerJoin(userStudy.user).fetchJoin()
                .innerJoin(userStudy.study).fetchJoin()
                .fetch();
    }

    @Override
    @Transactional
    public Long deleteInBatchByStudyId(Long studyId) {
        return query
                .delete(userStudy)
                .where(studyIdEq(studyId))
                .execute();
    }

    @Override
    public Long findStudyLeaderIdByStudyId(Long studyId) {
        return query.select(user.id)
                .from(userStudy)
                .innerJoin(userStudy.user, user)
                .innerJoin(userStudy.study, study)
                .where(studyIdEq(studyId))
                .fetchFirst();
    }

    @Override
    public List<ParticipateUser> findParticipateUserListByStudyId(Long studyId) {
        return query.select(new QParticipateUser(user.id, user.name, user.nickName, user.email, user.storageName, userStudy.teamLeader))
                .from(userStudy)
                .innerJoin(userStudy.user, user)
                .innerJoin(userStudy.study, study)
                .where(studyIdEq(studyId))
                .fetch();
    }

    private BooleanExpression studyIdEq(Long studyId) {
        return Objects.isNull(studyId)
                ? null
                : study.id.eq(studyId);
    }
}
