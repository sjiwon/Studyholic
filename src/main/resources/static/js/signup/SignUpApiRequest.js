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
        ToastResponse.fire({
            html: '<b>닉네임을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            nickname.focus();
        })
        return false;
    }

    ToastApi.fire({
        html: '해당 닉네임을 사용하시겠습니까? : <b style="font-size: 20px;">' + nickname.val() + '</b>',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'resource': 'nickname',
                'value': nickname.val()
            }

            axios.post('/api/user/duplicate-check', data)
                .then(() => {
                    ToastResponse.fire({
                        html: '<b>[' + nickname.val() + ']</b>은/는 사용 가능합니다',
                        icon: 'success'
                    }).then(() => {
                        nicknameVerificationButton.attr("disabled", true);
                        nicknameVerificationToken.val('success');
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;
                    ToastResponse.fire({
                        color: '#FF0000',
                        text: jsonData['message'],
                        icon: 'error'
                    }).then(() => {
                        nickname.val('');
                        nicknameVerificationToken.val('fail');
                        nickname.focus();
                    })
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
        ToastResponse.fire({
            html: '<b>아이디를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            loginId.focus();
        })
        return false;
    }

    ToastApi.fire({
        html: '해당 아이디를 사용하시겠습니까? : <b style="font-size: 20px;">' + loginId.val() + '</b>',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'resource': 'id',
                'value': loginId.val()
            }

            axios.post('/api/user/duplicate-check', data)
                .then(() => {
                    ToastResponse.fire({
                        html: '<b>[' + loginId.val() + ']</b>은/는 사용 가능합니다',
                        icon: 'success'
                    }).then(() => {
                        idVerificationButton.attr("disabled", true);
                        idVerificationToken.val('success');
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;
                    ToastResponse.fire({
                        color: '#FF0000',
                        text: jsonData['message'],
                        icon: 'error'
                    }).then(() => {
                        idVerificationToken.val('fail');
                        loginId.val('');
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
        ToastResponse.fire({
            html: '<b>이메일을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            email.focus();
        })
        return false;
    } else if (!reg.test(email.val())) {
        ToastResponse.fire({
            html: '<b>이메일을 다시 확인해주세요</b><br><small>- 이메일 형식에 맞춰서 입력해주세요</small>',
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

    ToastApi.fire({
        html: '<b>' + email.val() + '</b>을 사용하시겠습니까?<br><small>- 인증번호가 발송되면 변경이 불가능합니다</small>',
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
                    ToastResponse.fire({
                        color: '#FF0000',
                        text: jsonData['message'],
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

            explainEmailCheck.html('인증번호가 잘못되었습니다');
            explainEmailCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })

            $('#emailAuthenticationToken').val('fail');
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

            $('#emailAuthenticationToken').val('success');
        }
    })
}