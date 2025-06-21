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
		    width: 300px;     /* 가로 크기 고정 */
		    height: auto;     /* 세로는 비율에 따라 자동 */
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
			    <c:choose>
			        <c:when test="${not empty image 
			            and fn:contains(image, '/') 
			            and not fn:contains(image, 'via.placeholder.com')}">
			            <img src="${image}" alt="장소 이미지">
			        </c:when>
			        <c:otherwise>
			            <c:choose>
			                <c:when test="${param.type eq '동물병원'}">
			                    <img src="/images/animal_hospital.png" alt="동물병원 이미지">
			                </c:when>
			                <c:when test="${param.type eq '애견카페'}">
			                    <img src="/images/dog_cafe.png" alt="애견카페 이미지">
			                </c:when>
			                <c:when test="${param.type eq '공원'}">
			                    <img src="/images/park.png" alt="공원 이미지">
			                </c:when>
			                <c:otherwise>
			                    <img src="/images/HereIsDog-logo.png" alt="기본 이미지">
			                </c:otherwise>
			            </c:choose>
			        </c:otherwise>
			    </c:choose>
			</div>


            <div class="info-section">
                <div class="highlight">
                    <span>
				        <c:choose>
				            <c:when test="${fromDb}">
				                ${place.name}
				            </c:when>
				            <c:otherwise>
				                ${name}
				            </c:otherwise>
				        </c:choose>
				    </span>
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
				<c:choose>
				  <c:when test="${fromDb}">
				      <div>영업시간: ${place.openingHours}</div>
				      <div>주소: ${place.address}</div>
				      <div>전화번호: ${place.phoneNumber}</div>
				  </c:when>
				  <c:otherwise>
				      <div>영업시간: ${hours}</div>
				      <div>주소: ${address}</div>
				      <div>전화번호: ${phone}</div>
				  </c:otherwise>
				</c:choose>
				
				<c:if test="${sessionScope.loginUser.userType == 'OWNER'}">
	                <form id="placeRegisterForm">
					    <input type="hidden" name="name" value="${name}" />
					    <input type="hidden" name="address" value="${address}" />
					    <input type="hidden" name="description" value="${name}" />
					    <input type="hidden" name="phoneNumber" value="${phone}" />
					    <input type="hidden" name="openingHours" value="${hours}" />
					    <button type="button" onclick="submitMyPlace()">내 가게로 등록</button>
					</form>
				</c:if>
				
				<script>
				function submitMyPlace() {
				    const form = document.getElementById('placeRegisterForm');
				    const formData = new FormData(form);
				    
				    fetch('/places/my', {
				        method: 'POST',
				        body: new URLSearchParams(formData)
				    })
				   .then(res => res.text().then(text => {
					    if (res.status === 200) {
					        alert(text); // 성공
					    } else if (res.status === 400) {
					        alert(text); // 사용자 실수 (이미 등록한 가게 등)
					    } else {
					        alert("서버 오류가 발생했습니다.");
					    }
					}))
								    
					.catch(err => {
				        alert(err.message); // 서버에서 넘긴 예외 메시지 출력
				    });
				}



				</script>

                
                
            </div>
        </div>

        <div class="review-scroll">
            <c:forEach var="rev" items="${reviews}">
                <div>
                    <strong>${rev.userId}</strong> (${rev.rating}점)…
                    <p>${rev.content}</p>
                </div>
            </c:forEach>
            <c:if test="${empty reviews}">
                <div>아직 작성된 리뷰가 없습니다</div>
            </c:if>
        </div>
    
        <!--<c:set var="placeId" value="${fn:replace(phone, '-', '')}" />
        -->
        
        <div class="review-button">
            <a href="${pageContext.request.contextPath}/reviews/${placeId}/new">
                <button type="button">리뷰 작성 하러가기</button>
            </a>
        </div>

        
    </div>
</body>
</html>