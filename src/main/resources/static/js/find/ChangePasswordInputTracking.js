// 현재 비밀번호 입력 Tracking
function trackingCurrentPassword() {
    let currentPassword = $('#currentPassword');
    let currentPasswordToken = $('#currentPasswordToken');

    if (currentPassword.val().trim() !== '') {
        currentPassword.css({
            "border-color": "#0D6EFD",
            "border": "2px solid",
            "color": "#0D6EFD",
            "font-size": "15px"
        });

        currentPasswordToken.val('success');
    } else {
        currentPassword.css({
            "border-color": "",
            "border": "",
            "color": "",
            "font-weight": "",
            "font-size": ""
        });

        currentPasswordToken.val('fail');
    }
}

// 변경할 비밀번호 입력 Tracking
function trackingChangePassword() {
    let changePassword = $('#changePassword'); // input
    let explainPasswordRegExp = $('#explainPasswordRegExp'); // span
    let changePasswordToken = $('#changePasswordToken'); // hidden
    let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}/; // regExp

    if (changePassword.val().trim() !== '') {
        if (reg.test(changePassword.val())) {
            changePassword.css({
                "border-color": "#0D6EFD",
                "border": "2px solid",
                "color": "#0D6EFD",
                "font-size": "15px"
            });

            explainPasswordRegExp.show();
            explainPasswordRegExp.html('사용 가능한 비밀번호입니다.');
            explainPasswordRegExp.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })
            changePasswordToken.val('success');
        } else {
            changePassword.css({
                "border-color": "#FA3E3E",
                "border": "2px solid",
                "color": "#FA3E3E",
                "font-size": "15px"
            })

            explainPasswordRegExp.show();
            explainPasswordRegExp.html('영문, 숫자, 특수문자를 하나 이상 포함하고 8자 이상이여야 합니다');
            explainPasswordRegExp.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })
            changePasswordToken.val('fail');
        }
    } else {
        changePassword.css({
            "border-color": "",
            "border": "",
            "color": "",
            "font-size": ""
        });
        explainPasswordRegExp.hide();
        changePasswordToken.val('fail');
    }
}

// 변경할 비밀번호 확인란 입력 Tracking
function trackingChangePasswordCheck() {
    let changePassword = $('#changePassword'); // 변경할 비밀번호 input
    let checkChangePassword = $('#checkChangePassword'); // 변경할 비밀번호 확인란 input
    let explainPasswordCheck = $('#explainPasswordCheck'); // span
    let checkChangePasswordVerificationToken = $('#checkChangePasswordVerificationToken'); // hidden

    if (checkChangePassword.val().trim() !== '') {
        if (changePassword.val() === checkChangePassword.val()) {
            checkChangePassword.css({
                "border-color": "#0D6EFD",
                "border": "2px solid",
                "color": "#0D6EFD",
                "font-size": "15px"
            });

            explainPasswordCheck.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })
            explainPasswordCheck.show();
            explainPasswordCheck.html('일치합니다');
            checkChangePasswordVerificationToken.val('success');
        } else {
            checkChangePassword.css({
                "border-color": "#FA3E3E",
                "border": "2px solid",
                "color": "#FA3E3E",
                "font-size": "15px"
            })

            explainPasswordCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })
            explainPasswordCheck.show();
            explainPasswordCheck.html('일치하지 않습니다');
            checkChangePasswordVerificationToken.val('fail');
        }
    } else {
        checkChangePassword.css({
            "border-color": "",
            "border": "",
            "color": "",
            "font-size": ""
        });
        explainPasswordCheck.hide();
        checkChangePasswordVerificationToken.val('fail');
    }
}