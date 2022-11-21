function changePasswordProcess(userId) {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let currentPassword = $('#currentPassword');
    if (validationCurrentPassword(currentPassword) === false) {
        let currentPasswordValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>현재 비밀번호를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please recheck your current password</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: currentPasswordValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            currentPassword.focus();
        })
        return false;
    }

    let changePassword = $('#changePassword');
    if (validationChangePassword(changePassword) === 'fail1') {
        let changePasswordValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>변경할 비밀번호를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please reconfirm the password you want to change</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: changePasswordValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            $('#checkChangePassword').val('');
            changePassword.focus();
        })
        return false;
    } else if (validationChangePassword(changePassword) === 'fail2') {
        let changePasswordValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>변경할 비밀번호를 다시 확인해주세요</b><br><small>- 영문, 숫자, 특수문자를 하나 이상 포함하고 8자 이상</small>')
            : ('<b>Please reconfirm the password you want to change</b><br><small>- At least 8 characters including at least one English letter, number, or special character</small>');

        ToastResponse.fire({
            html: changePasswordValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            $('#checkChangePassword').val('');
            changePassword.focus();
        })
        return false;
    }

    let checkChangePassword = $('#checkChangePassword');
    if (validationChangePasswordVerification(changePassword, checkChangePassword) === 'fail1') {
        let checkChangePasswordValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>변경할 비밀번호 확인란을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please double check the password to change checkbox</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: checkChangePasswordValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            checkChangePassword.focus();
        })
        return false;
    } else if (validationChangePasswordVerification(changePassword, checkChangePassword) === 'fail2') {
        let checkChangePasswordValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>변경할 비밀번호 확인란을 다시 확인해주세요</b><br><small>- 변경할 비밀번호와 확인란이 일치하지 않습니다</small>')
            : ('<b>Please double check the password to change checkbox</b><br><small>- Change password and checkbox do not match</small>');

        ToastResponse.fire({
            html: checkChangePasswordValidationFailHtml,
            icon: 'warning'
        })
        return false;
    }

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('비밀번호를 변경하시겠습니까?')
        : ('Are you sure you want to change your password?');

    ToastApi.fire({
        text: question,
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
                            let successHtml = (navigator.language === 'ko')
                                ? ('비밀번호가 변경되었습니다<br>다시 로그인해주세요')
                                : ('Your password has been changed<br>Please log in again');

                            ToastResponse.fire({
                                html: successHtml,
                                icon: 'success'
                            }).then(() => {
                                location.href = '/login';
                            })
                        })
                })
                .catch(error => {
                    let jsonData = error.response.data;
                    let error409Html = (navigator.language === 'ko')
                        ? (jsonData['message'])
                        : ('Current passwords do not match');

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: error409Html,
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