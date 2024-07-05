<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--    Thêm thư viện SweetAlert2 để thiển thị thông báo--%>
<!-- SweetAlert2 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">

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
    <title>MSStore_Tại quầy</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
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


    <style>
        .table-scroll {
            height: 340px;
            overflow: scroll;
        }

        .table-scroll2 {
            height: 460px;
            overflow: scroll;
        }

        ::-webkit-scrollbar {
            width: 10px;
            background-color: light grey;
        }

        ::-webkit-scrollbar-thumb {
            background-color: rgb(238, 234, 234);
            border-radius: 5px;
        }

        .button-container {
            display: flex;
            gap: 10px; /* Tạo khoảng cách giữa các button */
        }

        #button {
            flex: none; /* Đảm bảo kích thước button không thay đổi */
        }

        th, td {
            text-align: center;
        }

    </style>

    <style>
        .icon-container i {
            transition: color 0.3s ease;
        }
        .icon-container i:hover {
            color: #4e73df; /* Change this to your desired hover color */
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
            <div class="sidebar-brand-text mx-3">MS - Store</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="index.html">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Thống kê doanh thu</span></a>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/ban-hang-tai-quay/admin/ban-tai-quay">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Bán hàng tại quầy</span></a>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="charts.html">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý đơn hàng</span></a>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="charts.html">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý hóa đơn</span></a>
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
            <a class="nav-link" href="charts.html">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý nhân viên</span></a>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="charts.html">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý khách hàng</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

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
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${nv.hoTen}</span>
                            <img class="img-profile rounded-circle"
                                 src="/imageUser/${nv.anhDaiDien}">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="/store/tai-khoan-cua-toi">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Tài khoản của tôi
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/store/dang-xuat" id="dang-xuat" data-toggle="modal"
                               data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Đăng xuất
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <!-- Bán hàng tại quầy -->
            <div class="container-fluid bg-white">
                <div class="container">
                    <div class="row">
                        <div class="mt-3">
                            <h4 class="border-bottom">Quản lý sản phẩm chi tiết</h4>
                            <div class="d-flex gap-3 mt-3">
                                <img id="hinhAnhSP" width="200" height="200" alt="">
                                <div id="product-details">
                                    <h5 class="border-bottom">Tên sản phẩm:&nbsp&nbsp<span id="tenSP"></span></h5>
                                    <h5 class="border-bottom">Mã sản phẩm:&nbsp&nbsp<span id="maSP"></span></h5>
                                    <h5 class="border-bottom">Ngày tạo:&nbsp&nbsp<span id="ngayTaoSP"></span></h5>
                                    <h5 class="border-bottom">Trạng thái:&nbsp&nbsp<span id="trangThaiSP"></span></h5>
                                </div>
                            </div>
                            <h5 class="mt-3 border-bottom">Danh sách sản phẩm chi tiết: </h5>
                            <div class="mt-3">
                                <div class="row">
                                    <div class="col col-md-2">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblMauSac" style="width: 150px;" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn màu sắc
                                            </button>
                                            <ul class="dropdown-menu" id="cboMauSac"
                                                aria-labelledby="dropdownMenuButton2">
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col col-md-2">
                                        <div class="dropdown" style="width: 200px;">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblKichThuoc" style="width: 150px;" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn kích thước
                                            </button>
                                            <ul class="dropdown-menu" id="cboKichThuoc"
                                                aria-labelledby="dropdownMenuButton3">
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col col-md-2">
                                        <div class="dropdown" style="width: 200px;">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblChatLieu" style="width: 150px;" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn chất liệu
                                            </button>
                                            <ul class="dropdown-menu" id="cboChatLieu"
                                                aria-labelledby="dropdownMenuButton4">
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col col-md-2">
                                        <div class="dropdown" style="width: 200px;">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblKieuTay" style="width: 150px;" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn kiểu tay
                                            </button>
                                            <ul class="dropdown-menu" id="cboKieuTay"
                                                aria-labelledby="dropdownMenuButton5">
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col col-md-3 d-flex gap-3">
                                        <button id="btnSearch" class="btn btn-success me-2" onclick="search(event)">
                                            Tìm kiếm
                                        </button>
                                        <button id="btnRef" class="btn btn-success me-2" onclick="refresh(event)">
                                            Refresh
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Màu sắc</th>
                                    <th>Kích thước</th>
                                    <th>Chất liệu</th>
                                    <th>Kiểu tay</th>
                                    <th>Số lượng</th>
                                    <th>Trạng thái</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tbody id="tbl_ds_spct">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-row-reverse">
                <button id="btnAdd" class="btn btn-success me-2" data-bs-toggle="modal" data-bs-target="#ModalAdd">Thêm
                    mới
                </button>
            </div>
            <div class="d-flex flex-row-reverse">
                <button onclick="testDataMaping(event)" class="btn btn-success me-2">test</button>
            </div>
            <div class="col-12 pb-1">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center mb-3">
                        <li class="page-item" id="prev">
                            <Button class="page-link" onclick="navigate(-1,event)">Previous</Button>
                        </li>
                        <div class="d-flex" id="paginationBody">
                        </div>
                        <li class="page-item" id="next">
                            <Button class="page-link" onclick="navigate(1,event)">Next</Button>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- ModalSPAdd-->
        <div class="modal fade" id="ModalAdd" tabindex="-1" aria-labelledby="exampleModalLabel4" aria-hidden="true"
             data-bs-backdrop="static" data-bs-keyboard="false">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row">
                            <h3 class="modal-title">Chỉnh sửa sản phẩm chi tiết</h3>
                        </div>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <div class="row">
                                <div class="d-flex" style="gap: 500px;">
                                    <h5 class="border-bottom">Tên sản phẩm:&nbsp&nbsp<span id="tenSPModalAdd"></span></h5>
                                    <h5 class="border-bottom">Ngày tạo:&nbsp&nbsp<span id="ngayTaoModalAdd"></span></h5>
                                </div>
                                <div class="col col-md-3">
                                    <div class="d-flex">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblMauSacModalAdd" style="width: 150px;" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn màu sắc
                                            </button>
                                            <ul class="dropdown-menu" id="cboMauSacModalAdd"
                                                aria-labelledby="dropdownMenuButton2">
                                            </ul>
                                            <p style="color: red;"id="cboMauSacModalAddErr"></p>
                                        </div>
                                        <div class="icon-container">
                                            <i class=" bi bi-folder-plus col-3" data-bs-toggle="modal" data-bs-target="#ModalHotAdd" id="iconHotAddMSModalAdd" style="font-size: 25px"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="col col-md-3">
                                    <div class="d-flex">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblKichThuocModalAdd" style="width: 150px;"
                                                    data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn kích thước
                                            </button>
                                            <ul class="dropdown-menu" id="cboKichThuocModalAdd"
                                                aria-labelledby="dropdownMenuButton3">
                                            </ul>
                                            <p style="color: red;"id="cboKichThuocModalAddErr"></p>
                                        </div>
                                        <div class="icon-container">
                                            <i class=" bi bi-folder-plus col-3" data-bs-toggle="modal" data-bs-target="#ModalHotAdd" id="iconHotAddKTModalAdd" style="font-size: 25px"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="col col-md-3">
                                    <div class="d-flex">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblChatLieuModalAdd" style="width: 150px;"
                                                    data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn chất liệu
                                            </button>
                                            <ul class="dropdown-menu" id="cboChatLieuModalAdd"
                                                aria-labelledby="dropdownMenuButton4">
                                            </ul>
                                            <p style="color: red;"id="cboChatLieuModalAddErr"></p>
                                        </div>
                                        <div class="icon-container">
                                            <i class=" bi bi-folder-plus col-3" data-bs-toggle="modal" data-bs-target="#ModalHotAdd" id="iconHotAddCLModalAdd" style="font-size: 25px"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="col col-md-3">
                                    <div class="d-flex">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblKieuTayModalAdd" style="width: 150px;" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn kiểu tay
                                            </button>
                                            <ul class="dropdown-menu" id="cboKieuTayModalAdd"
                                                aria-labelledby="dropdownMenuButton5">
                                            </ul>
                                            <p style="color: red;"id="cboKieuTayModalAddErr"></p>
                                        </div>
                                        <div class="icon-container">
                                            <i class=" bi bi-folder-plus col-3" data-bs-toggle="modal" data-bs-target="#ModalHotAdd" id="iconHotAddKTAModalAdd" style="font-size: 25px"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-4">
                                    <label for="soLuongModalAdd" class="form-label">Số lượng</label>
                                    <input type="number" name="file" class="form-control" id="soLuongModalAdd"
                                           value="">
                                    <p style="color: red;" id="soLuongModalAddErr"></p>
                                </div>
                                <div class="col col-md-4">
                                    <label for="giaNhapModalAdd" class="form-label">Giá nhập</label>
                                    <input type="number" name="file" class="form-control" id="giaNhapModalAdd"
                                           value="">
                                    <p style="color: red;" id="giaNhapModalAddErr"></p>
                                </div>
                                <div class="col col-md-4">
                                    <label for="giaBanModalAdd" class="form-label">Giá bán</label>
                                    <input type="number" name="file" class="form-control" id="giaBanModalAdd" value="">
                                    <p style="color: red;" id="giaBanModalAddErr"></p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-3">
                                    <label for="moTaModalAdd" class="form-label">Ghi chú</label>
                                    <textarea class="form-control custom-textarea" style="width: 550px;"
                                              id="moTaModalAdd" name="moTa"></textarea>
                                    <p style="color: red;" id="moTaModalAddErr"></p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-3">
                                    <button type="button" class="btn btn-secondary">Chọn hình ảnh</button>
                                </div>
                            </div>
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch" id="trangThaiModalAdd"
                                       checked>
                                <label class="form-check-label" for="trangThaiLabelModalAdd" id="trangThaiLabelModalAdd">Trạng
                                    thái</label>
                            </div>
                            <button id="saveAddBtn" type="submit" class="btn btn-primary">Lưu</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--End modal --%>
        <!-- ModalSPEdit-->
        <div class="modal fade" id="ModalEdit" tabindex="-1" aria-labelledby="exampleModalLabel4" aria-hidden="true"
             data-bs-backdrop="static" data-bs-keyboard="false">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row">
                            <h3 class="modal-title" id="exampleModalLabel4">Chỉnh sửa sản phẩm chi tiết</h3>
                        </div>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <div class="row">
                                <div class="d-flex" style="gap: 500px;">
                                    <h5 class="border-bottom">Tên sản phẩm:&nbsp&nbsp<span id="tenSPModalEdit"></span></h5>
                                    <h5 class="border-bottom">Ngày tạo:&nbsp&nbsp<span id="ngayTaoModalEdit"></span></h5>
                                </div>
                                <div class="col col-md-3">
                                    <div class="d-flex">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblMauSacModalEdit" style="width: 150px;" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn màu sắc
                                            </button>
                                            <ul class="dropdown-menu" id="cboMauSacModalEdit"
                                                aria-labelledby="dropdownMenuButton2">
                                            </ul>
                                            <p style="color: red;"id="cboMauSacModalEditErr"></p>
                                        </div>
                                        <div class="icon-container">
                                            <i class=" bi bi-folder-plus col-3" data-bs-toggle="modal" data-bs-target="#ModalHotAdd" style="font-size: 25px"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="col col-md-3">
                                    <div class="d-flex">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblKichThuocModalEdit" style="width: 150px;"
                                                    data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn kích thước
                                            </button>
                                            <ul class="dropdown-menu" id="cboKichThuocModalEdit"
                                                aria-labelledby="dropdownMenuButton3">
                                            </ul>
                                            <p style="color: red;"id="cboKichThuocModalEditErr"></p>
                                        </div>
                                        <div class="icon-container">
                                            <i class=" bi bi-folder-plus col-3"  data-bs-toggle="modal" data-bs-target="#ModalHotAdd" style="font-size: 25px"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="col col-md-3">
                                    <div class="d-flex">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblChatLieuModalEdit" style="width: 150px;"
                                                    data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn chất liệu
                                            </button>
                                            <ul class="dropdown-menu" id="cboChatLieuModalEdit"
                                                aria-labelledby="dropdownMenuButton4">
                                            </ul>
                                            <p style="color: red;"id="cboChatLieuModalEditErr"></p>
                                        </div>
                                        <div class="icon-container">
                                            <i class=" bi bi-folder-plus col-3"  data-bs-toggle="modal" data-bs-target="#ModalHotAdd" style="font-size: 25px"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="col col-md-3">
                                    <div class="d-flex">
                                        <div class="dropdown">
                                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                    id="lblKieuTayModalEdit" style="width: 150px;" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Chọn kiểu tay
                                            </button>
                                            <ul class="dropdown-menu" id="cboKieuTayModalEdit"
                                                aria-labelledby="dropdownMenuButton5">
                                            </ul>
                                            <p style="color: red;"id="cboKieuTayModalEditErr"></p>
                                        </div>
                                        <div class="icon-container">
                                            <i class=" bi bi-folder-plus col-3"  data-bs-toggle="modal" data-bs-target="#ModalHotAdd" style="font-size: 25px"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-4">
                                    <label for="soLuongModalEdit" class="form-label">Số lượng</label>
                                    <input type="number" name="file" class="form-control" id="soLuongModalEdit"
                                           value="">
                                    <p style="color: red;" id="soLuongModalEditErr"></p>
                                </div>
                                <div class="col col-md-4">
                                    <label for="giaNhapModalEdit" class="form-label">Giá nhập</label>
                                    <input type="number" name="file" class="form-control" id="giaNhapModalEdit"
                                           value="">
                                    <p style="color: red;" id="giaNhapModalEditErr"></p>
                                </div>
                                <div class="col col-md-4">
                                    <label for="giaBanModalEdit" class="form-label">Giá bán</label>
                                    <input type="number" name="file" class="form-control" id="giaBanModalEdit" value="">
                                    <p style="color: red;" id="giaBanModalEditErr"></p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-3">
                                    <label for="moTaModalEdit" class="form-label">Ghi chú</label>
                                    <textarea class="form-control custom-textarea" style="width: 550px;"
                                              id="moTaModalEdit" name="moTa"></textarea>
                                    <p style="color: red;" id="moTaModalEditErr"></p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col col-md-3">
                                    <button type="button" class="btn btn-secondary">Chọn hình ảnh</button>
                                </div>
                            </div>
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch" id="trangThaiModalEdit"
                                       checked>
                                <label class="form-check-label" for="trangThaiLabelModalEdit" id="trangThaiLabelModalEdit">Trạng
                                    thái</label>
                            </div>
                            <button id="saveEditBtn" type="submit" class="btn btn-primary">Lưu</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--End modal --%>

        <!-- ModalHotAdd -->
        <div class="modal fade" id="modalHotAdd" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"
             data-bs-backdrop="static" data-bs-keyboard="false" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row">
                            <h5 class="modal-title" id="exampleModalLabel1">Thêm nhanh</h5>
                        </div>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/ban-hang-tai-quay/create">
                            <div class="mb-3 mt-3">
                                <label for="tenModalHotAdd" class="form-label">Tên thộc tính:</label>
                                <input type="text" class="form-control" id="tenModalHotAdd">
                            </div>
                            <div class="text-center">
                                <button class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
<%--        end modal hot add--%>

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>MS - Store</span>
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
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel3">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>
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
    let idSPCTLocal = "";
    let currentPage = 1;
    let totalPage = 0;
    const queryString = window.location.pathname;
    const pathParts = queryString.split('/');
    const pathVariable = pathParts[pathParts.length - 1];
    let idMauSac = "";
    let idKichThuoc = "";
    let idChatLieu = "";
    let idKieuTay = "";
    const lblMauSac = document.getElementById("lblMauSac");
    const lblKichThuoc = document.getElementById("lblKichThuoc");
    const lblChatLieu = document.getElementById("lblChatLieu");
    const lblKieuTay = document.getElementById("lblKieuTay");
    let idMauSacModalEdit = "";
    let idKichThuocModalEdit = "";
    let idChatLieuModalEdit = "";
    let idKieuTayModalEdit = "";
    let soLuongModalEdit = document.getElementById("soLuongModalEdit");
    let giaNhapModalEdit = document.getElementById("giaNhapModalEdit");
    let giaBanModalEdit = document.getElementById("giaBanModalEdit");
    let ghiChuModalEdit = document.getElementById("moTaModalEdit");
    let trangThaiModalEditRaw = document.getElementById("trangThaiModalEdit");
    const trangThaiLabelModalEdit = document.getElementById("trangThaiLabelModalEdit");

    const lblMauSacModalEdit = document.getElementById("lblMauSacModalEdit");
    const lblKichThuocModalEdit = document.getElementById("lblKichThuocModalEdit");
    const lblChatLieuModalEdit = document.getElementById("lblChatLieuModalEdit");
    const lblKieuTayModalEdit = document.getElementById("lblKieuTayModalEdit");
    let idMauSacModalAdd = "";
    let idKichThuocModalAdd = "";
    let idChatLieuModalAdd = "";
    let idKieuTayModalAdd = "";
    let soLuongModalAdd = document.getElementById("soLuongModalAdd");
    let giaNhapModalAdd = document.getElementById("giaNhapModalAdd");
    let giaBanModalAdd = document.getElementById("giaBanModalAdd");
    let ghiChuModalAdd = document.getElementById("moTaModalAdd");
    let trangThaiModalAddRaw  = document.getElementById("trangThaiModalAdd");
    const trangThaiLabelModalAdd = document.getElementById("trangThaiLabelModalAdd");

    const lblMauSacModalAdd = document.getElementById("lblMauSacModalAdd");
    const lblKichThuocModalAdd = document.getElementById("lblKichThuocModalAdd");
    const lblChatLieuModalAdd = document.getElementById("lblChatLieuModalAdd");
    const lblKieuTayModalAdd = document.getElementById("lblKieuTayModalAdd");

</script>

<script>
    // change sttlbl add
    function updateLabeladd() {
        if (trangThaiModalAddRaw.checked) {
            trangThaiLabelModalAdd.textContent = "Đang hoạt động";
        } else {
            trangThaiLabelModalAdd.textContent = "Dừng hoạt động";
        }
    }
    trangThaiModalAddRaw.addEventListener("change", updateLabeladd);
    updateLabeladd();
    // change sttlbl edit
    function updateLabeledit() {
        if (trangThaiModalEditRaw.checked) {
            trangThaiLabelModalEdit.textContent = "Đang hoạt động";
        } else {
            trangThaiLabelModalEdit.textContent = "Dừng hoạt động";
        }
    }
    trangThaiModalEditRaw.addEventListener("change", updateLabeledit);
    // end change sttlbl edit
    updateLabeledit();
    function setMauSac(msString) {
        const ms = JSON.parse(msString.replace(/&quot;/g, '"'));
        idMauSac = ms.id;
        lblMauSac.textContent = ms.ten;
        console.log('Selected mau sac ID:', idMauSac);
        // You can add more logic here to handle the selected value
    }

    function setKichThuoc(ktString) {
        const kt = JSON.parse(ktString.replace(/&quot;/g, '"'));
        idKichThuoc = kt.id;
        lblKichThuoc.textContent = kt.ten;
        console.log('Selected kich thuoc ID:', idKichThuoc);
        // You can add more logic here to handle the selected value
    }

    function setChatLieu(clString) {
        const cl = JSON.parse(clString.replace(/&quot;/g, '"'));
        idChatLieu = cl.id;
        lblChatLieu.textContent = cl.ten;
        console.log('Selected chat lieu ID:', idChatLieu);
        // You can add more logic here to handle the selected value
    }
    function setKieuTay(ktString) {
        const kt = JSON.parse(ktString.replace(/&quot;/g, '"'));
        idKieuTay = kt.id;
        lblKieuTay.textContent = kt.ten;
        console.log('Selected kieu tay ID modal:', idKieuTay);
        // You can add more logic here to handle the selected value
    }

    function setMauSacModalEdit(msString) {
        const ms = JSON.parse(msString.replace(/&quot;/g, '"'));
        idMauSacModalEdit = ms.id;
        lblMauSacModalEdit.textContent = ms.ten;
        console.log('Selected mau sac ID modal edit:', idMauSacModalEdit);
        // You can add more logic here to handle the selected value
    }

    function setKichThuocModalEdit(ktString) {
        const kt = JSON.parse(ktString.replace(/&quot;/g, '"'));
        idKichThuocModalEdit = kt.id;
        lblKichThuocModalEdit.textContent = kt.ten;
        console.log('Selected kich thuoc ID modal edit:', idKichThuocModalEdit);
        // You can add more logic here to handle the selected value
    }

    function setChatLieuModalEdit(clString) {
        const cl = JSON.parse(clString.replace(/&quot;/g, '"'));
        idChatLieuModalEdit = cl.id;
        lblChatLieuModalEdit.textContent = cl.ten;
        console.log('Selected chat lieu ID modal Edit:', idChatLieuModalEdit);
        // You can add more logic here to handle the selected value
    }

    function setKieuTayModalEdit(ktString) {
        const kt = JSON.parse(ktString.replace(/&quot;/g, '"'));
        idKieuTayModalEdit = kt.id;
        lblKieuTayModalEdit.textContent = kt.ten;
        console.log('Selected kieu tay ID modal edit:', idKieuTayModalEdit);
        // You can add more logic here to handle the selected value
    }

    function setMauSacModalAdd(msString) {
        const ms = JSON.parse(msString.replace(/&quot;/g, '"'));
        idMauSacModalAdd = ms.id;
        lblMauSacModalAdd.textContent = ms.ten;
        console.log('Selected mau sac ID modal add:', idMauSacModalAdd);
        // You can add more logic here to handle the selected value
    }

    function setKichThuocModalAdd(ktString) {
        const kt = JSON.parse(ktString.replace(/&quot;/g, '"'));
        idKichThuocModalAdd = kt.id;
        lblKichThuocModalAdd.textContent = kt.ten;
        console.log('Selected kich thuoc ID modal Add:', idKichThuocModalAdd);
        // You can add more logic here to handle the selected value
    }

    function setChatLieuModalAdd(clString) {
        const cl = JSON.parse(clString.replace(/&quot;/g, '"'));
        idChatLieuModalAdd = cl.id;
        lblChatLieuModalAdd.textContent = cl.ten;
        console.log('Selected chat lieu ID modal add:', idChatLieuModalAdd);
        // You can add more logic here to handle the selected value
    }

    function setKieuTayModalAdd(ktString) {
        const kt = JSON.parse(ktString.replace(/&quot;/g, '"'));
        idKieuTayModalAdd = kt.id;
        lblKieuTayModalAdd.textContent = kt.ten;
        console.log('Selected kieu tay ID modal add:', idKieuTayModalAdd);
        // You can add more logic here to handle the selected value
    }

    const loadSP = () => {
        fetch("/san-pham/detail/" + pathVariable, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                let trangThaiSP = "";
                if (resp.trangThai == 1) {
                    trangThaiSP = "Đang hoạt động";
                } else {
                    trangThaiSP = "Dừng hoạt động";
                }
                $('#hinhAnhSP').attr('src', "/image/" + resp.hinhAnh);
                $('#tenSP').text(resp.ten);
                $('#maSP').text(resp.ma);
                $('#ngayTaoSP').text(resp.ngayTao);
                $('#trangThaiSP').text(trangThaiSP);
            });
    }
    loadSP();
    const loadCboMauSac = () => {
        let datatest = "data testing";
        fetch("/mau-sac/index", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                let htmlSearch = '';
                let htmlCboModalEdit='';
                let htmCboModalAdd='';
                resp.map((ms, i) => {
                    const msString = JSON.stringify(ms).replace(/"/g, '&quot;');
                    htmlSearch += '<li><a class="dropdown-item"  onclick="setMauSac(\'' + msString + '\')">' + ms.ten + '</a></li>';
                    htmlCboModalEdit += '<li><a class="dropdown-item"  onclick="setMauSacModalEdit(\'' + msString + '\')">' + ms.ten + '</a></li>'
                    htmCboModalAdd += '<li><a class="dropdown-item"  onclick="setMauSacModalAdd(\'' + msString + '\')">' + ms.ten + '</a></li>'
                });
                $("#cboMauSac").html(htmlSearch);
                $("#cboMauSacModalAdd").html(htmCboModalAdd);
                $("#cboMauSacModalEdit").html(htmlCboModalEdit);
            });
    }
    loadCboMauSac();
    const loadCboKichThuoc = () => {
        let datatest = "data testing";
        fetch("/kich-thuoc/index", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                let htmlSearch = '';
                let htmlCboModalEdit='';
                let htmlCboModalAdd='';
                resp.map((kt, i) => {
                    const ktString = JSON.stringify(kt).replace(/"/g, '&quot;');
                    htmlSearch += '<li><a class="dropdown-item" onclick="setKichThuoc(\'' + ktString + '\')">' + kt.ten + '</a></li>';
                    htmlCboModalEdit += '<li><a class="dropdown-item" onclick="setKichThuocModalEdit(\'' + ktString + '\')">' + kt.ten + '</a></li>';
                    htmlCboModalAdd += '<li><a class="dropdown-item" onclick="setKichThuocModalAdd(\'' + ktString + '\')">' + kt.ten + '</a></li>';
                });
                $("#cboKichThuoc").html(htmlSearch);
                $("#cboKichThuocModalAdd").html(htmlCboModalAdd);
                $("#cboKichThuocModalEdit").html(htmlCboModalEdit);
            });
    }
    loadCboKichThuoc();
    const loadCboChatLieu = () => {
        let datatest = "data testing";
        fetch("/chat-lieu/index", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                let htmlSearch = '';
                let htmlCboModalEdit='';
                let htmlCboModalAdd='';
                resp.map((cl, i) => {
                    const clString = JSON.stringify(cl).replace(/"/g, '&quot;');
                    htmlSearch += '<li><a class="dropdown-item" onclick="setChatLieu(\'' + clString + '\')">' + cl.ten + '</a></li>';
                    htmlCboModalAdd += '<li><a class="dropdown-item" onclick="setChatLieuModalAdd(\'' + clString + '\')">' + cl.ten + '</a></li>';
                    htmlCboModalEdit += '<li><a class="dropdown-item" onclick="setChatLieuModalEdit(\'' + clString + '\')">' + cl.ten + '</a></li>';
                });
                $("#cboChatLieu").html(htmlSearch)
                $("#cboChatLieuModalAdd").html(htmlCboModalAdd);
                $("#cboChatLieuModalEdit").html(htmlCboModalEdit);
            });
    }
    loadCboChatLieu();
    const loadCboKieuTay = () => {
        let datatest = "data testing";
        fetch("/kieu-tay/index", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                let htmlSearch = '';
                let htmlCboModalEdit='';
                let htmlCboModalAdd='';
                resp.map((kt, i) => {
                    const kieuTayString = JSON.stringify(kt).replace(/"/g, '&quot;');
                    htmlSearch += '<li><a class="dropdown-item"  onclick="setKieuTay(\'' + kieuTayString + '\')">' + kt.ten + '</a></li>';
                    htmlCboModalEdit += '<li><a class="dropdown-item"  onclick="setKieuTayModalEdit(\'' + kieuTayString + '\')">' + kt.ten + '</a></li>';
                    htmlCboModalAdd += '<li><a class="dropdown-item"  onclick="setKieuTayModalAdd(\'' + kieuTayString + '\')">' + kt.ten + '</a></li>';
                });
                $("#cboKieuTay").html(htmlSearch);
                $("#cboKieuTayModalAdd").html(htmlCboModalAdd);
                $("#cboKieuTayModalEdit").html(htmlCboModalEdit);
            });
    }
    loadCboKieuTay();
    const loadDSSPCT = (pageParams) => {
        // get api + scpt.id
        let datatest = "data testing";
        fetch("/chi-tiet-sp/detail-byidsp-all/" + pathVariable + "?page=" + pageParams, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                let html = '';
                resp.map((spct, i) => {
                    const trangThai = spct.trangThai == 1 ? "Hoạt động" : "Dừng hđ";
                    const mauSac = spct.idMauSac.ten || 'N/A';
                    const kichThuoc = spct.idKichThuoc.ten || 'N/A';
                    const chatLieu = spct.idChatLieu.ten || 'N/A';
                    const kieuTay = spct.idKieuTay.ten || 'N/A';
                    const soLuong = spct.soLuong || 'N/A';
                    html += '<tr>' +
                        '<td>' + (i + 1) + '</td>' +
                        '<td>' + mauSac + '</td>' +
                        '<td>' + kichThuoc + '</td>' +
                        '<td>' + chatLieu + '</td>' +
                        '<td>' + kieuTay + '</td>' +
                        '<td>' + soLuong + '</td>' +
                        '<td>' + trangThai + '</td>' +
                        '<td>' +
                        '<div class="d-inline">' +
                        '<button id="editSPCTBtn_' + spct.id + '" class="btn btn-success me-2" data-bs-toggle="modal" data-bs-target="#ModalEdit">Chỉnh sửa</button>' +
                        '</div>' +
                        '</td>' +
                        '</tr>';
                });
                $("#tbl_ds_spct").html(html)
            });
    }

    function search(e) {
        e.preventDefault();
        console.log('data mau sac ID:', idMauSac);
        console.log('data kich thuoc ID:', idKichThuoc);
        console.log('data chat lieu ID:', idChatLieu);
        console.log('data kieu tay ID:', idKieuTay);
        console.log('data sp local ID:', idSPCTLocal);
        fetch("/chi-tiet-sp/search"+ "?idSanPham=" + pathVariable + "&idMauSac=" + idMauSac + "&idKichThuoc=" + idKichThuoc + "&idChatLieu=" + idChatLieu + "&idKieuTay=" + idKieuTay, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                let html = '';
                resp.map((spct, i) => {
                    const trangThai = spct.trangThai == 1 ? "Hoạt động" : "Dừng hđ";
                    const mauSac = spct.idMauSac.ten || 'N/A';
                    const kichThuoc = spct.idKichThuoc.ten || 'N/A';
                    const chatLieu = spct.idChatLieu.ten || 'N/A';
                    const kieuTay = spct.idKieuTay.ten || 'N/A';
                    const soLuong = spct.soLuong || 'N/A';
                    html += '<tr>' +
                        '<td>' + (i + 1) + '</td>' +
                        '<td>' + mauSac + '</td>' +
                        '<td>' + kichThuoc + '</td>' +
                        '<td>' + chatLieu + '</td>' +
                        '<td>' + kieuTay + '</td>' +
                        '<td>' + soLuong + '</td>' +
                        '<td>' + trangThai + '</td>' +
                        '<td>' +
                        '<div class="d-inline">' +
                        '<button id="editSPCTBtn_' + spct.id + '" class="btn btn-success me-2" data-bs-toggle="modal" data-bs-target="#ModalEdit">Chỉnh sửa</button>' +
                        '</div>' +
                        '</td>' +
                        '</tr>';
                });
                $("#tbl_ds_spct").html(html)
            });
    }

    function refresh(e) {
        e.preventDefault();
        idMauSac = "";
        idKichThuoc = "";
        idChatLieu = "";
        idKieuTay = "";
        lblMauSac.textContent = "Chọn màu sắc"
        lblKichThuoc.textContent = "Chọn kích thước";
        lblChatLieu.textContent = "Chọn chất liệu";
        lblKieuTay.textContent = "Chọn kiểu tay";
        loadDSSPCT(currentPage);
    }

    function setActive(element, page) {
        // Remove active class from all pagination items
        let items = document.querySelectorAll('.page-item');
        items.forEach(item => item.classList.remove('active'));
        element.parentElement.classList.add('active');
        currentPage = page;
        updateButtons();
        loadDSSPCT(currentPage);
    }

    function updateButtons() {
        let items = document.querySelectorAll('.page-item');
        let activeIndex = Array.from(items).findIndex(item => item.classList.contains('active'));
        document.getElementById('prev').classList.toggle('disabled', activeIndex === 1);
        document.getElementById('next').classList.toggle('disabled', activeIndex === items.length - 2);
    }

    function navigate(direction, e) {
        e.preventDefault();
        if (totalPage > 1) {
            let items = document.querySelectorAll('.page-item');
            let activeIndex = Array.from(items).findIndex(item => item.classList.contains('active'));
            let newIndex = activeIndex + direction;
            currentPage = newIndex
            loadDSSPCT(currentPage);
            if (newIndex > 0 && newIndex < items.length - 1) {
                setActive(items[newIndex].querySelector('a'));
            }
        }
    }

    updateButtons();

    const loadTotalPagination = (currentPage) => {
        fetch("/chi-tiet-sp/count-byidsp/" + pathVariable, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                let html = '';
                // Check if resp is a number and greater than 0
                if (typeof resp === 'number' && resp > 0) {
                    totalPage = Math.ceil(resp / 20);
                    for (let i = 1; i <= Math.ceil(resp / 20); i++) {
                        const activeClass = (i === currentPage) ? 'active' : '';
                        html += '<li class="page-item ' + activeClass + '"><a class="page-link" href="#" onclick="setActive(this, ' + i + ')">' + i + '</a></li>';
                    }
                } else {
                    // Handle case where resp is not a valid number or is <= 0
                    html = '<li class="page-item"><a class="page-link" href="#">No pages found</a></li>';
                }
                $("#paginationBody").html(html);
            }).catch(error => {
            console.error('Error fetching pagination data:', error);
            // Handle fetch error
        });
    }

    loadDSSPCT(currentPage);
    loadTotalPagination(currentPage);
    $(document).on('click', "button[id^='editSPCTBtn_']", e => {
        e.preventDefault();
        const queryString = window.location.pathname;
        const pathParts = queryString.split('/');
        const pathVariable = pathParts[pathParts.length - 1];
        const spctid = e.currentTarget.id.replace("editSPCTBtn_", "");
        idSPCTLocal = spctid;
        fetch("/chi-tiet-sp/detail/" + spctid, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(resp => {
                lblMauSacModalEdit.textContent = resp.idMauSac.ten;
                lblKichThuocModalEdit.textContent = resp.idKichThuoc.ten;
                lblChatLieuModalEdit.textContent = resp.idChatLieu.ten;
                lblKieuTayModalEdit.textContent  = resp.idKieuTay.ten;
                idMauSacModalEdit = resp.idMauSac.id;
                idKichThuocModalEdit = resp.idKichThuoc.id;
                idChatLieuModalEdit = resp.idChatLieu.id;
                idKieuTayModalEdit = resp.idKieuTay.id;
                ghiChuModalEdit.value  = resp.moTa;
                soLuongModalEdit.value = resp.soLuong;
                giaNhapModalEdit.value = resp.giaNhap;
                giaBanModalEdit.value = resp.giaBan;
                if (resp.trangThai == 1) {
                    trangThaiModalEditRaw.checked = true;
                    trangThaiLabelModalEdit.textContent = "Đang hoạt động";
                } else {
                    trangThaiModalEditRaw.checked = false;
                    trangThaiLabelModalEdit.textContent = "Dừng hoạt động";
                }
            });
    });
    $(document).on('click', "button[id^='detailSPBtn_']", e => {
        e.preventDefault();
        const spid = e.currentTarget.id.replace("detailSPBtn_", "");
        console.log("====================test id button detail: ", spid);
        window.location.href = '/qlsp/' + spid;
        // fetch(apiGet, {
        //     headers: {
        //         'Accept': 'application/json',
        //         'Content-Type': 'application/json'
        //     }
        // }).then(response => response.json())
        //     .then(resp => {
        //         console.log("test response spct: ", resp);
        //         spctLocal =resp;
        //         const apiAdd = "/san-pham/update/"+spid;
        //         fetch(apiAdd, {
        //             method: "post",
        //             headers: {
        //                 'Accept': 'application/json',
        //                 'Content-Type': 'application/json'
        //             }
        //         }).then( (response) => {
        //             console.log(response);
        //             loadDSSP();
        //         });
        //     });
    });
    const addBtn = document.querySelectorAll('#addBtn');
    const editSPCTBtn = document.querySelectorAll('#editSPCTBtn');
    const saveEditBtn = document.querySelectorAll('#saveEditBtn');
    const saveAddBtn = document.querySelectorAll('#saveAddBtn');
    addBtn.forEach(button => {
        button.addEventListener('click', function (e) {
            e.preventDefault();
            console.log("test check btn");
            var idHD = document.getElementsByName("idHD")[0].value;
            var idKH = document.getElementsByName("idKH")[0].value;
            var idKhuyenMai = document.getElementsByName("idKhuyenMai")[0].value;
            var tongTien = document.getElementsByName("tongTien")[0].value;
            var thongBao = document.getElementById("errTraLai");
            var moneyGiven = parseInt(document.getElementById("tienKhachDua").value);
            var thongBao = document.getElementById("errTraLai");
            console.log("====================== id hd:", idHD);
            console.log("====================== id kh:", idKH);
            console.log("====================== id khuyen mai:", idKhuyenMai);
            console.log("====================== tong tien:", tongTien);
            console.log("====================== money given:", moneyGiven);
            if (tongTien <= moneyGiven && !isNaN(tongTien)) {
                Swal.fire({
                    title: 'Xác nhận?',
                    text: "Dữ liệu sẽ được lưu lại!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Ok!',
                    cancelButtonText: 'Hủy'
                }).then((result) => {
                    if (result.isConfirmed) {
                        const data = {
                            idHD: idHD,
                            idKhuyenMai: idKhuyenMai,
                            idKH: idKH,
                            tongTien: tongTien
                        };
                        fetch(`/san-pham/save/`, {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(data)
                        }).then(() => {
                            Swal.fire(
                                'Đã thanh toán!',
                                'Dữ liệu đã được ghi nhận.',
                                'success'
                            ).then(() => {
                                window.location.href = '/ban-hang-tai-quay';
                            });
                            button.closest('tr').remove();
                        });
                        button.closest('tr').remove();
                        thongBao.textContent = "";
                    }
                });
            } else {
                thongBao.textContent = "Số tiền khách đưa phải lớn hơn hoặc bằng tổng tiền.";
            }
        });
    });

    function getFileName(fullPath) {
        // Check for the last occurrence of the backslash or forward slash
        var startIndex = Math.max(fullPath.lastIndexOf('\\'), fullPath.lastIndexOf('/'));
        // Extract the file name
        var fileName = fullPath.substring(startIndex + 1);
        return fileName;
    }

    saveAddBtn.forEach(button => {
        button.addEventListener('click', function (e) {
            e.preventDefault();
            console.log("test check btn");
            console.log("id spct local: ", idSPCTLocal);
            let trangThaiModalAdd = 0;
            if (trangThaiModalAddRaw.checked == true) {
                trangThaiModalAdd = 1;
            } else {
                trangThaiModalAdd = 0;
            }
            if (validateModalAdd()==7) {
                Swal.fire({
                    title: 'Xác nhận?',
                    text: "Dữ liệu sẽ được lưu lại!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Ok!',
                    cancelButtonText: 'Hủy'
                }).then((result) => {
                    if (result.isConfirmed) {
                        const data = {
                            idSp: pathVariable,
                            idMauSac: idMauSacModalAdd,
                            idKichThuoc: idKichThuocModalAdd,
                            idChatLieu: idChatLieuModalAdd,
                            idKieuTay: idKieuTayModalAdd,
                            moTa: ghiChuModalAdd?ghiChuModalAdd.value:null,
                            soLuong:  soLuongModalAdd.value,
                            giaNhap: giaNhapModalAdd.value,
                            giaBan: giaBanModalAdd.value,
                            trangThai: trangThaiModalAdd
                        };
                        console.log("data json: ",data);
                        var formData = new FormData($('#uploadFormEdit')[0]); // Use FormData to get all form data
                        // Handle file upload via AJAX
                        // $.ajax({
                        //     url: '/upload',
                        //     type: 'POST',
                        //     data: formData,
                        //     processData: false,
                        //     contentType: false,
                        //     success: function (response) {
                        //         console.log("save image success ");
                        //     },
                        //     error: function (xhr, status, error) {
                        //         console.log("save image =error");
                        //     }
                        // });
                        fetch(`/chi-tiet-sp/save`, {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(data)
                        }).then(() => {
                            Swal.fire(
                                'Đã thanh toán!',
                                'Dữ liệu đã được ghi nhận.',
                                'success'
                            ).then(() => {
                                loadDSSPCT(currentPage);
                            });
                        });
                    }
                });
            } else {

            }
        });
    });

    function validateNull(param) {
        console.log("validate null");
        if (param === "" || param === undefined) {
            return true;
        } else {
            return false;
        }
    }

    function validateModalEdit(){
        let  checkCount  = 0;
        var cboMauSacModalEditErr = document.getElementById("cboMauSacModalEditErr");
        var cboKichThuocModalEditErr = document.getElementById("cboKichThuocModalEditErr");
        var cboChatLieuModalEditErr = document.getElementById("cboChatLieuModalEditErr");
        var cboKieuTayModalEditErr = document.getElementById("cboKieuTayModalEditErr");
        var soLuongModalEditErr = document.getElementById("soLuongModalEditErr");
        var giaNhapModalEditErr = document.getElementById("giaNhapModalEditErr");
        var giaBanModalEditErr = document.getElementById("giaBanModalEditErr");
        console.log("so luong test:",soLuongModalEdit.value);
        console.log("so luong test:",giaBanModalEdit.value);
        console.log("so luong test:",giaNhapModalEdit.value);

        if(idMauSacModalEdit == ""){
            cboMauSacModalEditErr.textContent = "Vui lòng chọn màu sắc ";
            checkCount=0;
        }
        else{
            cboMauSacModalEditErr.textContent = "";
            checkCount++;
        }
        if(idKichThuocModalEdit == ""){
            cboKichThuocModalEditErr.textContent = "Vui lòng chọn kích thước";
            checkCount=0
        }
        else{
            cboKichThuocModalEditErr.textContent = "";
             checkCount++;
        }
        if(idChatLieuModalEdit == ""){
            cboChatLieuModalEditErr.textContent = "Vui lòng chọn chất liệu";
            checkCount=0;
        }
        else{
            cboChatLieuModalEditErr.textContent = "";
            checkCount++;
        }
        if(idKieuTayModalEdit == ""){
            cboKieuTayModalEditErr.textContent = "Vui lòng chọn kiểu tay";
            checkCount=0;
        }
        else{
            cboKieuTayModalEditErr.textContent = "";
            checkCount++;
        }
        if(validateNull(soLuongModalEdit.value)){
            soLuongModalEditErr.textContent = "Vui lòng nhập số lượng";
            checkCount = 0;
        }
        else{
            if(soLuongModalEdit.value<=0){
                soLuongModalEditErr.textContent ="Số lượng phải lơn hơn 0";
                checkCount=0
            }
            else{
                soLuongModalEditErr.textContent ="";
                checkCount++;
            }
        }
        if(validateNull(giaNhapModalEdit.value)){
            giaNhapModalEditErr.textContent = "Vui lòng nhập giá nhập";
            checkCount = 0;
        }
        else{
            if(giaNhapModalEdit.value<=0){
                giaNhapModalEditErr.textContent ="Giá nhập phải lớn hơn 0";
                checkCount=0;
            }
            else{
                giaNhapModalEditErr.textContent ="";
                checkCount++;
            }
        }
        if(validateNull(giaBanModalEdit.value)){
            giaBanModalEditErr.textContent = "Vui lòng nhập giá bán";
            checkCount = 0;
        }
        else{
            if(giaBanModalEdit.value<=0){
                giaBanModalEditErr.textContent = "Giá bán phải lớn hơn 0";
                checkCount=0;
            }
            else{
                giaBanModalEditErr.textContent = "";
                checkCount++;
            }
        }
        console.log("check count: ", checkCount);
        return checkCount;
    }

    function validateModalAdd(){
        let  checkCount  = 0;
        var cboMauSacModalAddErr = document.getElementById("cboMauSacModalAddErr");
        var cboKichThuocModalAddErr = document.getElementById("cboKichThuocModalAddErr");
        var cboChatLieuModalAddErr = document.getElementById("cboChatLieuModalAddErr");
        var cboKieuTayModalAddErr = document.getElementById("cboKieuTayModalAddErr");
        var soLuongModalAddErr = document.getElementById("soLuongModalAddErr");
        var giaNhapModalAddErr = document.getElementById("giaNhapModalAddErr");
        var giaBanModalAddErr = document.getElementById("giaBanModalAddErr");
        console.log("so luong test:",soLuongModalAdd.value);
        console.log("so luong test:",giaBanModalAdd.value);
        console.log("so luong test:",giaNhapModalAdd.value);

        if(idMauSacModalAdd == ""){
            cboMauSacModalAddErr.textContent = "Vui lòng chọn màu sắc ";
            checkCount=0;
        }
        else{
            cboMauSacModalAddErr.textContent = "";
            checkCount++;
        }
        if(idKichThuocModalAdd == ""){
            cboKichThuocModalAddErr.textContent = "Vui lòng chọn kích thước";
            checkCount=0
        }
        else{
            cboKichThuocModalAddErr.textContent = "";
            checkCount++;
        }
        if(idChatLieuModalAdd == ""){
            cboChatLieuModalAddErr.textContent = "Vui lòng chọn chất liệu";
            checkCount=0;
        }
        else{
            cboChatLieuModalAddErr.textContent = "";
            checkCount++;
        }
        if(idKieuTayModalAdd == ""){
            cboKieuTayModalAddErr.textContent = "Vui lòng chọn kiểu tay";
            checkCount=0;
        }
        else{
            cboKieuTayModalAddErr.textContent = "";
            checkCount++;
        }
        if(validateNull(soLuongModalAdd.value)){
            soLuongModalAddErr.textContent = "Vui lòng nhập số lượng";
            checkCount = 0;
        }
        else{
            if(soLuongModalAdd.value<=0){
                soLuongModalAddErr.textContent ="Số lượng phải lơn hơn 0";
                checkCount=0
            }
            else{
                soLuongModalAddErr.textContent ="";
                checkCount++;
            }
        }
        if(validateNull(giaNhapModalAdd.value)){
            giaNhapModalAddErr.textContent = "Vui lòng nhập giá nhập";
            checkCount = 0;
        }
        else{
            if(giaNhapModalAdd.value<=0){
                giaNhapModalAddErr.textContent ="Giá nhập phải lớn hơn 0";
                checkCount=0;
            }
            else{
                giaNhapModalAddErr.textContent ="";
                checkCount++;
            }
        }
        if(validateNull(giaBanModalAdd.value)){
            giaBanModalAddErr.textContent = "Vui lòng nhập giá bán";
            checkCount = 0;
        }
        else{
            if(giaBanModalAdd.value<=0){
                giaBanModalAddErr.textContent = "Giá bán phải lớn hơn 0";
                checkCount=0;
            }
            else{
                giaBanModalAddErr.textContent = "";
                checkCount++;
            }
        }
        console.log("check count: ", checkCount);
        return checkCount;
    }


    saveEditBtn.forEach(button => {
        button.addEventListener('click', function (e) {
            e.preventDefault();
            console.log("test check btn");
            console.log("id spct local: ", idSPCTLocal);
            let trangThaiModalEdit = 0;
            if (trangThaiModalEditRaw.checked == true) {
                trangThaiModalEdit = 1;
            } else {
                trangThaiModalEdit = 0;
            }
            if (validateModalEdit()==7) {
                Swal.fire({
                    title: 'Xác nhận?',
                    text: "Dữ liệu sẽ được lưu lại!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Ok!',
                    cancelButtonText: 'Hủy'
                }).then((result) => {
                    if (result.isConfirmed) {
                        const data = {
                            idMauSac: idMauSacModalEdit,
                            idKichThuoc: idKichThuocModalEdit,
                            idChatLieu: idChatLieuModalEdit,
                            idKieuTay: idKieuTayModalEdit,
                            moTa: ghiChuModalEdit?ghiChuModalEdit.value:null,
                            soLuong:  soLuongModalEdit.value,
                            giaNhap: giaNhapModalEdit.value,
                            giaBan: giaBanModalEdit.value,
                            trangThai: trangThaiModalEdit
                        };
                        console.log("data json: ",data);
                        var formData = new FormData($('#uploadFormEdit')[0]); // Use FormData to get all form data
                        // Handle file upload via AJAX
                        // $.ajax({
                        //     url: '/upload',
                        //     type: 'POST',
                        //     data: formData,
                        //     processData: false,
                        //     contentType: false,
                        //     success: function (response) {
                        //         console.log("save image success ");
                        //     },
                        //     error: function (xhr, status, error) {
                        //         console.log("save image =error");
                        //     }
                        // });
                        fetch(`/chi-tiet-sp/update/` + idSPCTLocal, {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(data)
                        }).then(() => {
                            Swal.fire(
                                'Đã thanh toán!',
                                'Dữ liệu đã được ghi nhận.',
                                'success'
                            ).then(() => {
                                loadDSSPCT(currentPage);
                            });
                        });
                    }
                });
            } else {

            }
        });
    });

</script>
<script>
    $(document).ready(function () {
        $('#saveAddBtn').on('click', function (event) {

            // Optionally, submit the form normally after AJAX request (if needed)
        });
    });
</script>
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
</script>
<script>


</script>
</html>