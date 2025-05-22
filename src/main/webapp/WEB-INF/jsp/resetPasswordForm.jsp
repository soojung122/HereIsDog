<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>비밀번호 재설정</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }
        .form-box { width: 400px; margin: auto; padding: 20px; border: 1px solid #ccc; }
        .form-box h2 { text-align: center; }
        .form-group { margin: 15px 0; }
        label { display: block; font-weight: bold; margin-bottom: 5px; }
        input[type="password"] {
            width: 100%; padding: 8px;
        }
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
        button {
            width: 100%; padding: 10px;
            background-color: #333; color: white; border: none;
        }
    </style>
</head>
<body>
	<div class="logo">
        <img src="/images/HereIsDog-logo.png" alt="여기다멍 로고">
        <h2>여기다멍</h2>
    </div>
    <div class="form-box">
        <h2>비밀번호 재설정</h2>
        <form method="post" action="/auth/reset-password">
            <input type="hidden" name="username" value="${username}" />

            <div class="form-group">
                <label>새 비밀번호</label>
                <input type="password" name="newPassword" required />
            </div>

            <div class="form-group">
                <label>비밀번호 확인</label>
                <input type="password" name="confirmPassword" required />
            </div>

            <div class="form-group">
                <button type="submit">완료</button>
            </div>
        </form>
    </div>
</body>
</html>
