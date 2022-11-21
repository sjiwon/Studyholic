function studyRegister(userId, editor, tagify) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let studyName = $('#studyName'); // 스터디 이름
    let studyNameVerificationToken = $('#studyNameVerificationToken');
    if (validateionStudyName(studyName, studyNameVerificationToken) === 'fail1') {
        let nameValidationFailHtml1 = (navigator.language === 'ko')
            ? ('<b>스터디 이름을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check the study name again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: nameValidationFailHtml1,
            icon: 'warning'
        }).then(() => {
            studyName.focus();
        })
        return false;
    } else if (validateionStudyName(studyName, studyNameVerificationToken) === 'fail2') {
        let nameValidationFailHtml2 = (navigator.language === 'ko')
            ? ('<b>스터디명 중복 체크를 진행해주세요</b>')
            : ('<b>Please proceed with the duplicate study name check</b>');

        ToastResponse.fire({
            html: nameValidationFailHtml2,
            icon: 'warning'
        })
        return false;
    }

    let studyBriefDescription = $('#studyBriefDescription'); // 스터디 간단 설명
    if (validationStudyBriefDescription(studyBriefDescription) === false) {
        let briefDescriptionValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>스터디 간단 설명을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check the study brief description again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: briefDescriptionValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            studyBriefDescription.focus();
        })
        return false;
    }

    let studyDescription = editor.getHTML(); // 스터디 상세 설명
    let studyMaxMember = $('#studyMaxMember'); // 스터디 모집 회원 수
    if (validationStudyMaxMember(studyMaxMember)) {
        let maxMemberValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>스터디 최대 인원수를 다시 확인해주세요</b><br><small>- 최소 2명, 최대 10명까지 가능합니다</small>')
            : ('<b>Please double check the maximum number of study participants.</b><br><small>- Minimum of 2 people, maximum of 10 people</small>');

        ToastResponse.fire({
            html: maxMemberValidationFailHtml,
            icon: 'warning'
        })
        return false;
    }

    let studyRecruitDeadline = $('#studyRecruitDeadline'); // 스터디 모집 마감일

    let list = []; // 스터디 태그 리스트
    let tags = [...tagify.value];
    for (let i = 0; i < tags.length; i++) {
        list.push(tags[i].value);
    }

    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('<b>' + studyName.val() + '</b>을 등록하시겠습니까?')
        : ('Would you like to register for the <b>' + studyName.val() + '</b>');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'userId': userId,
                'name': studyName.val(),
                'maxMember': studyMaxMember.val(),
                'briefDescription': studyBriefDescription.val(),
                'description': studyDescription,
                'recruitDeadline': studyRecruitDeadline.val(),
                'tagList': list
            };

            axios.post('/api/study', data)
                .then(() => {
                    let successText = (navigator.language === 'ko')
                        ? ('스터디 등록이 완료되었습니다')
                        : ('Study registration is complete');

                    ToastResponse.fire({
                        text: successText,
                        icon: 'success'
                    }).then(() => {
                        location.href = '/user/' + userId + '/study';
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '사용자 정보가 존재하지 않습니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('User information does not exist');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    })
                });
        }
    });
}

function validateionStudyName(studyName, studyNameToken) {
    if (studyName.val().trim() == '') {
        return "fail1";
    } else if (studyNameToken.val() === 'fail') {
        return "fail2";
    }
}

function validationStudyBriefDescription(studyBriefDescription) {
    if (studyBriefDescription.val().trim() === '') {
        return false;
    }
}

function validationStudyMaxMember(maxMember) {
    if (Number(maxMember) < 2 || Number(maxMember) > 10) {
        return false;
    }
}