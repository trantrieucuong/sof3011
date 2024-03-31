<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/13/2024
  Time: 12:37 PM
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
  <div>
    <h2>Thêm Sinh Viên</h2>
    <form class="row g-3">
      <div class="col-md-6">
        <label for="id" class="form-label">Mã Sinh viên</label>
        <input type="text" class="form-control" id="id">
      </div>
      <div class="col-md-6">
        <label for="name" class="form-label">Họ và Tên</label>
        <input type="text" class="form-control" id="name">
      </div>
      <div class="col-md-6">
        <label for="age" class="form-label">Tuổi</label>
        <input type="number" class="form-control" id="age">
      </div>
      <div class="col-md-6">
        <label for="sex" class="form-label">Tuổi</label>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="flexRadioDefault" id="sex">
          <label class="form-check-label" for="sex">
            Nam
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="flexRadioDefault" id="sex" checked>
          <label class="form-check-label" for="sex">
            Nữ
          </label>
        </div>
      </div>
      <div class="col-md-6">
        <label for="class" class="form-label">Lớp</label>
        <select id="class" class="form-select">
          <option selected>Choose...</option>
          <option>...</option>
        </select>
      </div>
      <div class="col-12">
        <button type="submit" class="btn btn-primary">Sign in</button>
      </div>
    </form>
  </div>
</body>
</html>
