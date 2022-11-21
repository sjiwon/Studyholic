function logout() {
    let yes = (navigator.language === 'ko') ? ('네') : ('Yes');
    let no = (navigator.language === 'ko') ? ('아니요') : ('No');
    const ToastApi = Swal.mixin({
        confirmButtonText: yes,
        showCancelButton: true,
        cancelButtonText: no,
        focusConfirm: false
    });

    const ToastResponse = Swal.mixin({
        showConfirmButton: false,
        timer: 1000,
        timerProgressBar: true
    });

    let logoutQuestion = (navigator.language === 'ko')
        ? ('로그아웃 하시겠습니까?')
        : ('Are you sure you want to logout?');

    ToastApi.fire({
        text: logoutQuestion,
        icon: 'question',
    }).then((result) => {
        if (result.isConfirmed) {
            axios.post('/api/logout')
                .then(() => {
                    let successText = (navigator.language === 'ko')
                        ? ('로그아웃이 완료되었습니다')
                        : ('Logout Complete');
                    ToastResponse.fire({
                        text: successText,
                        icon: 'success'
                    }).then(() => {
                        location.replace('/');
                    })
                });
        }
    });
}