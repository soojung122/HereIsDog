<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %> <!-- 공통 네비게이션 바 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>리뷰 작성</title>
    <style>
        .container {
            display: flex;
            border: 2px solid #ccc;
            border-radius: 12px;
            padding: 20px;
            max-width: 900px;
            margin: 40px auto;
            background-color: #f8f8f8;
        }

        .image-section {
            width: 60%;
            padding-right: 20px;
        }

        .image-section img {
            width: 100%;
            border-radius: 8px;
            object-fit: cover;
        }

        .form-section {
            width: 40%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
        }

        select, textarea {
            width: 100%;
            font-size: 14px;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        button {
            padding: 10px 20px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        
    .text-danger {
        color: red;
        font-size: 14px;
        margin-top: 5px;
        margin-left: 10px;
    }
</style>

</head>
<body>

    <h2 style="text-align:center;">Review</h2>

    <div class="container">
        <!-- 왼쪽: 장소 이미지 -->
        <div class="image-section">
        <%--<p>타입: ${type}</p> --%>
            <c:choose>
                <c:when test="${type eq '동물병원'}">
                    <img src="/images/animal_hospital.png" alt="동물병원 이미지">
                </c:when>
                <c:when test="${type eq '애견카페'}">
                    <img src="/images/dog_cafe.png" alt="애견카페 이미지">
                </c:when>
                <c:when test="${type eq '공원'}">
                    <img src="/images/park.png" alt="공원 이미지">
                </c:when>
                <c:otherwise>
                    <img src="/images/HereIsDog-logo.png" alt="기본 이미지">
                </c:otherwise>
            </c:choose>

         </div>


        <!-- 오른쪽: 폼 영역 -->
        <div class="form-section">
        <!--<c:url var="SubmitUrl" value="/reviews/${placeId}" />-->
        <form method="post" action="${pageContext.request.contextPath}/reviews/${placeId}">
         
                <input type="hidden" name="userId" value="${userId}">
                <input type="hidden" name="placeId" value="${placeId}">
                <input type="hidden" name="name" value="${name}">
                <input type="hidden" name="image" value="${image}">
                <input type="hidden" name="address" value="${address}">
                <input type="hidden" name="place_url" value="${place_url}">
                <div class="form-group">
                    <label for="rating">평점</label>
                    <select name="rating" id="rating">
                        <option value="">별점을 선택해주세요</option>
                        <option value="5">⭐️⭐️⭐️⭐️⭐️ (5점)</option>
                        <option value="4">⭐️⭐️⭐️⭐️ (4점)</option>
                        <option value="3">⭐️⭐️⭐️ (3점)</option>
                        <option value="2">⭐️⭐️ (2점)</option>
                        <option value="1">⭐️ (1점)</option>
                    </select>
                    <!-- 에러 메시지 출력 -->
                    <c:if test="${errors != null && errors.hasFieldErrors('rating')}">
                    <div class="text-danger">${errors.getFieldError('rating').defaultMessage}</div>
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="content">리뷰</label>
                    <textarea name="content" id="content" rows="6" placeholder="리뷰를 작성해주세요.">${reviewForm.content}</textarea>
                </div>

                <button type="submit">리뷰 저장</button>
            </form>
        </div>
    </div>

</body>
</html>