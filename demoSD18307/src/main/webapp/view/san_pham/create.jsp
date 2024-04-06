<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/27/2024
  Time: 12:22 PM
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
<form class="row g-3" action="/sanpham/store" method="post">
    <div class="col-md-6">
        <label for="id" class="form-label">Mã sản phẩm</label>
        <input type="text" class="form-control" id="id" name="ma" required>
    </div>
    <div class="col-md-6">
        <label for="name" class="form-label">Tên sản phẩm</label>
        <input type="text" class="form-control" id="name" name="ten" required>
    </div>
    <div class="col-md-6">
        <label for="sex" class="form-label">TRạng thái</label>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="trangThai" id="sta" value="Active" required>
            <label class="form-check-label" for="sta">
                Active
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="trangThai" id="sex" value="Inactive" required>
            <label class="form-check-label" for="sex">
                Inactive
            </label>
        </div>
        <div class="col-md-6">
            <label for="inputState" class="form-label">Danh mục</label>
            <select id="inputState" class="form-select" name="dm" required>
                <c:forEach items="${dm}" var="dm">
                    <option value="${dm.id}" required>${dm.tenDanhMuc}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-12">
        <button type="submit" class="btn btn-primary">Them SP</button>
    </div>
</form>
</body>
</html>
