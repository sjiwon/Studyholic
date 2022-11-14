function login() {
    let id = $('#id');
    let password = $('#password');

    let data = {
        'loginId': id.val(),
        'loginPassword': password.val()
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
                password.val('').focus();
                alert(jsonData['message']);
            } else if (errorStatus === 404) {
                password.val('');
                id.val('').focus();
                alert(jsonData['message']);
            }
        });
}