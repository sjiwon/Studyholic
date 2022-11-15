// 스터디 수정 프로세스
function studyEdit(studyId, editor, tagify) {
    let studyName = $('#studyName'); // 스터디 이름
    let studyNameVerificationToken = $('#studyNameVerificationToken');
    if (validateionStudyName(studyName, studyNameVerificationToken) === false) {
        return false;
    }

    let studyBriefDescription = $('#studyBriefDescription'); // 스터디 간단 설명
    let studyBriefDescriptionVerificationToken = $('#studyBriefDescriptionVerificationToken');
    if (validationStudyBriefDescription(studyBriefDescription, studyBriefDescriptionVerificationToken) === false) {
        return false;
    }

    let studyDescription = editor.getHTML(); // 스터디 상세 설명
    let studyMaxMember = $('#studyMaxMember'); // 스터디 모집 회원 수
    if (validationStudyMaxMember(studyMaxMember)) {
        return false;
    }

    let studyRecruitDeadline = $('#studyRecruitDeadline'); // 스터디 모집 마감일

    let list = []; // 스터디 태그 리스트
    let tags = [...tagify.value];
    for (let i = 0; i < tags.length; i++) {
        list.push(tags[i].value);
    }

    let select = confirm('기입하신 정보로 수정하시겠습니까?');
    if (select) {
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
                alert('스터디 수정이 완료되었습니다');
                location.href = '/study/' + Number(studyId);
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else {
        return false;
    }
}

function validateionStudyName(studyName, studyNameToken) {
    if (studyNameToken.val() === 'fail') {
        if (studyName.val().trim() === '') {
            alert('스터디 이름을 다시 확인해주세요');
            studyName.focus();
            return false;
        } else {
            alert('스터디 이름 중복체크를 진행해주세요');
            studyNameToken.focus();
            return false;
        }
    }
}

function validationStudyBriefDescription(studyBriefDescription, studyBriefDescriptionVerificationToken) {
    if (studyBriefDescriptionVerificationToken.val() === 'fail') {
        alert('스터디 간단 설명을 다시 확인해주세요');
        studyBriefDescription.focus();
        return false;
    }
}

function validationStudyMaxMember(maxMember) {
    if (Number(maxMember) < 2 || Number(maxMember) > 10) {
        alert('스터디는 최소 2명/최대 10명까지 모집할 수 있습니다');
        return false;
    }
}