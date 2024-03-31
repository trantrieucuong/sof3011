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
                <!-- Button to toggle input field -->
                <button class="btn btn-warning update-btn">Cập nhật</button>
                <!-- Input field for quantity -->
                <form class="quantity-form" action="/home/updateQuantity" method="post" style="display: none;">
                    <div class="input-group">
                        <input type="number" class="form-control" value="${hdcts.soLuongMua}" name="newQuantity">
                        <input type="hidden" name="hdctId" value="${hdcts.id}">
                        <button type="submit" class="btn btn-primary">Xác nhận</button>
                    </div>
                </form>
            </td>
        </tr>

</c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        // JavaScript to toggle input field
        document.querySelectorAll('.update-btn').forEach(button => {
            button.addEventListener('click', () => {
                const row = button.parentNode.parentNode;
                const inputField = row.querySelector('.input-group');
                inputField.style.display = inputField.style.display === 'none' ? 'block' : 'none';
            });
        });

        // JavaScript to handle update button click
        document.querySelectorAll('.confirm-update').forEach(button => {
            button.addEventListener('click', () => {
                const row = button.parentNode.parentNode.parentNode;
                const quantity = row.querySelector('.quantity-input').value;
                const hdctId = row.querySelector('td:nth-child(2)').innerText;
                // Send AJAX request to update quantity
                fetch(`/home/updateQuantity?hdctId=${hdctId}&newQuantity=${quantity}`, { method: 'POST' })
                    .then(response => {
                        if (response.ok) {
                            // Reload page after successful update
                            location.reload();
                        } else {
                            console.error('Failed to update quantity');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            });
        });
    </script>
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
    <h2>Danh sách chi tiếtsản phẩm</h2>
    <table class="table">
        <thead>
        <tr>
            <td>STT</td>
            <td>ID CTSP</td>
            <td>Ten san pham</td>
            <td>Mau sac</td>
            <td>Size</td>
            <td>Gia ban</td>
            <td>So luong ton</td>
            <td>Trang Thai</td>
            <td>Chuc nang</td>
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
                <td><button class="btn btn-primary">Chọn mua</button></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
