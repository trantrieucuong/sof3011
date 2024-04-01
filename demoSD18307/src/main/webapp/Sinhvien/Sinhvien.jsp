<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/8/2024
  Time: 1:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <title>hello</title>
</head>
<body>
<div>
    <h2>Thêm Sinh Viên</h2>
    <form class="row g-3" action="/addSv" method="post">
        <div class="col-md-6">
            <label for="id" class="form-label">Mã Sinh viên</label>
            <input type="text" class="form-control" id="id" name="id">
        </div>
        <div class="col-md-6">
            <label for="name" class="form-label">Họ và Tên</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="col-md-6">
            <label for="age" class="form-label">Tuổi</label>
            <input type="number" class="form-control" id="age" name="age">
        </div>
<%--        <div class="col-md-6">--%>
<%--            <label for="sex" class="form-label">Giới Tính</label>--%>
<%--            <input type="text" class="form-control" id="sex" name="sex">--%>
<%--        </div>--%>
<%--        <div class="col-md-6">--%>
<%--            <label for="class" class="form-label">Lớp</label>--%>
<%--            <input type="text" class="form-control" id="class" name="class">--%>
<%--        </div>--%>
        <div class="col-md-6">
            <fieldset class="row mb-3">
                <legend class="col-form-label col-sm-2 pt-0">Giới Tính</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sex" id="sex1" value="Nam" checked>
                        <label class="form-check-label" for="sex1">
                            Nam
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sex" id="sex2" value="Nữ">
                        <label class="form-check-label" for="sex2">
                            Nữ
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sex" id="sex3" value="Giới Tính thứ ba" >
                        <label class="form-check-label" for="sex3">
                            Giới Tính thứ ba
                        </label>
                    </div>
                </div>
            </fieldset>
        </div>
        <div class="col-md-6">
            <label for="class2" class="form-label">Lớp</label>
            <select id="class2" class="form-select" name="class">
                <option selected>Choose...</option>
                <option value="SD18307">SD18307</option>
                <option value="SD18308">SD18308</option>
                <option value="SD18309">SD18309</option>
            </select>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Thêm Sinh Viên</button>
        </div>
    </form>
</div>
<div class="">
    <h2>Sinh vien ong vang</h2>
    <table class="table" border="1">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Mã Sinh Viên</th>
            <th scope="col">Họ và Tên</th>
            <th scope="col">Tuổi</th>
            <th scope="col">Giới Tính</th>
            <th scope="col">Lớp</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${listSv}" varStatus="i">
            <tr>
                <th scope="row">${i.index+1}</th>
                <td>${s.id}</td>
                <td>${s.ten}</td>
                <td>${s.tuoi}</td>
                <td>${s.sex}</td>
                <td>${s.lop}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
