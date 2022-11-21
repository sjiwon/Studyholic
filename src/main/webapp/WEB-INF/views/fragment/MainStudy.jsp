<%@ page import="java.util.List" %>
<%@ page import="com.sjiwon.studyholic.domain.entity.study.service.dto.response.StudySimpleInformation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                            <p class="card-text" style="font-size: 16px; text-align: center">"${study.basicStudy.briefDescription}"</p>
                        </div>

                        <div class="card-body">
                            <p class="card-text"><b><spring:message code="main.study.leader"/></b> |
                                <span>
                                    <img src="<c:out value="/images/user/${study.studyLeader.storageName}"/>" alt="test" width="30" height="30" style="border-radius: 25%; margin: 3px;"/>
                                    <b style="text-align: center; margin: 3px;">${study.studyLeader.nickname}</b>
                                </span>
                            </p>
                            <p class="card-text"><b><spring:message code="main.study.current.recruit"/></b> | ${study.basicStudy.currentMemberCount} / ${study.basicStudy.maxMemberCount}</p>
                            <p class="card-text"><b><spring:message code="main.study.recruit.deadline"/></b> | ${study.basicStudy.recruitDeadline}</p>

                            <c:forEach var="tag" items="${study.studyTagList}">
                                <span class="tag" style="font-weight: bold"># ${tag}</span>
                            </c:forEach>
                        </div>

                        <div class="card-footer">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <button type="button" class="btn btn-sm btn-primary" style="margin: 2px;" onclick="moveToStudyDetailPage(${study.basicStudy.id})"><spring:message code="main.study.detail"/></button>
                                </div>
                                <small class="text-muted" style="font-size: 12px;"><spring:message code="main.study.register"/> | ${study.basicStudy.registerDateFromCurrentDate}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>