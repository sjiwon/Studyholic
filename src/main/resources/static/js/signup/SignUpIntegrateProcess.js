// 최종 회원가입 진행 Process
function signUpProcess() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let name = $('#name');
    if (validationName(name) === false) {
        ToastResponse.fire({
            html: '<b>이름을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            name.focus();
        })
        return false;
    }

    let nickname = $('#nickname');
    let nicknameVerificationToken = $('#nicknameVerificationToken');
    if (validationNickname(nickname, nicknameVerificationToken) === 'fail1') {
        ToastResponse.fire({
            html: '<b>닉네임을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            nickname.focus();
        })
        return false;
    } else if (validationNickname(nickname, nicknameVerificationToken) === 'fail2') {
        ToastResponse.fire({
            html: '<b>닉네임 중복 체크를 진행해주세요</b>',
            icon: 'warning'
        })
        return false;
    }

    let loginId = $('#loginId');
    let idVerificationToken = $('#idVerificationToken');
    if (validationLoginId(loginId, idVerificationToken) === 'fail1') {
        ToastResponse.fire({
            html: '<b>아이디를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            loginId.focus();
        })
        return false;
    } else if (validationLoginId(loginId, idVerificationToken) === 'fail2') {
        ToastResponse.fire({
            html: '<b>아이디 중복 체크를 진행해주세요</b>',
            icon: 'warning'
        })
        return false;
    }

    let loginPassword = $('#loginPassword');
    let checkPassword = $('#checkPassword');
    if (validationPassword(loginPassword, checkPassword) === 'fail1') {
        ToastResponse.fire({
            html: '<b>비밀번호를 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            loginPassword.focus();
        })
        return false;
    } else if (validationPassword(loginPassword, checkPassword) === 'fail2') {
        ToastResponse.fire({
            html: '<b>비밀번호 확인란을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            checkPassword.focus();
        })
        return false;
    } else if (validationPassword(loginPassword, checkPassword) === 'fail3') {
        ToastResponse.fire({
            html: '<b>비밀번호와 확인란이 일치하지 않습니다</b>',
            icon: 'warning'
        })
        return false;
    }

    let email = $('#email');
    let emailAuthenticationToken = $('#emailAuthenticationToken');
    if (validationEmail(email, emailAuthenticationToken) === 'fail1') {
        ToastResponse.fire({
            html: '<b>이메일을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
            icon: 'warning'
        }).then(() => {
            email.focus();
        })
        return false;
    } else if (validationEmail(email, emailAuthenticationToken) === 'fail2') {
        ToastResponse.fire({
            html: '<b>이메일 인증을 진행해주세요</b>',
            icon: 'warning'
        })
        return false;
    }

    let birth = $('#birth');
    if (validationBirth(birth) === false) {
        ToastResponse.fire({
            html: '<b>생년월일을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>',
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

    ToastApi.fire({
        text: '회원가입을 진행하시겠습니까?',
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
                        ToastResponse.fire({
                            text: '회원가입에 성공하였습니다',
                            icon: 'success'
                        }).then(() => {
                            location.href = '/';
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
            } else { // 이미지 업로드
                axios.post('/api/user', formData, fileExistsAxiosConfig)
                    .then(() => {
                        ToastResponse.fire({
                            text: '회원가입에 성공하였습니다',
                            icon: 'success'
                        }).then(() => {
                            location.href = '/';
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
            }
        }
    });
}

function validationName(name) {
    if (nameToken.val().trim() === '') {
        return false;
    }
}

function validationNickname(nickname, nicknameToken) {
    if (nickname.val.trim() == '') {
        return "fail1";
    } else if (nicknameToken.val() === 'fail') {
        return "fail2";
    }
}

function validationLoginId(loginId, loginIdToken) {
    if (loginId.val.trim() == '') {
        return "fail1";
    } else if (loginIdToken.val() === 'fail') {
        return "fail2";
    }
}

function validationPassword(password, checkPassword) {
    if (password.val().trim() === '') {
        return "fail1";
    } else if (checkPassword.val().trim() === '') {
        return "fail2";
    } else if (password.val() !== checkPassword.val()) {
        return "fail3";
    }
}

function validationEmail(email, emailToken) {
    if (email.val.trim() == '') {
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