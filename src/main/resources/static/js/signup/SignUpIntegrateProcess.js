// 최종 회원가입 진행 Process
function signUpProcess() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let name = $('#name');
    if (validationName(name) === false) {
        let nameValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>이름을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your name again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: nameValidationFailHtml,
            icon: 'warning'
        }).then(() => {
            name.focus();
        })
        return false;
    }

    let nickname = $('#nickname');
    let nicknameVerificationToken = $('#nicknameVerificationToken');
    if (validationNickname(nickname, nicknameVerificationToken) === 'fail1') {
        let nicknameValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>닉네임을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your nickname again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: 'nicknameValidationFailHtml',
            icon: 'warning'
        }).then(() => {
            nickname.focus();
        })
        return false;
    } else if (validationNickname(nickname, nicknameVerificationToken) === 'fail2') {
        let nicknameValidationFailHtml2 = (navigator.language === 'ko')
            ? ('<b>닉네임 중복 체크를 진행해주세요</b>')
            : ('<b>Please proceed with the nickname duplication check</b>');

        ToastResponse.fire({
            html: nicknameValidationFailHtml2,
            icon: 'warning'
        })
        return false;
    }

    let loginId = $('#loginId');
    let idVerificationToken = $('#idVerificationToken');
    if (validationLoginId(loginId, idVerificationToken) === 'fail1') {
        let idValidationFailHtml1 = (navigator.language === 'ko')
            ? ('<b>아이디를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your ID again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: idValidationFailHtml1,
            icon: 'warning'
        }).then(() => {
            loginId.focus();
        })
        return false;
    } else if (validationLoginId(loginId, idVerificationToken) === 'fail2') {
        let idValidationFailHtml2 = (navigator.language === 'ko')
            ? ('<b>아이디 중복 체크를 진행해주세요</b>')
            : ('<b>Please proceed to ID duplicate check</b>');

        ToastResponse.fire({
            html: idValidationFailHtml2,
            icon: 'warning'
        })
        return false;
    }

    let loginPassword = $('#loginPassword');
    let checkPassword = $('#checkPassword');
    if (validationPassword(loginPassword, checkPassword) === 'fail1') {
        let passwordValidationFailHtml1 = (navigator.language === 'ko')
            ? ('<b>비밀번호를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your password again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: passwordValidationFailHtml1,
            icon: 'warning'
        }).then(() => {
            loginPassword.focus();
        })
        return false;
    } else if (validationPassword(loginPassword, checkPassword) === 'fail2') {
        let passwordValidationFailHtml2 = (navigator.language === 'ko')
            ? ('<b>변경할 비밀번호를 다시 확인해주세요</b><br><small>- 영문, 숫자, 특수문자를 하나 이상 포함하고 8자 이상</small>')
            : ('<b>Please check your password again</b><br><small>- At least 8 characters including at least one English letter, number, or special character</small>');

        ToastResponse.fire({
            html: passwordValidationFailHtml2,
            icon: 'warning'
        }).then(() => {
            loginPassword.focus();
        })
        return false;
    } else if (validationPassword(loginPassword, checkPassword) === 'fail3') {
        let passwordValidationFailHtml3 = (navigator.language === 'ko')
            ? ('<b>비밀번호 확인란을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check the password checkbox again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: passwordValidationFailHtml3,
            icon: 'warning'
        }).then(() => {
            checkPassword.focus();
        });
        return false;
    } else if (validationPassword(loginPassword, checkPassword) === 'fail4') {
        let passwordValidationFailHtml4 = (navigator.language === 'ko')
            ? ('<b>비밀번호와 확인란이 일치하지 않습니다</b>')
            : ('<b>Password and checkbox do not match</b>');

        ToastResponse.fire({
            html: passwordValidationFailHtml4,
            icon: 'warning'
        });
        return false;
    }

    let email = $('#email');
    let emailAuthenticationToken = $('#emailAuthenticationToken');
    if (validationEmail(email, emailAuthenticationToken) === 'fail1') {
        let emailValidationFailHtml1 = (navigator.language === 'ko')
            ? ('<b>이메일을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your email again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: emailValidationFailHtml1,
            icon: 'warning'
        }).then(() => {
            email.focus();
        })
        return false;
    } else if (validationEmail(email, emailAuthenticationToken) === 'fail2') {
        let emailValidationFailHtml2 = (navigator.language === 'ko')
            ? ('<b>이메일 인증을 진행해주세요</b>')
            : ('<b>Please proceed with email verification</b>');

        ToastResponse.fire({
            html: emailValidationFailHtml2,
            icon: 'warning'
        })
        return false;
    }

    let birth = $('#birth');
    if (validationBirth(birth) === false) {
        let birthValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>생년월일을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your date of birth</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: birthValidationFailHtml,
            icon: 'warning'
        })
        return false;
    }

    let image = document.getElementById('image');

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('회원가입을 진행하시겠습니까?')
        : ('Would you like to proceed with membership registration?');

    ToastApi.fire({
        text: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
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
                        let successText = (navigator.language === 'ko')
                            ? ('회원가입에 성공하였습니다')
                            : ('You have successfully registered as a member');

                        ToastResponse.fire({
                            text: successText,
                            icon: 'success'
                        }).then(() => {
                            location.href = '/';
                        })
                    })
                    .catch(error => {
                        let jsonData = error.response.data;

                        let failText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');

                        ToastResponse.fire({
                            color: '#FF0000',
                            text: failText,
                            icon: 'error'
                        })
                    });
            } else { // 이미지 업로드
                axios.post('/api/user', formData, fileExistsAxiosConfig)
                    .then(() => {
                        let successText = (navigator.language === 'ko')
                            ? ('회원가입에 성공하였습니다')
                            : ('You have successfully registered as a member');

                        ToastResponse.fire({
                            text: successText,
                            icon: 'success'
                        }).then(() => {
                            location.href = '/';
                        })
                    })
                    .catch(error => {
                        let jsonData = error.response.data;

                        let failText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');

                        ToastResponse.fire({
                            color: '#FF0000',
                            text: failText,
                            icon: 'error'
                        })
                    });
            }
        }
    });
}

function validationName(name) {
    if (name.val().trim() === '') {
        return false;
    }
}

function validationNickname(nickname, nicknameToken) {
    if (nickname.val().trim() == '') {
        return "fail1";
    } else if (nicknameToken.val() === 'fail') {
        return "fail2";
    }
}

function validationLoginId(loginId, loginIdToken) {
    if (loginId.val().trim() == '') {
        return "fail1";
    } else if (loginIdToken.val() === 'fail') {
        return "fail2";
    }
}

function validationPassword(password, checkPassword) {
    let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}/; // regExp

    if (password.val().trim() === '') {
        return "fail1";
    } else if (!reg.test(password.val())) {
        return "fail2";
    } else if (checkPassword.val().trim() === '') {
        return "fail3";
    } else if (password.val() !== checkPassword.val()) {
        return "fail4";
    }
}

function validationEmail(email, emailToken) {
    if (email.val().trim() == '') {
        return "fail1";
    } else if (emailToken.val() === 'fail') {
        return "fail2";
    }
}

function validationBirth(birth) {
    if (birth.val().trim() === '') {
        return false;
    }
}