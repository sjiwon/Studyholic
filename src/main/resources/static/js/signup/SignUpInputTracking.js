// 1. 이름 기입 여부 Tracking
function trackingName() {
    let name = $('#name');
    let nameToken = $('#nameToken');

    if (name.val().trim() !== '') {
        name.css({
            "border-color": "#0D6EFD",
            "border": "2px solid",
            "color": "#0D6EFD",
            "font-size": "15px"
        });

        nameToken.val('success');
    } else {
        name.css({
            "border-color": "",
            "border": "",
            "color": "",
            "font-weight": "",
            "font-size": ""
        });

        nameToken.val('fail');
    }
}

// 2. 닉네임 기입 여부 Tracking
function trackingNickname() {
    let nickname = $('#nickname');
    let nicknameVerificationButton = $('#nicknameVerificationButton');
    let nicknameVerificationToken = $('#nicknameVerificationToken');

    nickname.css({
        "border-color": "",
        "border": "",
        "color": "",
        "font-size": ""
    });
    nicknameVerificationButton.attr("disabled", false);
    nicknameVerificationToken.val('fail');
}

// 3. 로그인 아이디 기입 여부 Tracking
function trackingId() {
    let loginId = $('#loginId');
    let idVerificationButton = $('#idVerificationButton');
    let idVerificationToken = $('#idVerificationToken');

    loginId.css({
        "border-color": "",
        "border": "",
        "color": "",
        "font-size": ""
    });
    idVerificationButton.attr("disabled", false);
    idVerificationToken.val('fail');
}

// 4-1. 비밀번호 정규식 만족 여부 Tracking
function trackingPasswordRegExp() {
    let loginPassword = $('#loginPassword'); // input
    let explainPasswordRegExp = $('#explainPasswordRegExp'); // span
    let passwordToken = $('#passwordToken'); // hidden
    let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}/; // regExp

    if (loginPassword.val().trim() !== '') {
        if (reg.test(loginPassword.val())) {
            loginPassword.css({
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
            passwordToken.val('success');
        } else {
            loginPassword.css({
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
            passwordToken.val('fail');
        }
    } else {
        loginPassword.css({
            "border-color": "",
            "border": "",
            "color": "",
            "font-size": ""
        });
        explainPasswordRegExp.hide();
        passwordToken.val('fail');
    }
}

// 4-2. 비밀번호 확인란 일치 여부 Tracking
function trackingPasswordEquals() {
    let loginPassword = $('#loginPassword'); // 사용할 비밀번호 input
    let checkPassword = $('#checkPassword'); // 비밀번호 확인 input
    let explainPasswordCheck = $('#explainPasswordCheck'); // span
    let passwordVerificationToken = $('#passwordVerificationToken'); // hidden

    if (checkPassword.val().trim() !== '') {
        if (loginPassword.val() === checkPassword.val()) {
            checkPassword.css({
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
            passwordVerificationToken.val('success');
        } else {
            checkPassword.css({
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
            passwordVerificationToken.val('fail');
        }
    } else {
        checkPassword.css({
            "border-color": "",
            "border": "",
            "color": "",
            "font-size": ""
        });
        explainPasswordCheck.hide();
        passwordVerificationToken.val('fail');
    }
}
