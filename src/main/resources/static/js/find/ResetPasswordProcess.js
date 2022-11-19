function userVericiationAndApplyRandomPassword() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let name = $('#name');
    if (validationName(name) === false) {
        ToastResponse.fire({
            html: '<b>이름을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            name.focus();
        })
        return false;
    }

    let loginId = $('#loginId');
    if (validationLoginId(loginId) === false) {
        ToastResponse.fire({
            html: '<b>아이디를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            loginId.focus();
        })
        return false;
    }

    let email = $('#email');
    let emailAuthenticationToken = $('#emailAuthenticationToken');
    if (validationEmail(email, emailAuthenticationToken) === 'fail1') {
        ToastResponse.fire({
            html: '<b>이메일을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            email.focus();
        })
        return false;
    } else if (validationEmail(email, emailAuthenticationToken) === 'fail2') {
        ToastResponse.fire({
            html: '<b>이메일 인증을 진행해주세요</b>',
            icon: 'warning'
        }).then(() => {
            emailAuthenticationToken.focus();
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
        text: '비밀번호를 초기화 하시겠습니까?',
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
                    ToastResponse.fire({
                        html: '메일로 임시 비밀번호가 발송되었습니다<br>임시 비밀번호로 로그인 후 비밀번호 재설정을 진행하세요',
                        icon: 'success'
                    }).then(() => {
                        location.href = '/login';
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;
                    ToastResponse.fire({
                        color: '#FF0000',
                        text: jsonData['message'],
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