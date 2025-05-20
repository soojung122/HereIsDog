<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>여기다멍 - 회원가입</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }
        .form-box { width: 400px; margin: auto; padding: 20px; border: 1px solid #ccc; }
        .form-box h2 { text-align: center; }
        .form-group { margin: 15px 0; }
        label { display: block; font-weight: bold; }
        input[type="text"], input[type="password"], input[type="email"] {
            width: 100%; padding: 8px; margin-top: 5px;
        }
        .owner-extra { display: none; }
        button { width: 100%; padding: 10px; background-color: #333; color: white; border: none; }
    </style>
    <script>
        function toggleBusinessNumber() {
            const checkbox = document.getElementById("isOwner");
            const bizField = document.getElementById("businessNumberGroup");
            bizField.style.display = checkbox.checked ? "block" : "none";
        }
    </script>
</head>
<body>
    <div class="form-box">
        <h2>회원가입</h2>

        <form:form modelAttribute="signupForm" method="post" action="/auth/signup">
            <div class="form-group">
                <label>아이디</label>
                <form:input path="username" />
            </div>

            <div class="form-group">
                <label>비밀번호</label>
                <p>최소 6자 이상이어야 합니다</p>
                <form:password path="password" />
            </div>

            <div class="form-group">
                <label>닉네임</label>
                <form:input path="nickname" />
            </div>

            <div class="form-group">
                <label>이메일</label>
                <form:input path="email" />
            </div>

            <div class="form-group">
                <label>
                    <input type="checkbox" id="isOwner" onclick="toggleBusinessNumber()">
                    사업자 (Owner) 권한 요청
                </label>
            </div>

            <div class="form-group" id="businessNumberGroup">
                <label>사업자 번호 (Owner만 입력)</label>
                <form:input path="businessNumber" />
            </div>

            <div class="form-group">
                <button type="submit">가입하기</button>
            </div>
        </form:form>
    </div>
</body>
</html>
