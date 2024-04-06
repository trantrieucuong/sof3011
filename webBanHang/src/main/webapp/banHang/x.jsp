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
                <td>
                    <a class="btn btn-danger" href="/home/deletehd?id=${hd.id}">Xóa</a>
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
                <td>Tên sản phẩm</td>
                <td>Số lượng</td>
                <td>Giá bán</td>
                <td>Tổng tiền</td>
                <td>Chức năng</td>
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
                        <a class="btn btn-danger" href="/home/deletehdct?id=${hdcts.id}">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <script>
            // Xử lý sự kiện khi nhấp vào nút Cập nhật
            var updateButtons = document.querySelectorAll('.updateBtn');
            updateButtons.forEach(function(button) {
                button.addEventListener('click', function() {
                    var updateForm = this.nextElementSibling;
                    updateForm.style.display = 'block';
                });
            });

            // Xử lý sự kiện khi xác nhận cập nhật
            var confirmButtons = document.querySelectorAll('.confirmUpdate');
            confirmButtons.forEach(function(button) {
                button.addEventListener('click', function() {
                    var quantityInput = this.previousElementSibling;
                    var newQuantity = quantityInput.value;
                    var hdctId = this.closest('tr').querySelector('td:nth-child(2)').innerText; // Lấy ID từ cột thứ 2 trong hàng
                    // Gửi yêu cầu cập nhật đến máy chủ
                    fetch('/home/updateQuantity', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: 'id=' + hdctId + '&quantity=' + newQuantity
                    }).then(function(response) {
                        if (response.redirected) {
                            window.location.href = response.url; // Sau khi cập nhật thành công, chuyển hướng lại trang chính
                        }
                    }).catch(function(error) {
                        console.error('Error:', error);
                    });
                });
            });
        </script>




            </tbody>
        </table>
    </div>

    <div class="col-5">
        <h2>Tạo hoá đơn</h2>
        <form id="invoiceForm" action="/home/createInvoice" method="post">
            <div class="row">
                <div class="mb-3">
                    <label for="phone_number" class="col-3">Số điện thoại</label>
                    <input type="text" id="phone_number" name="phone_number" class="col-7">
                </div>
                <button type="button" class="btn btn-primary" onclick="searchCustomer()">Tìm kiếm</button>
            </div>
            <div class="mb-3">
                <label for="customer_id" class="col-3">ID Khách hàng</label>
                <input type="text" id="customer_id" name="customer_id" class="col-7" readonly>
            </div>
            <div class="mb-3">
                <label for="customer_name" class="col-3">Tên Khách hàng</label>
                <input type="text" id="customer_name" name="customer_name" class="col-7" readonly>
            </div>
            <div class="mb-3">
                <label for="bill_id" class="col-3">ID Hoá đơn</label>
                <input type="text" id="bill_id" name="bill_id" class="col-7" readonly>
            </div>
            <div class="mb-3">
                <label for="total_amount" class="col-3">Tổng tiền</label>
                <input type="text" id="total_amount" name="total_amount" class="col-7" readonly>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Tạo hoá đơn</button>
                <button type="button" class="btn btn-primary">Thanh toán</button>
            </div>
        </form>

        <script>
            function searchCustomer() {
                var phoneNumber = document.getElementById('phone_number').value; // Lấy số điện thoại

                // Gửi yêu cầu tìm kiếm khách hàng đến servlet
                fetch('/home/searchCustomer', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'phone_number=' + phoneNumber
                }).then(function(response) {
                    return response.json();
                }).then(function(data) {
                    console.log('Customer data:', data);
                    if (data) {
                        // Nếu có dữ liệu khách hàng, cập nhật trường nhập liệu "ID Khách hàng" và "Tên Khách hàng"
                        document.getElementById('customer_id').value = data.id;
                        document.getElementById('customer_name').value = data.hoTen;
                    } else {
                        // Nếu không có dữ liệu khách hàng, hiển thị thông báo không tìm thấy
                        alert("Không tìm thấy khách hàng");
                    }
                }).catch(function(error) {
                    console.error('Error:', error);
                });
            }

            function createInvoice() {
                var customerId = document.getElementById('customer_id').value; // Lấy ID khách hàng
                var phoneNumber = document.getElementById('phone_number').value; // Lấy số điện thoại

                // Gửi yêu cầu tạo hoá đơn đến servlet
                fetch('/home/createInvoice', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'customer_id=' + customerId + '&phone_number=' + phoneNumber
                }).then(function(response) {
                    if (response.ok) {
                        // Nếu tạo hoá đơn thành công, hiển thị cửa sổ cảnh báo
                        alert("Hoá đơn đã được tạo thành công!");
                    } else {
                        // Nếu có lỗi khi tạo hoá đơn, hiển thị cửa sổ cảnh báo
                        alert("Có lỗi xảy ra khi tạo hoá đơn!");
                    }
                }).catch(function(error) {
                    console.error('Error:', error);
                });
            }
        </script>
    </div>
</div>
<div>


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
                    <button class="btn btn-primary addToCartBtn"
                            data-product-id="${ct.id}"
                            data-gia-ban="${ct.giaBan}">Chọn mua</button>
                </td>
            </tr>
        </c:forEach>

        <script>
            document.addEventListener("DOMContentLoaded", function() {
                var addToCartBtns = document.querySelectorAll(".addToCartBtn");
                addToCartBtns.forEach(function(btn) {
                    btn.addEventListener("click", function() {
                        var productId = this.getAttribute("data-product-id");
                        var giaBan = this.getAttribute("data-gia-ban");
                        var quantity = prompt("Nhập số lượng:", "1");

                        if (quantity !== null && quantity.trim() !== "") {
                            // Gửi yêu cầu AJAX để thêm sản phẩm vào giỏ hàng
                            addToCart(productId, giaBan, quantity);
                        } else {
                            alert("Số lượng không hợp lệ!");
                        }
                    });
                });

                function addToCart(productId, giaBan, quantity) {
                    // Gửi yêu cầu AJAX để thêm sản phẩm vào giỏ hàng
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "/home/addToCart", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function() {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                alert("Sản phẩm đã được thêm vào giỏ hàng!");
                                window.location.reload(); // Làm mới trang sau khi thêm vào giỏ hàng
                            } else {
                                alert("Đã xảy ra lỗi khi thêm vào giỏ hàng!");
                            }
                        }
                    };
                    xhr.send("productId=" + productId + "&giaBan=" + giaBan + "&quantity=" + quantity);
                }
            });
        </script>

        </tbody>
    </table>

</div>
</body>
</html>
