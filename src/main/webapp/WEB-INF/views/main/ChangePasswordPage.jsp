<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/find/ResetPasswordInputTracking.js"/>"></script>
    <script src="<c:url value="/js/find/ResetPasswordProcess.js"/>"></script>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container">
    <h1 style="text-align: center">비밀번호 재설정</h1>

    <div class="col-md-4 row mx-auto">
        <hr>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="currentPassword" class="form-label">현재 비밀번호</label>
            <input type="password" class="form-control" id="currentPassword" onkeyup="trackingCurrentPassword()" required>
            <input type="hidden" value="fail" id="currentPasswordToken"/> <%-- 현재 비밀번호 기입 여부 체크 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="changePassword" class="form-label">변경할 비밀번호</label>
            <input type="password" class="form-control" id="changePassword" onkeyup="trackingChangePassword()" required>
            <span id="explainPasswordRegExp" style="font-size: 13px; display: none; margin-top: 10px;"></span> <%-- 비밀번호 정규식 만족 여부 텍스트 --%>
            <input type="hidden" value="fail" id="changePasswordToken"/> <%-- 이름 기입 여부 체크 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="checkChangePassword" class="form-label">변경할 비밀번호 확인</label>
            <input type="password" class="form-control" id="checkChangePassword" onkeyup="trackingChangePasswordCheck()" required>
            <span id="explainPasswordCheck" style="font-size: 13px; display: none; margin-top: 10px;"></span> <%-- 비밀번호 일치 여부 텍스트 --%>
            <input type="hidden" value="fail" id="checkChangePasswordVerificationToken"/> <%-- 이름 기입 여부 체크 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <button id="findPasswordButton" class="btn btn-primary btn-lg btn-block" type="button" onclick="changePassword('${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id}')">비밀번호 변경</button>
    </div>
    <br>
</div>
</body>
</html>
