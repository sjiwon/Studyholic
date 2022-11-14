package com.sjiwon.studyholic.domain.entity.studytag.repository.dsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjiwon.studyholic.domain.entity.studytag.StudyTag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sjiwon.studyholic.domain.entity.study.QStudy.study;
import static com.sjiwon.studyholic.domain.entity.studytag.QStudyTag.studyTag;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyTagQueryDslRepositoryImpl implements StudyTagQueryDslRepository {
    private final JPAQueryFactory query;

    @Override
    public List<StudyTag> findAllWithFetchStudy() {
        return query
                .select(studyTag)
                .from(studyTag)
                .innerJoin(studyTag.study, study).fetchJoin()
                .orderBy(studyTag.tag.asc())
                .fetch();
    }
}
