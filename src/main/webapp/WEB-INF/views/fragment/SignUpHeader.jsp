<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="<c:url value="/images/header.png"/>" alt="Image.." style="width: 50px; height: 50px; margin: 10px;"/>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li>
                    <a href="/" class="nav-link px-2 link-dark">
                        <span style="font-style: italic; font-size: 30px;">Studyholic</span>
                    </a>
                </li>
            </ul>

            <div class="text-end">
                <button type="button" class="btn btn-secondary btn-rounded">
                    <a href="/login">
                        <spring:message code="header.login"/>
                    </a>
                </button>
            </div>
        </div>
    </div>
</header>