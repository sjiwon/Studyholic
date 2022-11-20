// 1. 특정 스터디로 페이지 이동
function moveToStudyDetailPage(studyId) {
    location.href = '/study/' + Number(studyId);
}

// 2. 스터디 참여
function participate(studyName, studyId, userId) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    ToastApi.fire({
        html: '<b>[' + studyName + ']</b>에 참여하시겠습니까?',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'studyId': studyId,
                'userId': userId
            };

            axios.post('/api/user/join-study', data)
                .then(() => {
                    ToastResponse.fire({
                        html: '<b>[' + studyName + ']</b> 참여가 완료되었습니다',
                        icon: 'success'
                    }).then(() => {
                        location.href = '/user/' + userId + '/study';
                    })
                })
                .catch((error) => {
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

// 3. 스터디 참여 취소
function participateCancle(studyName, studyId, userId) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    ToastApi.fire({
        html: '<b>[' + studyName + ']</b> 참여를 취소하시겠습니까?',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'studyId': studyId,
                'userId': userId
            };

            axios.post('/api/user/cancel-study', data)
                .then(() => {
                    ToastResponse.fire({
                        html: '<b>[' + studyName + ']</b> 참여 취소가 완료되었습니다',
                        icon: 'success'
                    }).then(() => {
                        location.href = '/user/' + userId + '/study';
                    })
                })
                .catch((error) => {
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

// 4. 스터디 삭제
function deleteStudy(studyName, studyId) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    ToastApi.fire({
        html: '<b>[' + studyName + ']</b>를 삭제하시겠습니까?',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            axios.delete('/api/study/' + studyId)
                .then(() => {
                    ToastResponse.fire({
                        html: '<b>[' + studyName + ']</b> 삭제가 완료되었습니다',
                        icon: 'success'
                    }).then(() => {
                        location.href = '/';
                    })
                })
                .catch((error) => {
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

// 5-1. 스터디 수정 페이지로 이동
function moveToStudyEditPage(studyId) {
    location.href = '/study/' + Number(studyId) + '/edit';
}