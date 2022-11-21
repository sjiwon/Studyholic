<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="title"/></title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/find/FindIdApiRequest.js"/>"></script>
    <script src="<c:url value="/js/find/FindIdProcess.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/css/input.css"/>"/>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container">
    <h1 style="text-align: center"><spring:message code="find.id.title"/></h1>

    <div class="col-md-4 row mx-auto">
        <hr>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="name" class="form-label"><spring:message code="find.id.name"/></label>
            <input type="text" class="form-control custom-input" id="name" style="border-radius: 15px;" required>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="email" class="form-label"><spring:message code="find.id.email"/></label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="text" class="form-control custom-input" id="email" style="border-radius: 15px;" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="emailVerificationButton" onclick="enableEmailVerificationInFindIdProcess()"><spring:message code="find.id.email.authenticate"/></button>
        </div>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-8">
            <input type="text" class="form-control" id="checkEmail" placeholder="<spring:message code="find.id.email.code.input"/>" style="border-radius: 15px; display: none" required/>

            <div style="margin-top: 5px;">
                <span id="explainEmailCheck" style="font-size: 13px; display: none;"><spring:message code="find.id.email.process.message"/></span>
            </div>
        </div>
        <input type="hidden" value="fail" id="emailAuthenticationToken"/> <%-- 이메일 인증 완료 여부 체크 --%>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <button id="findIdButton" disabled class="btn btn-primary btn-lg btn-block" type="button" onclick="findIdProcess()"><spring:message code="find.id.process"/></button>
    </div>
    <br>
</div>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
</html>
