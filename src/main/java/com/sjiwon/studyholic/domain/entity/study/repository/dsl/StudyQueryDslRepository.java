package com.sjiwon.studyholic.domain.entity.study.repository.dsl;

import com.sjiwon.studyholic.domain.entity.study.Study;
import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudyQueryDslRepository {
    Optional<Study> findByStudyIdWithFetchStudyTag(Long studyId);
    Optional<Study> findByStudyIdWithFetchUserStudy(Long studyId);
    Optional<BasicStudy> getBasicStudyInformation(Long studyId);
    Page<BasicStudy> getMainPageStudyList(Pageable pageRequest, String sort);
    Page<BasicStudy> getMainPageStudyListWithKeyword(Pageable pageRequest, String sort, String keyword);
    List<BasicStudy> getUserParticipateStudyInformation(Long userId);
    Long deleteByStudyId(Long studyId);
}
