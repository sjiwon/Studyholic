<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="<c:url value="/js/search/SearchProcess.js"/>"></script>
<%
    String searchType = request.getParameter("searchType");
    String keyword = request.getParameter("keyword");
%>
<div class="d-flex justify-content-center align-items-center flex-row">
    <div>
        <div class="input-group mb-3">
            <button id="searchType" type="button" class="btn btn-outline-primary"><%=searchType%>
            </button>
            <button type="button" class="btn btn-outline-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                <span class="visually-hidden"></span>
            </button>
            <ul class="dropdown-menu">
                <li>
                    <button id="registerDate" class="dropdown-item" type="button" value="registerDate" onclick="selectDate()">등록 날짜</button>
                </li>
                <li>
                    <button id="popularity" class="dropdown-item" type="button" value="popularity" onclick="selectPopularity()">참여 인원</button>
                </li>
                <li>
                    <button id="recruitDeadLine" class="dropdown-item" type="button" value="recruitDeadline" onclick="selectRecruitDeadLine()">모집 마감일</button>
                </li>
                <li>
                    <button id="maxMember" class="dropdown-item" type="button" value="maxMember" onclick="selectMaxMember()">모집 정원</button>
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

            if (searchType.html() === '등록 날짜') {
                detailSearch('registerDate', keyword.val());
            } else if (searchType.html() === '참여 인원') {
                detailSearch('popularity', keyword.val());
            } else if (searchType.html() === '모집 마감일') {
                detailSearch('recruitDeadline', keyword.val());
            } else { // 모집 정원
                detailSearch('maxMember', keyword.val());
            }
        }
    });
</script>