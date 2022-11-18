<%@ page import="java.util.List" %>
<%@ page import="com.sjiwon.studyholic.domain.entity.study.service.dto.response.StudySimpleInformation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="<c:url value="/js/study/AboutStudy.js"/>"></script>
<link rel="stylesheet" href="<c:out value="/css/tag.css"/>"/>

<%
    List<StudySimpleInformation> studyList = (List<StudySimpleInformation>) request.getAttribute("studyList");
%>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach begin="0" end="<%=studyList.size()%>" var="study" items="<%=studyList%>">
                <div class="col">
                    <div class="card">
                        <div class="card-header">
                            <h3 style="text-align: center">${study.basicStudy.name}</h3>
                        </div>

                        <div class="card-body">
                            <p class="card-text" style="font-size: 20px; font-weight: bold;">${study.basicStudy.briefDescription}</p>
                            <p class="card-text">스터디 리더 |
                                <span>
                                    <img src="<c:out value="/images/user/${study.studyLeader.storageName}"/>" alt="test" width="30" height="30" style="border-radius: 25%; margin: 3px;"/>
                                    <b style="text-align: center; margin: 3px;">${study.studyLeader.nickname}</b>
                                </span>
                            </p>
                            <p class="card-text">모집 현황 | ${study.basicStudy.currentMemberCount} / ${study.basicStudy.maxMemberCount}</p>
                            <p class="card-text">모집 마감일 | ${study.basicStudy.recruitDeadline}</p>

                            <c:forEach var="tag" items="${study.studyTagList}">
                                <span class="tag" style="font-weight: bold"># ${tag}</span>
                            </c:forEach>
                        </div>

                        <div class="card-footer">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <button type="button" class="btn btn-sm btn-primary" style="margin: 2px;" onclick="moveToStudyDetailPage(${study.basicStudy.id})">상세정보</button>
                                </div>
                                <small class="text-muted">등록 | ${study.basicStudy.registerDateFromCurrentDate}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>