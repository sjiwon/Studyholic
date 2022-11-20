// 변경할 비밀번호 입력 Tracking
function trackingChangePassword() {
    let changePassword = $('#changePassword'); // input
    let explainPasswordRegExp = $('#explainPasswordRegExp'); // span
    let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}/; // regExp

    if (changePassword.val().trim() !== '') { // 입력값 존재 O
        if (reg.test(changePassword.val())) { // 정규식 만족 O
            explainPasswordRegExp.show();
            explainPasswordRegExp.html('사용 가능한 비밀번호입니다.');
            explainPasswordRegExp.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })
        } else { // 정규식 만족 X
            explainPasswordRegExp.show();
            explainPasswordRegExp.html('영문, 숫자, 특수문자를 하나 이상 포함하고 8자 이상이여야 합니다');
            explainPasswordRegExp.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })
        }
    } else { // 입력값 존재 X
        explainPasswordRegExp.hide();
    }
}

// 변경할 비밀번호 확인란 입력 Tracking
function trackingChangePasswordCheck() {
    let changePassword = $('#changePassword'); // 변경할 비밀번호 input
    let checkChangePassword = $('#checkChangePassword'); // 변경할 비밀번호 확인란 input
    let explainPasswordCheck = $('#explainPasswordCheck'); // span

    if (checkChangePassword.val().trim() !== '') { // 확인란 입력 O
        if (changePassword.val() === checkChangePassword.val()) { // 일치 O
            explainPasswordCheck.show();
            explainPasswordCheck.html('일치합니다');
            explainPasswordCheck.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })
        } else { // 일치 X
            explainPasswordCheck.show();
            explainPasswordCheck.html('일치하지 않습니다');
            explainPasswordCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })
        }
    } else { // 확인란 입력 X
        explainPasswordCheck.hide();
    }
}