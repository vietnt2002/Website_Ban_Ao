<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a class="navbar-brand" href="#"><strong>NEXTCOP</strong></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/Home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/Home/employee">Employee</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/Home/contactServlet">Contacts</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/Home/loginServlet">Login</a>
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
    <form action="addHoaDon" method=post>
        <div class="card bg-light text-white" style="height: 900px">
            <div class="card-img-overlay">
                <h5 class="card-title" style="color:darkblue; text-align: center; font-size: 36px;">Quản lý hóa đơn
                </h5>
                <div class="d-flex justify-content-center bg-white"
                     style="margin-left: auto;margin-right:auto;max-width:1280px">
                    <div class="form bg-white m-auto" style="color:black;width:1280px;padding:56px;">
                        <div class="row">
                            <div class="col-md-12 fw-bold mb-3" style="color:darkblue;font-size: 20px;">
                                <i class="bi bi-square-fill me-2" style="color:#f26522"></i>
                                Hóa đơn
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Khách hàng</label>
                                <div class="input-group mb-2">
                                    <input type="text" class="form-control" placeholder="sđt khách hàng"
                                           aria-label="Username" aria-describedby="basic-addon1" name="khachHangInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Nhân viên</label>
                                <div class="input-group mb-2">
                                    <input type="text" class="form-control" placeholder="Mã nv"
                                           aria-label="Username" aria-describedby="basic-addon1" name="nhanVienInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Mã</label>
                                <div class="input-group mb-2">
                                    <input type="text" class="form-control" placeholder="Mã hóa đơn"
                                           aria-label="Username" aria-describedby="basic-addon1" name="maInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Ngày tạo</label>
                                <div class="input-group mb-2">
                                    <input type="date" class="form-control" placeholder="Từ ngày"
                                           aria-label="Username" aria-describedby="basic-addon1" name="ngayTaoInp">
                                    <input type="date" class="form-control" placeholder="Đến ngày"
                                           aria-label="Username" aria-describedby="basic-addon1" name="ngayTaoInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Ngày thanh toán</label>
                                <div class="input-group mb-2">
                                    <input type="date" class="form-control" placeholder="Từ ngày"
                                           aria-label="Username" aria-describedby="basic-addon1" name="ngayTaoInp">
                                    <input type="date" class="form-control" placeholder="Đến ngày"
                                           aria-label="Username" aria-describedby="basic-addon1" name="ngayTaoInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Ngày ship</label>
                                <div class="input-group mb-2">
                                    <input type="date" class="form-control" placeholder="Từ ngày"
                                           aria-label="Username" aria-describedby="basic-addon1" name="ngayTaoInp">
                                    <input type="date" class="form-control" placeholder="Đến ngày"
                                           aria-label="Username" aria-describedby="basic-addon1" name="ngayTaoInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Ngày nhận</label>
                                <div class="input-group mb-2">
                                    <input type="date" class="form-control" placeholder="Từ ngày"
                                           aria-label="Username" aria-describedby="basic-addon1" name="ngayTaoInp">
                                    <input type="date" class="form-control" placeholder="Đến ngày"
                                           aria-label="Username" aria-describedby="basic-addon1" name="ngayTaoInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Tình trạng</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="" aria-label=""
                                           aria-describedby="basic-addon1" name="tinhTrangInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Tên người nhận</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="" aria-label=""
                                           aria-describedby="basic-addon1" name="tenNguoiNhanInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Sđt</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="" aria-label=""
                                           aria-describedby="basic-addon1" name="sdtInp">
                                    <a class="btn btn-primary" href="#" role="button">Tìm kiếm</a>
                                </div>
                            </div>

                            <div class="text-center ">
                                <br><br><br>
                                <button type="submit" class="btn btn-primary" style="border-radius:0%;">Thêm
                                </button>
                            </div>

                            <table class="table mt-5">
                                <thead>
                                <tr class="table-light">
                                    <th scope="col">Stt</th>
                                    <th scope="col">Khách hàng</th>
                                    <th scope="col">Nhân viên</th>
                                    <th scope="col">Mã</th>
                                    <th scope="col">Ngày tạo</th>
                                    <th scope="col">Ngày thanh toán</th>
                                    <th scope="col">Ngày ship</th>
                                    <th scope="col">Ngày nhận</th>
                                    <th scope="col">Tình trạng</th>
                                    <th scope="col">Ten người nhận</th>
                                    <th scope="col">Địa chỉ</th>
                                    <th scope="col">Sđt</th>
                                    <th scope="col">Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach varStatus="i" items="${lstHoaDon}" var="hoaDon">
                                    <tr>
                                        <td>1</td>
                                        <td>${hoaDon.KH.sdt}</td>
                                        <td>${hoaDon.nv.ten} ${hoaDon.nv.ma}</td>
                                        <td>${hoaDon.ma}</td>
                                        <td>${hoaDon.ngayTao}</td>
                                        <td>${hoaDon.ngayThanhToan}</td>
                                        <td>${hoaDon.ngayShip}</td>
                                        <td>${hoaDon.ngayNhan}</td>
                                        <td>${hoaDon.tinhTrang}</td>
                                        <td>${hoaDon.tenNguoiNhan}</td>
                                        <td>${hoaDon.diaChi}</td>
                                        <td>${hoaDon.sdt}</td>
                                        <td>
                                            <a href="/Home/detail-hoaDon?id=${hoaDon.idHoaDon}">
                                                <button type="button" class="btn btn-primary">Detail</button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</main>
</body>
</html>