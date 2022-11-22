<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="title"/></title>
    <%@ include file="../util/resources.jsp" %>
    <link href="https://unpkg.com/@yaireo/tagify/dist/tagify.css" rel="stylesheet" type="text/css"/>
    <script src="https://unpkg.com/@yaireo/tagify"></script>
    <script src="https://unpkg.com/@yaireo/tagify/dist/tagify.polyfills.min.js"></script>
    <script src="<c:url value="/js/study/StudyEditInputTracking.js"/>"></script>
    <script src="<c:url value="/js/study/StudyEditApiRequest.js"/>"></script>
    <script src="<c:url value="/js/study/StudyEditIntegrateProcess.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/css/input.css"/>"/>
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

<div class="container">
    <h1 style="text-align: center">
        <spring:message code="study.edit.title"/>
    </h1>
    <hr>

    <div class="col-md-12">
        <div class="col-md-6">
            <h4>
                <spring:message code="study.edit.name"/>
            </h4>
        </div>
        <div class="col-md-9 row">
            <div class="col-md-6" style="margin-bottom: 10px;">
                <input id="studyName" type="text" class="form-control custom-input" name="studyName" style="border-radius: 15px;" onkeyup="trackingStudyName('${studyDetailToEdit.studyName}')" max="20" value="${studyDetailToEdit.studyName}"/>
            </div>
            <div class="col-md-2">
                <button id="studyNameDuplicateCheckButton" class="btn btn-danger btn-sm" type="button" onclick="studyNameDuplicateCheckInEdit('${studyDetailToEdit.studyId}')" disabled>
                    <spring:message code="study.edit.name.duplicate.check"/>
                </button>
            </div>
            <input type="hidden" id="studyNameVerificationToken" value="success"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4>
                <spring:message code="study.edit.brief.description"/>
            </h4>
        </div>
        <div class="col-md-6">
            <input id="studyBriefDescription" type="text" class="form-control custom-input" name="studyBriefDescription" style="border-radius: 15px;" maxlength="50" value="${studyDetailToEdit.studyBriefDescription}"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4>
                <spring:message code="study.edit.description"/>
            </h4>
        </div>
        <div id="editor">${studyDetailToEdit.studyDescription}</div>
        <script src="https://cdn.ckeditor.com/ckeditor5/35.3.1/super-build/ckeditor.js"></script>

        <br><br>

        <div class="col-md-6">
            <h4>
                <spring:message code="study.edit.max.member"/>
            </h4>
        </div>
        <div class="col-md-3">
            <input id="studyMaxMember" type="number" class="form-control custom-input" name="studyMaxMember" style="border-radius: 15px;" min="2" max="10" value="${studyDetailToEdit.studyMaxMemberCount}"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4>
                <spring:message code="study.edit.recruit.deadline"/>
            </h4>
        </div>
        <div class="col-md-3">
            <input id="studyRecruitDeadline" type="datetime-local" class="form-control" name="studyRecruitDeadline" value="${studyDetailToEdit.studyRecruitDeadLine}"/>
        </div>

        <br><br>

        <div class="col-md-6">
            <h4>
                <spring:message code="study.edit.tag"/>
            </h4>
        </div>
        <div class="col-md-6">
            <input name="tag" class="form-control" placeholder="<spring:message code="study.edit.tag.input.message"/>" value="<c:out value='${studyDetailToEdit.studyTagList}'/>"/>
        </div>

        <br><br>

        <button type="button" class="btn btn-primary btn-lg btn-block" onclick="editProcess('${studyDetailToEdit.studyId}')">
            <spring:message code="study.edit.process"/>
        </button>
    </div>
</div>
<jsp:include page="../fragment/Footer.jsp"/>
</body>
<script>
    // CKEditor
    let editor;

    CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {
        toolbar: {
            items: [
                'exportPDF','exportWord', '|',
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
                { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
                { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
                { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' },
                { model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3' },
                { model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4' },
                { model: 'heading5', view: 'h5', title: 'Heading 5', class: 'ck-heading_heading5' },
                { model: 'heading6', view: 'h6', title: 'Heading 6', class: 'ck-heading_heading6' }
            ]
        },
        placeholder: '스터디에 대한 설명을 작성해주세요',
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
            options: [ 10, 12, 14, 'default', 18, 20, 22 ],
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
    }).then(newEditor => {
        editor = newEditor;
    }).catch(error => {
        console.log(error);
    });

    // Tagify
    const input = document.querySelector('input[name=tag]');
    let tagify = new Tagify(input);

    function editProcess(studyId) {
        studyEdit(studyId, editor, tagify);
    }
</script>
</html>
