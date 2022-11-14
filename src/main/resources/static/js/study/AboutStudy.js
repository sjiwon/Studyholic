// 1. 특정 스터디로 페이지 이동
function moveToStudyDetailPage(studyId) {
    location.href = '/study/' + Number(studyId);
}

// 2. 스터디 참여
function participate(studyName, studyId, userId) {
    let select = confirm('[' + studyName + ']에 참여하시겠습니까?');
    if (select) {
        let data = {
            'studyId': studyId,
            'userId': userId
        }

        axios.post('/api/user/join-study', data)
            .then(() => {
                alert('[' + studyName + '] 참여가 완료되었습니다');
                location.href = '/user/' + userId + '/study';
            })
            .catch((error) => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else {
        return false;
    }
}

// 3. 스터디 참여 취소
function participateCancle(studyName, studyId, userId) {
    let select = confirm('[' + studyName + '] 참여를 취소하시겠습니까?');
    if (select) {
        let data = {
            'studyId': studyId,
            'userId': userId
        }

        axios.post('/api/user/cancel-study', data)
            .then(() => {
                alert('[' + studyName + '] 참여 취소가 완료되었습니다');
                location.href = '/user/' + userId + '/study';
            })
            .catch((error) => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else {
        return false;
    }
}

// 4. 스터디 삭제
function deleteStudy(studyName, studyId) {
    let select = confirm('[' + studyName + ']를 삭제하시겠습니까?');
    if (select) {
        axios.delete('/api/study/' + studyId)
            .then(() => {
                alert('[' + studyName + '] 삭제가 완료되었습니다');
                location.href = '/';
            })
            .catch((error) => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else {
        return false;
    }
}

// 5-1. 스터디 수정 페이지로 이동
function moveToStudyEditPage(studyId) {
    location.href = '/study/edit/' + Number(studyId);
}