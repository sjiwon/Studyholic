// 1. 아이디 인증 이메일 인증 버튼 활성화
function enableEmailVerificationInFindIdProcess() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let name = $('#name');
    let email = $('#email');
    let emailVerificationButton = $('#emailVerificationButton');

    if (name.val().trim() === '') {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '이름을 다시 확인해주세요',
            icon: 'warning'
        }).then(() => {
            name.focus();
        })
        return false;
    }

    if (email.val().trim() === '') {
        ToastResponse.fire({
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
            text: '이메일을 입력해주세요',
            icon: 'warning'
        }).then(() => {
            email.focus();
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
        html: '<b>' + email.val() + '</b>이 본인 소유의 이메일이 맞습니까?',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'resource': 'id',
                'email': email.val()
            };

            axios.post('/api/email/authenticate', data)
                .then(response => {
                    $('#checkEmail').prop('disabled', false);
                    $('#explainEmailCheck').show();

                    email.attr("readonly", true);
                    email.css({
                        "border-color": "#0D6EFD",
                        "border": "2px solid",
                        "color": "#0D6EFD",
                        "font-size": "15px"
                    })

                    const emailCode = response['data'];
                    emailVerificationButton.attr("disabled", true);
                    checkEmailConfirm(emailCode);
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    ToastResponse.fire({
                        showConfirmButton: false,
                        timer: 1000,
                        timerProgressBar: true,
                        color: '#FF0000',
                        text: jsonData['message'],
                        icon: 'error'
                    })
                });
        }
    })
}

// 2. 이메일 인증 프로세스
function checkEmailConfirm(emailCode) {
    let checkEmail = $('#checkEmail');
    let explainEmailCheck = $('#explainEmailCheck');

    checkEmail.on('keyup', () => {
        let emailVerificationToken = $('#emailVerificationToken');
        let findIdButton = $('#findIdButton');

        if (checkEmail.val() !== emailCode) {
            checkEmail.css({
                "border-color": "#FA3E3E",
                "border": "2px solid",
                "color": "#FA3E3E",
                "font-size": "15px"
            })

            explainEmailCheck.html('인증번호가 일치하지 않습니다');
            explainEmailCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })

            emailVerificationToken.val('fail');
            findIdButton.attr("disabled", true);
        } else {
            checkEmail.css({
                "border-color": "#0D6EFD",
                "border": "2px solid",
                "color": "#0D6EFD",
                "font-size": "15px"
            })

            explainEmailCheck.html('인증번호가 일치합니다');
            explainEmailCheck.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })

            emailVerificationToken.val('success');
            findIdButton.attr("disabled", false);
        }
    })
}