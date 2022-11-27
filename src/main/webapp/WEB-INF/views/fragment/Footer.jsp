<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<footer class="bg-light text-center text-white">
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0);">
        <a class="text-black" href="https://github.com/sjiwon">
            <spring:message code="footer.copyright"/>
        </a><br>
        <a class="btn text-white btn-floating m-1" style="background-color: #ac2bac;" href="https://www.instagram.com/sjiwon_/" role="button">
            <i class="fab fa-instagram"></i>
        </a>
        <a class="btn text-white btn-floating m-1" style="background-color: #333333;" href="https://github.com/sjiwon" role="button">
            <i class="fab fa-github"></i>
        </a>
    </div>
</footer>