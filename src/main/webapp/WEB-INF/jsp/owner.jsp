<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>여기다멍 - 마이페이지(사업자)</title>
  <style>
    body {
      background: #fff;
      font-family: '맑은 고딕', Arial, sans-serif;
      margin: 0;
    }
    .main-wrap {
      display: flex;
      align-items: flex-start;
      justify-content: center;
      gap: 40px;
      max-width: 1200px;
      margin: 40px auto 20px;
      padding: 0 20px;
      box-sizing: border-box;
    }

    /* 프로필 박스 */
    .profile-area {
      display: flex;
      align-items: center;
      gap: 12px;
      min-width: 260px;
      max-width: 280px;
    }
    .profile-img {
      width: 140px;
      height: 140px;
      background: #e3e0ec;
      border: 2px solid #888;
      border-radius: 15px;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 70px;
    }
    .nickname {
      font-size: 1.25rem;
      font-weight: 500;
    }

    /* 가게 정보 박스 */
    .shop-info-box {
      background: #faf8b3;
      border: 2px solid #888;
      border-radius: 28px;
      padding: 18px 30px;
      box-sizing: border-box;
      min-width: 330px;
      max-width: 420px;
    }
    .shop-title {
      font-size: 1.13rem;
      margin-bottom: 8px;
      font-weight: bold;
    }
    .shop-detail {
      font-size: 1.13rem;
      line-height: 1.8;
    }

    .shop-form-box {
      background: #faf8b3;
      border: 2px solid #888;
      border-radius: 28px;
      padding: 18px 30px;
      box-sizing: border-box;
      margin: 0 auto 40px;
      max-width: 960px;
    }
    .shop-form {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
    }
    .shop-form label {
      flex: 1 1 200px;
      display: flex;
      flex-direction: column;
      font-size: 1rem;
    }
    .shop-form input,
    .shop-form select {
      margin-top: 6px;
      padding: 8px;
      font-size: 1rem;
      border: 1px solid #bbb;
      border-radius: 4px;
      width: 100%;
      box-sizing: border-box;
    }
    .inline-radio {
      display: flex;
      align-items: center;
      gap: 15px;
      margin-top: 6px;
    }
    .save-btn {
      flex: 1 1 100%;
      padding: 10px 0;
      background: #ffea70;
      border: 1.7px solid #888;
      border-radius: 9px;
      font-size: 1.03rem;
      font-weight: 600;
      cursor: pointer;
      margin-top: 10px;
    }

    @media (max-width: 950px) {
      .main-wrap {
        flex-direction: column;
        align-items: center;
        gap: 20px;
      }
      .shop-form-box {
        width: 95%;
      }
    }
  </style>
</head>
<body>

  <!-- 1) 프로필 + 가게 정보 -->
  <div class="main-wrap">
    <!-- 프로필 -->
    <div class="profile-area">
      <div class="profile-img">👤</div>
      <div class="nickname">${owner.nickname}</div>
    </div>

    <!-- 가게 정보 -->
    <div class="shop-info-box">
      <div class="shop-title">
        <span style="color:#ef5151;">🚩</span> 가게 정보
      </div>
      <div class="shop-detail">
        이름       : ${shop.name}<br>
        영업시간   : ${shop.openingHour}<br>
        주소       : ${shop.address}<br>
        전화번호   : ${shop.phone}
      </div>
    </div>
  </div>

  <!-- 2) 가게 정보 수정 폼 전체 너비 -->
  <div class="shop-form-box">
    <div class="shop-title">가게 정보 수정</div>
    <form class="shop-form" action="/owner/shop/update" method="post">
      <label>이름
        <input type="text" name="name" value="${shop.name}">
      </label>

      <label>영업시간
        <select name="openingHour">
          <option value="9-18"   ${shop.openingHour eq '9-18'   ? 'selected' : ''}>09:00~18:00</option>
          <option value="24hours"${shop.openingHour eq '24hours'? 'selected' : ''}>24시간</option>
        </select>
      </label>

      <label>전화번호
        <input type="text" name="phone" value="${shop.phone}">
      </label>
<!--    <label class="inline-radio">
        <input type="radio" name="status" value="open"   ${shop.status eq 'open'   ? 'checked' : ''}/> 영업중
        <input type="radio" name="status" value="closed" ${shop.status eq 'closed' ? 'checked' : ''}/> 휴무
      </label> -->
      <button type="submit" class="save-btn">저장</button>
    </form>
  </div>

</body>
</html>
