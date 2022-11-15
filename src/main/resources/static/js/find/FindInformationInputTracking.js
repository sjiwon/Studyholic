// Common - 이름 기입 Tracking
function trackingNameInFindProcess() {
    let name = $('#name');
    let nameToken = $('#nameToken');

    if (name.val().trim() !== '') {
        name.css({
            "border-color": "#0D6EFD",
            "border": "2px solid",
            "color": "#0D6EFD",
            "font-size": "15px"
        });

        nameToken.val('success');
    } else {
        name.css({
            "border-color": "",
            "border": "",
            "color": "",
            "font-weight": "",
            "font-size": ""
        });

        nameToken.val('fail');
    }
}