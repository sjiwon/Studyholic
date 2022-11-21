function login() {
    let language = navigator.language;

    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let loginId = $('#loginId');
    let loginPassword = $('#loginPassword');
    let validationHtml = (navigator.language === 'ko')
        ? ('<b>아이디나 비밀번호를 다시 확인해주세요</b><br><small>- 빈 값이 존재합니다</small>')
        : ('<b>Please check your ID or Password again</b><br><small>- Empty value exists</small>');
    if (validationInput(loginId, loginPassword) === false) {
        ToastResponse.fire({
            html: validationHtml,
            icon: 'warning'
        }).then(() => {
            if (loginId.val().trim() === '' && loginPassword.val().trim() === '') {
                loginId.focus();
            } else if (loginId.val().trim() === '') {
                loginId.focus();
            } else {
                loginPassword.focus();
            }
        })
        return false;
    }

    let data = {
        'loginId': loginId.val(),
        'loginPassword': loginPassword.val()
    };

    axios.post('/api/login', data)
        .then((res) => {
            let successText = (navigator.language === 'ko')
                ? ('로그인에 성공하였습니다')
                : ('Login successful');
            ToastResponse.fire({
                showConfirmButton: false,
                timer: 1000,
                timerProgressBar: true,
                text: successText,
                icon: 'success'
            }).then(() => {
                location.replace(res['data']);
            })
        })
        .catch(error => {
            let errorStatus = error.response.status;
            let jsonData = error.response.data;

            if (errorStatus === 401) {
                let error400Text = (navigator.language === 'ko')
                    ? (jsonData['message'])
                    : ('Passwords do not match');
                ToastResponse.fire({
                    color: '#FF0000',
                    text: error400Text,
                    icon: 'error'
                }).then(() => {
                    loginPassword.val('').focus();
                });
            } else if (errorStatus === 404) {
                let error404Text = (navigator.language === 'ko')
                    ? (jsonData['message'])
                    : ('User information does not exist');
                ToastResponse.fire({
                    color: '#FF0000',
                    text: error404Text,
                    icon: 'error'
                }).then(() => {
                    loginPassword.val('');
                    loginId.val('').focus();
                });
            }
        });
}

function validationInput(loginId, loginPassword) {
    if (loginId.val().trim() === '' || loginPassword.val().trim() === '') {
        return false;
    }
}