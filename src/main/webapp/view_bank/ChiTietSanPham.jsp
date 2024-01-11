<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
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
    <form action="addChiTietSP" method=post>
        <div class="card bg-light text-white">
            <div class="card-img-overlay">
                <h5 class="card-title" style="color:darkblue; text-align: center; font-size: 36px;">Quản lý chi tiết sản
                    phẩm
                </h5>
                <div class="d-flex justify-content-center bg-white"
                     style="margin-left: auto;margin-right:auto;max-width:1280px">
                    <div class="form bg-white m-auto" style="color:black;width:1280px;padding:56px;">
                        <div class="row">
                            <div class="col-md-12 fw-bold mb-3" style="color:darkblue;font-size: 20px;">
                                <i class="bi bi-square-fill me-2" style="color:#f26522"></i>
                                Chi tiết sản phẩm
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Sản phẩm</label>
                                    <select class="form-select" aria-label="Default select example" name="sanPhamInp">
                                        <c:forEach varStatus="i" items="${lstSanPham}" var="sanPham">
                                            <option value="${sanPham.idSanPham}">${sanPham.ten} - ${sanPham.ma}</option>
                                        </c:forEach>
                                    </select>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Nơi sản xuất</label>
                                <select class="form-select" aria-label="Default select example" name="nsxInp">
                                    <c:forEach varStatus="i" items="${lstNSX}" var="nsx">
                                        <option value="${nsx.idNSX}">${nsx.ten} - ${nsx.ma}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Màu sắc</label>
                                <select class="form-select" aria-label="Default select example" name="mauSacInp">
                                    <c:forEach varStatus="i" items="${lstMauSac}" var="mauSac">
                                        <option value="${mauSac.idMauSac}">${mauSac.ten} - ${mauSac.ma}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Dòng sản phẩm</label>
                                <select class="form-select" aria-label="Default select example" name="dongSPInp">
                                    <c:forEach varStatus="i" items="${lstDongSP}" var="dongSP">
                                        <option value="${dongSP.idDongSP}">${dongSP.ten} - ${dongSP.ma}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Năm bảo hành</label>
                                <div class="input-group mb-2">
                                    <input type="number" class="form-control" placeholder=""
                                           aria-label="Username" aria-describedby="basic-addon1" name="namBHInp">
                                </div>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Mô tả</label>
                                <div class="input-group mb-2">
                                    <input type="text" class="form-control" placeholder=""
                                           aria-label="Username" aria-describedby="basic-addon1" name="motaInp">
                                </div>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Số lượng tồn</label>
                                <div class="input-group mb-2">
                                    <input type="text" class="form-control" placeholder=""
                                           aria-label="Username" aria-describedby="basic-addon1" name="soLuongTonInp">
                                </div>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Giá nhập</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="" aria-label=""
                                           aria-describedby="basic-addon1" name="giaNhapInp">
                                </div>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Giá bán</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="" aria-label=""
                                           aria-describedby="basic-addon1" name="giaBanInp">
                                </div>
                            </div>
                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Link</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="" aria-label=""
                                           aria-describedby="basic-addon1" name="linkInp">
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
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">NSX</th>
                                    <th scope="col">Màu sắc</th>
                                    <th scope="col">Dòng SP</th>
                                    <th scope="col">Năm BH</th>
                                    <th scope="col">Mô tả</th>
                                    <th scope="col">Số lượng tồn</th>
                                    <th scope="col">Giá nhập</th>
                                    <th scope="col">Giá bán</th>
                                    <th scope="col">Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach varStatus="i" items="${lstChiTietSP}" var="chiTietSP">
                                    <tr>
                                        <td>1</td>
                                        <td>${chiTietSP.sp.ten}-${chiTietSP.sp.ma}</td>
                                        <td>${chiTietSP.nsx.ten}-${chiTietSP.nsx.ma}</td>
                                        <td>${chiTietSP.mauSac.ten}-${chiTietSP.mauSac.ma}</td>
                                        <td>${chiTietSP.dongSP.ten} ${chiTietSP.dongSP.ma}</td>
                                        <td>${chiTietSP.namBH}</td>
                                        <td>${chiTietSP.mota}</td>
                                        <td>${chiTietSP.soLuongTon}</td>
                                        <td>${chiTietSP.giaNhap}</td>
                                        <td>${chiTietSP.giaBan}</td>
                                        <td>
                                            <a href="/Home/detail-chiTietSP?id=${chiTietSP.idChiTietSP}">
                                                <button type="button" class="btn btn-primary">Detail</button>
                                            </a>
                                            <a href="/Home/routeUpdate-chiTietSP?id=${chiTietSP.idChiTietSP}">
                                                <button type="button" class="btn btn-success ">Update</button>
                                            </a>
                                            <a href="/Home/delete-chiTietSP?id=${chiTietSP.idChiTietSP}">
                                                <button type="button" class="btn btn-danger">Delete</button>
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