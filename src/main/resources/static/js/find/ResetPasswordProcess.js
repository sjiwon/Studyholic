function userVericiationAndApplyRandomPassword() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let name = $('#name');
    let nameToken = $('#nameToken');
    if (validationName(name, nameToken) === false) {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '이름을 다시 확인해주세요',
            icon: 'warning'
        }).then(() => {
            name.focus();
        })
        return false;
    }

    let loginId = $('#loginId');
    let loginIdToken = $('#loginIdToken');
    if (validationLoginId(loginId, loginIdToken) === false) {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '아이디를 다시 확인해주세요',
            icon: 'warning'
        }).then(() => {
            loginId.focus();
        })
        return false;
    }

    let email = $('#email');
    let emailVerificationToken = $('#emailVerificationToken');
    if (validationEmail(email, emailVerificationToken) === 'fail1') {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '이메일을 다시 확인해주세요',
            icon: 'warning'
        }).then(() => {
            email.focus();
        })
        return false;
    } else if (validationEmail(email, emailVerificationToken) === 'fail2') {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '이메일 인증을 진행해주세요',
            icon: 'warning'
        }).then(() => {
            emailVerificationToken.focus();
        })
        return false;
    }

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
                showConfirmButton: false,
                timer: 1000,
                timerProgressBar: true,
                color: '#FF0000',
                text: jsonData['message'],
                icon: 'error'
            }).then(() => {
                location.replace('/find-password');
            });
        });
}

function validationName(name, nameToken) {
    if (nameToken.val() === 'fail') {
        return false;
    }
}

function validationLoginId(loginId, loginIdToken) {
    if (loginIdToken.val() === 'fail') {
        return false;
    }
}

function validationEmail(email, emailToken) {
    if (emailToken.val() === 'fail') {
        if (email.val().trim() === '') {
            return 'fail1';
        } else {
            return 'fail2';
        }
    }
}