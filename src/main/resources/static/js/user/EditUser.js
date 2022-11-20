// 1. 기본 이미지로 변경
function editProfileToDefaultImage() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    let userId = $('#userId');

    ToastApi.fire({
        text: '기본 이미지로 변경하시겠습니까?',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            axios.patch('/api/user/change-default-profile?userId=' + userId.val())
                .then(() => {
                    ToastResponse.fire({
                        text: '프로필 변경이 완료되었습니다',
                        icon: 'success'
                    }).then(() => {
                        location.href = '/user/' + userId.val();
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
    });
}

// 2. 커스텀 이미지로 변경
function editProfileToCustomImage() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    let userId = $('#userId');
    let image = document.getElementById('image');

    if (image.files.length === 0) {
        ToastResponse.fire({
            text: '파일을 업로드하고 변경 버튼을 눌러주세요',
            icon: 'warning'
        })
        return false;
    }

    ToastApi.fire({
        text: '선택하신 이미지로 변경하시겠습니까?',
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
                    ToastResponse.fire({
                        text: '프로필 변경이 완료되었습니다',
                        icon: 'success'
                    }).then(() => {
                        location.href = '/user/' + userId.val();
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
    });
}

// 3-1. 닉네임 변경 버튼 활성화
function editNicknameButtonEnable() {
    let nickname = $('#nickname');
    let nicknameEditButton = $('#nicknameEditButton');

    nickname.attr('readonly', false);
    nicknameEditButton.html('완료');
    nicknameEditButton.removeAttr('onclick');
    nicknameEditButton.attr('onclick', 'editNicknameProcess()');
}

// 3-2. 닉네임 변경 프로세스
function editNicknameProcess() {
    const ToastResponse = Swal.mixin({
        focusConfirm: false,
        returnFocus: false
    });

    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    let userId = $('#userId');
    let nickname = $('#nickname');

    if (nickname.val().trim() === '') {
        ToastResponse.fire({
            text: '변경할 닉네임을 입력해주세요',
            icon: 'warning'
        })
        return false;
    }

    ToastApi.fire({
        html: '해당 닉네임으로 변경하시겠습니까?<br><b style="font-size: 20px;">-> ' + nickname.val() + '</b>',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            let data = {
                'userId': userId.val(),
                'nickname': nickname.val()
            };

            axios.patch('/api/user/change-nickname', data)
                .then(() => {
                    ToastResponse.fire({
                        text: '닉네임 변경이 완료되었습니다',
                        icon: 'success'
                    }).then(() => {
                        location.href = '/user/' + userId.val();
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
    });
}

// 4. 비밀번호 재설정 페이지로 이동
function moveToEditPasswordPage() {
    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
        focusConfirm: false
    });

    ToastApi.fire({
        html: '비밀번호 변경 페이지로 이동하시겠습니까?',
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            location.href = "/change-password";
        }
    });
}