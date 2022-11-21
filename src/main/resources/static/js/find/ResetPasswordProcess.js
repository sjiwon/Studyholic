function userVericiationAndApplyRandomPassword() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let name = $('#name');
    if (validationName(name) === false) {
        let nameValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>이름을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your name again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: nameValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            name.focus();
        })
        return false;
    }

    let loginId = $('#loginId');
    if (validationLoginId(loginId) === false) {
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

    let email = $('#email');
    let emailAuthenticationToken = $('#emailAuthenticationToken');
    if (validationEmail(email, emailAuthenticationToken) === 'fail1') {
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
    } else if (validationEmail(email, emailAuthenticationToken) === 'fail2') {
        let emailValidationFailHtml2 = (navigator.language === 'ko')
            ? ('<b>이메일 인증을 진행해주세요</b>')
            : ('<b>Please proceed with email verification</b>');

        ToastResponse.fire({
            html: emailValidationFailHtml2,
            icon: 'warning'
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
        ? ('비밀번호를 초기화 하시겠습니까?')
        : ('Are you sure you want to reset your password?');

    ToastApi.fire({
        text: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'name': name.val(),
                'loginId': loginId.val(),
                'email': email.val()
            }

            axios.post('/api/user/random-password', data)
                .then(() => {
                    let successHtml = (navigator.language === 'ko')
                        ? ('메일로 임시 비밀번호가 발송되었습니다<br>임시 비밀번호로 로그인 후 비밀번호 재설정을 진행하세요')
                        : ('A temporary password has been sent to you by email<br>Log in with your temporary password and reset your password');

                    ToastResponse.fire({
                        html: successHtml,
                        icon: 'success'
                    }).then(() => {
                        location.href = '/login';
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '사용자 정보가 존재하지 않습니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('User information does not exist');
                    } else if(jsonData['message'] === '인증내역이 없는 이메일입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('This email has no verification history');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    }).then(() => {
                        location.replace('/reset-password');
                    });
                });
        }
    });
}

function validationName(name) {
    if (name.val().trim() === '') {
        return false;
    }
}

function validationLoginId(loginId) {
    if (loginId.val().trim() === '') {
        return false;
    }
}

function validationEmail(email, emailToken) {
    if (email.val().trim() === '') {
        return "fail1";
    } else if (emailToken.val() === 'fail') {
        return "fail2";
    }
}