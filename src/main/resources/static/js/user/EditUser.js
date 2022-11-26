// 1. 기본 이미지로 변경
function editProfileToDefaultImage() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('기본 이미지로 변경하시겠습니까?')
        : ('Do you want to change to default image?');

    ToastApi.fire({
        text: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let userId = $('#userId');

            axios.patch('/api/user/change-default-profile?userId=' + userId.val())
                .then(() => {
                    let successText = (navigator.language === 'ko')
                        ? ('프로필 변경이 완료되었습니다')
                        : ('Your profile change is complete');

                    ToastResponse.fire({
                        text: successText,
                        icon: 'success'
                    }).then(() => {
                        location.href = '/mypage';
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '로그인이 필요합니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('Login is required');
                    } else if (jsonData['message'] === '잘못된 접근입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('The wrong approach');
                    } else if (jsonData['message'] === '사용자 정보가 존재하지 않습니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('User information does not exist');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    });
                });
        }
    });
}

// 2. 커스텀 이미지로 변경
function editProfileToCustomImage() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    let userId = $('#userId');
    let image = document.getElementById('image');

    if (image.files.length === 0) {
        let fileUploadFailText = (navigator.language === 'ko')
            ? ('파일을 업로드하고 변경 버튼을 눌러주세요')
            : ('Upload the file and press the change button');

        ToastResponse.fire({
            text: fileUploadFailText,
            icon: 'warning'
        })
        return false;
    }

    let question = (navigator.language === 'ko')
        ? ('선택하신 이미지로 변경하시겠습니까?')
        : ('Are you sure you want to change to the selected image?');

    ToastApi.fire({
        text: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let formData = new FormData();
            formData.append('userId', userId.val());
            formData.append('profile', image.files[0]);

            let fileExistsAxiosConfig = {
                headers: {
                    "Content-Type": "multipart/form-data",
                }
            };

            axios.patch('/api/user/change-profile', formData, fileExistsAxiosConfig)
                .then(() => {
                    let successText = (navigator.language === 'ko')
                        ? ('프로필 변경이 완료되었습니다')
                        : ('Your profile change is complete');

                    ToastResponse.fire({
                        text: successText,
                        icon: 'success'
                    }).then(() => {
                        location.href = '/mypage';
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '로그인이 필요합니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('Login is required');
                    } else if (jsonData['message'] === '잘못된 접근입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('The wrong approach');
                    } else if (jsonData['message'] === '사용자 정보가 존재하지 않습니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('User information does not exist');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    })
                });
        }
    });
}

// 3-1. 닉네임 변경 버튼 활성화
function editNicknameButtonEnable() {
    let nickname = $('#nickname');
    let nicknameEditButton = $('#nicknameEditButton');

    nickname.attr('readonly', false);
    if (navigator.language === 'ko') {
        nicknameEditButton.html('완료');
    } else {
        nicknameEditButton.html('Complete');
    }
    nicknameEditButton.removeAttr('onclick');
    nicknameEditButton.attr('onclick', 'editNicknameProcess()');
}

// 3-2. 닉네임 변경 프로세스
function editNicknameProcess() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    let userId = $('#userId');
    let nickname = $('#nickname');

    if (nickname.val().trim() === '') {
        let nicknameValidationFailText = (navigator.language === 'ko')
            ? ('변경할 닉네임을 입력해주세요')
            : ('Please enter a nickname to change');

        ToastResponse.fire({
            text: nicknameValidationFailText,
            icon: 'warning'
        })
        return false;
    }

    let question = (navigator.language === 'ko')
        ? ('해당 닉네임으로 변경하시겠습니까?<br><b style="font-size: 20px;">-> ' + nickname.val() + '</b>')
        : ('Would you like to change to that nickname?<br><b style="font-size: 20px;">-> ' + nickname.val() + '</b>');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'userId': userId.val(),
                'nickname': nickname.val()
            };

            axios.patch('/api/user/change-nickname', data)
                .then(() => {
                    let successText = (navigator.language === 'ko')
                        ? ('닉네임 변경이 완료되었습니다')
                        : ('Nickname change completed');

                    ToastResponse.fire({
                        text: successText,
                        icon: 'success'
                    }).then(() => {
                        location.href = '/mypage';
                    })
                })
                .catch(error => {
                    let jsonData = error.response.data;

                    let errorText;
                    if (jsonData['message'] === '로그인이 필요합니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('Login is required');
                    } else if (jsonData['message'] === '잘못된 접근입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('The wrong approach');
                    } else if (jsonData['message'] === '사용자 정보가 존재하지 않습니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('User information does not exist');
                    } else if (jsonData['message'] === '이전과 동일한 닉네임은 사용 불가능합니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('You cannot use the same nickname as before');
                    } else if (jsonData['message'] === '중복된 닉네임입니다') {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('This is a duplicate nickname');
                    } else {
                        errorText = (navigator.language === 'ko')
                            ? (jsonData['message'])
                            : ('An error occurred on the server');
                    }

                    ToastResponse.fire({
                        color: '#FF0000',
                        text: errorText,
                        icon: 'error'
                    });
                });
        }
    });
}

// 4. 비밀번호 재설정 페이지로 이동
function moveToEditPasswordPage() {
    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    let question = (navigator.language === 'ko')
        ? ('비밀번호 변경 페이지로 이동하시겠습니까?')
        : ('Would you like to go to the password change page?');

    ToastApi.fire({
        html: question,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            location.href = "/change-password";
        }
    });
}