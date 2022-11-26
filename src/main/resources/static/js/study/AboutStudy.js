// 1. 특정 스터디로 페이지 이동
function moveToStudyDetailPage(studyRandomSequence) {
    location.href = '/study/' + String(studyRandomSequence);
}

// 2. 스터디 참여
function participate(studyName, studyId, userId) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('<b>' + studyName + '</b>에 참여하시겠습니까?')
        : ('Would you like to join <b>' + studyName + '?</b>');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'studyId': studyId,
                'userId': userId
            };

            axios.post('/api/user/join-study', data)
                .then(() => {
                    let successHtml = (navigator.language === 'ko')
                        ? ('<b>' + studyName + '</b> 참여가 완료되었습니다')
                        : ('<b>' + studyName + '</b> Engagement is complete');

                    ToastResponse.fire({
                        html: successHtml,
                        icon: 'success'
                    }).then(() => {
                        location.href = '/mypage/study';
                    })
                })
                .catch((error) => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '스터디 정보가 존재하지 않습니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('Study information does not exist');
                    } else if (jsonData['message'] === '모집 완료된 스터디입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('This is a completed study');
                    } else if (jsonData['message'] === '사용자 정보가 존재하지 않습니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('User information does not exist');
                    } else if (jsonData['message'] === '이미 참여하고 있는 스터디입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('This is a study you are already participating in.');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    });
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

    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('<b>' + studyName + '</b> 참여를 취소하시겠습니까?')
        : ('Are you sure you want to cancel your participation in the <b>' + studyName + '?</b>');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'studyId': studyId,
                'userId': userId
            };

            axios.post('/api/user/cancel-study', data)
                .then(() => {
                    let successHtml = (navigator.language === 'ko')
                        ? ('<b>' + studyName + '</b> 참여 취소가 완료되었습니다')
                        : ('<b>' + studyName + '</b> participation cancellation has been completed');

                    ToastResponse.fire({
                        html: successHtml,
                        icon: 'success'
                    }).then(() => {
                        location.href = '/mypage/study';
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

    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('<b>' + studyName + '</b>를 삭제하시겠습니까?')
        : ('Are you sure you want to delete <b>' + studyName + '?</b>');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            axios.delete('/api/study/' + studyId)
                .then(() => {
                    let successHtml = (navigator.language === 'ko')
                        ? ('<b>' + studyName + '</b> 삭제가 완료되었습니다')
                        : ('Deletion of <b>' + studyName + '</b> is complete');

                    ToastResponse.fire({
                        html: successHtml,
                        icon: 'success'
                    }).then(() => {
                        location.href = '/';
                    })
                })
                .catch((error) => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '로그인이 필요합니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('Login is required');
                    } else if (jsonData['message'] === '스터디 삭제는 스터디 리더만 가능합니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('Study deletion can only be done by the study leader');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    });
                });
        }
    });
}

// 5-1. 스터디 수정 페이지로 이동
function moveToStudyEditPage(studyRandomSequence) {
    location.href = '/study/' + String(studyRandomSequence) + '/edit';
}