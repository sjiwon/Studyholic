function findIdProcess() {
    let name = $('#name');
    let nameToken = $('#nameToken');
    if (validationName(name, nameToken) === false) {
        return false;
    }

    let email = $('#email');
    let emailVerificationToken = $('#emailVerificationToken');
    if (validationEmail(email, emailVerificationToken) === false) {
        return false;
    }

    let data = {
        'name': name.val(),
        'email': email.val()
    }

    axios.post('/api/user/find/login-id', data)
        .then((response) => {
            alert('아이디는 [' + response['data'] + ']입니다.\n로그인 페이지로 이동합니다');
            location.href = '/login';
        })
        .catch(error => {
            let jsonData = error.response.data;
            alert(jsonData['message']);
            location.replace('/find-id');
        });
}

function validationName(name, nameToken) {
    if (nameToken.val() === 'fail') {
        alert('이름을 다시 확인해주세요');
        name.focus();
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