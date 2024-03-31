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
            <tr>
                <td>1</td>
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>20/11/2023</td>
                <td>2.000.000đ</td>
                <td>Chua thanh toan</td>
            </tr>
            </tbody>
        </table>
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
            <tr>
                <td>1</td>
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>20/11/2023</td>
                <td>2.000.000đ</td>
                <td>Chua thanh toan</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="col-5">
        <h2>Tạo hoá đơn</h2>
        <div class="row">
            <div>
                <div>
                    <label class="mb-3 col-3">Số điện thoại</label>
                    <input type="text" class="col-7">
                </div>
                <button  class="btn btn-primary">Search</button>
            </div>
            <div class="mb-3">
                <label class="col-3">Ten Khach hang</label>
                <input type="text" class="col-7" readonly>
            </div>
            <div class="mb-3">
                <label class="col-3">ID Hoa don</label>
                <input type="text" class="col-7" readonly>
            </div>
            <div class="mb-3">
                <label class="col-3">Tong tien</label>
                <input type="text" class="col-7" readonly>
            </div>
            <div>
                <button  class="btn btn-primary">Tạo hoá đơn</button>
                <button  class="btn btn-primary">Thanh toán</button>
            </div>
        </div>

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
        <tr>
            <td>1</td>
            <td>1</td>
            <td>Nguyen Van A</td>
            <td>20/11/2023</td>
            <td>20/11/2023</td>
            <td>20/11/2023</td>
            <td>2.000.000đ</td>
            <td>Chua thanh toan</td>
            <td><button  class="btn btn-primary">Chọn mua</button></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
