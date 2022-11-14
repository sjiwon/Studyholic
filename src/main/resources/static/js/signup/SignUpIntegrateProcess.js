// 최종 회원가입 진행 Process
function signUpProcess() {
    let name = $('#name');
    let nameToken = $('#nameToken');
    if (validationName(name, nameToken) === false) {
        return false;
    }

    let nickname = $('#nickname');
    let nicknameVerificationToken = $('#nicknameVerificationToken');
    if (validationNickname(nickname, nicknameVerificationToken) === false) {
        return false;
    }

    let loginId = $('#loginId');
    let idVerificationToken = $('#idVerificationToken');
    if (validationLoginId(loginId, idVerificationToken) === false) {
        return false;
    }

    let loginPassword = $('#loginPassword');
    let passwordToken = $('#passwordToken');
    let checkPassword = $('#checkPassword');
    let passwordVerificationToken = $('#passwordVerificationToken');
    if (validationPassword(loginPassword, passwordToken, checkPassword, passwordVerificationToken) === false) {
        return false;
    }

    let email = $('#email');
    let emailVerificationToken = $('#emailVerificationToken');
    if (validationEmail(email, emailVerificationToken) === false) {
        return false;
    }

    let birth = $('#birth');
    if (validationBirth(birth) === false) {
        return false;
    }

    let image = document.getElementById('image');

    let formData = new FormData();
    formData.append('name', name.val());
    formData.append('nickname', nickname.val());
    formData.append('loginId', loginId.val());
    formData.append('loginPassword', loginPassword.val());
    formData.append('birth', birth.val());
    formData.append('email', email.val());

    if (image.files.length === 1) { // 0이면 기본 이미지로 대체해서 저장
        formData.append('profile', image.files[0]);
    }

    let fileExistsAxiosConfig = {
        headers: {
            "Content-Type": "multipart/form-data",
        }
    };

    if (image.files.length === 0) { // 기본 이미지
        axios.post('/api/user/default-profile', formData)
            .then(() => {
                alert('회원가입에 성공하였습니다');
                location.replace('/');
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else { // 이미지 업로드
        axios.post('/api/user', formData, fileExistsAxiosConfig)
            .then(() => {
                alert('회원가입에 성공하였습니다');
                location.replace('/');
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    }
}

function validationName(name, nameToken) {
    if (nameToken.val() === 'fail') {
        alert('이름을 다시 확인해주세요');
        name.focus();
        return false;
    }
}

function validationNickname(nickname, nicknameToken) {
    if (nicknameToken.val() === 'fail') {
        if (nickname.val().trim() === '') {
            alert('닉네임을 다시 확인해주세요');
            nickname.focus();
            return false;
        } else {
            alert('닉네임 중복체크를 진행해주세요');
            nicknameToken.focus();
            return false;
        }
    }
}

function validationLoginId(loginId, loginIdToken) {
    if (loginIdToken.val() === 'fail') {
        if (loginId.val().trim() === '') {
            alert('아이디를 다시 확인해주세요');
            loginId.focus();
            return false;
        } else {
            alert('아이디 중복체크를 진행해주세요');
            loginIdToken.focus();
            return false;
        }
    }
}

function validationPassword(password, passwordToken, checkPassword, checkPasswordToken) {
    if (passwordToken.val() === 'fail') {
        alert('비밀번호를 다시 확인해주세요');
        password.focus();
        return false;
    } else if (checkPasswordToken.val() === 'fail') {
        alert('비밀번호 확인란을 다시 확인해주세요');
        checkPassword.focus();
        return false;
    }
}

function validationEmail(email, emailToken) {
    if (emailToken.val() === 'fail') {
        if (email.val().trim() === '') {
            alert('이메일을 다시 확인해주세요');
            email.focus();
            return false;
        } else {
            alert('이메일 인증을 진행해주세요');
            emailToken.focus();
            return false;
        }
    }
}

function validationBirth(birth) {
    if (birth.val() === '') {
        alert('생년월일을 선택해주세요');
        birth.focus();
        return false;
    }
}