function studyNameDuplicateCheckApi() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let studyName = $('#studyName');
    let studyNameDuplicateCheckButton = $('#studyNameDuplicateCheckButton');
    let studyNameVerificationToken = $('#studyNameVerificationToken');

    if (studyName.val().trim() === '') {
        ToastResponse.fire({
            html: '<b>스터디 이름을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            studyName.focus();
        })
        return false;
    }

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    ToastApi.fire({
        html: '해당 스터디명을 사용하시겠습니까?<br><b style="font-size: 20px;">-> ' + studyName.val() + '</b>',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'studyId': null,
                'name': studyName.val()
            }

            axios.post('/api/study/register/duplicate-check', data)
                .then(() => {
                    ToastResponse.fire({
                        html: '<b>[' + studyName.val() + ']</b>은/는 사용 가능합니다',
                        icon: 'success'
                    }).then(() => {
                        studyNameDuplicateCheckButton.attr("disabled", true);
                        studyNameVerificationToken.val('success');
                    })
                })
                .catch((error) => {
                    let jsonData = error.response.data;
                    ToastResponse.fire({
                        color: '#FF0000',
                        text: jsonData['message'],
                        icon: 'error'
                    }).then(() => {
                        studyNameVerificationToken.val('fail');
                        studyName.focus();
                    })
                });
        }
    });
}