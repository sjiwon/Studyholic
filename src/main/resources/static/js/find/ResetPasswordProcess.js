function changePassword(userId) {
    let currentPassword = $('#currentPassword');
    let currentPasswordToken = $('#currentPasswordToken');
    if (validationCurrentPassword(currentPassword, currentPasswordToken) === false) {
        return false;
    }

    let changePassword = $('#changePassword');
    let changePasswordToken = $('#changePasswordToken');
    if (validationChangePassword(changePassword, changePasswordToken) === false) {
        return false;
    }

    let checkChangePassword = $('#checkChangePassword');
    let checkChangePasswordVerificationToken = $('#checkChangePasswordVerificationToken');
    if (validationChangePasswordVerification(checkChangePassword, checkChangePasswordVerificationToken) === false) {
        return false;
    }

    let select = confirm('비밀번호를 변경하시겠습니까?');
    if (select) {
        let data = {
            'userId': userId,
            'currentPassword': currentPassword.val(),
            'changePassword': changePassword.val()
        };

        axios.patch('/api/user/change-password', data)
            .then(() => {
                axios.post("/api/logout")
                    .then(() => {
                        alert('비밀번호가 변경되었습니다\n다시 로그인해주세요');
                        location.href = '/login';
                    })
                    .catch(error => {
                        let jsonData = error.response.data;
                        alert(jsonData['message']);
                    });
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else {
        return false;
    }
}

function validationCurrentPassword(password, passwordToken) {
    if (passwordToken.val() === 'fail') {
        alert('현재 비밀번호를 다시 확인해주세요');
        password.focus();
        return false;
    }
}

function validationChangePassword(password, passwordToken) {
    if (passwordToken.val() === 'fail') {
        alert('변경할 비밀번호를 다시 확인해주세요');
        password.focus();
        return false;
    }
}

function validationChangePasswordVerification(password, passwordToken) {
    if (passwordToken.val() === 'fail') {
        alert('변경할 비밀번호 확인란을 다시 확인해주세요');
        password.focus();
        return false;
    }
}