<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 공통 네비게이션 바에 마이페이지 버튼을 추가하고 싶어서 -->
<%
    request.setAttribute("pageName", "detail");
%>
<%@ include file="/WEB-INF/jsp/header.jsp" %> <!-- 공통 네비게이션 바 -->



<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>${name} - 상세페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f7f7f7;
            padding-top: 70px;
        }
        .header {
            display: flex;
            align-items: center;
            padding: 20px;
            background: white;
            border-bottom: 1px solid #ccc;
        }
        .header .paw {
            font-size: 30px;
            margin-right: 10px;
        }
        .header h2 {
            margin: 0;
            font-size: 24px;
        }
        .container {
            border: 2px solid black;
            margin: 20px auto;
            padding: 20px;
            background: white;
            border-radius: 12px;
            max-width: 800px;
        }
        .main {
            display: flex;
        }
        .image-section {
            width: 60%;
            padding: 20px;
        }
        .image-section img {
            width: 100%;
            border-radius: 8px;
        }
        .info-section {
            width: 40%;
            padding: 20px;
            background: #f0f0f0;
            border-radius: 8px;
        }
        .info-section div {
            background: white;
            padding: 12px;
            margin-bottom: 10px;
            border-radius: 6px;
            font-size: 16px;
        }
        .highlight {
            background: #f9f9c5;
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .highlight button {
            background: none;
            border: none;
            cursor: pointer;
        }
        .highlight img {
            width: 24px;
            height: 24px;
        }
        .review-scroll {
            max-height: 150px;
            overflow-y: auto;
            border: 1px solid #ccc;
            padding: 10px;
            margin-top: 20px;
            background: #fdfdfd;
            border-radius: 8px;
        }
        .review-scroll div {
            margin-bottom: 10px;
            padding-bottom: 5px;
            border-bottom: 1px solid #eee;
        }
        .review-button {
            text-align: center;
            margin-top: 20px;
        }
        .review-button button {
            padding: 12px 24px;
            font-size: 16px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 6px;
        }
    </style>

</head>
<body>

    <div class="container">
        <div class="main">
            <div class="image-section">
                <img src="${image}" alt="장소 이미지">
                <div style="margin-top: 10px;">
                    <c:choose>
                        <c:when test="${not empty place_url}">
                            <a href="${place_url}" target="_blank" style="color: blue;">카카오맵에서 보기</a>
                        </c:when>
                        <c:otherwise>
                            <span style="color: gray;">카카오맵 링크: 준비 중입니다</span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="info-section">
                <div class="highlight">
                    <span>${name}</span>
                    <!-- 찜하기 버튼 (찜 등록) -->
                    <button onclick="addFavorite('${placeId}', '${name}', '${address}')" title="찜하기">
                        <img src="https://cdn-icons-png.flaticon.com/512/616/616408.png" alt="찜">
                    </button>
					
					<script>
					function addFavorite(placeId, name, address) {
					    fetch('/favorites/add', {
					        method: 'POST',
					        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
					        body: new URLSearchParams({
					        	placeId: Number(placeId),
					            name: name,
					            address: address
					            // 필요 시 phone, image, place_url도 추가
					        })
					    })
					    .then(res => res.text())
					    .then(message => {
					        alert(message);  // 서버 응답을 그대로 alert!
					    });
					}

					</script>
                </div>
                <div>영업시간: ${hours}</div>
                <div>주소: ${address}</div>
                <div>전화번호: ${phone}</div>
            </div>
        </div>

		<c:set var="placeId" value="${fn:replace(phone, '-', '')}" />
		
        <div class="review-scroll">
            <div>사용자1: 매우 친절하고 깨끗했어요!</div>
            <div>사용자2: 우리 강아지도 좋아했어요 😊</div>
            <div>사용자3: 주차 공간이 협소한 점만 빼면 좋았어요.</div>
        </div>
			
		
        <div class="review-button">	
      		<a href="/reviews/${placeId}/new?name=${name}&ddress=${address}&image=${image}">
            	<button>리뷰 작성 하러가기</button>
         	</a>
        </div>
    </div>
</body>
</html>
