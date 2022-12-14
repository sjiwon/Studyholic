package com.sjiwon.studyholic.domain.entity.study.repository.dsl;

import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudyQueryDslRepository {
    Optional<BasicStudy> getBasicStudyInformation(String randomSequence);
    Page<BasicStudy> getMainPageStudyList(Pageable pageRequest, String sort);
    Page<BasicStudy> getMainPageStudyListWithKeyword(Pageable pageRequest, String sort, String keyword);
    List<BasicStudy> getUserParticipateStudyInformation(Long userId);
    Long deleteByStudyId(Long studyId);
}
