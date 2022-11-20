// 1. 닉네임 Tracking
function nicknameTracking() {
    $('#nicknameVerificationButton').attr("disabled", false);
    $('#nicknameVerificationToken').val('fail');
}

// 2. 아이디 Tracking
function loginIdTracking() {
    $('#idVerificationButton').attr("disabled", false);
    $('#idVerificationToken').val('fail');
}

// 3. 비밀번호 정규식 만족 여부 Tracking
function trackingPasswordRegExp() {
    let loginPassword = $('#loginPassword'); // input
    let explainPasswordRegExp = $('#explainPasswordRegExp'); // span
    let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}/; // regExp

    if (loginPassword.val().trim() !== '') { // 입력값 존재 O
        if (reg.test(loginPassword.val())) { // 정규식 만족 O
            explainPasswordRegExp.show();
            explainPasswordRegExp.html('사용 가능한 비밀번호입니다.');
            explainPasswordRegExp.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })

            loginPassword.css({
                "border": "2px solid #0D6EFD",
                "font-size": "20px"
            })
        } else { // 정규식 만족 X
            explainPasswordRegExp.show();
            explainPasswordRegExp.html('영문, 숫자, 특수문자를 하나 이상 포함하고 8자 이상이여야 합니다');
            explainPasswordRegExp.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })

            loginPassword.css({
                "border": "2px solid #FA3E3E",
                "font-size": "20px"
            })
        }
    } else {
        explainPasswordRegExp.hide();
    }
}

// 4. 비밀번호 확인란 일치 여부 Tracking
function trackingPasswordEquals() {
    let loginPassword = $('#loginPassword'); // 사용할 비밀번호 input
    let checkPassword = $('#checkPassword'); // 비밀번호 확인 input
    let explainPasswordCheck = $('#explainPasswordCheck'); // span

    if (checkPassword.val().trim() !== '') { // 확인란 입력 O
        if (loginPassword.val() === checkPassword.val()) { // 일치 O
            explainPasswordCheck.show();
            explainPasswordCheck.html('일치합니다');
            explainPasswordCheck.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })

            checkPassword.css({
                "border": "2px solid #0D6EFD",
                "font-size": "20px"
            })
        } else { // 일치 X
            explainPasswordCheck.show();
            explainPasswordCheck.html('일치하지 않습니다');
            explainPasswordCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })

            checkPassword.css({
                "border": "2px solid #FA3E3E",
                "font-size": "20px"
            })
        }
    } else { // 확인란 입력 X
        explainPasswordCheck.hide();
    }
}
