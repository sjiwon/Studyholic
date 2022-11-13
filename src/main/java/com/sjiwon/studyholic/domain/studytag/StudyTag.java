package com.sjiwon.studyholic.domain.studytag;

import com.sjiwon.studyholic.domain.study.Study;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "study_tag")
public class StudyTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag", nullable = false, length = 100)
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", referencedColumnName = "id")
    private Study study;

    private StudyTag(Study study, String tag) {
        this.study = study;
        this.tag = tag;

        study.getStudyTagList().add(this);
    }

    public static StudyTag addTagInStudy(Study study, String tag) {
        return new StudyTag(study,  tag);
    }
}
