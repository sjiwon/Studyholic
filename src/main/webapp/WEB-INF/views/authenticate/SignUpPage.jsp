<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/signup/SignUpInputTracking.js"/>"></script>
    <script src="<c:url value="/js/signup/SignUpApiRequest.js"/>"></script>
    <script src="<c:url value="/js/signup/SignUpIntegrateProcess.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/css/input.css"/>"/>
</head>
<body>
<jsp:include page="../fragment/SignUpHeader.jsp"/>

<div class="container">
    <h1 style="text-align: center">회원가입</h1>

    <div class="col-md-4 row mx-auto">
        <hr>
    </div>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="name" class="form-label">이름</label>
            <input type="text" class="form-control custom-input" id="name" style="border-radius: 15px;" required>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="nickname" class="form-label">닉네임</label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="text" class="form-control custom-input" id="nickname" onkeyup="nicknameTracking()" style="border-radius: 15px;" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="nicknameVerificationButton" onclick="nicknameDuplicateCheckApi()">중복 체크</button>
        </div>
        <input type="hidden" value="fail" id="nicknameVerificationToken"/> <%-- 닉네임 중복 체크 성공 여부 체크 --%>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="loginId" class="form-label">아이디</label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="text" class="form-control custom-input" id="loginId" onkeyup="loginIdTracking()" style="border-radius: 15px;" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="idVerificationButton" onclick="idDuplicateCheckApi()">중복 체크</button>
        </div>
        <input type="hidden" value="fail" id="idVerificationToken"/> <%-- 아이디 중복 체크 성공 여부 체크 --%>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="loginPassword" class="form-label">비밀번호</label>
            <input type="password" class="form-control custom-input" id="loginPassword" onkeyup="trackingPasswordRegExp()" style="border-radius: 15px;" required>
            <span id="explainPasswordRegExp" style="font-size: 13px; display: none; margin-top: 10px;"></span> <%-- 비밀번호 정규식 만족 여부 텍스트 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="checkPassword" class="form-label">비밀번호 확인</label>
            <input type="password" class="form-control custom-input" id="checkPassword" onkeyup="trackingPasswordEquals()" style="border-radius: 15px;" required>
            <span id="explainPasswordCheck" style="font-size: 13px; display: none; margin-top: 10px;"></span> <%-- 비밀번호 일치 여부 텍스트 --%>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <label for="email" class="form-label">이메일</label>
        <div class="col-md-8" style="margin-bottom: 10px;">
            <input type="email" class="form-control custom-input" id="email" style="border-radius: 15px;" required>
        </div>
        <br>
        <div class="col-md-4">
            <button class="btn btn-secondary" type="button" id="emailVerificationButton" onclick="enableEmailVerificationWithSignUp()">이메일 인증</button>
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
        <div class="col-md-12">
            <label for="birth" class="form-label">생년월일</label>
            <input type="date" class="form-control" id="birth" style="border-radius: 15px;" required>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <div class="col-md-12">
            <label for="image" class="form-label">프로필 사진</label>
            <input class="form-control" type="file" id="image" style="border-radius: 15px;" >
            <div style="margin-top: 5px;">
                <span style="font-size: 13px;">업로드하지 않으면 기본 이미지로 대체됩니다</span>
            </div>
        </div>
    </div>
    <br>

    <div class="col-md-4 row mx-auto">
        <button class="btn btn-primary btn-lg btn-block" type="button" onclick="signUpProcess()">회원가입</button>
    </div>
    <br>

</div>
</body>
</html>
