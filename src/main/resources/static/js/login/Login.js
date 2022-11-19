function login() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let loginId = $('#loginId');
    let loginPassword = $('#loginPassword');
    if (validationInput(loginId, loginPassword) === false) {
        ToastResponse.fire({
            html: '<b>아이디나 비밀번호를 다시 확인해주세요</b><br><small>- 빈 값이 존재합니다</small>',
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
            ToastResponse.fire({
                showConfirmButton: false,
                timer: 1000,
                timerProgressBar: true,
                text: '로그인에 성공하였습니다',
                icon: 'success'
            }).then(() => {
                location.replace(res['data']);
            })
        })
        .catch(error => {
            let errorStatus = error.response.status;
            let jsonData = error.response.data;

            if (errorStatus === 401) {
                ToastResponse.fire({
                    color: '#FF0000',
                    text: jsonData['message'],
                    icon: 'error'
                }).then(() => {
                    loginPassword.val('').focus();
                });
            } else if (errorStatus === 404) {
                ToastResponse.fire({
                    color: '#FF0000',
                    text: jsonData['message'],
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