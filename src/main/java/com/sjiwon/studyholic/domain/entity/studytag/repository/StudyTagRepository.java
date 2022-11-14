package com.sjiwon.studyholic.domain.entity.studytag.repository;

import com.sjiwon.studyholic.domain.entity.studytag.StudyTag;
import com.sjiwon.studyholic.domain.entity.studytag.repository.dsl.StudyTagQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTagRepository extends JpaRepository<StudyTag, Long>, StudyTagQueryDslRepository {
}
