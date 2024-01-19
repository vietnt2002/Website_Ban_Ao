<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <title>Document</title>
</head>

<body>
<header>
    <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">NEXTCOP</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/Home/shoping">Thông tin cá nhân</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/Home/login">Login</a>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control bg-light me-2" style="border:none" type="search"
                           placeholder="Tìm kiếm" aria-label="Tìm kiếm">
                    <button type="button" class="btn btn-danger">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>
<!-- <div class="col-f" style="width:700px;margin-left: auto;margin-right: auto;"> -->
<main>
    <div class="row ms-3">
    <div class="col-md-3">
        <h2>Danh sách sản phẩm</h2>
        <c:forEach varStatus="i" items="${dsSPCT}" var="spct">
            <div class="col-md-6">
                <div class="card" style="width: 18rem;">
                    <img src="https://bullsheathleather.com/cdn/shop/collections/Minimalist_Wallet.jpg?v=1648425217"
                         class="card-img-top" alt="..." style="width: 100%;height: 200px">
                    <div class="card-body">
                        <h5 class="card-title">${spct.sanPham.ten}-${spct.maSPCT}</h5>
                        <div class="d-flex flex-row mb-3">
                            <p class="card-text">Giá bán: ${spct.donGia}</p>
                            <p class="card-text ms-2">| Size: ${spct.kichThuoc.ten}</p>
                        </div>
                        <div class="d-flex flex-row mb-3">
                            <p class="card-text">Màu sắc: ${spct.mauSac.ten}</p>
                            <p class="card-text ms-2">| Còn lại: ${spct.soLuong}</p>
                        </div>
                        <div class="input-group">
                            <a href="addToCart/${spct.id}" class="btn btn-warning ms-3">Add</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
        <div class="col-md-5">
            <h2>Hóa đơn</h2>
            <table class="table mt-5">
                <thead>
                <tr class="table-light">
                    <th scope="col">Stt</th>
                    <th scope="col">Id HĐ</th>
                    <th scope="col">Nhân viên</th>
                    <th scope="col">Khách hàng</th>
                    <th scope="col">Ngày mua hàng</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach varStatus="i" items="${dsHoaDon}" var="hoaDon">
                    <tr>
                        <td>1</td>
                        <td>${hoaDon.id}</td>
                        <td>${hoaDon.nv.ten}-${hoaDon.nv.maNV}</td>
                        <td>${hoaDon.kh.ten}-${hoaDon.kh.maKH}</td>
                        <td>${hoaDon.ngayMua}</td>
                        <td>
                            <a href="select/${hoaDon.id}">
                                <button type="button" class="btn btn-success ">Select</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="addNewHD">
                <button type="button" class="btn btn-success ">Add new</button>
            </a>
        </div>
        <div class="col-md-4">
            <h2>Hóa đơn chi tiết</h2>
            <div class="d-flex flex-row mb-3">
                <p1>IdHD: ${hoaDon.id}</p1
                <p1>|Tên KH: ${hoaDon.kh.ten}</p1>
            </div>
            <div class="d-flex flex-row mb-3">
                <p1>Tên NV: ${hoaDon.nv.ten}-${hoaDon.nv.maNV} </p1>
                <p1>|Ngày mua:${hoaDon.ngayMua}</p1>
            </div>
                <table class="table mt-5">
                <thead>
                <tr class="table-light">
                    <th scope="col">Stt</th>
                    <th scope="col">idHoaDon</th>
                    <th scope="col">SPCT</th>
                    <th scope="col">Số lương</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Tổng </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach varStatus="i" items="${dsHDCT}" var="hdct">
                    <tr>
                        <td>1</td>
                        <td>${hdct.hd.id}</td>
                        <td>${hdct.spct.sanPham.ten}-${hdct.spct.maSPCT}</td>
                        <td>${hdct.soLuong}</td>
                        <td>${hdct.donGia}</td>
                        <td>${hdct.soLuong*hdct.donGia}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <p1>Trạng thái:${trangThai}</p1>
            <br>
            <a href="check/${hoaDon.id}">
                <button type="button" class="btn btn-success ">Check</button>
            </a>
        </div>
    </div>
</main>
</body>
</html>