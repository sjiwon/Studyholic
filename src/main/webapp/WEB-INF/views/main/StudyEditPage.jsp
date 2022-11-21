<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="title"/></title>
    <%@ include file="../util/resources.jsp" %>
    <link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css"/>
    <link href="https://unpkg.com/@yaireo/tagify/dist/tagify.css" rel="stylesheet" type="text/css"/>
    <script src="https://unpkg.com/@yaireo/tagify"></script>
    <script src="https://unpkg.com/@yaireo/tagify/dist/tagify.polyfills.min.js"></script>
    <script src="<c:url value="/js/study/StudyEditInputTracking.js"/>"></script>
    <script src="<c:url value="/js/study/StudyEditApiRequest.js"/>"></script>
    <script src="<c:url value="/js/study/StudyEditIntegrateProcess.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/css/input.css"/>"/>
</head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container">
    <h1 style="text-align: center"><spring:message code="study.edit.title"/></h1>
    <hr>

    <div class="col-md-12">
        <div class="col-md-6">
            <h4><spring:message code="study.edit.name"/></h4>
        </div>
        <div class="col-md-9 row">
            <div class="col-md-6" style="margin-bottom: 10px;">
                <input id="studyName" type="text" class="form-control custom-input" name="studyName" style="border-radius: 15px;"
                       onkeyup="trackingStudyName('${studyDetailToEdit.studyName}')" max="20" value="${studyDetailToEdit.studyName}"/>
            </div>
            <div class="col-md-2">
                <button id="studyNameDuplicateCheckButton" class="btn btn-danger btn-sm" type="button"
                        onclick="studyNameDuplicateCheckInEdit('${studyDetailToEdit.studyId}')" disabled><spring:message code="study.edit.name.duplicate.check"/></button>
            </div>
            <input type="hidden" id="studyNameVerificationToken" value="success"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.edit.brief.description"/></h4>
        </div>
        <div class="col-md-6">
            <input id="studyBriefDescription" type="text" class="form-control custom-input" name="studyBriefDescription" style="border-radius: 15px;"
                   maxlength="50" value="${studyDetailToEdit.studyBriefDescription}"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.edit.description"/></h4>
        </div>
        <div id="editor"></div>
        <input type="hidden" value="${studyDetailToEdit.studyDescription}" id="studyDescription"/>
        <!-- Color Picker -->
        <script src="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.js"></script>
        <!-- Editor -->
        <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
        <!-- Editor's Plugin -->
        <script src="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.js"></script>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.edit.max.member"/></h4>
        </div>
        <div class="col-md-3">
            <input id="studyMaxMember" type="number" class="form-control custom-input" name="studyMaxMember" style="border-radius: 15px;"
                   min="2" max="10" value="${studyDetailToEdit.studyMaxMemberCount}"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.edit.recruit.deadline"/></h4>
        </div>
        <div class="col-md-3">
            <input id="studyRecruitDeadline" type="datetime-local" class="form-control" name="studyRecruitDeadline" value="${studyDetailToEdit.studyRecruitDeadLine}"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.edit.tag"/></h4>
        </div>
        <div class="col-md-6">
            <input name="tag" class="form-control" placeholder="<spring:message code="study.edit.tag.input.message"/>" value="<c:out value='${studyDetailToEdit.studyTagList}'/>"/>
        </div>

        <br><br>

        <button type="button" class="btn btn-primary btn-lg btn-block" onclick="editProcess('${studyDetailToEdit.studyId}')"><spring:message code="study.edit.process"/></button>
        <br>
    </div>
</div>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
<script>
    // Toast UI Editor
    const {Editor} = toastui;
    const {colorSyntax} = Editor.plugin;

    const editor = new Editor({
        el: document.querySelector('#editor'),
        initialEditType: 'markdown',
        previewStyle: 'vertical',
        height: '600px',
        initialValue: $('#studyDescription').val(),
        toolbarItems: [
            ['heading', 'bold', 'italic', 'strike'],
            ['hr', 'quote'],
            ['ul', 'ol', 'task'],
            ['table', 'link'],
            ['code'],
        ],
        plugins: [colorSyntax],
    });

    // Tagify
    const input = document.querySelector('input[name=tag]');
    let tagify = new Tagify(input);

    function editProcess(studyId) {
        studyEdit(studyId, editor, tagify);
    }
</script>
</html>
