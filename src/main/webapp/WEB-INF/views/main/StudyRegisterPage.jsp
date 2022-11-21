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
    <script src="<c:url value="/js/study/StudyRegisterInputTracking.js"/>"></script>
    <script src="<c:url value="/js/study/StudyRegisterApiRequest.js"/>"></script>
    <script src="<c:url value="/js/study/StudyRegisterIntegrateProcess.js"/>"></script>
    <link rel="stylesheet" href=
    <c:url value="/css/input.css"/>
            </head>
<body>
<jsp:include page="../fragment/Header.jsp"/>

<div class="container">
    <h1 style="text-align: center"><spring:message code="study.register.title"/></h1>
    <hr>

    <div class="col-md-12">
        <div class="col-md-6">
            <h4><spring:message code="study.register.name"/></h4>
        </div>
        <div class="col-md-9 row">
            <div class="col-md-6" style="margin-bottom: 10px;">
                <input id="studyName" type="text" class="form-control custom-input" name="studyName" onkeyup="trackingStudyName()" style="border-radius: 15px;"
                       max="20" placeholder="<spring:message code="study.register.name.placeholder"/>" required/>
            </div>
            <br>
            <div class="col-md-4">
                <button class="btn btn-secondary" type="button" id="studyNameDuplicateCheckButton" onclick="studyNameDuplicateCheckApi()"><spring:message
                        code="study.register.name.duplicate.check"/></button>
            </div>
            <input type="hidden" id="studyNameVerificationToken" value="fail"/> <%--스터디 이름 기입 여부 체크--%>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.register.brief.description"/></h4>
        </div>
        <div class="col-md-6">
            <input id="studyBriefDescription" type="text" class="form-control" name="studyBriefDescription" style="border-radius: 15px;"
                   maxlength="50" placeholder="<spring:message code="study.register.brief.description.placeholder"/>" required/>
            <input type="hidden" id="studyBriefDescriptionVerificationToken" value="fail"/> <%--스터디 간단 설명 기입 여부 체크--%>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.register.description"/></h4>
        </div>
        <div id="editor"></div>
        <!-- Color Picker -->
        <script src="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.js"></script>
        <!-- Editor -->
        <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
        <!-- Editor's Plugin -->
        <script src="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.js"></script>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.register.max.member"/></h4>
        </div>
        <div class="col-md-3">
            <input id="studyMaxMember" type="number" class="form-control" name="studyMaxMember" min="2" max="10" style="border-radius: 15px;" required/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.register.recruit.deadline"/></h4>
        </div>
        <div class="col-md-3">
            <input id="studyRecruitDeadline" type="date" class="form-control" name="studyRecruitDeadline" style="border-radius: 15px;" required/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4><spring:message code="study.register.tag"/></h4>
        </div>
        <div class="col-md-6">
            <input name="tag" class="form-control" placeholder="<spring:message code="study.register.tag.input.message"/>"/>
        </div>

        <br><br>

        <button type="button" class="btn btn-primary btn-lg btn-block" onclick="registerProcess('${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id}')"><spring:message
                code="study.register.process"/></button>

        <%--        <br>--%>
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
        initialValue: '> Markdown 문법을 활용해서 스터디에 대한 설명을 작성해주세요',
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

    function registerProcess(userId) {
        studyRegister(userId, editor, tagify);
    }
</script>

</html>
