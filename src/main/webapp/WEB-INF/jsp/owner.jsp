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
            padding: 100px;
        }

        .main-wrap {
            display: flex;
            flex-direction: column;
            max-width: 1000px;
            margin: 0 auto;
        }

        .top-row {
            display: flex;
            gap: 40px;
            align-items: flex-start;
        }

        .profile-area {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 180px;
        }

        .profile-img {
            width: 180px;
            height: 180px;
            background: #e3e0ec;
            border: 2px solid #888;
            border-radius: 15px;
            margin-bottom: 14px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 70px;
        }

        .nickname {
            font-size: 1.25rem;
            font-weight: 500;
            margin-bottom: 14px;
        }

        .info-group {
		    display: flex;
		    flex-direction: column;
		    gap: 4px; /* 제목과 박스 사이 간격 */
		}
		
		.left-column {
		    display: flex;
		    flex-direction: column;
		    gap: 50px; /* 그룹 간 간격 */
		}

        .shop-info-box,
        .shop-form-box,
        .service-box {
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
            margin-bottom: 8px;
            line-height: 1.8;
        }

        .shop-form label {
            display: block;
            margin-bottom: 8px;
            font-size: 1.01rem;
        }

        .shop-form input,
        .shop-form select {
            padding: 7px;
            border-radius: 7px;
            border: 1px solid #bbb;
            margin-bottom: 12px;
            width: 95%;
            font-size: 1rem;
        }

        .inline-radio {
		  display: flex;
		  align-items: center;
		  gap: 20px;
		}
		
		.radio-label-main {
		  font-size: 1rem;
		  margin-right: 8px;
		  position: relative;
		  top: -4px;
		}
		
		.radio-group {
		  display: flex;
		  align-items: center;
		  gap: 4px;
		}
		
		.radio-text {
		  position: relative;
		  top: -0px;  /* 글자만 살짝 위로 */
		  font-size: 1rem;
		}
		
		.inline-radio label {
		    display: flex;
		    align-items: center;
		    flex-direction: row;  /* 수평 정렬 */
		    gap: 6px;             /* 버튼과 텍스트 사이 간격 */
		    font-size: 1rem;
		    white-space: nowrap;  /* 줄바꿈 방지 */
		}
		.inline-radio label span {
		  position: relative;
		  top: -5px;  /* 숫자를 조정해보세요 (예: -1px ~ -4px) */
		}

        .save-btn {
		    margin: 10px auto 0 auto; /* 상단 여백 10px, 좌우 auto로 중앙 정렬 */
		    display: block;
            margin-top: 10px;
            padding: 8px 23px;
            background: #ffea70;
            border: 1.7px solid #888;
            border-radius: 9px;
            font-weight: 600;
            font-size: 1.03rem;
            cursor: pointer;
            color: black; 
        }
        .service-checks {
		    text-align: center;
		    margin-bottom: 20px;
		}

        .service-checks label {
        	margin-bottom: 12px;
            margin-right: 18px;
            font-size: 1.05rem;
        }

        @media (max-width: 950px) {
            .top-row {
                flex-direction: column;
                align-items: center;
            }

            .main-wrap {
                padding: 20px;
            }

            .profile-area,
            .left-column,
            .shop-form-box {
                width: 100%;
                align-items: center;
            }
        }
        .section-title {
		    font-size: 1.2rem;
		    font-weight: bold;
		    margin-bottom: 4px;
		    margin-left: 20px;
		}
		
    </style>
</head>
<body>
<div class="main-wrap">

    <!-- 🔶 프로필 | 가게 정보 + 서비스 기능 | 가게 정보 수정 폼 -->
	<div class="top-row">
	
	    <!-- 프로필 -->
	    <div class="profile-area">
	        <div class="profile-img">👤</div>
	        <div class="nickname">${owner.nickname}</div>
	    </div>
	
	    <!-- 가게 정보 + 서비스 기능 -->
	    <div class="left-column">
	
	        <!-- 가게 정보 -->
			<div class="info-group">
			    <div class="section-title"><span style="color:#ef5151;">🚩</span> 가게 정보</div>
			    <div class="shop-info-box">
			        <div class="shop-detail">
			            이름 : ${shop.name}<br>
			            영업시간 : ${shop.openingHour}<br>
			            주소 : ${shop.address}<br>
			            전화번호 : ${shop.phone}
			        </div>
			    </div>
			</div>
			
			<!-- 서비스 기능 -->
			<div class="info-group">
			    <div class="section-title">서비스 기능 선택</div>
			    <div class="service-box">
				    <form action="/owner/shop/service-update" method="post">
				        <div class="service-checks">
				            <label><input type="checkbox" name="service" value="WIFI" ${shop.serviceWifi ? 'checked' : ''}> Wi-Fi</label>
				            <label><input type="checkbox" name="service" value="PARKING" ${shop.serviceParking ? 'checked' : ''}> 주차</label>
				            <label><input type="checkbox" name="service" value="PET" ${shop.servicePet ? 'checked' : ''}> 반려동물 동반</label>
				        </div>
				        <div style="text-align: center;">
				            <button type="submit" class="save-btn">저장</button>
				        </div>
				    </form>
				</div>
			</div>
	    </div>
	
	    <!-- 🔸 가게 정보 수정 제목 -->
	    <div>
	        <div class="section-title">가게 정보 수정</div>
	        <div class="shop-form-box">
	            <form class="shop-form" action="/owner/shop/update" method="post">
	                <label>이름
	                    <input type="text" name="name" value="${shop.name}">
	                </label>
	                <label>영업시간
	                    <select name="openingHour">
	                        <option value="9-18" ${shop.openingHour eq '9-18' ? 'selected' : ''}>09:00~18:00</option>
	                        <option value="24hours" ${shop.openingHour eq '24hours' ? 'selected' : ''}>24시간</option>
	                    </select>
	                </label>
	                <label>전화번호
	                    <input type="text" name="phone" value="${shop.phone}">
	                </label>
	                <div class="inline-radio">
					  <label class="radio-label-main">영업상태</label>
					
					  <label class="radio-group">
					    <input type="radio" name="status" value="OPEN" ${shop.status eq 'OPEN' ? 'checked' : ''}>
					    <span class="radio-text">영업중</span>
					  </label>
					
					  <label class="radio-group">
					    <input type="radio" name="status" value="CLOSE" ${shop.status eq 'CLOSE' ? 'checked' : ''}>
					    <span class="radio-text">휴업</span>
					  </label>
					</div>

	                <button type="submit" class="save-btn">저장</button>
	            </form>
	        </div>
	    </div>
	
	</div>
    

</div>
</body>
</html>
