<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            padding: 100px;
        }

        .main-wrap {
            max-width: 900px;
            margin: 0 auto;
        }

        .profile-row {
            display: flex;
            gap: 300px; /* 두 박스 사이 간격 */
    		justify-content: flex-start;
            align-items: flex-start;
        }

        .profile-img-box {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .profile-img {
            width: 180px;
            height: 180px;
            background: #e3e0ec;
            border: 2px solid #888;
            border-radius: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 70px;
        }

        .nickname {
            font-size: 1.3rem;
            font-weight: 500;
            margin-top: 12px;
        }

        .fav-box {
            background: #fdfca3;
            border: 2px solid #888;
            border-radius: 20px;
            padding: 18px 20px;
            min-width: 240px;
            width: 300px;  
		    height: 130px;    
        }

        .fav-item {
            font-size: 1.02rem;
            display: flex;
            align-items: center;
            margin-bottom: 6px;
        }

        .fav-item:last-child {
            margin-bottom: 0;
        }
		.fav-wrapper {
		    display: flex;
		    flex-direction: column;
		    align-items: flex-start;  /* 제목과 박스를 왼쪽 정렬 */
		    gap: 8px;
		    margin-top: 12px;
		}
		.fav-title {
		    text-align: left; 
		    font-weight: bold;
		    font-size: 1.1rem;
		    margin-bottom: 10px;
		    margin-left: 30px;
		}
		.fav-scroll {
		    max-height: 130px;
		    overflow-y: auto;
		    padding-right: 10px;  /* 스크롤바와 텍스트 간격 */
		    box-sizing: content-box;
		}
        .bone {
            margin-right: 6px;
        }

        .reviews-box {
            margin-top: 10px;
            background: #fdfca3;
            border: 2px solid #888;
            border-radius: 30px;
            padding: 25px;
            width: 100%;  
		    height: 200px;    
            
        }

        .reviews-title {
            font-size: 1.2rem;
            font-weight: bold;
            margin-bottom: 15px;
            margin-top: 40px;
            margin-left: 30px;
        }

        .reviews-list {
            display: flex;
            flex-direction: column;
            gap: 15px;
            padding-right: 20px; 
        }

        .review-item {
            background: #eee;
            border-radius: 10px;
            padding: 10px 15px;
            font-size: 1.05rem;
            display: flex;
            align-items: center;
            gap: 8px;
            border: 1px solid #bbb;
        }
        .reviews-scroll {
		    max-height: 200px;        
		    overflow-y: auto;
		    border-radius: 20px;      
		}

        .flag {
            color: #ef5151;
        }

        @media (max-width: 768px) {
            .profile-row {
                flex-direction: column;
                align-items: center;
                gap: 20px;
            }

            .fav-box {
                width: 100%;
            }

            .reviews-box {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="main-wrap">
    <div class="profile-row">
	    <div class="profile-img-box">
	        <div class="profile-img">👤</div>
	        <div class="nickname">${user.username}</div>
	    </div>
	
	    <div class="fav-column">
	        <div class="fav-title">찜 목록<br><span style="font-size: 0.9rem; font-weight: normal;"></span></div>
	        <div class="fav-box">
			    <div class="fav-scroll">
			        <c:choose>
			            <c:when test="${empty favList}">
			                <div class="fav-item"><span class="bone">🦴</span> 예시카페 · 강남구</div>
			                <div class="fav-item"><span class="bone">🦴</span> 멍멍하우스 · 마포구</div>
			                <div class="fav-item"><span class="bone">🦴</span> 예시카페 · 강남구</div>
			                <div class="fav-item"><span class="bone">🦴</span> 멍멍하우스 · 마포구</div>
			                <div class="fav-item"><span class="bone">🦴</span> 예시카페 · 강남구</div>
			                <div class="fav-item"><span class="bone">🦴</span> 멍멍하우스 · 마포구</div>
			            </c:when>
			            <c:otherwise>
			                <c:forEach var="fav" items="${favList}">
			                    <div class="fav-item">
			                        <span class="bone">🦴</span> ${fav.name}
			                    </div>
			                </c:forEach>
			            </c:otherwise>
			        </c:choose>
			    </div>
			</div>
	    </div>
	</div>

    <div class="reviews-title">작성한 리뷰</div>
		<div class="reviews-box">
			<div class="reviews-scroll"> 
			    <div class="reviews-list">
				    <c:choose>
				        <c:when test="${empty reviewList}">
				            <div class="review-item">
				                <span class="flag">🚩</span> <b>예시카페</b>
				                <span style="color:#888;">"분위기가 좋아요!"</span>
				            </div>
				            <div class="review-item">
				                <span class="flag">🚩</span> <b>멍멍펍</b>
				                <span style="color:#888;">"반려견과 편하게 즐겼어요."</span>
				            </div>
				            <div class="review-item">
				                <span class="flag">🚩</span> <b>예시카페</b>
				                <span style="color:#888;">"분위기가 좋아요!"</span>
				            </div>
				            <div class="review-item">
				                <span class="flag">🚩</span> <b>멍멍펍</b>
				                <span style="color:#888;">"반려견과 편하게 즐겼어요."</span>
				            </div>
				        </c:when>
				        <c:otherwise>
				            <c:forEach var="review" items="${reviewList}">
							    <div class="review-item">
							        <c:set var="placeFull" value="${review.placeId}" />
							        <c:set var="placeName" value="${fn:substringBefore(placeFull, '_')}" />
							        <span class="flag">🚩</span>
							        <b>${placeName}</b>
							        <span style="color:#888;">"${review.content}"</span>
							    </div>
							</c:forEach>
				        </c:otherwise>
				    </c:choose>
				</div>
			</div>
		</div>
</div>
</body>
</html>
