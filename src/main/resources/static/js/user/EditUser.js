// 1. 기본 이미지로 변경
function editProfileToDefaultImage() {
    let userId = $('#userId');

    let select = confirm('기본 이미지로 변경하시겠습니까?');
    if (select) {
        axios.patch('/api/user/change-default-profile?userId=' + userId.val())
            .then(() => {
                alert('프로필 변경이 완료되었습니다');
                location.href = '/user/' + userId.val();
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else {
        return false;
    }
}

// 2. 커스텀 이미지로 변경
function editProfileToCustomImage() {
    let userId = $('#userId');
    let image = document.getElementById('image');

    if (image.files.length === 0) {
        alert('파일을 업로드하고 변경 버튼을 눌러주세요');
        return false;
    }


    let select = confirm('선택하신 이미지로 변경하시겠습니까?');
    if (select) {
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
                alert('프로필 변경이 완료되었습니다');
                location.href = '/user/' + userId.val();
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else {
        return false;
    }
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
    let userId = $('#userId');
    let nickname = $('#nickname');

    if (nickname.val().trim() === '') {
        alert('변경할 닉네임을 입력해주세요');
        return false;
    }

    let select = confirm('[' + nickname.val() + ']로 변경하시겠습니까?');
    if (select) {
        let data = {
            'userId': userId.val(),
            'nickname': nickname.val()
        };

        axios.patch('/api/user/change-nickname', data)
            .then(() => {
                alert('닉네임 변경이 완료되었습니다');
                location.href = '/user/' + userId.val();
            })
            .catch(error => {
                let jsonData = error.response.data;
                alert(jsonData['message']);
            });
    } else {
        return false;
    }
}

// 4. 비밀번호 재설정 페이지로 이동
function moveToEditPasswordPage() {
    let check = confirm('비밀번호 변경 페이지로 이동하시겠습니까?');

    if (check) {
        location.href = "/change-password";
    } else {
        return false;
    }
}