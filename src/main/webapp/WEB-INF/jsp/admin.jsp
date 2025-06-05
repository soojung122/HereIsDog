<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>여기다멍 - 관리자 페이지</title>
    <style>
        body { background: #fff; font-family: '맑은 고딕', Arial, sans-serif; margin: 0; padding: 0; }
        .header { display: flex; align-items: center; margin-top: 40px; margin-bottom: 18px; gap: 14px; margin-left: 55px; }
        .logo { width: 48px; height: 48px; background: url('https://cdn-icons-png.flaticon.com/512/616/616408.png') no-repeat center/cover; }
        .header-title { font-size: 2.1rem; font-weight: bold; }
        .admin-title {
            background: #f8f8f8; border: 2px solid #888; border-radius: 6px;
            padding: 8px 40px; font-size: 1.25rem; margin: 0 auto; display: block; text-align: center; margin-bottom: 30px;
        }
        .manage-box { margin: 0 auto 18px auto; width: 93%; }
        .manage-label {
            font-size: 1.25rem; font-weight: bold;
            border: 2px solid #888;
            background: #fff;
            padding: 9px 30px;
            border-radius: 8px;
            display: inline-block;
            margin-bottom: 18px;
        }
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
            font-size: 0.98rem;
            padding: 6px 17px;
            margin-left: 16px;
            cursor: pointer;
        }
        @media (max-width: 900px) {
            .member-table { flex-direction: column; gap: 25px; }
            .manage-box { width: 99%; }
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo"></div>
        <span class="header-title">여기다멍</span>
        <span class="admin-title">관리자 페이지</span>
    </div>
    <div class="manage-box">
        <div class="manage-label">회원 관리(방문객, 오너)</div>
        <div class="member-table">
            <!-- 왼쪽: 방문객(일반회원) 리스트 -->
            <div class="member-list">
                <c:forEach var="cust" items="${customerList}">
                    <div class="member-card">
                        <span>
                            방문객&nbsp;|&nbsp;ID: <b>${cust.username}</b>
                            &nbsp;PW: <b>${cust.password}</b>
                            &nbsp;EMAIL: <b>${cust.email}</b>
                            <!-- 필요한 정보 추가 -->
                        </span>
                        <form action="/admin/customer/delete" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${cust.id}">
                            <button type="submit" class="delete-btn">삭제</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
            <!-- 오른쪽: 오너(사업자) 리스트 -->
            <div class="member-list">
                <c:forEach var="own" items="${ownerList}">
                    <div class="member-card">
                        <span>
                            오너&nbsp;|&nbsp;ID: <b>${own.username}</b>
                            &nbsp;PW: <b>${own.password}</b>
                            &nbsp;EMAIL: <b>${own.email}</b>
                            &nbsp;사업자번호: <b>${own.businessNumber}</b>
                            <!-- 가게정보 등 추가 가능 -->
                        </span>
                        <form action="/admin/owner/delete" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${own.id}">
                            <button type="submit" class="delete-btn">삭제</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
