package com.sjiwon.studyholic.domain.entity.study.repository.dsl;

import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyQueryDslRepository {
    Page<BasicStudy> getMainPageStudyList(Pageable pageRequest, String sort);
    Page<BasicStudy> getMainPageStudyListWithKeyword(Pageable pageRequest, String sort, String keyword);
    Long deleteByStudyId(Long studyId);
}
