<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<%
    String type = request.getParameter("type");
    if (type == null) type = "공원";  // 기본값
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여기다멍 지도 페이지</title>
    <style>
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: Arial, sans-serif;
            box-sizing: border-box;
        }

        body {
            display: flex;
            flex-direction: column;
        }

        .logo {
            margin: 30px 20px 0 20px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .logo .paw {
            font-size: 40px;
            margin: 0;
        }

        .logo h2 {
            margin: 0;
            font-size: 28px;
        }

        .filter-dropdown {
            background: #ffffff;
            width: 100%;
            text-align: right;
            padding: 10px 20px;
            box-sizing: border-box;
        }

        .filter-dropdown select {
            padding: 10px;
            border: 1px solid black;
            cursor: pointer;
            background: white;
        }

        .container {
            display: flex;
            width: 100%;
            height: 500px;
            box-sizing: border-box;
        }

        .sidebar {
            width: 30%;
            padding: 20px;
            background: #f9f9c5;
            margin-right: 10px;
            box-sizing: border-box;
            height: 100%;
        }

        .map-container {
            flex-grow: 1;
            background: #f0f0f0;
            position: relative;
            height: 100%;
            box-sizing: border-box;
        }

        .slide-panel {
            margin-top: 10px;
            height: 100%;
            overflow-y: auto;
            box-sizing: border-box;
        }

        .slide-panel div {
            background: #ffffcc;
            padding: 20px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            font-size: 18px;
            height: 120px;
            display: flex;
            align-items: center;
            cursor: pointer;
            box-sizing: border-box;
            transition: background 0.3s;
        }

        .slide-panel div.highlight {
            background: #ffd700 !important;
            border: 2px solid #ff9900;
        }

        .map {
            width: 100%;
            height: 100%;
            background: gray;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="logo">
        <div class="paw">🐾</div>
        <h2>여기다멍</h2>
    </div>
    
   	<div class="filter-dropdown">
	    <select id="subFilter" onchange="applySubFilterAjax(this)">
	        <option value="all">전체</option>
	        <option value="24hours">24시간 운영</option>
	        <option value="emergency">응급실</option>
	    </select>
	</div>

    <div class="container">
        <div class="sidebar">
            <div class="slide-panel">
                <div>공원 정보1(운영시간, 위치, ~)</div>
                <div>공원 정보2(운영시간, 위치, ~)</div>
                <div>공원 정보3(운영시간, 위치, ~)</div>
                <div>공원 정보1(운영시간, 위치, ~)</div>
                <div>공원 정보2(운영시간, 위치, ~)</div>
                <div>공원 정보3(운영시간, 위치, ~)</div>
            </div>
        </div>
        <div class="map-container">
            <div id="map" class="map"></div>
        </div>
    </div>

    <!-- Kakao 지도 API -->
    <script>
    const kakaoScript = document.createElement("script");
    kakaoScript.src = "https://dapi.kakao.com/v2/maps/sdk.js?appkey=303355970f1d1827f32ff70b2b262aed&autoload=false&libraries=services";
    kakaoScript.onload = function () {
        kakao.maps.load(function () {
            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(37.5665, 126.9780),
                level: 3
            };
            const map = new kakao.maps.Map(container, options);

            const ps = new kakao.maps.services.Places();
            const type = "<%= type %>";
            const slidePanel = document.querySelector(".slide-panel");
            const markers = [];

            let currentInfoWindow = null;  // ✅ 현재 열린 InfoWindow 저장용

            ps.keywordSearch(type, function (data, status) {
                if (status === kakao.maps.services.Status.OK) {
                    const bounds = new kakao.maps.LatLngBounds();
                    slidePanel.innerHTML = "";

                    data.forEach(function (place, index) {
                        const pos = new kakao.maps.LatLng(place.y, place.x);
                        const marker = new kakao.maps.Marker({
                            map: map,
                            position: pos
                        });
                        markers.push(marker);

                        const infowindow = new kakao.maps.InfoWindow({
                            content:
                                '<div style="padding:5px; font-size:14px;">' +
                                '<strong><a href="' + place.place_url + '" target="_blank">' + place.place_name + '</a></strong><br/>' +
                                (place.road_address_name || place.address_name) + '<br/>' +
                                (place.phone || '') +
                                '</div>'
                        });

                        kakao.maps.event.addListener(marker, 'click', function () {
                            if (currentInfoWindow) currentInfoWindow.close(); // ✅ 이전 InfoWindow 닫기
                            infowindow.open(map, marker);
                            currentInfoWindow = infowindow;
                            highlightSlide(index);
                        });

                        const placeDiv = document.createElement("div");
                        placeDiv.classList.add("place-card");

                        placeDiv.innerHTML =
                            '<strong><a href="' + place.place_url + '" target="_blank" style="text-decoration: none; color: black;">' +
                            place.place_name + '</a></strong>' +
                            '<span>📍 ' + (place.road_address_name || place.address_name) + '</span>' +
                            (place.phone ? '<span>📞 ' + place.phone + '</span>' : '');

                        placeDiv.dataset.index = index;

                        placeDiv.onclick = (function (marker, infowindow, pos, index) {
                            return function () {
                                map.setCenter(pos);
                                if (currentInfoWindow) currentInfoWindow.close(); // ✅ 이전 InfoWindow 닫기
                                infowindow.open(map, marker);
                                currentInfoWindow = infowindow;
                                highlightSlide(index);
                            };
                        })(marker, infowindow, pos, index);

                        slidePanel.appendChild(placeDiv);
                        bounds.extend(pos);
                    });

                    map.setBounds(bounds);
                } else {
                    slidePanel.innerHTML = "<div>검색 결과가 없습니다.</div>";
                }
            });

            // 현재 위치 마커
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    const lat = position.coords.latitude;
                    const lon = position.coords.longitude;
                    const locPosition = new kakao.maps.LatLng(lat, lon);

                    const marker = new kakao.maps.Marker({
                        position: locPosition,
                        map: map
                    });

                    const infowindow = new kakao.maps.InfoWindow({
                        content: '<div style="padding:5px;">현재 위치</div>'
                    });

                    infowindow.open(map, marker);
                });
            }

            // 리스트 항목 하이라이트 함수
            function highlightSlide(index) {
                const items = document.querySelectorAll(".place-card");
                items.forEach(function (el, i) {
                    el.classList.toggle("highlight", i === index);
                });
            }
        });
    };
    document.head.appendChild(kakaoScript);
</script>


</body>
</html>
