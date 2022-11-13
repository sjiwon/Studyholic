package com.sjiwon.studyholic.domain.study;

import com.sjiwon.studyholic.domain.studytag.StudyTag;
import com.sjiwon.studyholic.domain.userstudy.UserStudy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "study")
@EntityListeners(AuditingEntityListener.class)
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 80)
    private String name;

    @Column(name = "max_member", nullable = false)
    private Integer maxMember;

    @Column(name = "brief_description", nullable = false, length = 200)
    private String briefDescription;

    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "recruit_deadline", nullable = false)
    private LocalDate recruitDeadLine;

    @CreatedDate
    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "study") // QueryDSL
    private Set<UserStudy> userStudyList = new HashSet<>();

    @OneToMany(mappedBy = "study", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true) // QueryDSL
    private Set<StudyTag> studyTagList = new HashSet<>();

    private Study(String name, Integer maxMember, String briefDescription, String description, LocalDate recruitDeadLine) {
        this.name = name;
        this.maxMember = maxMember;
        this.briefDescription = briefDescription;
        this.description = description;
        this.recruitDeadLine = recruitDeadLine;
    }

    public static Study createStudy(String name, Integer maxMember, String briefDescription, String description, LocalDate recruitDeadLine) {
        return new Study(name, maxMember, briefDescription, description, recruitDeadLine);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeRecruitDeadLine(LocalDate recruitDeadLine) {
        this.recruitDeadLine = recruitDeadLine;
    }

    public void changeMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }
}
