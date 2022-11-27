<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value="/js/search/SearchProcess.js"/>"></script>

<%
    String searchType = request.getParameter("searchType");
    String keyword = request.getParameter("keyword");
%>
<div class="d-flex justify-content-center align-items-center flex-row">
    <div>
        <div class="input-group mb-3">
            <button id="searchType" type="button" class="btn btn-outline-primary"><%=searchType%></button>
            <button type="button" class="btn btn-outline-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                <span class="visually-hidden"></span>
            </button>
            <ul class="dropdown-menu">
                <li>
                    <button id="registerDate" class="dropdown-item" type="button" value="registerDate" onclick="selectDate()">
                        <spring:message code="search.register.date"/>
                    </button>
                </li>
                <li>
                    <button id="popularity" class="dropdown-item" type="button" value="popularity" onclick="selectPopularity()">
                        <spring:message code="search.current.member"/>
                    </button>
                </li>
                <li>
                    <button id="recruitDeadLine" class="dropdown-item" type="button" value="recruitDeadline" onclick="selectRecruitDeadLine()">
                        <spring:message code="search.recruit.deadline"/>
                    </button>
                </li>
                <li>
                    <button id="maxMember" class="dropdown-item" type="button" value="maxMember" onclick="selectMaxMember()">
                        <spring:message code="search.max.member"/>
                    </button>
                </li>
            </ul>
            <input id="keyword" type="text" class="form-control" placeholder="Search... Enter" value="<%=keyword%>"/>
        </div>
    </div>
</div>

<script>
    let searchType = $('#searchType');
    let keyword = $('#keyword');

    keyword.on('keydown', function (event) {
        let key = event.key || event.keyCode;

        if (key === 'Enter' || key === 13) {
            event.preventDefault();

            if (searchType.html() === '등록 날짜' || searchType.html() === 'Registration Date') {
                detailSearch('registerDate', keyword.val());
            } else if (searchType.html() === '참여 인원' || searchType.html() === 'Popularity') {
                detailSearch('popularity', keyword.val());
            } else if (searchType.html() === '모집 마감일' || searchType.html() === 'Recruitment Deadline') {
                detailSearch('recruitDeadline', keyword.val());
            } else { // 모집 정원
                detailSearch('maxMember', keyword.val());
            }
        }
    });
</script>