function userVericiationAndApplyRandomPassword() {
    let name = $('#name');
    let nameToken = $('#nameToken');
    if (validationName(name, nameToken) === false) {
        return false;
    }

    let loginId = $('#loginId');
    let loginIdToken = $('#loginIdToken');
    if (validationLoginId(loginId, loginIdToken) === false) {
        return false;
    }

    let email = $('#email');
    let emailVerificationToken = $('#emailVerificationToken');
    if (validationEmail(email, emailVerificationToken) === false) {
        return false;
    }

    let data = {
        'name': name.val(),
        'loginId': loginId.val(),
        'email': email.val()
    }

    axios.post('/api/user/random-password', data)
        .then(() => {
            alert('인증이 완료되었고 메일로 임시 비밀번호가 발송되었습니다\n임시 비밀번호로 로그인 후 비밀번호 재설정을 진행하세요');
            location.href = '/login';
        })
        .catch(error => {
            let jsonData = error.response.data;
            alert(jsonData['message']);
            location.replace('/find-password');
        });
}

function validationName(name, nameToken) {
    if (nameToken.val() === 'fail') {
        alert('이름을 다시 확인해주세요');
        name.focus();
        return false;
    }
}

function validationLoginId(loginId, loginIdToken) {
    if (loginIdToken.val() === 'fail') {
        alert('아이디를 다시 확인해주세요');
        loginId.focus();
        return false;
    }
}

function validationEmail(email, emailToken) {
    if (emailToken.val() === 'fail') {
        if (email.val().trim() === '') {
            alert('이메일을 다시 확인해주세요');
            email.focus();
            return false;
        } else {
            alert('이메일 인증을 진행해주세요');
            emailToken.focus();
            return false;
        }
    }
}