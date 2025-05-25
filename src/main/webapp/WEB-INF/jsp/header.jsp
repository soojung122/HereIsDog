<%@ page contentType="text/html; charset=UTF-8" %>

<style>
    .site-header {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        height: 60px;
        background-color: white;
        border-bottom: 1px solid #ccc;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 20px;
        z-index: 1000;
        transition: top 0.3s;
    }

    .site-header.hidden {
        top: -70px;
    }

    .site-logo {
        display: flex;
        align-items: center;
        text-decoration: none;
        color: black;
        font-size: 20px;
        font-weight: bold;
    }

    .site-logo img {
        width: 40px;
        margin-right: 10px;
    }

    body {
        padding-top: 70px; /* 헤더 높이만큼 공간 확보 */
    }
</style>

<div class="site-header" id="siteHeader">
    <a href="/index.html" class="site-logo">
        <img src="/images/HereIsDog-logo.png" alt="로고">
        여기다멍
    </a>
</div>

<script>
    let lastScrollTop = 0;
    const header = document.getElementById('siteHeader');

    window.addEventListener("scroll", function () {
        const st = window.pageYOffset || document.documentElement.scrollTop;
        if (st > lastScrollTop) {
            // 아래로 스크롤 시 숨김
            header.classList.add("hidden");
        } else {
            // 위로 스크롤 시 보임
            header.classList.remove("hidden");
        }
        lastScrollTop = st <= 0 ? 0 : st;
    }, false);
</script>
