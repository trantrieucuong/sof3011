<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/28/2024
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="row g-3" action="/danhmuc/update?id=${dm.id}" method="post">
    <div class="col-md-6">
        <label for="id" class="form-label">Mã danh mục</label>
        <input type="text" class="form-control" id="id" name="ma" value="${dm.maDanhMuc}">
    </div>
    <div class="col-md-6">
        <label for="name" class="form-label">Tên danh muc</label>
        <input type="text" class="form-control" id="name" name="ten" value="${dm.tenDanhMuc}">
    </div>
    <div class="col-md-6">
        <label for="sex" class="form-label">TRạng thái</label>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="trangThai" id="sta" value="Active">
            <label class="form-check-label" for="sta">
                Active
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="trangThai" id="sex" value="Inactive">
            <label class="form-check-label" for="sex">
                Inactive
            </label>
        </div>
    </div>
    <div class="col-12">
        <button type="submit" class="btn btn-primary">update</button>
    </div>
</form>
</body>
</html>
