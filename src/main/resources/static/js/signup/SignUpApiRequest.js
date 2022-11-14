// 1. 닉네임 중복 체크 API
function nicknameDuplicateCheckApi() {
    let nickname = $('#nickname');
    let nicknameVerificationButton = $('#nicknameVerificationButton');
    let nicknameVerificationToken = $('#nicknameVerificationToken');

    if (nickname.val().trim() === '') {
        alert('닉네임을 입력해주세요');
        nickname.val('');
        nickname.focus();
        return false;
    }

    let data = {
        'resource': 'nickname',
        'value': nickname.val()
    }

    axios.post('/api/user/duplicate-check', data)
        .then(() => {
            let use = confirm('['+ nickname.val() + ']은 사용 가능합니다\n사용하시겠습니까?');
            if (use) {
                nickname.css({
                    "border-color": "#0D6EFD",
                    "border": "2px solid",
                    "color": "#0D6EFD",
                    "font-size": "15px"
                });

                nicknameVerificationButton.attr("disabled", true);
                nicknameVerificationToken.val('success');
            } else {
                return false;
            }
        })
        .catch(error => {
            let jsonData = error.response.data;
            alert(jsonData['message']);
        });
}

// 2. 로그인 아이디 중복 체크 API
function idDuplicateCheckApi() {
    let loginId = $('#loginId');
    let idVerificationButton = $('#idVerificationButton');
    let idVerificationToken = $('#idVerificationToken');

    if (loginId.val().trim() === '') {
        alert('아이디를 입력해주세요');
        loginId.val('');
        loginId.focus();
        return false;
    }

    let data = {
        'resource': 'id',
        'value': loginId.val()
    }

    axios.post('/api/user/duplicate-check', data)
        .then(() => {
            let use = confirm('[' + inputLoginId.val() + ']은 사용 가능합니다\n사용하시겠습니까?');
            if (use) {
                inputLoginId.css({
                    "border-color": "#0D6EFD",
                    "border": "2px solid",
                    "color": "#0D6EFD",
                    "font-size": "15px"
                });

                idVerificationButton.attr("disabled", true);
                idVerificationToken.val('success');
            } else {
                return false;
            }
        })
        .catch(error => {
            let jsonData = error.response.data;
            alert(jsonData['message']);
        });
}

// 3-1. 이메일 인증 시도를 위한 버튼 활성화
function enableEmailVerificationWithSignUp() {
    let email = $('#email');
    let emailVerificationButton = $('#emailVerificationButton');

    if (email.val().trim() === '') {
        alert('이메일을 입력해주세요');
        email.val('');
        email.focus();
        return false;
    }

    let use = confirm(email.val() + '을 사용하시겠습니까?\n인증번호가 발송되면 변경이 불가능합니다');

    if (use) {
        let data = {
            'resource': 'join',
            'email': email.val()
        }

        axios.post('/api/email/authenticate', data)
            .then(response => {
                $('#checkEmail').prop('disabled', false);
                $('#explainEmailCheck').show();

                email.attr("readonly",true);
                email.css({
                    "border-color": "#0D6EFD",
                    "border": "2px solid",
                    "color": "#0D6EFD",
                    "font-size": "15px"
                })

                const emailCode = response['data'];
                emailVerificationButton.attr("disabled", true);
                emailVerificationApiProcess(emailCode);
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    }
}

// 3-2. 이메일 인증 API 진행
function emailVerificationApiProcess(emailCode) {
    $('#checkEmail').on('keyup', () => {
        let checkEmail = $('#checkEmail');
        let emailVerificationToken = $('#emailVerificationToken');

        if (checkEmail.val() !== emailCode) {
            checkEmail.css({
                "border-color": "#FA3E3E",
                "border": "2px solid",
                "color": "#FA3E3E",
                "font-size": "15px"
            })

            let explainEmailCheck = $('#explainEmailCheck');
            explainEmailCheck.html('인증번호가 잘못되었습니다');
            explainEmailCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })

            emailVerificationToken.val('fail');
        } else {
            checkEmail.css({
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
        }
    })
}