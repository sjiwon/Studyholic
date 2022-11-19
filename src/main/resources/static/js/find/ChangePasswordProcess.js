function changePasswordProcess(userId) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let currentPassword = $('#currentPassword');
    if (validationCurrentPassword(currentPassword) === false) {
        ToastResponse.fire({
            html: '<b>현재 비밀번호를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            currentPassword.focus();
        })
        return false;
    }

    let changePassword = $('#changePassword');
    if (validationChangePassword(changePassword) === 'fail1') {
        ToastResponse.fire({
            html: '<b>변경할 비밀번호를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            $('#checkChangePassword').val('');
            changePassword.focus();
        })
        return false;
    } else if (validationChangePassword(changePassword) === 'fail2') {
        ToastResponse.fire({
            html: '<b>변경할 비밀번호를 다시 확인해주세요</b><br><small>- 영문, 숫자, 특수문자를 하나 이상 포함하고 8자 이상</small>',
            icon: 'warning'
        }).then(() => {
            $('#checkChangePassword').val('');
            changePassword.focus();
        })
        return false;
    }

    let checkChangePassword = $('#checkChangePassword');
    if (validationChangePasswordVerification(changePassword, checkChangePassword) === 'fail1') {
        ToastResponse.fire({
            html: '<b>변경할 비밀번호 확인란을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            checkChangePassword.focus();
        })
        return false;
    } else if (validationChangePasswordVerification(changePassword, checkChangePassword) === 'fail2') {
        ToastResponse.fire({
            html: '<b>변경할 비밀번호 확인란을 다시 확인해주세요</b><br><small>- 변경할 비밀번호와 확인란이 일치하지 않습니다</small>',
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
                                color: '#FF0000',
                                text: jsonData['message'],
                                icon: 'error'
                            })
                        });
                })
                .catch(error => {
                    let jsonData = error.response.data;
                    ToastResponse.fire({
                        color: '#FF0000',
                        text: jsonData['message'],
                        icon: 'error'
                    })
                });
        }
    })
}

function validationCurrentPassword(currentPassword) {
    if (currentPassword.val() === '') {
        return false;
    }
}

function validationChangePassword(changePassword) {
    let changePasswordInput = changePassword.val();
    let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}/; // regExp

    if (changePasswordInput === '') {
        return "fail1";
    } else if (!reg.test(changePasswordInput)) {
        return "fail2";
    }
}

function validationChangePasswordVerification(changePassword, changePasswordCheck) {
    let changePasswordInput = changePassword.val();
    let changePasswordCheckInput = changePasswordCheck.val();

    if (changePasswordCheckInput === '') {
        return "fail1";
    } else if (changePasswordInput !== changePasswordCheckInput) {
        return "fail2";
    }
}