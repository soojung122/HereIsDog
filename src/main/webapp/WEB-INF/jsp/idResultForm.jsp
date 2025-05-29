<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %> <!-- 공통 네비게이션 바 -->
<html>
<head>
    <title>아이디 찾기 결과</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; text-align: center; }
        .result-box { border: 1px solid #ccc; display: inline-block; padding: 30px; }
        .id-value { font-size: 24px; font-weight: bold; color: #333; margin-top: 10px; }
        a { display: block; margin-top: 20px; text-decoration: none; color: #0066cc; }

    </style>
</head>
<body>
    <div class="result-box">
        <h2>찾은 아이디</h2>
        <div class="id-value">${foundId}</div>
        <a href="/auth/login">로그인 페이지로 이동</a>
    </div>
</body>
</html>
