<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %> <!-- 공통 네비게이션 바 -->
<%
    String type = request.getParameter("type");
    if (type == null || type.trim().isEmpty()) type = "동물병원";
%>
<!DOCTYPE html>
<html lang="ko">
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
            padding-top: 30px;
        }
        body {
            display: flex;
            flex-direction: column;
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
            height: 550px;
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
                <div>장소를 불러오는 중...</div>
            </div>
        </div>
        <div class="map-container">
            <div id="map" class="map"></div>
        </div>
    </div>

<script>
const defaultType = "<%= type %>";

function applySubFilterAjax(select) {
    const value = select.value;
    let keyword;
    if (value === '24hours') keyword = '24시간 동물병원';
    else if (value === 'emergency') keyword = '응급 동물';
    else keyword = '동물병원';
    if (typeof fetchPlaces === "function") {
        if (window.currentInfoWindow) {
            window.currentInfoWindow.close(); // 드롭다운 클릭 시 기존 열려있던 인포윈도우 닫기
        }
        fetchPlaces(keyword);
    }
}

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
        const slidePanel = document.querySelector(".slide-panel");
        const markers = [];
        window.currentInfoWindow = null;
        let userPosition = null;

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                userPosition = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
                const markerImage = new kakao.maps.MarkerImage(
                    'https://cdn-icons-png.flaticon.com/512/684/684908.png',
                    new kakao.maps.Size(40, 40)
                );
                const marker = new kakao.maps.Marker({ position: userPosition, map, image: markerImage });
                new kakao.maps.InfoWindow({ content: '<div style="padding:5px;">현재 위치</div>' }).open(map, marker);
                fetchPlaces(defaultType);
            }, () => fetchPlaces(defaultType));
        } else {
            fetchPlaces(defaultType);
        }

        window.fetchPlaces = function(type) {
            const allResults = [];
            function fetchPage(page) {
                ps.keywordSearch(type, function (data, status, pagination) {
                    if (status === kakao.maps.services.Status.OK) {
                        allResults.push(...data);
                        if (page === 1 && pagination.hasNextPage) fetchPage(2);
                        else renderPlaces(allResults);
                    } else slidePanel.innerHTML = "<div>검색 결과가 없습니다.</div>";
                }, { location: userPosition, page });
            }
            fetchPage(1);
        }

        function renderPlaces(data) {
            markers.forEach(m => m.setMap(null));
            markers.length = 0;
            if (userPosition) {
                data.forEach(p => {
                    const d = new kakao.maps.Polyline({ path: [userPosition, new kakao.maps.LatLng(p.y, p.x)] });
                    p.distance = d.getLength();
                });
                data.sort((a, b) => a.distance - b.distance);
            }

            slidePanel.innerHTML = "";
            const bounds = new kakao.maps.LatLngBounds();
            data.forEach((place, index) => {
                const pos = new kakao.maps.LatLng(place.y, place.x);
                const marker = new kakao.maps.Marker({ map, position: pos });
                markers.push(marker);
                const infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="padding:5px; font-size:14px;"><strong><a href="' + place.place_url + '" target="_blank">' + place.place_name + '</a></strong><br/>' + (place.road_address_name || place.address_name) + '<br/>' + (place.phone || '') + '</div>'
                });
                kakao.maps.event.addListener(marker, 'click', () => {
                    if (window.currentInfoWindow) window.currentInfoWindow.close();
                    infowindow.open(map, marker);
                    window.currentInfoWindow = infowindow;
                    highlightSlide(index);
                });
                const div = document.createElement("div");
                div.className = "place-card";
                div.innerHTML = '<strong><a href="' + place.place_url + '" target="_blank" style="text-decoration:none;color:black">' + place.place_name + '</a></strong><br/><span>📍 ' + (place.road_address_name || place.address_name) + '</span><br/>' + (place.phone ? '<span>📞 ' + place.phone + '</span>' : '');
                div.dataset.index = index;
                div.onclick = function () {
                    map.setCenter(pos);
                    if (window.currentInfoWindow) window.currentInfoWindow.close();
                    infowindow.open(map, marker);
                    window.currentInfoWindow = infowindow;
                    highlightSlide(index);

                    const url = "/places/detail"
                        + "?name=" + encodeURIComponent(place.place_name)
                        + "&address=" + encodeURIComponent(place.road_address_name || place.address_name)
                        + "&phone=" + encodeURIComponent(place.phone || '')
                        + "&image=" + encodeURIComponent('')
                        + "&place_url=" + encodeURIComponent(place.place_url || '');

                    location.href = url;
                };
                slidePanel.appendChild(div);
                bounds.extend(pos);
            });
            map.setBounds(bounds);
        }

        function highlightSlide(index) {
            document.querySelectorAll(".place-card").forEach((el, i) => {
                el.classList.toggle("highlight", i === index);
            });
        }
    });
};
document.head.appendChild(kakaoScript);
</script>
</body>
</html>
