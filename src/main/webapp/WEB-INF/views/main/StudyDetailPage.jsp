<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="title"/></title>
    <%@ include file="../util/resources.jsp" %>
    <script src="<c:url value="/js/study/AboutStudy.js"/>"></script>
    <link rel="stylesheet" href="<c:out value="/css/tag.css"/>"/>
    <style>
        .ck-editor__editable[role="textbox"] {
            /* editing area */
            min-height: 200px;
        }

        .ck-content .image {
            /* block images */
            max-width: 80%;
            margin: 20px auto;
        }
    </style>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container col-md-8">
    <h1 style="text-align: center">${studyDetail.basicStudy.name}</h1>
    <div class="text-center">
        <c:forEach var="tag" items="${studyDetail.studyTagList}">
            <span class="tag" style="font-weight: bold;"># ${tag}</span>
        </c:forEach>
    </div>
    <div class="col-md-12">
        <div class="col-md-12">
            <div style="margin-bottom: 10px; text-align: right">
                <c:forEach var="user" items="${studyDetail.participateUserList}">
                    <c:if test="${user.teamLeader == true}">
                        <img src="<c:out value="/images/user/${user.profileImage}"/>" alt="test" width="40" height="40" style="border-radius: 25%; margin: 3px;"/>
                        <span style="font-size: 1.8rem;">${user.nickname}</span>
                    </c:if>
                </c:forEach>
            </div>
            <div style="margin-bottom: 10px; text-align: right">
                <spring:message code="study.detail.register.date"/> | ${studyDetail.basicStudy.registerDate}
            </div>
            <div style="margin-bottom: 10px; text-align: right">
                <spring:message code="study.detail.recruit.deadline"/> | ${studyDetail.basicStudy.recruitDeadline}
            </div>
            <div style="margin-bottom: 10px; text-align: right;">
                <spring:message code="study.detail.last.modified.date"/> | ${studyDetail.basicStudy.lastModifiedDate}
            </div>
            <div style="text-align: right">
                <sec:authorize access="isAuthenticated()">
                    <c:choose>
                        <c:when test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id == studyDetail.studyLeaderId}">
                            <button type="button" class="btn btn-secondary" style="margin: 5px;" onclick="moveToStudyEditPage(${studyDetail.basicStudy.id})">
                                <spring:message code="study.detail.edit"/>
                            </button>
                            <button type="button" class="btn btn-danger" style="margin: 5px;" onclick="deleteStudy('${studyDetail.basicStudy.name}', '${studyDetail.basicStudy.id}', '<sec:authentication property="principal.user.id"/>')">
                                <spring:message code="study.detail.delete"/>
                            </button>
                        </c:when>
                        <c:when test="${
                                sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id != studyDetail.studyLeaderId &&
                                !studyDetail.participateUserList.stream().map(user -> user.id).toList().contains(sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id)}"
                        >
                            <button type="button" class="btn btn-primary" style="margin: 5px;" onclick="participate('${studyDetail.basicStudy.name}', '${studyDetail.basicStudy.id}', '<sec:authentication property="principal.user.id"/>')">
                                <spring:message code="study.detail.participate"/>
                            </button>
                        </c:when>
                        <c:when test="${
                                sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id != studyDetail.studyLeaderId &&
                                studyDetail.participateUserList.stream().map(user -> user.id).toList().contains(sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id)}"
                        >
                            <button type="button" class="btn btn-danger" style="margin: 5px;" onclick="participateCancle('${studyDetail.basicStudy.name}', '${studyDetail.basicStudy.id}', '<sec:authentication property="principal.user.id"/>')">
                                <spring:message code="study.detail.participate.cancel"/>
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
                    <c:when test="${studyDetail.basicStudy.currentMemberCount == studyDetail.basicStudy.maxMemberCount}">
                        <span style="color: red; margin: 10px;">
                            <spring:message code="study.detail.member.count"/> |
                            <small style="font-size: 20px;">${studyDetail.basicStudy.currentMemberCount} / ${studyDetail.basicStudy.maxMemberCount}</small>
                        </span>
                    </c:when>
                    <c:when test="${studyDetail.basicStudy.currentMemberCount != studyDetail.basicStudy.maxMemberCount}">
                        <span style="margin: 10px;">
                            <spring:message code="study.detail.member.count"/> |
                            <small style="font-size: 20px;">${studyDetail.basicStudy.currentMemberCount} / ${studyDetail.basicStudy.maxMemberCount}</small>
                        </span>
                    </c:when>
                </c:choose>
            </h2>
            <c:forEach var="user" items="${studyDetail.participateUserList}">
                <div style="margin: 20px;">
                    <c:if test="${user.teamLeader == true}">
                        <img src="<c:out value="/images/user/${user.profileImage}"/>" alt="test" width="30" height="30" style="border-radius: 25%; margin: 3px;"/>
                        <span style="font-size: 1.2rem; margin: 5px;">
                                ${user.nickname} <spring:message code="study.detail.leader"/>
                        </span>
                    </c:if>
                    <c:if test="${user.teamLeader == false}">
                        <img src="<c:out value="/images/user/${user.profileImage}"/>" alt="test" width="30" height="30" style="border-radius: 25%; margin: 3px;"/>
                        <span style="font-size: 1.2rem; margin: 5px;">${user.nickname}</span>
                    </c:if>
                </div>
            </c:forEach>

            <br>
            <br>
            <hr>

            <h2>
                <img src="<c:out value="/images/utils/description.png"/>" alt="test" width="80" height="80" style="border-radius: 25%; margin: 3px;"/>
                <span style="margin: 10px;">
                    <spring:message code="study.detail.description"/>
                </span>
            </h2>
            <div id="editor">${studyDetail.basicStudy.description}</div>
            <script src="https://cdn.ckeditor.com/ckeditor5/35.3.1/super-build/ckeditor.js"></script>

            <br>
            <br>
            <hr>

            <h2>
                <img src="<c:out value="/images/utils/call.png"/>" alt="test" width="80" height="80" style="border-radius: 25%; margin-bottom: 10px;"/>
                <span style="margin: 10px;">
                    <spring:message code="study.detail.leader.email"/>
                </span>
            </h2>
            <c:forEach var="user" items="${studyDetail.participateUserList}">
                <c:if test="${user.teamLeader == true}">
                    <a href="<c:url value="mailto:${user.email}?cc=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.email}"/>" target="_blank">
                        <span style="font-size: 1.5rem;">${user.email}</span>
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
    // CKEditor
    CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {
        toolbar: {
            items: [
                'exportPDF', 'exportWord', '|',
                'findAndReplace', 'selectAll', '|',
                'heading', '|',
                'bold', 'italic', 'strikethrough', 'underline', 'code', 'subscript', 'superscript', '|',
                'bulletedList', 'numberedList', 'todoList', '|',
                'outdent', 'indent', '|',
                'undo', 'redo',
                '-',
                'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor', 'highlight', '|',
                'alignment', '|',
                'link', 'insertImage', 'blockQuote', 'insertTable', 'mediaEmbed', 'codeBlock', 'htmlEmbed', '|',
                'specialCharacters', 'horizontalLine', 'pageBreak', '|',
                'sourceEditing'
            ],
            shouldNotGroupWhenFull: true
        },
        codeBlock: {
            languages: [
                { language: 'plaintext', label: 'Plain text' }, // The default language.
                { language: 'c', label: 'C', class: 'C++' },
                { language: 'java', label: 'Java', class: 'Java' },
                { language: 'kotlin', label: 'Kotlin', class: 'Kotlin' },
                { language: 'javascript', label: 'JavaScript', class: 'JavaScript' },
                { language: 'typescript', label: 'TypeScript', class: 'TypeScript' },
                { language: 'python', label: 'Python', class: 'Python' },
                { language: 'html', label: 'HTML', class: 'HTML' },
                { language: 'css', label: 'CSS', class: 'CSS' },
                { language: 'xml', label: 'XML', class: 'XML' }
            ]
        },
        language: navigator.language,
        list: {
            properties: {
                styles: true,
                startIndex: true,
                reversed: true
            }
        },
        heading: {
            options: [
                {model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph'},
                {model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1'},
                {model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2'},
                {model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3'},
                {model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4'},
                {model: 'heading5', view: 'h5', title: 'Heading 5', class: 'ck-heading_heading5'},
                {model: 'heading6', view: 'h6', title: 'Heading 6', class: 'ck-heading_heading6'}
            ]
        },
        fontFamily: {
            options: [
                'default',
                'Arial, Helvetica, sans-serif',
                'Courier New, Courier, monospace',
                'Georgia, serif',
                'Lucida Sans Unicode, Lucida Grande, sans-serif',
                'Tahoma, Geneva, sans-serif',
                'Times New Roman, Times, serif',
                'Trebuchet MS, Helvetica, sans-serif',
                'Verdana, Geneva, sans-serif'
            ],
            supportAllValues: true
        },
        fontSize: {
            options: [10, 12, 14, 'default', 18, 20, 22],
            supportAllValues: true
        },
        htmlSupport: {
            allow: [
                {
                    name: /.*/,
                    attributes: true,
                    classes: true,
                    styles: true
                }
            ]
        },
        htmlEmbed: {
            showPreviews: true
        },
        link: {
            decorators: {
                addTargetToExternalLinks: true,
                defaultProtocol: 'https://',
                toggleDownloadable: {
                    mode: 'manual',
                    label: 'Downloadable',
                    attributes: {
                        download: 'file'
                    }
                }
            }
        },
        removePlugins: [
            'CKBox',
            'CKFinder',
            'EasyImage',
            'RealTimeCollaborativeComments',
            'RealTimeCollaborativeTrackChanges',
            'RealTimeCollaborativeRevisionHistory',
            'PresenceList',
            'Comments',
            'TrackChanges',
            'TrackChangesData',
            'RevisionHistory',
            'Pagination',
            'WProofreader',
        ]
    }).then(editor => {
        editor.enableReadOnlyMode("editor");
        const toolbarElement = editor.ui.view.toolbar.element;
        toolbarElement.style.display = 'none';
    }).catch(error => {
        console.log(error);
    });
</script>
</html>
