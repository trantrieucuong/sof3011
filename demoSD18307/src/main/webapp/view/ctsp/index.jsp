<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/25/2024
  Time: 1:36 PM
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
<a href="/ctsp/create" class="btn btn-success">Thêm chi tiết </a>
<table class="table" border="1">
    <thead>
    <tr>
<%--        <th scope="col">Mã chi tiết sản phẩm</th>--%>
        <th scope="col">Tên Sản phẩm</th>
        <th scope="col">Màu Sắc</th>
        <th scope="col">kích thước</th>
        <th scope="col">Danh mục</th>
        <th scope="col">Trang Thai</th>
        <th scope="col">Đơn giá</th>
        <th scope="col">Ngay tao</th>
        <th scope="col">Ngay Sua</th>
        <th colspan="2">Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ct}" var="ct">
        <tr>
<%--            <td>${ct.id}</td>--%>
            <td>${ct.sp.tenSanpham}</td>
            <td>${ct.ms.tenMau}</td>
            <td>${ct.sz.tenSize}</td>
            <td>${ct.sp.dm.tenDanhMuc}</td>
            <td>${ct.trangThai}</td>
            <td>${ct.giaBan}</td>

            <td>${ct.ngayTao}</td>
            <td>${ct.ngaySua}</td>
    <td>
        <a class="btn btn-primary" href="/ctsp/edit?id=${ct.id}">Cập nhật</a>
    </td>
            <td>
                <a class="btn btn-danger" href="/ctsp/delete?id=${ct.id}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
