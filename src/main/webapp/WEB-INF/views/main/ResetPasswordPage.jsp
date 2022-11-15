<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/find/FindInformationInputTracking.js"/>"></script>
    <script src="<c:url value="/js/find/FindInformationEmailAuthenticationApiRequest.js"/>"></script>
    <script src="<c:url value="/js/find/FindPasswordProcess.js"/>"></script>
</head>
<body>
<jsp:include page="../fragment/AnonymousHeader.jsp"/>

<div class="container">
    <h1 style="text-align: center">비밀번호 임시 발급</h1>

    <div class="col-md-4 row mx-auto">
        <hr>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-8">
            <label for="name" class="form-label">이름</label>
            <input type="text" class="form-control" id="name" onkeyup="trackingNameInFindProcess()" required>
            <input type="hidden" value="fail" id="nameToken"/> <%-- 이름 기입 여부 체크 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-8">
            <label for="loginId" class="form-label">아이디</label>
            <input type="text" class="form-control" id="loginId" onkeyup="trackingIdInFindProcess()" required>
            <input type="hidden" value="fail" id="loginIdToken"/> <%-- 이름 기입 여부 체크 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="email" class="form-label">이메일</label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="text" class="form-control" id="email" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="emailVerificationButton" onclick="enableEmailVerification('password')">이메일 인증</button>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-10">
            <input type="text" class="form-control" id="checkEmail" placeholder="인증 번호를 입력해주세요" disabled required/>

            <div style="margin-top: 5px;">
                <span id="explainEmailCheck" style="font-size: 13px; display: none;">
                        인증번호를 전송하였습니다.<br>
                        이메일을 확인해주세요.
                </span>
            </div>
        </div>
        <input type="hidden" value="fail" id="emailVerificationToken"/> <%-- 이메일 인증 완료 여부 체크 --%>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <button id="findPasswordButton" disabled class="btn btn-primary btn-lg btn-block" type="button" onclick="userVericiationAndApplyRandomPassword()">사용자 인증 후 임시 비밀번호 발급</button>
    </div>
    <br>
</div>
</body>
</html>
