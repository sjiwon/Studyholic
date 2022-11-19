<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/user/EditUser.js"/>"></script>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container col-md-6">
    <h1 style="text-align: center">마이페이지</h1>
    <input type="hidden" id="userId" value="${userDetail.basicUser.id}"/>
    <hr>
    <div class="col-md-12">
        <div class="col-md-6">
            <img src="<c:out value="/images/user/${userDetail.basicUser.profileImage}"/>" alt="test" width="130" height="130" style="border-radius: 25%; margin: 3px;"/>
            <button class="btn btn-secondary btn-sm" type="button" onclick="editProfileToDefaultImage()">기본 이미지로 변경</button>
        </div><br>
        <div class="col-md-12 row">
            <label for="image" class="form-label">프로필 변경</label>
            <div class="col-md-10" style="margin-bottom: 5px;">
                <input class="form-control" type="file" id="image">
            </div>
            <div class="col-md-2">
                <button class="btn btn-danger btn-sm" type="button" onclick="editProfileToCustomImage()">변경</button>
            </div>
        </div><br>

        <div class="card" style="border: 3px solid black">
            <div class="card-body">
                <div class="col-md-12 row">
                    <div class="col-md-12">
                        <label for="name" class="form-label">이름</label>
                        <input class="form-control" type="text" id="name" value="${userDetail.basicUser.name}" readonly>
                    </div>
                </div><br>

                <div class="col-md-12 row">
                    <label for="nickname" class="form-label">닉네임</label>
                    <div class="col-md-10" style="margin-bottom: 5px;">
                        <input class="form-control" type="text" id="nickname" value="${userDetail.basicUser.nickname}" readonly>
                    </div>
                    <div class="col-md-2">
                        <button id="nicknameEditButton" class="btn btn-danger btn-sm" type="button" onclick="editNicknameButtonEnable()">수정</button>
                    </div>
                </div><br>

                <div class="col-md-12 row">
                    <div class="col-md-12">
                        <label for="loginId" class="form-label">아이디</label>
                        <input class="form-control" type="text" id="loginId" value="${userDetail.basicUser.loginId}" readonly>
                    </div>
                </div><br>

                <div class="col-md-12 row">
                    <label for="loginPassword" class="form-label">비밀번호</label>
                    <div class="col-md-10" style="margin-bottom: 5px;">
                        <input class="form-control" type="password" id="loginPassword" value="<%=UUID.randomUUID().toString()%>" readonly>
                    </div>
                    <div class="col-md-2">
                        <button id="passwordEditButton" class="btn btn-danger btn-sm" type="button" onclick="moveToEditPasswordPage()">수정</button>
                    </div>
                </div><br>

                <div class="col-md-12 row">
                    <div class="col-md-12">
                        <label for="birth" class="form-label">생년월일</label>
                        <input class="form-control" type="text" id="birth" value="${userDetail.basicUser.birth}" readonly>
                    </div>
                </div><br>

                <div class="col-md-12 row">
                    <div class="col-md-12">
                        <label for="email" class="form-label">이메일</label>
                        <input class="form-control" type="text" id="email" value="${userDetail.basicUser.email}" readonly>
                    </div>
                </div><br>

                <hr>

                <div style="margin-bottom: 10px; text-align: right">
                    가입 날짜 | <small>${userDetail.basicUser.joinDate}</small>
                </div>
                <div style="margin-bottom: 10px; text-align: right">
                    최종 수정 날짜 | <small>${userDetail.basicUser.lastModifiedDate}</small>
                </div>
            </div>
        </div>
    </div>
    <br>
</div>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
</html>
