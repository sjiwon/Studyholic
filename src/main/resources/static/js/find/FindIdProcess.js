function findIdProcess() {
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

    let data = {
        'name': name.val(),
        'email': email.val()
    };

    axios.post('/api/user/find/login-id', data)
        .then((res) => {
            ToastResponse.fire({
                html: '아이디는 <b>' + res['data'] + '</b>입니다.<br>로그인 페이지로 이동합니다',
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
                location.replace('/find-id');
            });
        });
}

function validationName(name) {
    if (name.val().trim() === '') {
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