<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %> <!-- 공통 네비게이션 바 -->
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
        button {
            width: 100%; padding: 10px;
            background-color: #333; color: white; border: none;
        }
        .error {
	    color: red;
	    font-size: 13px;
		}
    </style>
</head>
<body>
	<div class="form-box">
        <h2>비밀번호 재설정</h2>
		<form:form method="post" action="/auth/reset-password" modelAttribute="signupForm">
		    <form:hidden path="username"/>
		
		    <div class="form-group">
		        <label>새 비밀번호</label>
		        <form:password path="password" />
		        <form:errors path="password" cssClass="error" />
		    </div>
		
		    <div class="form-group">
		        <label>비밀번호 확인</label>
		        <form:password path="confirmPassword" />
		        <form:errors path="confirmPassword" cssClass="error" />
		    </div>
		
		    <div class="form-group">
		        <button type="submit">완료</button>
		    </div>
		</form:form>
	</div>
</body>
</html>
