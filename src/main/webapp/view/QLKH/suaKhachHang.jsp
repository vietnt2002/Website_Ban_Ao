<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--    Thêm thư viện SweetAlert2 để thiển thị thông báo--%>
<!-- SweetAlert2 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- SweetAlert2 JS -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MSqlnv_Tại quầy</title>

    <!-- Custom fonts for this template-->
    <link href="/view_admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/view_admin/css/sb-admin-2.min.css" rel="stylesheet">

    <%--Link Ajax --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


    <style>
        .table-scroll {
            height: 340px;
            overflow: scroll;
        }

        ::-webkit-scrollbar {
            width: 10px;
            background-color: lightgray;
        }

        ::-webkit-scrollbar-thumb {
            background-color: rgb(238, 234, 234);
            border-radius: 5px;
        }

        .image-container {
            position: relative;
            display: inline-block;
        }

        #image-preview {
            width: 200px;
            height: 200px;
            border: 1px dashed gray;
            cursor: pointer;
            border-radius: 50%;
            display: block;
            padding: 4px;
        }

        #image-overlay {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: black;
            font-size: 18px;
            text-align: center;
            pointer-events: none;
            width: 80px; /* Adjust width as needed */
        }

        #image-preview:hover {
            background-color: #eee;
        }

        .file-info {
            margin-top: 10px;
            font-size: 14px;
            color: #333;
        }

        .error {
            color: red;
            font-size: 13px;
            width: 100%;
        }
    </style>

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">MS - qlnv</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <c:choose>
            <c:when test="${sessionScope.userRole == 'Quản lý'}">
                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="/admin/thong-ke/view" style="display: flex; align-items: center">
                        <i class="bi bi-graph-up" style="margin-left: 2px"></i>
                        <span style="margin-left: 6px">Thống kê doanh thu</span></a>
                </li>

                <!-- Nav Item - Dashboard -->
                <li class="nav-item">
                    <a class="nav-link" href="/ban-hang-tai-quay" style="display: flex; align-items: center">
                        <i class="bi bi-shop" style="margin-left: 2px"></i>
                        <span style="margin-left: 6px">Bán hàng tại quầy</span></a>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/hien-thi" style="display: flex; align-items: center">
                        <i class="bi bi-journal-text" style="margin-left: 2px"></i>
                        <span style="margin-left: 6px">Quản lý hóa đơn</span></a>
                </li>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                       aria-expanded="true" aria-controls="collapseTwo">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>Quản lý sản phẩm</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <a class="collapse-item" href="buttons.html">Màu sắc, kích thước các thứ</a>
                            <a class="collapse-item" href="cards.html">Cards</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="/qlnv/quan-ly-nhan-vien" style="display: flex; align-items: center">
                        <i class="bi bi-person-bounding-box" style="margin-left: 2px"></i>
                        <span style="margin-left: 6px">Quản lý nhân viên</span></a>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item" style="background: linear-gradient(45deg, black, transparent)">
                    <a class="nav-link" href="/qlkh/quan-ly-khach-hang" style="display: flex; align-items: center">
                        <i class="bi bi-person-bounding-box" style="color: white; margin-left: 2px"></i>
                        <span style="font-weight: bold; margin-left: 6px">Quản lý khách hàng</span></a>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="/qlkm/quan-ly-km" style="display: flex; align-items: center">
                        <i class="bi bi-gift" style="margin-left: 2px"></i>
                        <span style="margin-left: 6px">Quản lý khuyến mãi</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>
            </c:when>
            <c:otherwise>
                <!-- Nav Item - Dashboard -->
                <li class="nav-item">
                    <a class="nav-link" href="/ban-hang-tai-quay" style="display: flex; align-items: center">
                        <i class="bi bi-shop" style="margin-left: 2px"></i>
                        <span style="margin-left: 6px">Bán hàng tại quầy</span></a>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/hien-thi" style="display: flex; align-items: center">
                        <i class="bi bi-journal-text" style="margin-left: 2px"></i>
                        <span style="margin-left: 6px">Quản lý hóa đơn</span></a>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item" style="background: linear-gradient(45deg, black, transparent)">
                    <a class="nav-link" href="/qlkh/quan-ly-khach-hang" style="display: flex; align-items: center">
                        <i class="bi bi-person-bounding-box" style="color: white; margin-left: 2px"></i>
                        <span style="font-weight: bold; margin-left: 6px">Quản lý khách hàng</span></a>
                </li>
            </c:otherwise>
        </c:choose>

    </ul>

    <!--   -----------   -->
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-bell fa-fw"></i>
                            <!-- Counter - Alerts -->
                            <span class="badge badge-danger badge-counter">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="alertsDropdown">
                            <h6 class="dropdown-header">
                                Alerts Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-primary">
                                        <i class="fas fa-file-alt text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 12, 2019</div>
                                    <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-success">
                                        <i class="fas fa-donate text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 7, 2019</div>
                                    $290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-warning">
                                        <i class="fas fa-exclamation-triangle text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-envelope fa-fw"></i>
                            <!-- Counter - Messages -->
                            <span class="badge badge-danger badge-counter">7</span>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="messagesDropdown">
                            <h6 class="dropdown-header">
                                Message Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="/view_admin/img/undraw_profile_1.svg"
                                         alt="">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div class="font-weight-bold">
                                    <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                        problem I've been having.
                                    </div>
                                    <div class="small text-gray-500">Emily Fowler · 58m</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="/view_admin/img/undraw_profile_2.svg"
                                         alt="">
                                    <div class="status-indicator"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">I have the photos that you ordered last month, how
                                        would you like them sent to you?
                                    </div>
                                    <div class="small text-gray-500">Jae Chun · 1d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="/view_admin/img/undraw_profile_3.svg"
                                         alt="...">
                                    <div class="status-indicator bg-warning"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Last month's report looks great, I am very happy with
                                        the progress so far, keep up the good work!
                                    </div>
                                    <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                        told me that people say this to all dogs, even if they aren't good...
                                    </div>
                                    <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${nv.hoTen} | ${nv.idCV.ten}</span>
                            <img class="img-profile rounded-circle"
                                 src="/imageUser/${nv.anhDaiDien}">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="/qlnv/tai-khoan-cua-toi/${nv.id}">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Thông tin cá nhân
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/qlnv/dang-xuat" id="dang-xuat" data-toggle="modal"
                               data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Đăng xuất
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>


            <!-- Bán hàng tại quầy -->
            <div class="container-fluid">
                <div class="container">

                    <%--      Tìm kiếm & lọc thông tin nhân ciên      --%>
                    <h5 class="card-title mb-3">Sửa thông tin khách hàng</h5>
                    <sf:form id="khachHangForm" method="post" action="/qlkh/sua-khach-hang/${khachHang.id}" enctype="multipart/form-data"
                             modelAttribute="khachHang">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="card text-center" style="height: 550px;">
                                    <div class="card-body">
                                        <h5 class="card-title mt-4">Ảnh đại diện</h5>
                                        <label for="file-input" class="image-container mt-5">
                                            <img src="/imageUser/${khachHang.anhDaiDien}" alt="" id="image-preview">
                                            <div id="image-overlay">+ Upload</div>
                                            <sf:input type="file" id="file-input" accept="image/*"
                                                      style="display: none;" path="anhDaiDien"/>
                                            <input id="img" type="hidden" value="${khachHang.anhDaiDien}" name="img">
                                        </label>
                                        <div id="image-info" class="mt-4"></div>
                                    </div>
                                </div>
                            </div>


                            <div class="col-sm-8">
                                <div class="card" style="height: 550px">
                                    <div class="card-body">
                                        <h5 class="card-title mt-4" style="margin-bottom: 25px">Thông tin khách hàng</h5>
                                        <div class="row">
                                            <div class="col col-6">
                                                <div class="mb-3">
                                                    <label for="tenKhachHang" class="form-label"><span
                                                            class="text-danger">*</span> Tên khách hàng</label>
                                                    <sf:input type="text" class="form-control" id="tenKhachHang"
                                                              placeholder="Nhập họ tên.." path="hoTen"/>
                                                    <div id="error-tenKhachHang" class="error"></div>
                                                </div>

                                                <div class="mb-3">
                                                    <label for="ngaySinh" class="form-label"><span
                                                            class="text-danger">*</span> Ngày sinh</label>
                                                    <sf:input type="date" class="form-control" id="ngaySinh"
                                                              path="ngaySinh"/>
                                                    <div id="error-ngaySinh" class="error"></div>
                                                </div>

                                                <div class="mb-3">
                                                    <label for="email" class="form-label"><span
                                                            class="text-danger">*</span> Email</label>
                                                    <sf:input type="text" class="form-control" id="email"
                                                              placeholder="Nhập email.." path="email"/>
                                                    <div id="error-email" class="error"></div>
                                                </div>
                                            </div>

                                            <div class="col col-6">
                                                <div class="mb-3">
                                                    <label for="soDienThoai" class="form-label"><span
                                                            class="text-danger">*</span> Số điện thoại</label>
                                                    <sf:input type="number" class="form-control" id="soDienThoai"
                                                              placeholder="Nhập số điện thoại.." path="sdt"/>
                                                    <div id="error-soDienThoai" class="error"></div>
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label" style="display: block"><span
                                                            class="text-danger">*</span> Giới tính</label>
                                                    <sf:radiobutton path="gioiTinh" id="gioiTinhNam" label="Nam" value="1" cssStyle="margin-top: 5px" />
                                                    <sf:radiobutton path="gioiTinh" id="gioiTinhNu" label="Nữ" value="0" ssStyle="margin-top: 5px" />
                                                    <div id="error-gioiTinh" class="error"></div>
                                                </div>

                                                 <div class="mb-3">
                                                    <label for="trangThai" class="form-label"><span
                                                            class="text-danger">*</span> Trạng thái</label>
                                                    <sf:select class="form-select" aria-label="Default select example" path="trangThai" id="trangThai">

                                                        <option value="1" <c:if test="${khachHang.trangThai == 1}">selected</c:if>>Hoạt động</option>
                                                        <option value="0" <c:if test="${khachHang.trangThai == 0}">selected</c:if>>Ngừng hoạt động</option>
                                                    </sf:select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="text-center" style="margin-top: 40px">
                                            <button type="submit" class="btn btn-warning">Sửa</button>
                                            <a href="/qlkh/quan-ly-khach-hang">
                                                <button type="button" class="btn btn-secondary">Hủy</button>
                                            </a>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </sf:form>

                </div>


            </div>
            <!--  Kết thúc bán hàng tại quầy  -->

        </div>


        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>MS - qlnv</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>

</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<%--<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1"--%>
<%--     aria-hidden="true">--%>
<%--    <div class="modal-dialog" role="document">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="exampleModalLabel1">Bạn có chắc chắn muốn đăng xuất không?</h5>--%>
<%--                <button class="close" type="button" data-dismiss="modal" aria-label="Close">--%>
<%--                    <span aria-hidden="true">×</span>--%>
<%--                </button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">Chọn "Đăng xuất" bạn sẽ thoát khỏi trang này!</div>--%>
<%--            <div class="modal-footer">--%>
<%--                <button class="btn btn-secondary" type="button" data-dismiss="modal">Hủy</button>--%>
<%--                <a class="btn btn-primary" href="/qlnv/dang-xuat">Đăng xuất</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<!-- Bootstrap core JavaScript-->
<script src="/view_admin/vendor/jquery/jquery.min.js"></script>
<script src="/view_admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/view_admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/view_admin/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="/view_admin/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="/view_admin/js/demo/chart-area-demo.js"></script>
<script src="/view_admin/js/demo/chart-pie-demo.js"></script>

</body>
<script>
    // Hiển thị thông báo thêm thành công hoặc thất bại sử dụng thư viện Sweet Alert2
    const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
        }
    });

    <c:if test="${not empty success}">
    Toast.fire({
        icon: "success",
        title: "${success}"
    });
    </c:if>

    <c:if test="${not empty error}">
    Toast.fire({
        icon: "error",
        title: "${error}"
    });
    </c:if>

    <c:if test="${not empty errorBillMax}">
    Toast.fire({
        icon: "warning",
        title: "${errorBillMax}"
    });
    </c:if>

    //Đăng xuất
    document.getElementById('dang-xuat').addEventListener('click', function (event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>

        Swal.fire({
            title: "Bạn có chắc chắn muốn đăng xuất không?",
            text: "Bạn sẽ không thể hoàn tác hành động này!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            cancelButtonText: "Hủy",
            confirmButtonText: "Đăng xuất!"
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "Đã đăng xuất!",
                    text: "Bạn đã đăng xuất thành công.",
                    icon: "success"
                }).then(() => {
                    // Điều hướng tới URL đăng xuất sau khi người dùng xác nhận
                    window.location.href = "/admin/dang-nhap-view";
                });
            }
        });
    });

</script>

<script>
    //Chuyển list nhân viên java -> js
    var listKH = []
    <c:forEach items="${listKhachHang}" var="kh">
    if ('${kh.id}' !== '${khachHang.id}'){
        var KhachHang = {};
        KhachHang.id = "${kh.id}";
        KhachHang.hoTen = "${kh.hoTen}";
        KhachHang.gioiTinh = "${kh.gioiTinh}";
        KhachHang.ngaySinh = "${kh.ngaySinh}";
        KhachHang.sdt = "${kh.sdt}";
        KhachHang.email = "${kh.email}";
        KhachHang.anhDaiDien = "${kh.anhDaiDien}";
        KhachHang.trangThai = "${kh.trangThai}";
        listKH.push(KhachHang);
    }
    </c:forEach>
    console.log(listKH)
    //Validate form

    function isVietnamesePhoneNumber(phone) {
        const regex = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
        return regex.test(phone);
    }

    function validateEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    function clearErrors() {
        document.querySelectorAll('.error').forEach(element => {
            element.textContent = '';
        });
    }

    document.getElementById('khachHangForm').addEventListener('submit', function (event) {
        event.preventDefault();

        debugger
        clearErrors();

        const tenKhachHang = document.getElementById('tenKhachHang').value.trim();
        const ngaySinh = document.getElementById('ngaySinh').value.trim();
        const soDienThoai = document.getElementById('soDienThoai').value.trim();
        const email = document.getElementById('email').value.trim();
        const gioiTinhNam = document.getElementById('gioiTinhNam').checked;
        const gioiTinhNu = document.getElementById('gioiTinhNu').checked;

        let hasError = false;

        if (!tenKhachHang) {
            document.getElementById('error-tenKhachHang').textContent = 'Tên khách hàng không được để trống';
            hasError = true;
        }

        if (!ngaySinh) {
            document.getElementById('error-ngaySinh').textContent = 'Ngày sinh không được để trống';
            hasError = true;
        }

        if (!gioiTinhNam && !gioiTinhNu) {
            document.getElementById('error-gioiTinh').textContent = 'Giới tính không được để trống';
            hasError = true;
        }

        if (!soDienThoai) {
            document.getElementById('error-soDienThoai').textContent = 'Số điện thoại không được để trống';
            hasError = true;
        } else if (!isVietnamesePhoneNumber(soDienThoai)) {
            document.getElementById('error-soDienThoai').textContent = 'Số điện thoại không hợp lệ';
            hasError = true;
        } else if (listKH.some(khachHang => khachHang.sdt === soDienThoai)) {
            document.getElementById('error-soDienThoai').textContent = 'Số điện thoại đã tồn tại';
            hasError = true;
        }

        if (!email) {
            document.getElementById('error-email').textContent = 'Email không được để trống';
            hasError = true;
        } else if (!validateEmail(email)) {
            document.getElementById('error-email').textContent = 'Email không hợp lệ';
            hasError = true;
        } else if (listKH.some(khachHang => khachHang.email === email)) {
            document.getElementById('error-email').textContent = 'Email đã tồn tại';
            hasError = true;
        }

        if (hasError) {
            return;
        }

        event.preventDefault();
        Swal.fire({
            title: "Bạn có chắc chắn muốn sửa không?",
            text: "Bạn sẽ không thể hoàn tác hành động này!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            cancelButtonText: "Hủy",
            confirmButtonText: "Sửa"
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "Đã sửa!",
                    text: "Bạn đã sửa thông tin khách hàng thành công.",
                    icon: "success"
                }).then(() => {
                    // window.location.href = "/qlnv/quan-ly-nhan-vien";

                    document.getElementById('khachHangForm').submit();
                });
            }
        });
    });

    document.getElementById('file-input').addEventListener('change', function (event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('image-preview').src = e.target.result;
                document.getElementById('image-overlay').textContent = '';
                // Lấy thông tin file ảnh
                const fileInput = document.getElementById('file-input');
                const file = fileInput.files[0];
                if (file) {
                    const fileName = file.name;
                    document.getElementById('image-info').textContent = fileName;
                    document.getElementById('img').value = fileName;
                }
            }
            reader.readAsDataURL(file);
        }
    });
</script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const imageUrl = '${nhanVien.anhDaiDien}';
        if (imageUrl != null) {
            document.getElementById('image-overlay').textContent = '';
            document.getElementById('image-info').textContent = '${nhanVien.anhDaiDien}'
        } else {
            document.getElementById('image-overlay').textContent = '+ Upload';
        }
    });
</script>

</html>