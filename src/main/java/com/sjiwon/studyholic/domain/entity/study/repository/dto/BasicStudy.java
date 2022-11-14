package com.sjiwon.studyholic.domain.entity.study.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class BasicStudy {
    private final Long id;
    private final String name;
    private final String briefDescription;
    private final String description;
    private final Integer maxMember;
    private final LocalDateTime registerDate;
    private final LocalDate recruitDeadLine;
    private final LocalDateTime lastModifiedDate;
    private final Integer currentMemberCount; // Sub Query

    @QueryProjection
    public BasicStudy(Long id, String name, String briefDescription, String description, Integer maxMember, LocalDateTime registerDate, LocalDate recruitDeadLine, LocalDateTime lastModifiedDate, Integer currentMemberCount) {
        this.id = id;
        this.name = name;
        this.briefDescription = briefDescription;
        this.description = description;
        this.maxMember = maxMember;
        this.currentMemberCount = currentMemberCount;
        this.registerDate = registerDate;
        this.recruitDeadLine = recruitDeadLine;
        this.lastModifiedDate = lastModifiedDate;
    }
}
