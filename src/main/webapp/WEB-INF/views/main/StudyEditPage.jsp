<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../util/resources.jsp" %>
    <link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css"/>
    <link href="https://unpkg.com/@yaireo/tagify/dist/tagify.css" rel="stylesheet" type="text/css"/>
    <script src="https://unpkg.com/@yaireo/tagify"></script>
    <script src="https://unpkg.com/@yaireo/tagify/dist/tagify.polyfills.min.js"></script>
    <script src="<c:url value="/js/study/StudyRegisterInputTracking.js"/>"></script>
    <script src="<c:url value="/js/study/StudyEditApiRequest.js"/>"></script>
    <script src="<c:url value="/js/study/StudyEditIntegrateProcess.js"/>"></script>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.KGU_JSP_PROJECT == null}">
        <jsp:include page="../fragment/AnonymousHeader.jsp"/>
    </c:when>
    <c:when test="${sessionScope.KGU_JSP_PROJECT != null}">
        <jsp:include page="../fragment/AuthenticateHeader.jsp"/>
    </c:when>
</c:choose>

<div class="container">
    <h1 style="text-align: center">스터디 수정</h1>
    <hr>

    <div class="col-md-12">
        <div class="col-md-6">
            <h4>스터디 이름</h4>
        </div>
        <div class="col-md-9 row">
            <div class="col-md-6" style="margin-bottom: 10px;">
                <input id="studyName" type="text" class="form-control" name="studyName" onkeyup="trackingStudyName()" max="20" value="${studyDetailToEdit.studyName}"/>
            </div>
            <div class="col-md-2">
                <button id="studyNameDuplicateCheckButton" class="btn btn-danger btn-sm" type="button" onclick="studyNameDuplicateCheckInEdit('${studyDetailToEdit.studyId}')">중복 체크</button>
            </div>
            <input type="hidden" id="studyNameVerificationToken" value="fail"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4>스터디 간단 설명</h4>
        </div>
        <div class="col-md-6">
            <input id="studyBriefDescription" type="text" class="form-control" name="studyBriefDescription" onkeyup="trackingStudyBriefDescription()"
                   maxlength="50" value="${studyDetailToEdit.studyBriefDescription}"/>
            <input type="hidden" id="studyBriefDescriptionVerificationToken" value="fail"/> <%--스터디 간단 설명 기입 여부 체크--%>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4>스터디 상세 설명</h4>
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
            <h4>스터디 최대 인원수</h4>
        </div>
        <div class="col-md-3">
            <input id="studyMaxMember" type="number" class="form-control" name="studyMaxMember" min="2" max="10" value="${studyDetailToEdit.studyMaxMemberCount}"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4>스터디 모집 마감일</h4>
        </div>
        <div class="col-md-3">
            <input id="studyRecruitDeadline" type="date" class="form-control" name="studyRecruitDeadline" value="${studyDetailToEdit.studyRecruitDeadLine}"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4>스터디 태그</h4>
        </div>
        <div class="col-md-6">
            <input name="tag" class="form-control" placeholder="스터디 태그를 입력해주세요..." value="<c:out value='${studyDetailToEdit.studyTagList}'/>"/>
        </div>

        <br><br>

        <button type="button" class="btn btn-primary btn-lg btn-block" onclick="editProcess('${studyDetailToEdit.studyId}')">스터디 수정</button>
        <%--        TODO 스터디 수정 View--%>
        <br>
    </div>
</div>
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
            ['ul', 'ol', 'task', 'indent', 'outdent'],
            ['table', 'link'],
            ['code', 'codeblock'],
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