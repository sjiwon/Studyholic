// 1. 스터디 이름 중복 체크 API
function studyNameDuplicateCheckInEdit(studyId) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let studyName = $('#studyName');
    let studyNameDuplicateCheckButton = $('#studyNameDuplicateCheckButton');
    let studyNameVerificationToken = $('#studyNameVerificationToken');

    if (studyName.val().trim() === '') {
        let nameValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>스터디 이름을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check the study name again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: nameValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            studyName.focus();
        })
        return false;
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
        ? ('해당 스터디명을 사용하시겠습니까?<br><b style="font-size: 20px;">-> ' + studyName.val() + '</b>')
        : ('Would you like to use that study name?<br><b style="font-size: 20px;">-> ' + studyName.val() + '</b>');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'studyId': studyId,
                'name': studyName.val()
            }

            axios.post('/api/study/edit/duplicate-check', data)
                .then(() => {
                    let successHtml = (navigator.language === 'ko')
                        ? ('<b>' + studyName.val() + '</b>은/는 사용 가능합니다')
                        : ('<b>' + studyName.val() + '</b> is available');

                    ToastResponse.fire({
                        html: successHtml,
                        icon: 'success'
                    }).then(() => {
                        studyNameDuplicateCheckButton.attr("disabled", true);
                        studyNameVerificationToken.val('success');
                    })
                })
                .catch((error) => {
                    let jsonData = error.response.data;

                    let errorText = (navigator.language === 'ko')
                        ? (jsonData['message'])
                        : ('Duplicate study name');

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    }).then(() => {
                        studyNameVerificationToken.val('fail');
                        studyName.focus();
                    })
                });
        }
    });
}