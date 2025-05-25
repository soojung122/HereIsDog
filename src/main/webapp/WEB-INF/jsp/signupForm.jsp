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
        .error { color: red; font-size: 0.9em; margin-top: 4px; }
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
                <form:errors path="username" cssClass="error" />
            </div>

            <div class="form-group">
                <label>비밀번호</label>
                <p>최소 8자 이상이어야 합니다</p>
                <form:password path="password" />
                <form:errors path="password" cssClass="error" />
            </div>

            <div class="form-group">
                <label>비밀번호 확인</label>
                <form:password path="confirmPassword" />
                <form:errors path="confirmPassword" cssClass="error" />
            </div>

            <div class="form-group">
                <label>이메일</label>
                <form:input path="email" />
                <form:errors path="email" cssClass="error" />
            </div>

            <div class="form-group">
                <label>
                    <input type="checkbox" id="isOwner" onclick="toggleBusinessNumber()">
                    사업자 (Owner) 권한 요청
                </label>
            </div>

            <div class="form-group" id="businessNumberGroup" style="display: none;">
                <label>사업자 번호 (Owner만 입력)</label>
                <form:input path="businessNumber" />
                <form:errors path="businessNumber" cssClass="error" />
            </div>

            <div class="form-group">
                <form:errors cssClass="error" element="div" />
            </div>

            <div class="form-group">
                <button type="submit">가입하기</button>
            </div>

        </form:form>
    </div>
</body>
</html>
