<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <title>여기다멍 - 마이페이지(사용자)</title>
            <style>
                body {
                    background: #fff;
                    font-family: '맑은 고딕', Arial, sans-serif;
                    margin: 0;
                    padding: 40px;
                }

                .header {
                    display: flex;
                    align-items: center;
                    margin-top: 40px;
                    margin-bottom: 12px;
                    gap: 14px;
                    margin-left: 55px;
                }

                .logo {
                    width: 48px;
                    height: 48px;
                    background: url('https://cdn-icons-png.flaticon.com/512/616/616408.png') no-repeat center/cover;
                }

                .header-title {
                    font-size: 2.1rem;
                    font-weight: bold;
                }

                .main-wrap {
                    display: flex;
                    justify-content: center;
                    margin-top: 40px;
                }

                .profile-area {
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    min-width: 320px;
                }

                .profile-img {
                    width: 140px;
                    height: 140px;
                    background: #e3e0ec;
                    border: 2px solid #888;
                    border-radius: 15px;
                    margin-bottom: 14px;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }

                .profile-img img {
                    width: 82px;
                    height: 98px;
                }

                .nickname {
                    font-size: 1.3rem;
                    font-weight: 500;
                    margin-bottom: 18px;
                }

                .fav-box,
                .reviews-box {
                    background: #faf8b3;
                    border: 2px solid #888;
                    border-radius: 28px;
                    padding: 16px 30px;
                    box-sizing: border-box;
                    margin-top: 12px;
                    min-width: 260px;
                    max-width: 340px;
                }

                .fav-title,
                .reviews-title {
                    font-size: 1.08rem;
                    margin-bottom: 7px;
                }

                .fav-item {
                    font-size: 1.09rem;
                    display: flex;
                    align-items: center;
                    margin-bottom: 5px;
                    gap: 6px;
                }

                .fav-item:last-child {
                    margin-bottom: 0;
                }

                .bone {
                    font-size: 1.12rem;
                    margin-right: 6px;
                }

                .reviews-list {
                    max-height: 200px;
                    overflow-y: auto;
                    display: flex;
                    flex-direction: column;
                    gap: 10px;
                }

                .review-item {
                    background: #eee;
                    border-radius: 9px;
                    padding: 9px 14px;
                    font-size: 1.02rem;
                    display: flex;
                    align-items: center;
                    gap: 8px;
                    border: 1.2px solid #ccc;
                }

                .flag {
                    font-size: 1.1rem;
                    color: #ef5151;
                    margin-right: 4px;
                }

                @media (max-width: 900px) {
                    .main-wrap {
                        flex-direction: column;
                        align-items: center;
                    }

                    .profile-area {
                        width: 95%;
                        max-width: 95%;
                    }

                    .fav-box,
                    .reviews-box {
                        min-width: unset;
                        max-width: unset;
                        width: 95%;
                    }
                }
            </style>
        </head>

        <body>
            <div class="main-wrap">
                <div class="profile-area">
                    <div class="profile-img"
                        style="font-size:70px; display:flex; align-items:center; justify-content:center;">
                        👤
                    </div>
                    <div class="nickname">${user.nickname}</div>
                    <div class="fav-box">
                        <div class="fav-title"><b>찜</b></div>
                        <c:forEach var="fav" items="${favList}">
                            <div class="fav-item"><span class="bone">🔧</span>${fav.placeName}</div>
                        </c:forEach>
                    </div>
                    <div class="reviews-box">
                        <div class="reviews-title"><b>Review</b></div>
                        <div class="reviews-list">
                            <c:forEach var="review" items="${reviewList}">
                                <div class="review-item">
                                    <span class="flag">🚩</span> <b>${review.placeName}</b> <span
                                        style="color:#888;">"${review.content}"</span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>