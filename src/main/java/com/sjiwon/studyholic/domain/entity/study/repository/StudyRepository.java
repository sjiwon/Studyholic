package com.sjiwon.studyholic.domain.entity.study.repository;

import com.sjiwon.studyholic.domain.entity.study.Study;
import com.sjiwon.studyholic.domain.entity.study.repository.dsl.StudyQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long>, StudyQueryDslRepository {
    boolean existsByIdNotAndName(Long studyId, String name);
    boolean existsByName(String name);
}
