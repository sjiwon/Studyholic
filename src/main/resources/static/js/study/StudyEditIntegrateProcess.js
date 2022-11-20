// 스터디 수정 프로세스
function studyEdit(studyId, editor, tagify) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let studyName = $('#studyName'); // 스터디 이름
    let studyNameVerificationToken = $('#studyNameVerificationToken');
    if (validateionStudyName(studyName, studyNameVerificationToken) === 'fail1') {
        ToastResponse.fire({
            html: '<b>스터디명을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            studyName.focus();
        })
        return false;
    } else if (validateionStudyName(studyName, studyNameVerificationToken) === 'fail2') {
        ToastResponse.fire({
            html: '<b>스터디명 중복 체크를 진행해주세요</b>',
            icon: 'warning'
        })
        return false;
    }

    let studyBriefDescription = $('#studyBriefDescription'); // 스터디 간단 설명
    if (validationStudyBriefDescription(studyBriefDescription) === false) {
        ToastResponse.fire({
            html: '<b>스터디 간단 설명을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            studyBriefDescription.focus();
        })
        return false;
    }

    let studyDescription = editor.getHTML(); // 스터디 상세 설명
    let studyMaxMember = $('#studyMaxMember'); // 스터디 모집 회원 수
    if (validationStudyMaxMember(studyMaxMember)) {
        ToastResponse.fire({
            html: '<b>스터디 최대 인원수를 다시 확인해주세요</b><br><small>- 최소 2명, 최대 10명까지 가능합니다</small>',
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

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    ToastApi.fire({
        text: '기입하신 정보로 스터디를 수정하시겠습니까?',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'name': studyName.val(),
                'briefDescription': studyBriefDescription.val(),
                'description': studyDescription,
                'recruitDeadline': studyRecruitDeadline.val(),
                'maxMember': studyMaxMember.val(),
                'tagList': list
            };

            axios.patch('/api/study/' + Number(studyId), data)
                .then(() => {
                    ToastResponse.fire({
                        text: '스터디 수정이 완료되었습니다',
                        icon: 'success'
                    }).then(() => {
                        location.href = '/study/' + Number(studyId);
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;
                    ToastResponse.fire({
                        color: '#FF0000',
                        text: jsonData['message'],
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