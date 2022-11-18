<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/find/FindInfoInputTracking.js"/>"></script>
    <script src="<c:url value="/js/find/FindIdApiRequest.js"/>"></script>
    <script src="<c:url value="/js/find/FindIdProcess.js"/>"></script>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container">
    <h1 style="text-align: center">아이디 찾기</h1>

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
        <label for="email" class="form-label">이메일</label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="text" class="form-control" id="email" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="emailVerificationButton" onclick="enableEmailVerificationInFindIdProcess()">이메일 인증</button>
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
        <button id="findIdButton" disabled class="btn btn-primary btn-lg btn-block" type="button" onclick="findIdProcess()">아이디 확인하기</button>
    </div>
    <br>
</div>
</body>
</html>
