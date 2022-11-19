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

    ToastApi.fire({
        text: '로그아웃 하시겠습니까?',
        icon: 'question',
    }).then((result) => {
        if (result.isConfirmed) {
            axios.post('/api/logout')
                .then(() => {
                    ToastResponse.fire({
                        text: '로그아웃이 완료되었습니다',
                        icon: 'success'
                    }).then(() => {
                        location.replace('/');
                    })
                });
        }
    });
}