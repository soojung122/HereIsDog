<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>여기다멍 - 마이페이지(사업자)</title>
    <style>
        body { background: #fff; font-family: '맑은 고딕', Arial, sans-serif; margin: 0;}
        .header { display: flex; align-items: center; margin-top: 40px; margin-bottom: 18px; gap: 14px; margin-left: 55px; }
        .logo { width: 48px; height: 48px; background: url('https://cdn-icons-png.flaticon.com/512/616/616408.png') no-repeat center/cover; }
        .header-title { font-size: 2.1rem; font-weight: bold; }
        .mypage-title {
            background: #f8f8f8; border: 2px solid #888; border-radius: 6px;
            padding: 8px 30px; font-size: 1.13rem; margin-left: 18px;
        }
        .main-wrap { display: flex; align-items: flex-start; justify-content: center; gap: 40px; width: 100%; max-width: 1200px; margin: 0 auto; margin-top: 18px;}
        .profile-area { min-width: 260px; max-width: 280px; display: flex; flex-direction: column; align-items: center;}
        .profile-img { width: 140px; height: 140px; background: #e3e0ec; border: 2px solid #888; border-radius: 15px; margin-bottom: 14px; display: flex; align-items: center; justify-content: center; font-size:70px;}
        .nickname { font-size: 1.25rem; font-weight: 500; margin-bottom: 14px;}
        .shop-form-box, .shop-info-box, .service-box {
            background: #faf8b3; border: 2px solid #888; border-radius: 28px;
            padding: 18px 30px; box-sizing: border-box;
            margin-bottom: 18px; min-width: 330px; max-width: 420px;
        }
        .shop-info-box { margin-bottom: 28px; }
        .shop-title { font-size: 1.13rem; margin-bottom: 8px; font-weight: bold;}
        .shop-detail { font-size: 1.13rem; margin-bottom: 8px; line-height: 1.8;}
        .shop-form label { display:block; margin-bottom: 8px; font-size: 1.01rem;}
        .shop-form input, .shop-form select { padding: 7px; border-radius: 7px; border:1px solid #bbb; margin-bottom: 12px; width: 95%; font-size: 1rem;}
        .shop-form .inline-radio { display: flex; align-items: center; gap: 15px; margin-bottom: 13px;}
        .shop-form .inline-radio label { margin-bottom: 0;}
        .save-btn {
            margin-top: 10px;
            padding: 8px 23px;
            background: #ffea70;
            border: 1.7px solid #888;
            border-radius: 9px;
            font-weight: 600;
            font-size: 1.03rem;
            cursor: pointer;
        }
        .service-box { padding-top: 11px; }
        .service-checks label { margin-right: 18px; font-size: 1.05rem;}
        @media (max-width: 950px) {
            .main-wrap { flex-direction: column; align-items: center; gap: 18px;}
            .profile-area, .right-area { width: 95%; max-width: 95%; }
        }
    </style>
</head>
<body>
    <div class="main-wrap">
        <div class="profile-area">
            <div class="profile-img">👤</div>
            <div class="nickname">${owner.nickname}</div>
        </div>
        <div class="right-area" style="display:flex; flex-direction:column; align-items:center;">
            <div class="shop-info-box">
                <div class="shop-title"><span style="color:#ef5151;">🚩</span>가게 정보</div>
                <div class="shop-detail">
                    이름 : ${shop.name}<br>
                    영업시간 : ${shop.openingHour}<br>
                    주소 : ${shop.address}<br>
                    전화번호 : ${shop.phone}
                </div>
            </div>
            <!-- 가게 정보 수정폼 -->
            <div class="shop-form-box">
                <div class="shop-title">가게 정보 수정</div>
                <form class="shop-form" action="/owner/shop/update" method="post">
                    <label>이름
                        <input type="text" name="name" value="${shop.name}">
                    </label>
                    <label>영업시간
                        <select name="openingHour">
                            <option value="9-18" ${shop.openingHour eq '9-18' ? 'selected' : ''}>09:00~18:00</option>
                            <option value="24hours" ${shop.openingHour eq '24hours' ? 'selected' : ''}>24시간</option>
                            <!-- 필요한 옵션 추가 -->
                        </select>
                    </label>
                    <label>전화번호
                        <input type="text" name="phone" value="${shop.phone}">
                    </label>
                    <div class="inline-radio">
                        <label>영업상태</label>
                        <label><input type="radio" name="status" value="OPEN" ${shop.status eq 'OPEN' ? 'checked' : ''}>영업중</label>
                        <label><input type="radio" name="status" value="CLOSE" ${shop.status eq 'CLOSE' ? 'checked' : ''}>휴업</label>
                    </div>
                    <button type="submit" class="save-btn">저장</button>
                </form>
            </div>
            <!-- 서비스 기능 선택 -->
            <div class="service-box">
                <div class="shop-title">서비스 기능 선택</div>
                <form action="/owner/shop/service-update" method="post" class="service-checks">
                    <label><input type="checkbox" name="service" value="WIFI" ${shop.serviceWifi ? 'checked' : ''}> Wi-Fi</label>
                    <label><input type="checkbox" name="service" value="PARKING" ${shop.serviceParking ? 'checked' : ''}> 주차</label>
                    <label><input type="checkbox" name="service" value="PET" ${shop.servicePet ? 'checked' : ''}> 반려동물 동반</label>
                    <!-- 더 많은 서비스 옵션 필요하면 추가 -->
                    <button type="submit" class="save-btn" style="margin-left:14px;">저장</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
