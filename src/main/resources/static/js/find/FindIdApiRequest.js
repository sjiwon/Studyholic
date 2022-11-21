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

    if (email.val().trim() === '') {
        let emailValidationFailHtml = (navigator.language === 'ko')
            ? ('<b>이메일을 다시 확인해주세요</b><br><small>- 빈 값입니다</small>')
            : ('<b>Please check your email again</b><br><small>- Empty value exists</small>');

        ToastResponse.fire({
            html: emailValidationFailHtml,
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

    let question = (navigator.language === 'ko')
        ? ('<b>' + email.val() + '</b>이 본인 소유의 이메일이 맞습니까?')
        : ('Is <b>' + email.val() + '</b> your own email?');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'resource': 'id',
                'email': email.val()
            };

            axios.post('/api/email/authenticate', data)
                .then(response => {
                    $('#checkEmail').css("display", "block");
                    $('#explainEmailCheck').show();
                    email.attr("readonly", true);

                    const emailCode = response['data'];
                    emailVerificationButton.attr("disabled", true);
                    checkEmailConfirm(emailCode);
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '인증내역이 없는 이메일입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('This email has no verification history');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server while sending the email');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    });
                });
        }
    })
}

// 2. 이메일 인증 프로세스
function checkEmailConfirm(emailCode) {
    let checkEmail = $('#checkEmail');
    let explainEmailCheck = $('#explainEmailCheck');

    checkEmail.on('keyup', () => {
        let findIdButton = $('#findIdButton');

        if (checkEmail.val() !== emailCode) {
            checkEmail.css({
                "border-color": "#FA3E3E",
                "border": "2px solid",
                "color": "#FA3E3E",
                "font-size": "15px"
            })

            explainEmailCheck.css({
                "color": "#FA3E3E",
                "font-size": "13px"
            })
            let failHtml = (navigator.language === 'ko')
                ? ('인증번호가 일치하지 않습니다')
                : ('Verification number does not match');
            explainEmailCheck.html(failHtml);

            $('#emailAuthenticationToken').val('fail');
            findIdButton.attr("disabled", true);
        } else {
            checkEmail.css({
                "border-color": "#0D6EFD",
                "border": "2px solid",
                "color": "#0D6EFD",
                "font-size": "15px"
            })

            explainEmailCheck.css({
                "color": "#0D6EFD",
                "font-size": "13px"
            })
            let successHtml = (navigator.language === 'ko')
                ? ('인증번호가 일치합니다')
                : ('Verification number matches');
            explainEmailCheck.html(successHtml);

            $('#emailAuthenticationToken').val('success');
            findIdButton.attr("disabled", false);
        }
    })
}