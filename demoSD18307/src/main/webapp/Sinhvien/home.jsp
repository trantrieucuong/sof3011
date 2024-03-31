<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/8/2024
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<html>
<head>
    <title>Trang chu</title>
</head>
<body>

<h1>Golden Bee: </h1>
<h2>Ho va ten : ${sv.ten}</h2>
<h2>Tuoi: ${sv.tuoi}</h2>
<h2>Yeu thich: ${sv.sothich}</h2>
<h2>Id: ${sv.id}</h2>
<h1>Danh sach sinh vien</h1>
<table class="table" border="1">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Ten</th>
        <th scope="col">Tuoi</th>
        <th scope="col">So thich</th>
        <th scope="col">ID</th>
    </tr>
    </thead>

    <tbody>
<c:forEach var="s" items="${listSv}" varStatus="i">
    <tr>
        <th scope="row">${i.index+1}</th>
        <td>${s.ten}</td>
        <td>${s.tuoi}</td>
        <td>${s.sothich}</td>
        <td>${s.id}</td>
    </tr>
</c:forEach>
    </tbody>

</table>

<c:forEach var="s" items="${lstSv}" varStatus="i">

</c:forEach>


</body>
</html>
