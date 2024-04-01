
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a class="btn btn-primary" href="/sanpham/create">Thêm sản phẩm</a>
<h2>Danh sách Sản Phẩm</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Mã Sản Phẩm</th>
        <th scope="col">Tên Sản Phẩm</th>
        <th scope="col">ID Danh mục</th>
        <th scope="col">Trang Thai</th>
        <th scope="col">Ngay tao</th>
        <th scope="col">Ngay Sua</th>
        <th colspan="2">Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sp}" var="sp">
        <tr>
            <td>${sp.id}</td>
            <td>${sp.maSanpham}</td>
            <td>${sp.tenSanpham}</td>
            <td>${sp.dm.tenDanhMuc}</td>
            <td>${sp.trangThai}</td>
            <td>${sp.ngayTao}</td>
            <td>${sp.ngaySua}</td>
            <td>
                <a class="btn btn-primary" href="/sanpham/edit?id=${sp.id}">Cập nhật</a>
            </td>
            <td>
                <a class="btn btn-danger" href="/sanpham/delete?id=${sp.id}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
