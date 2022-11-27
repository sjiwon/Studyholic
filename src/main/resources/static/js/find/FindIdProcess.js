function findIdProcess() {
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
            let successHtml = (navigator.language === 'ko')
                ? ('아이디는 <b>' + res['data'] + '</b>입니다.<br>로그인 페이지로 이동합니다')
                : ('ID is <b>' + res['data'] + '</b><br>Go to the login page');

            ToastResponse.fire({
                html: successHtml,
                icon: 'success'
            }).then(() => {
                location.href = '/login';
            })
        })
        .catch(error => {
            let jsonData = error.response.data;
            let failHtml = (navigator.language === 'ko')
                ? (jsonData['message'])
                : ('User information does not exist');

            ToastResponse.fire({
                color: '#FF0000',
                text: failHtml,
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