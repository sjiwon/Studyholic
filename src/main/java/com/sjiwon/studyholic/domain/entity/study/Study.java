package com.sjiwon.studyholic.domain.entity.study;

import com.sjiwon.studyholic.domain.entity.studytag.StudyTag;
import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private LocalDateTime recruitDeadLine;

    @Column(name = "random_sequence", nullable = false, unique = true)
    private String randomSequence;

    @CreatedDate
    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "study") // QueryDSL
    private List<UserStudy> userStudyList = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true) // QueryDSL
    private List<StudyTag> studyTagList = new ArrayList<>();

    private Study(String name, Integer maxMember, String briefDescription, String description, LocalDateTime recruitDeadLine) {
        this.name = name;
        this.maxMember = maxMember;
        this.briefDescription = briefDescription;
        this.description = description;
        this.recruitDeadLine = recruitDeadLine;
        this.randomSequence = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Study createStudy(String name, Integer maxMember, String briefDescription, String description, LocalDateTime recruitDeadLine) {
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

    public void changeRecruitDeadLine(LocalDateTime recruitDeadLine) {
        this.recruitDeadLine = recruitDeadLine;
    }

    public void changeMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }
}
