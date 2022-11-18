<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Studyholic</title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/paging/Paging.js"/>"></script>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>
<jsp:include page="../fragment/MainSection.jsp"/>
<jsp:include page="../fragment/StudySearch.jsp">
    <jsp:param name="searchType" value="${searchType}"/>
    <jsp:param name="keyword" value="${keyword}"/>
</jsp:include>
<jsp:include page="../fragment/MainStudy.jsp">
    <jsp:param name="studyList" value="${studyList}"/>
</jsp:include>
<ul class="pagination justify-content-center pagination-circle">
    <c:if test="${pagination.prev}">
        <li class="page-item">
            <button class="page-link" type="button" onclick="movePreviousRange()">Previous</button>
        </li>
    </c:if>

    <c:forEach var="idx" begin="${pagination.rangeStartNumber}" end="${pagination.rangeEndNumber}">
        <li class="page-item <c:out value="${pagination.currentPage == idx ? 'active' : ''}"/> ">
            <button class="page-link" type="button" onclick="moveIdx('${idx}')">${idx}</button>
        </li>
    </c:forEach>

    <c:if test="${pagination.next}">
        <li class="page-item">
            <button class="page-link" type="button" onclick="moveNextRange()">Next</button>
        </li>
    </c:if>
</ul>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
</html>