package com.sjiwon.studyholic.domain.entity.studytag.repository.dsl;

import com.sjiwon.studyholic.domain.entity.studytag.StudyTag;

import java.util.List;

public interface StudyTagQueryDslRepository {
    List<StudyTag> findAllWithFetchStudy();
    Long deleteInBatchByStudyId(Long studyId);
    List<String> findTagListByStudyId(Long studyId);
}
