<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2024-09-29
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>IUH News</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="flex flex-col min-h-screen bg-gradient-to-r from-green-400 to-blue-500">

<header class="flex flex-col md:flex-row items-center bg-white shadow-md">
    <div class="w-full md:w-1/2 flex items-center justify-center py-8">
        <h1 class="text-4xl font-bold text-blue-600">IUH NEWS</h1>
    </div>
    <div class="w-full md:w-1/2 flex items-center justify-center overflow-hidden py-4">
        <h1 class="text-4xl font-bold text-blue-600">NEWS FOR YOU</h1>
    </div>
</header>

<nav class="bg-blue-600">
    <ul class="flex flex-col md:flex-row justify-center space-y-2 md:space-y-0 md:space-x-6 py-4">
        <li>
            <a href="/TinTuc?action=list"
               class="px-4 py-2 bg-white text-blue-600 rounded hover:bg-blue-100 transition duration-300">Danh
                sách tin tức</a>
        </li>
        <li>
            <a href="javascript:void(0)"
               onclick="loadForm('views/ThemTinTuc.jsp')"
               class="px-4 py-2 bg-white text-blue-600 rounded hover:bg-blue-100 transition duration-300">Thêm
                tin tức</a>
        </li>
        <li>
            <a href="#" class="px-4 py-2 bg-white text-blue-600 rounded hover:bg-blue-100 transition duration-300">Chức
                năng quản lý</a>
        </li>
    </ul>
</nav>

<main class="bg-white p-10 rounded-lg shadow-lg flex-grow flex items-center justify-center overflow-auto" id="main-container">
    <div class="w-11/12 h-72 bg-white bg-opacity-50 rounded-lg shadow-md">
        <c:set var="listTinTuc" value="${requestScope.listTinTuc}" scope="request"/>
        <c:choose>
            <c:when test="${not empty listTinTuc}">
                <jsp:include page="views/DanhSachTinTuc.jsp"/>
            </c:when>
            <c:when test="${not empty tinTuc}">
                <jsp:include page="views/ChiTietTinTuc.jsp"/>
            </c:when>
            <c:otherwise>
                <p class="text-gray-500 text-lg text-center">Vui lòng chọn một mục từ thanh điều hướng.</p>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<footer class="bg-blue-600 text-white py-4">
    <p class="text-center">Họ tên sinh viên - Mã sinh viên - Lớp</p>
</footer>
<script>
    function loadForm(page) {
        const contentArea = document.getElementById('main-container');
        contentArea.innerHTML = '';
        fetch(page)
            .then(response => response.text())
            .then(data => contentArea.innerHTML = data);
    }
</script>
</body>
</html>