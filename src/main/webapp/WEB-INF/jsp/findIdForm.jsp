<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %> <!-- 공통 네비게이션 바 -->
<html>
<head>
    <title>아이디 찾기</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }
        .form-box { width: 400px; margin: auto; padding: 20px; border: 1px solid #ccc; }
        .form-box h2 { text-align: center; }
        .form-group { margin: 15px 0; }
        label { display: block; font-weight: bold; margin-bottom: 5px; }
        input[type="text"], input[type="email"] {
            width: 100%; padding: 8px;
        }
        .radio-group {
            display: flex;
            gap: 10px;
            font-size: 14px;
        }
        .radio-group label { font-weight: normal; }
        button {
            width: 100%; padding: 10px;
            background-color: #333; color: white; border: none;
        }
    </style>
</head>
<body>
    <div class="form-box">
        <h2>아이디 찾기</h2>
        <form method="post" action="/auth/verify-id">
            <div class="form-group">
                <label>이메일</label>
                <input type="email" name="email" required />
            </div>

            <div class="form-group">
                <label>회원 유형</label>
                <div class="radio-group">
                    <label><input type="radio" name="role" value="guest" checked>사용자</label>
                    <label><input type="radio" name="role" value="owner">사업자</label>
                </div>
            </div>

            <div class="form-group">
                <button type="submit">본인인증</button>
            </div>
        </form>
    </div>
</body>
</html>
