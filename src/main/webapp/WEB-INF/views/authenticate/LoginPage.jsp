<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <link rel="stylesheet" href="<c:url value="/css/login.css"/>"/>
    <script src="<c:url value="/js/login/Login.js"/>"></script>
</head>
<style>
    .link-color-black{
        color: black;
    }
</style>
<body class="text-center">
<main class="form-signin w-100 m-auto">
    <a href="/">
        <img class="mb-4" src="<c:url value="/images/header.png"/>" alt="" width="100" height="100">
    </a>
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
        <input type="text" class="form-control" id="loginId" name="loginId" placeholder="ID" autocomplete="on" autofocus>
        <label for="loginId">ID</label>
    </div>
    <div class="form-floating">
        <input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="Password">
        <label for="loginPassword">Password</label>
    </div>
    <button id="login" class="w-100 btn btn-lg btn-primary" type="button" onclick="login()">로그인</button>
    <div class="form-floating mt-3">
        <a class="link-color-black" href="/find-id">아이디 찾기</a> |
        <a class="link-color-black" href="/find-password">비밀번호 찾기/재설정</a> |
        <a class="link-color-black" href="/signup">회원가입</a>
    </div>
</main>
<script>
    $('#loginId, #loginPassword').on('keydown', function (event) {
        let key = event.key || event.keyCode;

        if (key === 'Enter' || key === 13) {
            event.preventDefault();
            $('#login').click();
        }
    });
</script>
</body>
</html>
