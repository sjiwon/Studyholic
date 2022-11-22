<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="title"/></title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/find/ChangePasswordInputTracking.js"/>"></script>
    <script src="<c:url value="/js/find/ChangePasswordProcess.js"/>"></script>
    <link rel="stylesheet" href=<c:url value="/css/input.css"/>/>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container">
    <h1 style="text-align: center"><spring:message code="change.password.title"/></h1>

    <div class="col-md-4 row mx-auto">
        <hr>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="currentPassword" class="form-label"><spring:message code="change.password.current.password"/></label>
            <input type="password" class="form-control custom-input" id="currentPassword" style="border-radius: 15px;" required>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="changePassword" class="form-label"><spring:message code="change.password.change.password"/></label>
            <input type="password" class="form-control custom-input" id="changePassword" onkeyup="trackingChangePassword()" style="border-radius: 15px;" required>
            <span id="explainPasswordRegExp" style="font-size: 13px; display: none; margin-top: 10px;"></span> <%-- 비밀번호 정규식 만족 여부 텍스트 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="checkChangePassword" class="form-label"><spring:message code="change.password.change.password.check"/></label>
            <input type="password" class="form-control custom-input" id="checkChangePassword" onkeyup="trackingChangePasswordCheck()" style="border-radius: 15px;" required>
            <span id="explainPasswordCheck" style="font-size: 13px; display: none; margin-top: 10px;"></span> <%-- 비밀번호 일치 여부 텍스트 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <button id="findPasswordButton" class="btn btn-primary btn-lg btn-block" type="button" onclick="changePasswordProcess('${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id}')">
            <spring:message code="change.password.process"/>
        </button>
    </div>
    <br>
</div>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
</html>
