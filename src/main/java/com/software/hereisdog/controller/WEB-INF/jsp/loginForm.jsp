<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %> <!-- 공통 네비게이션 바 -->
<html>
<head>
    <title>여기다멍 - 로그인</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }

        .form-box {
            width: 400px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
        }

        .form-box h2 { text-align: center; }

        .form-group { margin: 15px 0; }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="password"] {
            padding: 8px;
            width: 100%;
        }

        .id-radio-wrapper {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .radio-group-inline {
            display: flex;
            gap: 10px;
            font-size: 14px;
        }

        .radio-group-inline label {
            font-weight: normal;
        }

        .error { color: red; font-size: 0.9em; }

        button {
            width: 100%;
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
        }

        .link-group {
            margin-top: 10px;
            text-align: center;
        }

        .link-group a {
            text-decoration: none;
            color: #0066cc;
            margin: 0 10px;
            font-size: 14px;
        }
        .label-radio-row {
		    display: flex;
		    align-items: center;
		    justify-content: space-between;
		    margin-bottom: 5px;
		}
		
		.label-radio-row label {
		    font-weight: bold;
		    margin: 0;
		}
		
		.radio-group-inline {
		    display: flex;
		    gap: 10px;
		    font-size: 14px;
		}
		
		.radio-group-inline label {
		    font-weight: normal;
		}
    </style>
</head>
<body>
    <div class="form-box">
        <h2>로그인</h2>

        <form:form modelAttribute="loginForm" method="post" action="/auth/login">

            <div class="form-group">
			    <div class="label-radio-row">
			        <label for="username">아이디</label>
			        <div class="radio-group-inline">
			            <label><input type="radio" name="role" value="guest" checked> 방문객</label>
			            <label><input type="radio" name="role" value="owner"> 오너</label>
			        </div>
			    </div>
			
			    <form:input path="username" id="username" />
			    <form:errors path="username" cssClass="error" />
			</div>

            <div class="form-group">
                <label>비밀번호</label>
                <form:password path="password" />
                <form:errors path="password" cssClass="error" />
            </div>

            <div class="form-group">
                <form:errors cssClass="error" element="div" />
            </div>

            <div class="form-group">
                <button type="submit">로그인</button>
            </div>
			
			
			<div class="form-group">
			    <button type="button" style="background-color: #e0e0e3; color: #333; border: 1px solid #bbb;" onclick="location.href='/auth/signup'">회원가입</button>
			</div>

            <div class="link-group">
                <a href="/auth/find-id">아이디 찾기</a> |
                <a href="/auth/find-password">비밀번호 찾기</a>
            </div>

        </form:form>
    </div>
</body>
</html>
