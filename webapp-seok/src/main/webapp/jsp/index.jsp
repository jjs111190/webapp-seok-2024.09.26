
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags. functions" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sekurity</title>
	<script type="text/javscript" scr="js/common.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header {
            background-color: #007bff;
            color: white;
            padding: 20px;
            font-size: 24px;
            position: sticky;
            top: 0;
            display: flex;
            align-items: center;
            z-index: 1001;
        }

        header button {
            background-color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            color: #007bff;
            margin-right: 10px;
        }

        header button:hover {
            background-color: #0056b3;
            color: white;
        }
        
/* 사용자 정보 부분 style */
	#auth-section {
	display: flex;
	align-items: center;
	}
	#auth-section button {

	background-color: white;
	border: none;
	padding: 10px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 18px;
	color: #007bff;
	margin-left: 10px;
}
#auth-section button:hover {
background-color: #0056b3; color: white;
}

        .layout {
            display: flex;
            flex: 1;
        }

        .sidebar-container {
            width: 250px;
            background-color: #343a40;
            color: white;
            padding: 20px;
            display: flex;
            flex-direction: column;
            height: 100%;
        }

        .sidebar-container.hidden {
            width: 0;
            padding: 0;
            overflow: hidden;
        }

        nav ul {
            list-style: none;
            padding-left: 0;
        }

        nav ul li {
            margin-bottom: 15px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            font-size: 18px;
            display: block;
        }

        nav ul li a:hover {
            color: #007bff;
        }

        .submenu {
            margin-left: 20px;
            display: none;
        }

        .submenu.active {
            display: block;
        }

        main {
            flex: 1;
            padding: 20px;
            position: relative;
            overflow-y: auto; /* ë©ì¸ ì½íì¸ ìì ì¤í¬ë¡¤ì´ ê°ë¥íê² ì¤ì  */
        }

        .page-card {
            display: none;
            width: 100%;
            min-height: 100%; /* íì´ì§ ì¹´ëì ëì´ë¥¼ ë¶ëª¨ ììì ë§ì¶ì´ ì¤ì  */
            padding: 20px;
            background-color: white;
            overflow-y: auto; /* íì´ì§ ì¹´ë ë´ìì ì¤í¬ë¡¤ ê°ë¥ */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        .page-card-home {
            display: none;
            width: 100%;
            min-height: 100%; /* íì´ì§ ì¹´ëì ëì´ë¥¼ ë¶ëª¨ ììì ë§ì¶ì´ ì¤ì  */
            /* padding: 20px; */
            background-color: white;
            overflow-y: auto; /* íì´ì§ ì¹´ë ë´ìì ì¤í¬ë¡¤ ê°ë¥ */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .page-card.active {
            display: block;
        }

        footer {
            background-color: #007bff;
            color: white;
            text-align: center;
            padding: 10px;
            margin-top: auto;
            width: 100%;
        }

        @media (max-width: 768px) {
            .sidebar-container {
                position: relative;
                width: 100%;
                height: auto;
                padding: 20px;
                overflow: hidden;
                transition: height 0.3s ease;
                z-index: 1000;
            }

            .sidebar-container.hidden {
                display: none;
            }

            .layout {
                flex-direction: column;
            }

            main {
                padding: 20px;
            }
        }
    </style>

    <script>
        // íì´ì§ë¥¼ ë¡ëíê³  ì¹´ëë¥¼ ëì ì¼ë¡ ìì±íë í¨ì
        
// 로그인 폼을 표시하는 함수 
	function showLogin() {

	window.location.href = context + '/login';
        }
// 로그아웃 처리 함수
	function logout() {
// 로그아웃 폼을 생성하고 제출하는 함수
	const form = document.createElement('form'); 
	form.method = 'POST';
	form.action = 'logout'; // URL
	document.body.appendChild(form); //
	form.submit(); //
	}
        function loadPage(pageId, pageUrl) {
            const mainContent = document.querySelector('main');
            const existingPage = document.getElementById(pageId);

            // ì´ë¯¸ ë¡ëë íì´ì§ê° ìì¼ë©´ ê·¸ íì´ì§ë¥¼ íì±í
            if (existingPage) {
                existingPage.remove();
                return;
            }

            // ìë¡ì´ page-card ìì±
            fetch(pageUrl)
                .then(response => response.text())
                .then(data => {
                    const newPageCard = document.createElement('div');
                    newPageCard.id = pageId;
                    newPageCard.classList.add('page-card');
                    newPageCard.innerHTML = data;
                    mainContent.appendChild(newPageCard);
                    setActivePage(pageId);
                    adjustPaddingForHome(pageId); 
                })
                .catch(error => {
                    console.error('íì´ì§ ë¡ë ì¤ ì¤ë¥ ë°ì:', error);
                });
        }
        
     // paddingì home íì´ì§ì¼ ëë§ 0ì¼ë¡ ì¤ì íë í¨ì
        function adjustPaddingForHome(pageId) {
            const pageElement = document.getElementById(pageId);
            
            if (pageId === 'home') {
                pageElement.style.padding = '0px';  // home íì´ì§ì paddingì 0ì¼ë¡ ì¤ì 
            } else {
                pageElement.style.padding = '20px';  // ë¤ë¥¸ íì´ì§ì paddingì ê¸°ë³¸ê°ì¼ë¡ ì¤ì 
            }
        }

        // í¹ì  íì´ì§ë¥¼ íì±ííë í¨ì
        function setActivePage(pageId) {
            const pages = document.querySelectorAll('.page-card');
            pages.forEach(page => {
                page.classList.remove('active');
            });

            const targetPage = document.getElementById(pageId);
            if (targetPage) {
                targetPage.classList.add('active');
                localStorage.setItem('currentPage', pageId); // íì¬ íì´ì§ ìí ì ì¥
            }
        }

        // ìë¸ë©ë´ í ê¸ í¨ì
        function toggleSubmenu() {
            const submenu = document.getElementById('submenu');
            submenu.classList.toggle('active');
        }

        // ë©ë´ ì ê¸°/í¼ì¹ê¸° ë²í¼ ëì
        function toggleSidebar() {
            const sidebar = document.querySelector('.sidebar-container');
            sidebar.classList.toggle('hidden');
            sidebar.classList.toggle('active');
        }

        document.addEventListener('DOMContentLoaded', function () {
            loadPage('home', 'home.html'); // ê¸°ë³¸ íì´ì§ë¥¼ ë¡ë
        });
    </script>
</head>
<body>

    <header>
        <button onclick="toggleSidebar()">â°</button>
        <span>Webapp Sekurity</span>
    </header>

    <div class="layout">
        <div class="sidebar-container hidden">
         
<nav>
<ul>
<li><a href="#" onclick="loadPage('home','html/home.html')"></a></li>
<c:if test="${not empty sessionScope.user}">
</c:if>
<li><a href="#" onclick="toggleSubmenu ()">사용자 관리</a></li>
<ul id="submenu" class="submenu">
<li><a href="#" onclick="loadPage('userList', 'user/userlist')">사용자 목록 </a></li>
<li><a href="#" onclick="loadPage('userForm', 'html/user-form.html')">사용자 입력 </a></li>
</ul>
<li><a href="#" onclick="loadPage('service', 'service.html')">서비스 </a> </li>
<li><a href="#" onclick="loadPage('contact', 'contact.html')">연락처 </a> </li>
</ul>
</nav>
        </div>

        <main>
<!-- 로그인 또는 사용자 정보가 표시되는 영역 -->
<div id="auth-section">
<c:if test="${empty sessionScope.user}">
</c:if>
<button id="login-button" onclick="showLogin()">1</button>
<c:if test="${not empty sessionScope.user}">
<div id="user-info" style="display: ${not empty sessionScope.user? block none};">
</div>
<span id="username" title="${sessionScope.user.roles [0]}
${sessionScope.user.email}">${sessionScope.user.name}</span>
<button id="logout-button" onclick="logout()">0</button>
</c:if>
</div>
            <!-- íì´ì§ ì¹´ëë¤ì´ ëì ì¼ë¡ ì¬ê¸°ì ìì±ë©ëë¤ -->
        </main>
    </div>

    <footer>
        Â© 2024 ë´ ì¹ì¬ì´í¸ - ëª¨ë  ê¶ë¦¬ ë³´ì 
    </footer>

</body>
</html>
