// 1. 이메일 인증 버튼 활성화
function enableEmailVerification(resource) {
    let email = $('#email');
    let emailVerificationButton = $('#emailVerificationButton');

    if (email.val().trim() === '') {
        alert('이메일을 입력해주세요');
        email.val('');
        email.focus();
        return false;
    }

    let use = confirm('[' + email.val() + ']이 본인 소유의 이메일이 맞습니까?');
    if (use) {
        let data = {
            'resource': resource,
            'email': email.val()
        }

        axios.post('/api/email/authenticate', data)
            .then(response => {
                $('#checkEmail').prop('disabled', false);
                $('#explainEmailCheck').show();

                inputEmail.attr("readonly",true);
                inputEmail.css({
                    "border-color": "#0D6EFD",
                    "border": "2px solid",
                    "color": "#0D6EFD",
                    "font-size": "15px"
                })

                const emailCode = response['data'];
                emailVerificationButton.attr("disabled", true);
                checkEmailConfirm(emailCode);
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    }
}

// 2. 이메일 인증 프로세스
function checkEmailConfirm(emailCode) {
    $('#checkEmail').on('keyup', () => {
        let inputEmail = $('#checkEmail');
        let emailVerificationToken = $('#emailVerificationToken');
        let findPasswordButton = $('#findPasswordButton');

        if (inputEmail.val() !== emailCode) {
            inputEmail.css({
                "border-color": "#FA3E3E",
                "border": "2px solid",
                "color": "#FA3E3E",
                "font-size": "15px"
            })

            let explainEmailCheck = $('#explainEmailCheck');
            explainEmailCheck.html('인증번호가 일치하지 않습니다');
            explainEmailCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })

            emailVerificationToken.val('fail');
            findPasswordButton.attr("disabled", true);
        } else {
            inputEmail.css({
                "border-color": "#0D6EFD",
                "border": "2px solid",
                "color": "#0D6EFD",
                "font-size": "15px"
            })

            let explainEmailCheck = $('#explainEmailCheck');
            explainEmailCheck.html('인증번호가 일치합니다');
            explainEmailCheck.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })

            emailVerificationToken.val('success');
            findPasswordButton.attr("disabled", false);
        }
    })
}