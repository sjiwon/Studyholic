package com.sjiwon.studyholic.domain.entity.user.repository.dsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.dto.BasicUser;
import com.sjiwon.studyholic.domain.entity.user.repository.dto.QBasicUser;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

import static com.sjiwon.studyholic.domain.entity.user.QUser.user;
import static com.sjiwon.studyholic.domain.entity.userstudy.QUserStudy.userStudy;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryDslRepositoryImpl implements UserQueryDslRepository {
    private final JPAQueryFactory query;

    @Override
    public Optional<User> findAllByIdWithFetchUserStudy(Long userId) {
        return Optional.ofNullable(
                query.selectDistinct(user)
                        .from(user)
                        .leftJoin(user.userStudyList, userStudy)
                        .where(userIdEq(userId))
                        .fetchFirst()
        );
    }

    @Override
    public Optional<BasicUser> getBasicUserInformation(Long userId) {
        return Optional.ofNullable(
                query.select(new QBasicUser(user.id, user.name, user.nickName, user.loginId, user.loginPassword, user.email, user.birth, user.joinDate, user.storageName, user.lastModifiedDate))
                        .from(user)
                        .where(userIdEq(userId))
                        .fetchFirst()
        );
    }

    private BooleanExpression userIdEq(Long userId) {
        return Objects.isNull(userId)
                ? null
                : user.id.eq(userId);
    }
}
