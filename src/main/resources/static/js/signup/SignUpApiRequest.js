// 1. 닉네임 중복 체크 API
function nicknameDuplicateCheckApi() {
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

    let nickname = $('#nickname');
    let nicknameVerificationButton = $('#nicknameVerificationButton');
    let nicknameVerificationToken = $('#nicknameVerificationToken');

    if (nickname.val().trim() === '') {
        let nicknameValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>닉네임을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your nickname again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: nicknameValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            nickname.focus();
        })
        return false;
    }

    let question = (navigator.language === 'ko')
        ? ('해당 닉네임을 사용하시겠습니까?<br><b style="font-size: 20px;">-> ' + nickname.val() + '</b>')
        : ('Would you like to use that nickname?<br><b style="font-size: 20px;">-> ' + nickname.val() + '</b>');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'resource': 'nickname',
                'value': nickname.val()
            }

            axios.post('/api/user/duplicate-check', data)
                .then(() => {
                    let successHtml = (navigator.language === 'ko')
                        ? ('<b>[' + nickname.val() + ']</b>은/는 사용 가능합니다')
                        : ('<b>[' + nickname.val() + ']</b> is available');

                    ToastResponse.fire({
                        html: successHtml,
                        icon: 'success'
                    }).then(() => {
                        nicknameVerificationButton.attr("disabled", true);
                        nicknameVerificationToken.val('success');
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '중복된 닉네임입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('This is a duplicate nickname');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server while sending the email');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    }).then(() => {
                        nicknameVerificationToken.val('fail');
                        nickname.focus();
                    });
                });
        }
    });
}

// 2. 로그인 아이디 중복 체크 API
function idDuplicateCheckApi() {
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

    let loginId = $('#loginId');
    let idVerificationButton = $('#idVerificationButton');
    let idVerificationToken = $('#idVerificationToken');

    if (loginId.val().trim() === '') {
        let idValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>아이디를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your ID again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: idValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            loginId.focus();
        })
        return false;
    }

    let question = (navigator.language === 'ko')
        ? ('해당 아이디를 사용하시겠습니까?<br><b style="font-size: 20px;">-> ' + loginId.val())
        : ('Would you like to use that ID?<br><b style="font-size: 20px;">-> ' + loginId.val());

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'resource': 'id',
                'value': loginId.val()
            }

            axios.post('/api/user/duplicate-check', data)
                .then(() => {
                    let successHtml = (navigator.language === 'ko')
                        ? ('<b>[' + loginId.val() + ']</b>은/는 사용 가능합니다')
                        : ('<b>[' + loginId.val() + ']</b> is available');

                    ToastResponse.fire({
                        html: successHtml,
                        icon: 'success'
                    }).then(() => {
                        idVerificationButton.attr("disabled", true);
                        idVerificationToken.val('success');
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '중복된 로그인 아이디입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('This is a duplicate login ID');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server while sending the email');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    }).then(() => {
                        idVerificationToken.val('fail');
                        loginId.focus();
                    })
                });
        }
    });
}

// 3-1. 이메일 인증 시도를 위한 버튼 활성화
function enableEmailVerificationWithSignUp() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let email = $('#email');
    let emailVerificationButton = $('#emailVerificationButton');
    let reg = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/; // regExp

    if (email.val().trim() === '') {
        let emailValidationFailHtml1 = (navigator.language === 'ko')
            ? ('<b>이메일을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your email again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: emailValidationFailHtml1,
            icon: 'warning'
        }).then(() => {
            email.focus();
        })
        return false;
    } else if (!reg.test(email.val())) {
        let emailValidationFailHtml2 = (navigator.language === 'ko')
            ? ('<b>이메일을 다시 확인해주세요</b><br><small>- 이메일 형식에 맞춰서 입력해주세요</small>')
            : ('<b>Please check your email again</b><br><small>- Please enter in the email format</small>');

        ToastResponse.fire({
            html: emailValidationFailHtml2,
            icon: 'warning'
        }).then(() => {
            email.focus();
        })
        return false;
    }

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('<b>' + email.val() + '</b>을 사용하시겠습니까?<br><small>- 인증번호가 발송되면 변경이 불가능합니다</small>')
        : ('Would you like to use <b>' + email.val() + '</b>?<br><small>- Once the verification number has been sent, it cannot be changed.</small>');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'resource': 'join',
                'email': email.val()
            }

            axios.post('/api/email/authenticate', data)
                .then(response => {
                    $('#checkEmail').css("display", "block");
                    $('#explainEmailCheck').show();
                    email.attr("readonly", true);

                    const emailCode = response['data'];
                    emailVerificationButton.attr("disabled", true);
                    emailVerificationApiProcess(emailCode);
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '중복된 이메일입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('This is a duplicate email');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server while sending the email');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    })
                });
        }
    })
}

// 3-2. 이메일 인증 API 진행
function emailVerificationApiProcess(emailCode) {
    let checkEmail = $('#checkEmail');
    let explainEmailCheck = $('#explainEmailCheck');

    checkEmail.on('keyup', () => {
        if (checkEmail.val() !== emailCode) {
            checkEmail.css({
                "border-color": "#FA3E3E",
                "border": "2px solid",
                "color": "#FA3E3E",
                "font-size": "15px"
            })

            explainEmailCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })
            let failHtml = (navigator.language === 'ko')
                ? ('인증번호가 일치하지 않습니다')
                : ('Verification number does not match');
            explainEmailCheck.html(failHtml);

            $('#emailAuthenticationToken').val('fail');
        } else {
            checkEmail.css({
                "border-color": "#0D6EFD",
                "border": "2px solid",
                "color": "#0D6EFD",
                "font-size": "15px"
            })

            explainEmailCheck.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })
            let successHtml = (navigator.language === 'ko')
                ? ('인증번호가 일치합니다')
                : ('Verification number matches');
            explainEmailCheck.html(successHtml);

            $('#emailAuthenticationToken').val('success');
        }
    })
}