<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value="/js/login/Logout.js"/>"></script>

<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="<c:url value="/images/header.png"/>" alt="Image.." style="width: 50px; height: 50px; margin: 10px;"/>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/" class="nav-link px-2 link-dark"><span style="font-style: italic; font-size: 30px;">Studyholic</span></a></li>
            </ul>

            <sec:authorize access="isAnonymous()">
                <div class="text-end">
                    <button type="button" class="btn btn-secondary btn-rounded">
                        <a href="/login"><spring:message code="header.login"/></a>
                    </button>
                    <button type="button" class="btn btn-info btn-rounded">
                        <a href="/signup"><spring:message code="header.signup"/></a>
                    </button>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <img src="<c:out value="/images/user/${sessionScope.SPECIAL_SESSION.storageName}"/>" alt="test" width="50" height="50" style="margin: 5px; border-radius: 25%;"/>
                <span style="text-align: center; margin: 10px;"><spring:message code="header.welcome" arguments="${sessionScope.SPECIAL_SESSION.nickname}"/></span>

                <div class="dropdown text-end">
                    <button class="btn btn-secondary btn-rounded dropdown-toggle" data-bs-toggle="dropdown">
                        Menu
                    </button>
                    <ul class="dropdown-menu text-secondary">
                        <li><a class="dropdown-item" href="/study/post"><spring:message code="header.dropdown.make.study"/></a></li>
                        <li><a class="dropdown-item" href="/user/<sec:authentication property="principal.user.id"/>/study"><spring:message code="header.dropdown.partipate.study"/></a></li>
                        <li><a class="dropdown-item" href="/user/<sec:authentication property="principal.user.id"/>"><spring:message code="header.dropdown.mypage"/></a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li>
                            <button class="dropdown-item" type="button" onclick="logout()"><spring:message code="header.dropdown.logout"/></button>
                        </li>
                    </ul>
                </div>
            </sec:authorize>
        </div>
    </div>
</header>