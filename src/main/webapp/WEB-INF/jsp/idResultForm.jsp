<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>아이디 찾기 결과</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; text-align: center; }
        .result-box { border: 1px solid #ccc; display: inline-block; padding: 30px; }
        .id-value { font-size: 24px; font-weight: bold; color: #333; margin-top: 10px; }
        a { display: block; margin-top: 20px; text-decoration: none; color: #0066cc; }
        .logo {
            margin: 0;
            padding: 10px 20px;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .logo img {
            width: 40px;
            height: 40px;
            margin: 0;
        }

        .logo h2 {
            margin: 0;
            font-size: 24px;
        }
    </style>
</head>
<body>
	<div class="logo">
        <img src="/images/HereIsDog-logo.png" alt="여기다멍 로고">
        <h2>여기다멍</h2>
    </div>
    <div class="result-box">
        <h2>찾은 아이디</h2>
        <div class="id-value">${foundId}</div>
        <a href="/auth/login">로그인 페이지로 이동</a>
    </div>
</body>
</html>
