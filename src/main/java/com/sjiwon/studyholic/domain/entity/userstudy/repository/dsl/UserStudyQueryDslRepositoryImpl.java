package com.sjiwon.studyholic.domain.entity.userstudy.repository.dsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.sjiwon.studyholic.domain.entity.study.QStudy.study;
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

    private BooleanExpression studyIdEq(Long studyId) {
        if (Objects.isNull(studyId)) {
            return null;
        }

        return study.id.eq(studyId);
    }
}
