<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
    <sf:form action="/spct/update/${data.id}" method="POST" modelAttribute="data">
        <div class="card bg-light text-white" style="height: 900px">
            <div class="card-img-overlay">
                <h5 class="card-title" style="color:darkblue; text-align: center; font-size: 36px;">Sản phẩm chi tiết
                </h5>
                <div class="d-flex justify-content-center bg-white"
                     style="margin-left: auto;margin-right:auto;max-width:1280px">
                    <div class="form bg-white m-auto" style="color:black;width:1280px;padding:56px;">
                        <div class="row">
                            <div class="col-md-12 fw-bold mb-3" style="color:darkblue;font-size: 20px;">
                                <i class="bi bi-square-fill me-2" style="color:#f26522"></i>
                                Quản lý sản phẩm chi tiết
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Id</label>
                                <div class="input-group mb-2">
                                    <sf:input type="text" class="form-control" placeholder="#idSystem"
                                              aria-label="Username" aria-describedby="basic-addon1" name="id"
                                              path="id"/>
                                    <sf:errors path="id" cssStyle="color: red"/>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Mã SPCT</label>
                                <div class="input-group mb-2">
                                    <sf:input type="text" class="form-control" placeholder="Mã SPCT"
                                              aria-label="Username" aria-describedby="basic-addon1" name="maSPCT"
                                              path="maSPCT"/>
                                    <sf:errors path="maSPCT" cssStyle="color: red"/>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Kích thước</label>
                                <div class="input-group mb-2">
                                    <sf:select class="form-select" id="inputGroupSelect01" name="kichThuoc"
                                               path="kichThuoc">
                                        <c:forEach varStatus="i" items="${dsKichThuoc}" var="kichThuoc">
                                            <option value=${kichThuoc.id} <c:if test="${data.kichThuoc == kichThuoc.id }">selected</c:if>>${kichThuoc.ten} - ${kichThuoc.ma}</option>
                                        </c:forEach>
                                    </sf:select>
                                    <sf:errors path="kichThuoc" cssStyle="color: red"/>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Màu sắc</label>
                                <div class="input-group mb-2">
                                    <select class="form-select" id="inputGroupSelect02" name="mauSac"
                                            path="mauSac">
                                        <c:forEach varStatus="i" items="${dsMauSac}" var="mauSac">
                                            <option value=${mauSac.id} <c:if test="${data.mauSac == mauSac.id }">selected</c:if>>${mauSac.ten} - ${mauSac.ma}</option>
                                        </c:forEach>
                                    </select>
                                    <sf:errors path="mauSac" cssStyle="color: red"/>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Sản phẩm</label>
                                <div class="input-group mb-2">
                                    <sf:select class="form-select" id="inputGroupSelect03" name="sanPham"
                                               path="sanPham">
                                        <c:forEach varStatus="i" items="${dsSanPham}" var="sanPham">
                                            <option value=${sanPham.id} <c:if test="${data.sanPham == sanPham.id }">selected</c:if>>${sanPham.ten} - ${sanPham.ma} </option>
                                        </c:forEach>
                                    </sf:select>
                                    <sf:errors path="sanPham" cssStyle="color: red"/>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Số lượng</label>
                                <div class="input-group mb-2">
                                    <sf:input type="text" class="form-control" placeholder="số lượng"
                                              aria-label="Username" aria-describedby="basic-addon1" name="soLuong"
                                              path="soLuong"/>
                                    <sf:errors path="soLuong" cssStyle="color: red"/>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Đơn giá</label>
                                <div class="input-group mb-2">
                                    <sf:input type="text" class="form-control" placeholder="Đơn giá"
                                              aria-label="Username" aria-describedby="basic-addon1" name="donGia"
                                              path="donGia"/>
                                    <sf:errors path="donGia" cssStyle="color: red"/>
                                </div>
                            </div>

                            <div class="col-xxl-3 col-xl-6 col-md-12 mb-2">
                                <label class="gfield_label fw-semibold" style="color:#374151;font-size: 14px;"
                                >Trạng thái</label>
                                <div class="input-group mb-2">
                                    <sf:input type="text" class="form-control" placeholder="Trạng thái"
                                              aria-label="Username" aria-describedby="basic-addon1" name="trangThai"
                                              path="trangThai"/>
                                    <sf:errors path="trangThai" cssStyle="color: red"/>
                                </div>
                            </div>

                            <div class="text-center ">
                                <br><br><br>
                                <button type="submit" class="btn btn-primary" style="border-radius:0%;">Thêm
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <h1>${data.kichThuoc==2}</h1>
    </sf:form>
</main>
</body>
</html>