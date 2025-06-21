<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>여기다멍 - 관리자 페이지</title>
    <style>
        body { background: #fff; font-family: '맑은 고딕', Arial, sans-serif; margin: 0; padding: 100px; }
        .manage-label {
            font-size: 1.25rem; font-weight: bold;
            border: 2px solid #888; background: #fff;
            padding: 9px 30px; border-radius: 8px;
            display: inline-block; margin-bottom: 18px;
        }
        .manage-box { margin: 0 auto 18px auto; width: 93%; }
        .member-table {
            width: 100%;
            display: flex;
            flex-direction: row;
            gap: 40px;
        }
        .member-list {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 18px;
        }
        .member-card {
            background: #ededed;
            border: 2px solid #aaa;
            border-radius: 9px;
            padding: 15px 23px;
            font-size: 1.11rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            min-width: 230px;
        }
        .delete-btn {
            background: #ffc3c3;
            border: 1.5px solid #b88;
            border-radius: 7px;
            color: #a33;
            font-weight: 600;
            font-size: 0.88rem;
            padding: 6px 17px;
            margin-left: 16px;
            cursor: pointer;
            white-space: nowrap     /* 강제 줄바꿈 안되에 */
        }
        @media (max-width: 900px) {
            .member-table { flex-direction: column; gap: 25px; }
            .manage-box { width: 99%; }
        }
    </style>
</head>
<body>
<div class="manage-box">
    <div class="manage-label">회원 관리(방문객, 오너)</div>
    <div class="member-table">
        <!-- 왼쪽: 방문객 -->
        <div class="member-list">
            <c:choose>
                <c:when test="${not empty customerList}">
                    <c:forEach var="cust" items="${customerList}">
                        <div class="member-card">
                            <span>
                                방문객 | ID: <b>${cust.username}</b>
                                &nbsp;PW: <b>${cust.password}</b>
                                &nbsp;EMAIL: <b>${cust.email}</b>
                            </span>
                            <form action="/admin/customer/delete" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${cust.id}">
                                <button type="submit" class="delete-btn">삭제</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <!-- 예시 데이터 (6개) -->
                    <c:forEach var="i" begin="1" end="6">
                        <div class="member-card">
                            <span>
                                방문객${i} 데이터 (아이디: guest${i}, 비밀번호: ****, 이메일: guest${i}@test.com)
                            </span>
                            <button type="button" class="delete-btn">삭제</button>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- 오른쪽: 오너 -->
        <div class="member-list">
            <c:choose>
                <c:when test="${not empty ownerList}">
                    <c:forEach var="own" items="${ownerList}">
                        <div class="member-card">
                            <span>
                                오너 | ID: <b>${own.username}</b>
                                &nbsp;PW: <b>${own.password}</b>
                                &nbsp;EMAIL: <b>${own.email}</b>
                                &nbsp;사업자번호: <b>${own.businessNumber}</b>
                            </span>
                            <form action="/admin/owner/delete" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${own.id}">
                                <button type="submit" class="delete-btn">삭제</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <!-- 예시 데이터 (6개) -->
                    <c:forEach var="i" begin="1" end="6">
                        <div class="member-card">
                            <span>
                                오너${i} 데이터 (아이디: owner${i}, 비밀번호: ****, 이메일: owner${i}@biz.com, 사업자번호: 123-45-00${i})
                            </span>
                            <button type="button" class="delete-btn">삭제</button>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
