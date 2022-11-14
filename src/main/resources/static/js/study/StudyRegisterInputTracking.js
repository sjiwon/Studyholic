// 1. 스터디 이름 Tracking
function trackingStudyName() {
    let studyName = $('#studyName');
    let studyNameDuplicateCheckButton = $('#studyNameDuplicateCheckButton');
    let studyNameVerificationToken = $('#studyNameVerificationToken');

    studyName.css({
        "border-color": "",
        "border": "",
        "color": "",
        "font-size": ""
    });
    studyNameDuplicateCheckButton.attr("disabled", false);
    studyNameVerificationToken.val('fail');
}

// 2. 스터디 간단 설명 Tracking
function trackingStudyBriefDescription() {
    let studyBriefDescription = $('#studyBriefDescription');
    let studyBriefDescriptionVerificationToken = $('#studyBriefDescriptionVerificationToken');

    if (studyBriefDescription.val().trim() !== '') { // 존재
        studyBriefDescription.css({
            "border-color": "#0D6EFD",
            "border": "2px solid",
            "color": "#0D6EFD",
            "font-size": "15px"
        });
        studyBriefDescriptionVerificationToken.val('success');
    } else { // 공백
        studyBriefDescription.css({
            "border-color": "",
            "border": "",
            "color": "",
            "font-size": ""
        });
        studyBriefDescriptionVerificationToken.val('fail');
    }
}