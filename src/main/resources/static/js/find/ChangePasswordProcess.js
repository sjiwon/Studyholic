function changePassword(userId) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let currentPassword = $('#currentPassword');
    let currentPasswordToken = $('#currentPasswordToken');
    if (validationCurrentPassword(currentPassword, currentPasswordToken) === false) {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '현재 비밀번호를 다시 확인해주세요',
            icon: 'warning'
        }).then(() => {
            currentPassword.focus();
        })
        return false;
    }

    let changePassword = $('#changePassword');
    let changePasswordToken = $('#changePasswordToken');
    if (validationChangePassword(changePassword, changePasswordToken) === false) {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '변경할 비밀번호를 다시 확인해주세요',
            icon: 'warning'
        }).then(() => {
            $('#checkChangePassword').val('');
            changePassword.focus();
        })
        return false;
    }

    let checkChangePassword = $('#checkChangePassword');
    let checkChangePasswordVerificationToken = $('#checkChangePasswordVerificationToken');
    if (validationChangePasswordVerification(checkChangePassword, checkChangePasswordVerificationToken) === false) {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '변경할 비밀번호 확인란을 다시 확인해주세요',
            icon: 'warning'
        }).then(() => {
            checkChangePassword.focus();
        })
        return false;
    }

    if (validateionChangePasswordAndVericifactionInput(changePassword, checkChangePassword) === false) {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '변경할 비밀번호와 확인란이 일치하지 않습니다',
            icon: 'warning'
        }).then(() => {
            checkChangePassword.focus();
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
        text: '비밀번호를 변경하시겠습니까?',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'userId': userId,
                'currentPassword': currentPassword.val(),
                'changePassword': changePassword.val()
            };

            axios.patch('/api/user/change-password', data)
                .then(() => {
                    axios.post("/api/logout")
                        .then(() => {
                            ToastResponse.fire({
                                html: '비밀번호가 변경되었습니다<br>다시 로그인해주세요',
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
                            })
                        });
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
                    })
                });
        }
    })
}

function validationCurrentPassword(password, passwordToken) {
    if (passwordToken.val() === 'fail') {
        return false;
    }
}

function validationChangePassword(password, passwordToken) {
    if (passwordToken.val() === 'fail') {
        return false;
    }
}

function validationChangePasswordVerification(password, passwordToken) {
    if (passwordToken.val() === 'fail') {
        return false;
    }
}

function validateionChangePasswordAndVericifactionInput(password, checkPassword) {
    if (password.val() !== checkPassword.val()) {
        return false;
    }
}