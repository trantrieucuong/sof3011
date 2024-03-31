<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguyenvv
  Date: 12/03/2024
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<div class="row">
    <div class="col-7">
        <h2>Danh sách hoá đơn</h2>
        <table class="table">
            <thead>

            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Ten khach hang</td>
                <td>Ngay tao</td>
                <td>Tong tien</td>
                <td>Trang Thai</td>
                <td>Chuc nang</td>
            </tr>
            </thead>
            <tbody>
        <c:forEach items="${hd}" var="hd" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${hd.id}</td>
                <td>${hd.khachHang.hoTen}</td>
                <td>${hd.ngayTao}</td>

                <td></td>
                <td>${hd.trangThai}</td>
                <td>
                    <form action="/home/thanhtoan" method="post">
                        <input type="hidden" name="idHoaDon" value="${hd.id}">
                        <button type="submit" class="btn btn-primary">Thanh toán</button>
                    </form>
                    <form action="/home/hoadonct" method="post">
                        <input type="hidden" name="idHoaDon" value="${hd.id}">
                        <button type="submit" class="btn btn-primary">Chi tiết</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
            </tbody>
        </table>
        <script>
            function thanhToan(idHoaDon) {
                fetch('/thanhToan', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ idHoaDon: idHoaDon }),
                })
                    .then(response => {
                        if (response.ok) {
                            // Nếu cập nhật thành công, làm gì đó (ví dụ: cập nhật trang web)
                            window.location.reload(); // Cập nhật lại trang web
                        } else {
                            console.error('Có lỗi xảy ra khi cập nhật trạng thái hóa đơn.');
                        }
                    })
                    .catch(error => {
                        console.error('Lỗi kết nối:', error);
                    });
            }
        </script>
        <h2>Danh sách hoá đơn chi tiết</h2>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Ten san pham</td>
                <td>So luong</td>
                <td>Gia ban</td>
                <td>Tong tien</td>
                <td>Chuc nang</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hdct}" var="hdcts" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${hdcts.id}</td>
                    <td>${hdcts.tenSanPham}</td>
                    <td>${hdcts.soLuongMua}</td>
                    <td>${hdcts.giaBan}</td>
                    <td>${hdcts.tongTien}</td>
                    <td>
                        <button class="btn btn-primary updateBtn" data-id="${hdcts.id}">Cập nhật</button>
                        <div class="updateForm" style="display: none;">
                            <input type="number" class="form-control quantityInput" value="${hdcts.soLuongMua}" min="1">
                            <button class="btn btn-success confirmUpdate">Xác nhận</button>
                        </div>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="/home/delete?id=${hdcts.id}">Xóa</a>
                    </td>
                </tr>
            </c:forEach>



            <script>
                document.addEventListener("DOMContentLoaded", function() {
                    var updateBtns = document.querySelectorAll(".updateBtn");
                    updateBtns.forEach(function(btn) {
                        btn.addEventListener("click", function() {
                            var id = this.getAttribute("data-id");
                            var updateForm = document.querySelector(".updateForm[data-id='" + id + "']");
                            updateForm.style.display = "block";
                        });
                    });

                    var confirmBtns = document.querySelectorAll(".confirmUpdate");
                    confirmBtns.forEach(function(btn) {
                        btn.addEventListener("click", function() {
                            var id = this.getAttribute("data-id");
                            var quantityInput = document.querySelector(".quantityInput[data-id='" + id + "']");
                            var newQuantity = quantityInput.value;
                            updateQuantity(id, newQuantity);
                        });
                    });

                    function updateQuantity(id, newQuantity) {
                        var xhr = new XMLHttpRequest();
                        xhr.open("POST", "/home/updateQuantity", true);
                        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                        xhr.onreadystatechange = function() {
                            if (xhr.readyState === 4 && xhr.status === 200) {
                                window.location.reload();
                            }
                        };
                        xhr.send("hdctId=" + id + "&newQuantity=" + newQuantity);
                    }
                });
            </script>


            </tbody>
        </table>
    </div>

    <div class="col-5">
        <h2>Tạo hoá đơn</h2>
        <form action="/home/add" method="post">
        <div class="row">
            <div>
                <div class="mb-3">
                    <label class="col-3">Số điện thoại</label>
                    <input type="text" class="col-7" id="phone_number">
                </div>
                <button class="btn btn-primary" onclick="searchCustomer()">Search</button>
            </div>
            <div class="mb-3">
                <label class="col-3">Tên Khách hàng</label>

                <input type="text" class="col-7" id="customer_name"  value="${lop.id}" name="id_khach_hang" >

            </div>
            <div class="mb-3">
                <label class="col-3">ID Hoá đơn</label>
                <input type="text" class="col-7" id="bill_id" readonly>
            </div>
            <div class="mb-3">
                <label class="col-3">Tổng tiền</label>
                <input type="text" class="col-7" id="total_amount" readonly>
            </div>
            <div>
                <button class="btn btn-primary" type="submit">Tạo hoá đơn</button>
                <button class="btn btn-primary">Thanh toán</button>
            </div>
        </div>
        </form>

    </div>
</div>
<div>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var buyButtons = document.querySelectorAll(".buyBtn");
            buyButtons.forEach(function(btn) {
                btn.addEventListener("click", function() {
                    var productId = this.getAttribute("data-product-id");
                    var productName = this.getAttribute("data-product-name");
                    showQuantityInput(productId, productName);
                });
            });

            function showQuantityInput(productId, productName) {
                var quantity = prompt("Nhập số lượng cho sản phẩm " + productName + ":");
                if (quantity !== null && quantity !== "") {
                    addToCart(productId, quantity);
                }
            }

            function addToCart(productId, quantity) {
                // Gửi yêu cầu POST để thêm sản phẩm vào giỏ hàng
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "/home/addhdct", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // Xử lý phản hồi từ máy chủ (nếu cần)
                        window.location.reload(); // Làm mới trang sau khi thêm vào giỏ hàng
                    }
                };
                xhr.send("productId=" + productId + "&quantity=" + quantity);
            }
        });
    </script>

    <table class="table">
        <thead>
        <tr>
            <th>STT</th>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Màu sắc</th>
            <th>Size</th>
            <th>Giá bán</th>
            <th>Số lượng tồn</th>
            <th>Trạng thái</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ls}" var="ct" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${ct.id}</td>
                <td>${ct.sp.tenSanpham}</td>
                <td>${ct.ms.tenMau}</td>
                <td>${ct.sz.tenSize}</td>
                <td>${ct.giaBan}</td>
                <td>${ct.soLg}</td>
                <td>${ct.trangThai}</td>
                <td>
                    <button class="btn btn-primary buyBtn"
                            data-product-id="${ct.id}"
                            data-product-name="${ct.sp.tenSanpham}">
                        Chọn mua
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
