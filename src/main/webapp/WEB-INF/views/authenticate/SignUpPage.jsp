<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="title"/></title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/signup/SignUpInputTracking.js"/>"></script>
    <script src="<c:url value="/js/signup/SignUpApiRequest.js"/>"></script>
    <script src="<c:url value="/js/signup/SignUpIntegrateProcess.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/css/input.css"/>"/>
</head>
<body>
<jsp:include page="../fragment/SignUpHeader.jsp"/>

<div class="container">
    <h1 style="text-align: center"><spring:message code="signup.page.title"/></h1>

    <div class="col-md-4 row mx-auto">
        <hr>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="name" class="form-label"><spring:message code="signup.page.name"/></label>
            <input type="text" class="form-control custom-input" id="name" style="border-radius: 15px;" required>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="nickname" class="form-label"><spring:message code="signup.page.nickname"/></label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="text" class="form-control custom-input" id="nickname" onkeyup="nicknameTracking()" style="border-radius: 15px;" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="nicknameVerificationButton" onclick="nicknameDuplicateCheckApi()"><spring:message code="signup.page.duplicate.axios"/></button>
        </div>
        <input type="hidden" value="fail" id="nicknameVerificationToken"/> <%-- 닉네임 중복 체크 성공 여부 체크 --%>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="loginId" class="form-label"><spring:message code="signup.page.id"/></label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="text" class="form-control custom-input" id="loginId" onkeyup="loginIdTracking()" style="border-radius: 15px;" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="idVerificationButton" onclick="idDuplicateCheckApi()"><spring:message code="signup.page.duplicate.axios"/></button>
        </div>
        <input type="hidden" value="fail" id="idVerificationToken"/> <%-- 아이디 중복 체크 성공 여부 체크 --%>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="loginPassword" class="form-label"><spring:message code="signup.page.password"/></label>
            <input type="password" class="form-control custom-input" id="loginPassword" onkeyup="trackingPasswordRegExp()" style="border-radius: 15px;" required>
            <span id="explainPasswordRegExp" style="font-size: 13px; display: none; margin-top: 10px;"></span> <%-- 비밀번호 정규식 만족 여부 텍스트 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="checkPassword" class="form-label"><spring:message code="signup.page.password.check"/></label>
            <input type="password" class="form-control custom-input" id="checkPassword" onkeyup="trackingPasswordEquals()" style="border-radius: 15px;" required>
            <span id="explainPasswordCheck" style="font-size: 13px; display: none; margin-top: 10px;"></span> <%-- 비밀번호 일치 여부 텍스트 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="email" class="form-label"><spring:message code="signup.page.email"/></label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="email" class="form-control custom-input" id="email" style="border-radius: 15px;" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="emailVerificationButton" onclick="enableEmailVerificationWithSignUp()"><spring:message code="signup.page.email.authenticate"/></button>
        </div>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-8">
            <input type="text" class="form-control" id="checkEmail" placeholder="<spring:message code="signup.page.email.code.input"/>" style="border-radius: 15px; display: none" required/>

            <div style="margin-top: 5px;">
                <span id="explainEmailCheck" style="font-size: 13px; display: none;"><spring:message code="signup.page.email.process.message"/></span>
            </div>
        </div>
        <input type="hidden" value="fail" id="emailAuthenticationToken"/> <%-- 이메일 인증 완료 여부 체크 --%>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="birth" class="form-label"><spring:message code="signup.page.birth"/></label>
            <input type="date" class="form-control" id="birth" style="border-radius: 15px;" required>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="image" class="form-label"><spring:message code="signup.page.profile"/></label>
            <input class="form-control" type="file" id="image" style="border-radius: 15px;">
            <div style="margin-top: 5px;">
                <span style="font-size: 13px;"><spring:message code="signup.page.profile.replace"/></span>
            </div>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <button class="btn btn-primary btn-lg btn-block" type="button" onclick="signUpProcess()"><spring:message code="signup.page.signup"/></button>
    </div>
    <br>

</div>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
</html>
