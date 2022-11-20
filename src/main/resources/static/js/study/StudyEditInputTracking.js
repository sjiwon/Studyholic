// 1. 스터디 이름 Tracking
function trackingStudyName(originStudyName) {
    let studyName = $('#studyName');
    let studyNameDuplicateCheckButton = $('#studyNameDuplicateCheckButton');
    let studyNameVerificationToken = $('#studyNameVerificationToken');

    if (originStudyName === studyName.val()) {
        studyNameDuplicateCheckButton.attr("disabled", true);
        studyNameVerificationToken.val('success');
    } else {
        studyNameDuplicateCheckButton.attr("disabled", false);
        studyNameVerificationToken.val('fail');
    }
}