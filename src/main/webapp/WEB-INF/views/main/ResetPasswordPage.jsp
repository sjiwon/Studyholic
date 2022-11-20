<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/find/ResetPasswordApiRequest.js"/>"></script>
    <script src="<c:url value="/js/find/ResetPasswordProcess.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/css/input.css"/>"/>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container">
    <h1 style="text-align: center">비밀번호 임시 발급</h1>

    <div class="col-md-4 row mx-auto">
        <hr>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="name" class="form-label">이름</label>
            <input type="text" class="form-control custom-input" id="name" style="border-radius: 15px;" required>
            <input type="hidden" value="fail" id="nameToken"/> <%-- 이름 기입 여부 체크 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="loginId" class="form-label">아이디</label>
            <input type="text" class="form-control custom-input" id="loginId" style="border-radius: 15px;" required>
            <input type="hidden" value="fail" id="loginIdToken"/> <%-- 아이디 기입 여부 체크 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="email" class="form-label">이메일</label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="text" class="form-control custom-input" id="email" style="border-radius: 15px;" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="emailVerificationButton" onclick="enableEmailVerificationInResetPasswordProcess()">이메일 인증</button>
        </div>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-6">
            <input type="text" class="form-control" id="checkEmail" placeholder="인증 번호를 입력해주세요" style="border-radius: 15px; display: none" required/>

            <div style="margin-top: 5px;">
                <span id="explainEmailCheck" style="font-size: 13px; display: none;">
                        인증번호를 전송하였습니다.<br>
                        이메일을 확인해주세요.
                </span>
            </div>
        </div>
        <input type="hidden" value="fail" id="emailAuthenticationToken"/> <%-- 이메일 인증 완료 여부 체크 --%>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <button id="ResetPasswordButton" disabled class="btn btn-primary btn-lg btn-block" type="button" onclick="userVericiationAndApplyRandomPassword()">사용자 인증 후 임시 비밀번호 발급</button>
    </div>
    <br>
</div>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
</html>
