function login() {
    let loginId = $('#loginId');
    let loginPassword = $('#loginPassword');

    let data = {
        'loginId': loginId.val(),
        'loginPassword': loginPassword.val()
    }

    axios.post('/api/login', data)
        .then(() => {
            alert('로그인에 성공하였습니다');
            location.replace("/");
        })
        .catch(error => {
            let errorStatus = error.response.status;
            let jsonData = error.response.data;

            if (errorStatus === 401) {
                loginPassword.val('').focus();
                alert(jsonData['message']);
            } else if (errorStatus === 404) {
                loginPassword.val('');
                loginId.val('').focus();
                alert(jsonData['message']);
            }
        });
}