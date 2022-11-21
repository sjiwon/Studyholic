function logout() {
    const ToastApi = Swal.mixin({
        confirmButtonText: '네',
        showCancelButton: true,
        cancelButtonText: '아니요',
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