<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2024-10-09
  Time: 2:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FonGo</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-pink-500 via-yellow-400 to-green-400 min-h-screen flex flex-col">

<!-- Banner -->
<header class="text-center py-10 bg-white shadow-lg">
    <h1 class="text-6xl font-bold">
        <span class="text-sky-500">Fon</span><span class="text-green-500">Go</span>
    </h1>
    <p class="text-xl">
        <span class="text-green-500">Home</span><span class="text-sky-500">Phone</span>
    </p>
</header>

<!-- Navbar -->
<nav class="bg-white shadow-md py-4 flex justify-center space-x-8">
    <a href="${pageContext.request.contextPath}/DienThoai?action=list"
       class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Danh sách sản phẩm</a>
    <a href="${pageContext.request.contextPath}/DienThoai?action=create"
       class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">Thêm mới sản phẩm</a>
    <a href="${pageContext.request.contextPath}/DienThoai?action=manage"
       class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600">Chức năng quản lý</a>
</nav>

<!-- Content -->
<main class="flex-1 grid place-items-center">
    <div class="max-w-4xl w-full bg-white p-10 rounded shadow-lg text-gray-800 overflow-auto text-center">
        <%
            String contentPage = request.getParameter("contentPage");
            String filePath = application.getRealPath("/") + contentPage;
            File file = new File(filePath);
            if (!file.exists()) {
                contentPage = "views/defaultContent.jsp";
            }
        %>

        <jsp:include page="<%= contentPage %>"/>
    </div>
</main>

<!-- Footer -->
<footer class="bg-white py-4 text-center shadow-lg">
    <p class="text-gray-600">Nguyễn Thiên Phú - 20073871 - DHKTPM16A</p>
</footer>

</body>
</html>
