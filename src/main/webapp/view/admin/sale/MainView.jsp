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
                        <a class="nav-link" aria-current="page" href="/nhan_vien/create">QL NV</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/khach_hang/create">QL kH</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/san_pham/create">Ql sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/spct/create">QL SPCT</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/mau_sac/create">QL mau sac</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/kich_thuoc/create">QL kich thuoc</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<!-- <div class="col-f" style="width:700px;margin-left: auto;margin-right: auto;"> -->
<main>
    <div class="row ms-3">
        <div class="col-md-6">
            <h2>Hóa đơn</h2>
            <table class="table mt-7">
                <thead>
                <tr class="table-light">
                    <th scope="col">Stt</th>
                    <th scope="col">Id HĐ</th>
                    <th scope="col">Nhân viên</th>
                    <th scope="col">Khách hàng</th>
                    <th scope="col">Ngày mua hàng</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach varStatus="i" items="${dsHoaDon}" var="hoaDon">
                    <tr>
                        <td>1</td>
                        <td>${hoaDon.id}</td>
                        <td>${hoaDon.nhanVien.ten}-${hoaDon.nhanVien.ma}</td>
                        <td>${hoaDon.khachHang.ten}-${hoaDon.khachHang.ma}</td>
                        <td>${hoaDon.ngayMuaHang}</td>
                        <td>${hoaDon.trangThai}</td>
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
                <button type="button" class="btn btn-primary">Add new</button>
            </a>
        </div>
        <div class="col-md-6">
            <h2>Hóa đơn chi tiết</h2>
            <div class="d-flex flex-row mb-3">
                <p1>IdHD: ${hoaDon.id}</p1
                <p1>|Tên KH: ${hoaDon.khachHang.ten}</p1>
            </div>
            <div class="d-flex flex-row mb-3">
                <p1>Tên NV: ${hoaDon.nhanVien.ten}-${hoaDon.nhanVien.ma} </p1>
                <p1>|Ngày mua:${hoaDon.ngayMuaHang}</p1>
            </div>
            <table class="table mt-5">
                <thead>
                <tr class="table-light">
                    <th scope="col">ID HD</th>
                    <th scope="col">SPCT</th>
                    <th scope="col">Số lương</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Tổng</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach varStatus="i" items="${dsHDCT}" var="hdct">
                    <tr>
                        <td>${hdct.hoaDon.id}</td>
                        <td>${hdct.spct.sanPham.ten}-${hdct.spct.maSPCT}</td>
                        <td>${hdct.soLuong}</td>
                        <td>${hdct.donGia}</td>
                        <td>${hdct.soLuong*hdct.donGia}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <p1>Trạng thái: ${hoaDon.trangThai}</p1>
            <br>
            <a href="check/${hoaDon.id}">
                <button type="button" class="btn btn-success ">Check</button>
            </a>
        </div>
        <div class="col-md-12">
            <h2>Danh sách sản phẩm</h2>
            <table class="table mt-5">
                <thead>
                <tr class="table-light">
                    <th scope="col">ID</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Kích thước</th>
                    <th scope="col">Màu săc</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach varStatus="i" items="${pageData.content}" var="spct">
                    <tr>
                        <td>${spct.id}</td>
                        <td>${spct.sanPham.ten}-${spct.maSPCT}</td>
                        <td>${spct.donGia}</td>
                        <td>${spct.kichThuoc.ten}</td>
                        <td>${spct.mauSac.ten}</td>
                        <td>${spct.soLuong}</td>
                        <td>${spct.trangThai}</td>
                        <td><a href="addToCart/${spct.id}" class="btn btn-warning ms-3">Add</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link"
                                             href="/sale/create?page=${pageData.number-1}">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="#">${pageData.number+1}</a></li>
                    <li class="page-item"><a class="page-link" href="/sale/create?page=${pageData.number+1}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</main>
</body>
</html>