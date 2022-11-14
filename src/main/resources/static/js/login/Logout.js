function logout() {
    let process = confirm("로그아웃 하시겠습니까?");
    if (process) {
        axios.post('/api/logout')
            .then(() => {
                alert('로그아웃이 완료되었습니다');
                location.replace('/');
            });
    } else {
        return false;
    }
}