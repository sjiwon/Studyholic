package com.sjiwon.studyholic.domain.entity.userstudy.service;

import com.sjiwon.studyholic.domain.entity.study.Study;
import com.sjiwon.studyholic.domain.entity.study.repository.StudyRepository;
import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.UserRepository;
import com.sjiwon.studyholic.domain.entity.userstudy.UserStudy;
import com.sjiwon.studyholic.domain.entity.userstudy.repository.UserStudyRepository;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserStudyService {
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final UserStudyRepository userStudyRepository;

    @Transactional
    public synchronized void participateStudy(Long studyId, Long userId) {
        Study study = studyRepository.findByStudyIdWithFetchUserStudy(studyId)
                .orElseThrow(() -> StudyholicException.type(STUDY_NOT_FOUND));
        canParticiateStudy(study);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> StudyholicException.type(USER_NOT_FOUND));
        isAlreadyParticipate(study, user);

        userStudyRepository.save(UserStudy.addUserInStudy(user, study, Boolean.FALSE));
    }

    private void canParticiateStudy(Study study) {
        if (Objects.equals(study.getUserStudyList().size(), study.getMaxMember())) {
            throw StudyholicException.type(ALREADY_FULL_STUDY);
        }
    }

    private void isAlreadyParticipate(Study study, User user) {
        if (userStudyRepository.existsByStudyAndUser(study, user)) {
            throw StudyholicException.type(ALREADY_PARTICIPATE);
        }
    }
}
