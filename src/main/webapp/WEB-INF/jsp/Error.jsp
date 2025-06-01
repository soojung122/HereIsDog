<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="IncludeTop.jsp" %>

<div style="margin: 100px auto; max-width: 600px; text-align: center;">
    <h1 style="color: red;">오류 발생</h1>
    <p><strong><c:out value="${message}" default="요청을 처리하는 중 오류가 발생했습니다." /></strong></p>

    <br><br>
    <a href="/" style="text-decoration: none; padding: 10px 20px; background-color: #4CAF50; color: white; border-radius: 6px;">
        홈으로 돌아가기
    </a>
</div>

<%@ include file="IncludeBottom.jsp" %>
