// 1. 비밀빈호 리셋 이메일 인증 버튼 활성화
function enableEmailVerificationInResetPasswordProcess() {
    let name = $('#name');
    let loginId = $('#loginId');
    let email = $('#email');
    let emailVerificationButton = $('#emailVerificationButton');

    if (name.val().trim() === '') {
        alert('이름을 먼저 입력해주세요');
        name.val('');
        name.focus();
        return false;
    }

    if (loginId.val().trim() === '') {
        alert('아이디를 먼저 입력해주세요');
        loginId.val('');
        loginId.focus();
        return false;
    }

    if (email.val().trim() === '') {
        alert('이메일을 입력해주세요');
        email.val('');
        email.focus();
        return false;
    }

    let use = confirm('[' + email.val() + ']이 본인 소유의 이메일이 맞습니까?');
    if (use) {
        let data = {
            'resource': 'password',
            'email': email.val()
        };

        axios.post('/api/email/authenticate', data)
            .then(response => {
                $('#checkEmail').prop('disabled', false);
                $('#explainEmailCheck').show();

                email.attr("readonly", true);
                email.css({
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
    let checkEmail = $('#checkEmail');
    let explainEmailCheck = $('#explainEmailCheck');

    checkEmail.on('keyup', () => {
        let emailVerificationToken = $('#emailVerificationToken');
        let ResetPasswordButton = $('#ResetPasswordButton');

        if (checkEmail.val() !== emailCode) {
            checkEmail.css({
                "border-color": "#FA3E3E",
                "border": "2px solid",
                "color": "#FA3E3E",
                "font-size": "15px"
            })

            explainEmailCheck.html('인증번호가 일치하지 않습니다');
            explainEmailCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })

            emailVerificationToken.val('fail');
            ResetPasswordButton.attr("disabled", true);
        } else {
            checkEmail.css({
                "border-color": "#0D6EFD",
                "border": "2px solid",
                "color": "#0D6EFD",
                "font-size": "15px"
            })

            explainEmailCheck.html('인증번호가 일치합니다');
            explainEmailCheck.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })

            emailVerificationToken.val('success');
            ResetPasswordButton.attr("disabled", false);
        }
    })
}