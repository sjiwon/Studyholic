package com.sjiwon.studyholic.domain.study.repository;

import com.sjiwon.studyholic.domain.study.Study;
import com.sjiwon.studyholic.domain.study.repository.dsl.StudyQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long>, StudyQueryDslRepository {
}
