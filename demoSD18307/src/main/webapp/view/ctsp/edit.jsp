<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/28/2024
  Time: 6:14 PM
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
<form class="row g-3" action="/ctsp/update?id=${data.id}" method="post">
    <div class="col-md-6">
        <label for="name" class="form-label">sản phẩm</label>
        <select name="sp" id="name" class="form-select">
            <c:forEach items="${sp}" var="sp">
                <option value="${sp.id}"
                <c:if test="${data.tenSanpham == sp.tenSanpham}"> selected </c:if>
                >${sp.tenSanpham}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-6">
        <label for="size" class="form-label">Kich thuoc</label>
        <select name="sz" id="size" class="form-select">
            <c:forEach items="${sz}" var="sz">
                <option value="${sz.id}">${sz.tenSize}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-6">
        <label for="mausac" class="form-label">Mau Sac</label>
        <select name="ms" id="mausac" class="form-select">
            <c:forEach items="${ms}" var="ms">
                <option value="${ms.id}">${ms.tenMau}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-6">
        <label for="price" class="form-label">Đơn giá</label>
        <input type="text" class="form-control" id="price" name="gia">
    </div>
    <div class="col-md-6">
        <label for="slg" class="form-label">Số Lượng</label>
        <input type="text" class="form-control" id="slg" name="slg">
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
        <button type="submit" class="btn btn-primary">Them SP</button>
    </div>
</form>
</body>
</html>
