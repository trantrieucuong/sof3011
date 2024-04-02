<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/22/2024
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a class="btn btn-primary" href="/size/create">Thêm size</a>
<h2>Danh sách size</h2>
<table class="table" border="1">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Mã Size</th>
        <th scope="col">Tên Size</th>
        <th scope="col">Trang Thai</th>
        <th scope="col">Ngay tao</th>
        <th scope="col">Ngay Sua</th>
        <th colspan="2">Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sm}" var="s">
        <tr>
            <td>${s.id}</td>
            <td>${s.maSize}</td>
            <td>${s.tenSize}</td>
            <td>${s.trangThai}</td>
            <td>${s.ngayTao}</td>
            <td>${s.ngaySua}</td>
            <td>
                <a class="btn btn-primary" href="/size/edit?id=${s.id}">Cập nhật</a>
            </td>
            <td>
                <a class="btn btn-danger" href="/size/delete?id=${s.id}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
