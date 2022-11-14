package com.sjiwon.studyholic.domain.entity.user.repository.dsl;

import com.sjiwon.studyholic.domain.entity.user.repository.dto.BasicUser;

import java.util.Optional;

public interface UserQueryDslRepository {
    Optional<BasicUser> getBasicUserInformation(Long userId);
}
