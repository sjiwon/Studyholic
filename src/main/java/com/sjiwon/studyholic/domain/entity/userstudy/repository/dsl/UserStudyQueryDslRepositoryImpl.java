package com.sjiwon.studyholic.domain.entity.userstudy.repository.dsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
