<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.css"/>
    <script src="<c:url value="/js/study/AboutStudy.js"/>"></script>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container col-md-8">
    <h1 style="text-align: center">${studyDetail.studyName}</h1>
    <div class="col-md-12">
        <div class="col-md-12">
            <div style="margin-bottom: 10px; text-align: right">
                <c:forEach var="user" items="${studyDetail.participateUserList}">
                    <c:if test="${user.teamLeader == true}">
                        <img src="<c:out value="/images/user/${user.userProfileImage}"/>" alt="test" width="40" height="40" style="border-radius: 25%; margin: 3px;"/>
                        <span style="font-size: 1.8rem;">${user.userNickname}</span>
                    </c:if>
                </c:forEach>
            </div>
            <div style="margin-bottom: 10px; text-align: right">
                등록 날짜 | ${studyDetail.studyRegisterDate}
            </div>
            <div style="margin-bottom: 10px; text-align: right">
                모집 마감일 | ${studyDetail.studyRecruitDeadLine}
            </div>
            <div style="margin-bottom: 10px; text-align: right;">
                <small>최종 수정 날짜 | ${studyDetail.studyLastModifiedDate}</small>
            </div>
            <div style="text-align: right">
                <sec:authorize access="isAuthenticated()">
                    <c:choose>
                        <c:when test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id == studyDetail.studyLeaderId}">
                            <button type="button" class="btn btn-secondary" style="margin: 5px;" onclick="moveToStudyEditPage(${studyDetail.studyId})">수정하기</button>
                            <button type="button" class="btn btn-danger" style="margin: 5px;"
                                    onclick="deleteStudy('${studyDetail.studyName}', '${studyDetail.studyId}', '<sec:authentication property="principal.user.id"/>')">삭제하기
                            </button>
                        </c:when>
                        <c:when test="${
                                sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id != studyDetail.studyLeaderId &&
                                !studyDetail.participateUserList.stream().map(user -> user.userId).toList().contains(sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id)}"
                        >
                            <button type="button" class="btn btn-primary" style="margin: 5px;"
                                    onclick="participate('${studyDetail.studyName}', '${studyDetail.studyId}', '<sec:authentication property="principal.user.id"/>')">참여하기
                            </button>
                        </c:when>
                        <c:when test="${
                                sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id != studyDetail.studyLeaderId &&
                                studyDetail.participateUserList.stream().map(user -> user.userId).toList().contains(sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id)}"
                        >
                            <button type="button" class="btn btn-danger" style="margin: 5px;"
                                    onclick="participateCancle('${studyDetail.studyName}', '${studyDetail.studyId}', '<sec:authentication property="principal.user.id"/>')">참여취소
                            </button>
                        </c:when>
                    </c:choose>
                </sec:authorize>
            </div>
            <hr>
            <br>
            <h2>
                <img src="<c:out value="/images/utils/participate.png"/>" alt="test" width="80" height="80" style="border-radius: 25%; margin: 3px;"/>
                <c:choose>
                    <c:when test="${studyDetail.studyCurrentMemberCount == studyDetail.studyMaxMemberCount}">
                        <span style="color: red; margin: 10px;">참여 인원 | <small style="font-size: 20px;">${studyDetail.studyCurrentMemberCount} / ${studyDetail.studyMaxMemberCount}</small></span>
                    </c:when>
                    <c:when test="${studyDetail.studyCurrentMemberCount != studyDetail.studyMaxMemberCount}">
                        <span style="margin: 10px;">참여 인원 | <small style="font-size: 20px;">${studyDetail.studyCurrentMemberCount} / ${studyDetail.studyMaxMemberCount}</small></span>
                    </c:when>
                </c:choose>
            </h2>
            <c:forEach var="user" items="${studyDetail.participateUserList}">
                <div style="margin: 20px;">
                    <c:if test="${user.teamLeader == true}">
                        <img src="<c:out value="/images/user/${user.userProfileImage}"/>" alt="test" width="30" height="30" style="border-radius: 25%; margin: 3px;"/>
                        <span style="font-size: 1.2rem; margin: 5px;">${user.userNickname} (리더)</span>
                    </c:if>
                    <c:if test="${user.teamLeader == false}">
                        <img src="<c:out value="/images/user/${user.userProfileImage}"/>" alt="test" width="30" height="30" style="border-radius: 25%; margin: 3px;"/>
                        <span style="font-size: 1.2rem; margin: 5px;">${user.userNickname}</span>
                    </c:if>
                </div>
            </c:forEach>

            <br>
            <br>
            <hr>

            <h2>
                <img src="<c:out value="/images/utils/description.png"/>" alt="test" width="80" height="80" style="border-radius: 25%; margin: 3px;"/>
                <span style="margin: 10px;">스터디 설명</span>
                <input type="hidden" id="descriptionValue" value="${studyDetail.studyDescription}"/>
            </h2>
            <div id="viewer"></div>
            <script src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.js"></script>

            <br>
            <br>
            <hr>

            <h2>
                <img src="<c:out value="/images/utils/call.png"/>" alt="test" width="80" height="80" style="border-radius: 25%; margin-bottom: 10px;"/>
                <span style="margin: 10px;">스터디 리더 이메일</span>
            </h2>
            <c:forEach var="user" items="${studyDetail.participateUserList}">
                <c:if test="${user.teamLeader == true}">
                    <a href="<c:url value="mailto:${user.userEmail}?cc=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.email}"/>" target="_blank">
                        <span style="font-size: 1.5rem;">${user.userEmail}</span>
                    </a>
                </c:if>
            </c:forEach>

            <br>
            <br>
        </div>
    </div>

    <br>
</div>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
<script>
    // Toast UI Editor
    const Viewer = toastui.Editor;

    const viewer = new Viewer({
        el: document.querySelector('#viewer'),
        initialValue: $('#descriptionValue').val()
    })
</script>
</html>
