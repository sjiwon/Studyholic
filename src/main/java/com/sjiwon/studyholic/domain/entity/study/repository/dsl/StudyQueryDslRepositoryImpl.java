package com.sjiwon.studyholic.domain.entity.study.repository.dsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjiwon.studyholic.domain.entity.study.repository.dto.BasicStudy;
import com.sjiwon.studyholic.domain.entity.study.repository.dto.QBasicStudy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.sjiwon.studyholic.common.VariableFactory.*;
import static com.sjiwon.studyholic.domain.entity.study.QStudy.study;
import static com.sjiwon.studyholic.domain.entity.studytag.QStudyTag.studyTag;
import static com.sjiwon.studyholic.domain.entity.userstudy.QUserStudy.userStudy;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyQueryDslRepositoryImpl implements StudyQueryDslRepository {
    private final JPAQueryFactory query;

    @Override
    public Optional<BasicStudy> getBasicStudyInformation(Long studyId) {
        return Optional.ofNullable(
                query.select(new QBasicStudy(
                                study.id, study.name, study.briefDescription, study.description, study.maxMember, study.registerDate, study.recruitDeadLine, study.lastModifiedDate, study.userStudyList.size()))
                        .from(study)
                        .leftJoin(study.userStudyList, userStudy)
                        .where(studyIdEq(studyId))
                        .fetchFirst()
        );
    }

    @Override
    public Page<BasicStudy> getMainPageStudyList(Pageable pageRequest, String sort) {
        JPAQuery<BasicStudy> beforeOrderByQuery = query
                .select(new QBasicStudy(
                        study.id, study.name, study.briefDescription, study.description, study.maxMember, study.registerDate, study.recruitDeadLine, study.lastModifiedDate, study.userStudyList.size()))
                .from(study)
                .leftJoin(study.userStudyList, userStudy)
                .groupBy(study.id, study.name, study.briefDescription, study.description, study.maxMember, study.registerDate, study.recruitDeadLine)
                .offset((long) pageRequest.getPageNumber() * pageRequest.getPageSize())
                .limit(pageRequest.getPageSize());

        List<BasicStudy> content = switch (sort) {
            case REGISTER_DATE_SORT ->  // 등록 날짜
                    beforeOrderByQuery
                            .orderBy(study.registerDate.desc(), study.userStudyList.size().desc())
                            .fetch();
            case POPULARITY_SORT ->  // 참여 인원
                    beforeOrderByQuery
                            .orderBy(study.userStudyList.size().desc(), study.recruitDeadLine.asc())
                            .fetch();
            case RECRUIT_DEADLINE_SORT ->  // 모집 마감일
                    beforeOrderByQuery
                            .orderBy(study.recruitDeadLine.asc(), study.userStudyList.size().desc())
                            .fetch();
            default ->  // 모집 정원
                    beforeOrderByQuery
                            .orderBy(study.maxMember.desc(), study.userStudyList.size().desc())
                            .fetch();
        };

        List<Long> countQuery = query
                .select(study.id)
                .from(study)
                .fetch();

        return PageableExecutionUtils.getPage(content, pageRequest, countQuery::size);
    }

    @Override
    public Page<BasicStudy> getMainPageStudyListWithKeyword(Pageable pageRequest, String sort, String keyword) {
        JPAQuery<BasicStudy> beforeOrderByQuery = query
                .select(new QBasicStudy(
                        study.id, study.name, study.briefDescription, study.description, study.maxMember, study.registerDate, study.recruitDeadLine, study.lastModifiedDate, study.userStudyList.size()))
                .from(study)
                .leftJoin(study.studyTagList, studyTag)
                .leftJoin(study.userStudyList, userStudy)
                .where(keywordContains(keyword))
                .groupBy(study.id, study.name, study.briefDescription, study.description, study.maxMember, study.registerDate, study.recruitDeadLine)
                .offset((long) pageRequest.getPageNumber() * pageRequest.getPageSize())
                .limit(pageRequest.getPageSize());

        List<BasicStudy> content = switch (sort) {
            case REGISTER_DATE_SORT ->  // 등록 날짜
                    beforeOrderByQuery
                            .orderBy(study.registerDate.desc())
                            .fetch();
            case POPULARITY_SORT ->  // 참여 인원
                    beforeOrderByQuery
                            .orderBy(study.userStudyList.size().desc())
                            .fetch();
            case RECRUIT_DEADLINE_SORT ->  // 모집 마감일
                    beforeOrderByQuery
                            .orderBy(study.recruitDeadLine.asc(), study.userStudyList.size().desc())
                            .fetch();
            default ->  // 모집 정원
                    beforeOrderByQuery
                            .orderBy(study.maxMember.desc(), study.userStudyList.size().desc())
                            .fetch();
        };

        List<Long> countQuery = query
                .select(study.id)
                .from(study)
                .fetch();

        return PageableExecutionUtils.getPage(content, pageRequest, countQuery::size);
    }

    @Override
    @Transactional
    public Long deleteByStudyId(Long studyId) {
        return query
                .delete(study)
                .where(studyIdEq(studyId))
                .execute();
    }

    private BooleanExpression keywordContains(String keyword) {
        if (Objects.isNull(keyword)) {
            return null;
        }

        return studyTag.tag.contains(keyword);
    }

    private BooleanExpression studyIdEq(Long studyId) {
        if (Objects.isNull(studyId)) {
            return null;
        }

        return study.id.eq(studyId);
    }
}
