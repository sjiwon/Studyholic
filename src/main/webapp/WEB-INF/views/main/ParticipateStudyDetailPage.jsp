<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/AboutStudy.js"/>"></script>
    <link rel="stylesheet" href="<c:out value="/css/tag.css"/>"/>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container col-md-12">
    <h1 style="text-align: center">참여중인 스터디 목록</h1>
    <hr>

    <div class="col-md-12">
        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <c:forEach begin="0" end="${participateStudyDetail.size()}" var="study" items="${participateStudyDetail}">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h3 style="text-align: center">${study.studyName}</h3>
                                </div>

                                <div class="card-body">
                                    <p class="card-text" style="font-size: 20px; font-weight: bold;">${study.studyBriefDescription}</p>
                                    <p class="card-text">스터디 리더 |
                                        <span>
                                            <img src="<c:out value="/images/user/${study.studyLeaderImage}"/>" alt="test" width="30" height="30" style="border-radius: 25%; margin: 3px;"/>
                                            <b style="text-align: center; margin: 3px;">${study.studyLeaderNickname}</b>
                                        </span>
                                    </p>
                                    <p class="card-text">모집 현황 | ${study.studyCurrentMemberCount} / ${study.studyMaxMemberCount}</p>
                                    <p class="card-text">모집 마감일 | ${study.studyRecruitDeadLine}</p>

                                    <c:forEach var="tag" items="${study.studyTagList}">
                                        <span class="tag" style="font-weight: bold"># ${tag}</span>
                                    </c:forEach>
                                </div>

                                <div class="card-footer">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div>
                                            <button type="button" class="btn btn-sm btn-primary" style="margin: 2px;" onclick="moveToStudyDetailPage(${study.studyId})">상세정보</button>
                                            <c:choose>
                                                <c:when test="${study.studyLeaderId == sessionScope.KGU_JSP_PROJECT.id}">
                                                    <button type="button" class="btn btn-sm btn-danger" style="margin: 2px;"
                                                            onclick="deleteStudy('${study.studyName}', '${study.studyId}', '${sessionScope.KGU_JSP_PROJECT.id}')">스터디 삭제
                                                    </button>
                                                </c:when>
                                                <c:when test="${study.studyLeaderId != sessionScope.KGU_JSP_PROJECT.id}">
                                                    <button type="button" class="btn btn-sm btn-danger" style="margin: 2px;"
                                                            onclick="participateCancle('${study.studyName}', '${study.studyId}', '${sessionScope.KGU_JSP_PROJECT.id}')">참여취소
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <small class="text-muted">등록 | ${study.registerDateFromCurrentDate}</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../fragment/Footer.jsp"/>
</body>
</html>
