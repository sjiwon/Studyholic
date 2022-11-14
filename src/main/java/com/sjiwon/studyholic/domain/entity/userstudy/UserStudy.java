package com.sjiwon.studyholic.domain.entity.userstudy;

import com.sjiwon.studyholic.domain.entity.study.Study;
import com.sjiwon.studyholic.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_study")
public class UserStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_team_leader")
    private boolean teamLeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", referencedColumnName = "id")
    private Study study;

    private UserStudy(User user, Study study, boolean teamLeader) {
        this.user = user;
        this.study = study;
        this.teamLeader = teamLeader;

        study.getUserStudyList().add(this);
        user.getUserStudyList().add(this);
    }

    public static UserStudy addUserInStudy(User user, Study study, boolean teamLeader) {
        return new UserStudy(user, study, teamLeader);
    }
}
