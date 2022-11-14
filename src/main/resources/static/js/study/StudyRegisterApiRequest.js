function studyNameDuplicateCheckApi() {
    let studyName = $('#studyName');
    let studyNameDuplicateCheckButton = $('#studyNameDuplicateCheckButton');
    let studyNameVerificationToken = $('#studyNameVerificationToken');

    if (studyName.val().trim() === '') {
        alert('스터디 이름을 입력해주세요');
        studyName.val('');
        studyName.focus();
        return false;
    }

    let data = {
        'resource': 'name',
        'value': studyName.val()
    }

    axios.post('/api/study/duplicate-check', data)
        .then(() => {
            let use = confirm('[' + studyName.val() + ']는 사용 가능합니다\n사용하시겠습니까?');
            if (use) {
                studyName.css({
                    "border-color": "#0D6EFD",
                    "border": "2px solid",
                    "color": "#0D6EFD",
                    "font-size": "15px"
                });

                studyNameDuplicateCheckButton.attr("disabled", true);
                studyNameVerificationToken.val('success');
            } else {
                return false;
            }
        })
        .catch((error) => {
            let jsonData = error.response.data;
            alert(jsonData['message']);
        });
}