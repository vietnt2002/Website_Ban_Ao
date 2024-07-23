<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" %>
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

    <title>MSStore_Quản lý hóa đơn</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/view_admin/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet">

    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">--%>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


    <style>
        .stat-section {
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .stat-section.today {
            background-color: #8ecae6;
        }
        .stat-section.this-week {
            background-color: #219ebc;
        }
        .stat-section.this-month {
            background-color: #28a745;
        }
        .stat-section.this-year {
            background-color: #ffb703;
        }
        .stat-section h3 {
            font-size: 1.25em;
            margin-bottom: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .stat-section h3 i {
            margin-right: 8px;
        }
        .stat-details {
            display: flex;
            justify-content: space-around;
            text-align: center;
        }
        .stat-details div {
            flex: 1;
            margin: 0 10px;
        }
        .stat-details p {
            margin: 5px 0;
        }
        .stat-value {
            font-size: 1em;
            font-weight: bold;
        }

        .stat-value#today-revenue,
        .stat-value#week-revenue,
        .stat-value#month-revenue,
        .stat-value#year-revenue {
            font-size: 1.5em;
            font-weight: bold;
        }

        .card-filter {
            margin-bottom: 20px;
        }
        .card-body-title {
            font-size: 1.25rem;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .card-title {
            font-weight: bold;
            font-size: 1.25rem;
        }
        .card-table {
            margin-top: 20px;
        }
        .chart {
            height: 300px;
            margin-top: 20px;
        }

        .form-control.with-title {
            width: calc(
                    100% - 8px
            ); /* Độ rộng ô input trừ đi khoảng cách của tiêu đề */
            padding-left: 8px; /* Padding bên trái của ô input để tạo khoảng cách cho tiêu đề */
        }

        /* CSS cho tiêu đề bên trong input */
        .input-title {
            position: absolute;
            top: -10px;
            left: 8px;
            padding: 0 4px;
            background-color: #fff;
            color: #6c757d;
            font-size: 12px;
            pointer-events: none;
        }

        .form-control.with-title:focus + .input-title {
            color: #adb5bd;
        }

        .btn-outline-primary:hover {
            color: #fff;
        }

        #wrapper {
            background-color: #f8f9fc;
            width: 100%;
            overflow-x: hidden;
        }

        #pie-chart {
            width: 100%;
            height: 100%;
            max-height: 300px; /* Adjust height as necessary */
        }

        .border-card {
            border: 1px solid #ddd;
        }
    </style>
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar  Menu bên tay trái-->
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
            <a class="nav-link" href="/admin/thong-ke/view">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Thống kê doanh thu</span></a>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/ban-hang-tai-quay">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Bán hàng tại quầy</span></a>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý hóa đơn</span></a>
        </li>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="/qlsp" data-toggle="collapse" data-target="#collapseTwo"
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
            <a class="nav-link" href="/qlnv/quan-ly-nhan-vien">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý nhân viên</span></a>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/qlkh/quan-ly-khach-hang">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý khách hàng</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/admin/quan-ly-khuyen-mai">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý khuyến mãi</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <form class="form-inline">
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                </form>

                <!-- Topbar Search  -->
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
<%--                            <span class="badge badge-danger badge-counter">3+</span>--%>
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
                                    <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                         alt="...">
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
                                    <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                         alt="...">
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
                                    <img class="rounded-circle" src="img/undraw_profile_3.svg"
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
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${nv.hoTen}</span>
                            <img class="img-profile rounded-circle"
                                 src="/imageUser/${nv.anhDaiDien}">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="/qlnv/tai-khoan-cua-toi/${nv.id}">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Tài khoản của tôi
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/hoa-don/dang-xuat">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Đăng xuất
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">
                <!-- Page Heading -->
                <div class="mt-4" id="">
                    <div class="row">
                        <div class="col-12" >

                            <!-- Phần đầu thống kê -->
                            <!-- Phần đầu -->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="stat-section today">
                                        <h3 class="text-center">
                                            <i class="bi bi-calendar-day"></i>Hôm nay
                                        </h3>
                                        <p class="stat-value text-center">
                                            <fmt:formatNumber value="${today.doanhThu}" type="currency" currencySymbol="₫" />
                                        </p>
                                        <div class="stat-details">
                                            <div>
                                                <p>Sản phẩm</p>
                                                <p class="stat-value">${today.soLuongSanPhamDaBan}</p>
                                            </div>
                                            <div>
                                                <p>Thành công</p>
                                                <p class="stat-value">${today.donHangThanhCong}</p>
                                            </div>
                                            <div>
                                                <p>Đơn hủy</p>
                                                <p class="stat-value">${today.donHangDaHuy}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 pr-2">
                                    <div class="stat-section this-week">
                                        <h3 class="text-center">
                                            <i class="bi bi-calendar-week"></i>Tuần này
                                        </h3>
                                        <p class="stat-value text-center">
                                            <fmt:formatNumber value="${week.doanhThu}" type="currency" currencySymbol="₫" />
                                        </p>
                                        <div class="stat-details">
                                            <div>
                                                <p>Sản phẩm</p>
                                                <p class="stat-value">${week.soLuongSanPhamDaBan}</p>
                                            </div>
                                            <div>
                                                <p>Thành công</p>
                                                <p class="stat-value">${week.donHangThanhCong}</p>
                                            </div>
                                            <div>
                                                <p>Đơn hủy</p>
                                                <p class="stat-value">${week.donHangDaHuy}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="stat-section this-month">
                                        <h3 class="text-center">
                                            <i class="bi bi-calendar3"></i>Tháng này
                                        </h3>
                                        <p class="stat-value text-center">
                                            <fmt:formatNumber value="${month.doanhThu}" type="currency" currencySymbol="₫" />
                                        </p>
                                        <div class="stat-details">
                                            <div>
                                                <p>Sản phẩm</p>
                                                <p class="stat-value">${month.soLuongSanPhamDaBan}</p>
                                            </div>
                                            <div>
                                                <p>Thành công</p>
                                                <p class="stat-value">${month.donHangThanhCong}</p>
                                            </div>
                                            <div>
                                                <p>Đơn hủy</p>
                                                <p class="stat-value">${month.donHangDaHuy}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="stat-section this-year">
                                        <h3 class="text-center">
                                            <i class="bi bi-calendar4"></i>Năm này
                                        </h3>
                                        <p class="stat-value text-center">
                                            <fmt:formatNumber value="${year.doanhThu}" type="currency" currencySymbol="₫" />
                                        </p>
                                        <div class="stat-details">
                                            <div>
                                                <p>Sản phẩm</p>
                                                <p class="stat-value">${year.soLuongSanPhamDaBan}</p>
                                            </div>
                                            <div>
                                                <p>Thành công</p>
                                                <p class="stat-value">${year.donHangThanhCong}</p>
                                            </div>
                                            <div>
                                                <p>Đơn hủy</p>
                                                <p class="stat-value">${year.donHangDaHuy}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Phần giữa -->
                            <div class="">
                                <div class="card-body bg-white mb-5">
                                    <div class="mb-5">
                                        <h5 class="ml-3 pt-2">Bộ lọc</h5>
                                        <div class="d-flex align-items-center">
                                            <div class="col-md-1">
                                                <button
                                                        type="button"
                                                        class="btn btn-outline-primary btn-block btn-sm font-weight-bold"
                                                >
                                                    Ngày
                                                </button>
                                            </div>
                                            <div class="col-md-1">
                                                <button
                                                        type="button"
                                                        class="btn btn-outline-info btn-block btn-sm font-weight-bold"
                                                >
                                                    Tuần
                                                </button>
                                            </div>
                                            <div class="col-md-1">
                                                <button
                                                        type="button"
                                                        class="btn btn-outline-success btn-block btn-sm font-weight-bold"
                                                >
                                                    Tháng
                                                </button>
                                            </div>
                                            <div class="col-md-1">
                                                <button
                                                        type="button"
                                                        class="btn btn-outline-warning btn-block btn-sm font-weight-bold"
                                                >
                                                    Năm
                                                </button>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group mb-0">
                                                    <input
                                                            type="date"
                                                            class="form-control with-title"
                                                            id="start-date"
                                                    />
                                                    <span class="input-title">Từ ngày</span>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group mb-0">
                                                    <input
                                                            type="date"
                                                            class="form-control with-title"
                                                            id="end-date"
                                                    />
                                                    <span class="input-title">Đến ngày</span>
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <button
                                                        type="button"
                                                        class="btn btn-primary btn-block btn-sm text-dark font-weight-bold"
                                                >
                                                    <span class="text-white p-3">Tìm kiếm</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="mb-5">
                                        <div class="card-header">Biểu đồ doanh thu </div>
                                        <div class="card-body bg-white">
                                            <div id="revenue-chart" style="height: 400px;"></div>
                                        </div>
                                    </div>


                                </div>
                            </div>

                            <div>
                                <div class="card-body bg-white">
                                    <!-- Danh sách sản phẩm bán chạy -->
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="mt-4">
                                                <h5 class="ml-3 text-center">
                                                    Top Sản Phẩm Bán Chạy Theo Tháng
                                                </h5>
                                                <table class="table">
                                                    <thead>
                                                    <tr>
                                                        <th scope="col"
                                                            style="background: orange; color: white; border-radius: 10px 0px 0px 0px">
                                                            STT
                                                        </th>
                                                        <th scope="col" style="background: orange; color: white">Ảnh</th>
                                                        <th scope="col" style="background: orange; color: white">Thông tin sản phẩm</th>
                                                        <th scope="col" style="background: orange; color: white">Giá bán</th>
                                                        <th scope="col" style="background: orange; color: white ;border-radius: 0px 10px 0px 0px" >Số lượng đã bán</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                        <!-- Biểu đồ Top 5 sản phẩm bán chạy -->
                                        <div class="col-6">
                                            <div class="mt-4">
                                                <h5 class="ml-3 text-center">Sản Phẩm Sắp Hết Hàng</h5>
                                                <table class="table">
                                                    <thead>
                                                    <tr>
                                                        <th scope="col"
                                                            style="background: orange; color: white; border-radius: 10px 0px 0px 0px">
                                                            STT
                                                        </th>
                                                        <th scope="col" style="background: orange; color: white">Ảnh</th>
                                                        <th scope="col" style="background: orange; color: white">Thông tin sản phẩm</th>
                                                        <th scope="col" style="background: orange; color: white">Giá bán</th>
                                                        <th scope="col" style="background: orange; color: white ;border-radius: 0px 10px 0px 0px" >Số lượng còn lại</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>

                                                    </tbody>
                                                </table>

                                            </div>

                                        </div>
                                    </div>

                                </div>
                            </div>



                        </div>
                    </div>
                </div>


            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

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
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

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
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
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
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    // Hiển thị thông báo thành công nếu xác nhận đơn hàng thành công
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

    <%--    Thông báo cập nhật trạng thi hóa đơn thành công--%>
    <c:if test="${not empty cancelSuccess}">
    Toast.fire({
        icon: "success",
        title: "${cancelSuccess}"
    });
    </c:if>


    $(document).ready(function() {
        $('#userDropdown .fas').on('click', function(e) {
            e.stopPropagation();
            $(this).closest('.nav-link').dropdown('toggle');
        });
    });


    // Reset lại form tìm kiếm và lọc
    function resetForm() {
        document.getElementById("searchKeyword").value = "";
        document.getElementById("inputLoaiHoaDon").selectedIndex = 0;
        document.getElementById("startDate").value = "";
        document.getElementById("endDate").value = "";

        // Lấy trang hiện tại
        var currentUrl = window.location.href;
        var cleanUrl = currentUrl.split('?')[0]; // Lấy phần URL trước dấu '?'

        // Lấy tham số page nếu có
        var params = new URLSearchParams(window.location.search);
        var pageParam = params.get('page');

        // Nếu có tham số page, thêm lại vào URL
        if (pageParam) {
            cleanUrl += '?page=' + pageParam;
        }

        // Đặt lại URL
        window.location.href = cleanUrl;
    }


    // Validate Bộ lọc và tìm kiếm
    function validateDates() {
        const startDateInput = document.getElementById('startDate');
        const endDateInput = document.getElementById('endDate');
        const startDateError = document.getElementById('startDateError');
        const endDateError = document.getElementById('endDateError');
        const startDate = new Date(startDateInput.value);
        const endDate = new Date(endDateInput.value);
        const currentYear = new Date().getFullYear();

        let isValid = true;

        // Clear previous errors
        startDateInput.classList.remove('border-danger');
        endDateInput.classList.remove('border-danger');
        startDateError.textContent = '';
        endDateError.textContent = '';

        if (!startDateInput.value && !endDateInput.value) {
            return true; // No need to validate if both dates are not selected
        }

        if (startDateInput.value && endDateInput.value) {
            if (startDate.getFullYear() !== currentYear) {
                startDateInput.classList.add('border-danger');
                startDateError.textContent = 'Ngày bắt đầu chỉ được chọn trong năm nay.';
                isValid = false;
            }
            if (endDate.getFullYear() !== currentYear) {
                endDateInput.classList.add('border-danger');
                endDateError.textContent = 'Ngày kết thúc chỉ được chọn trong năm nay.';
                isValid = false;
            }
            if (endDate < startDate) {
                endDateInput.classList.add('border-danger');
                endDateError.textContent = 'Ngày kết thúc phải bằng hoặc sau ngày bắt đầu.';
                isValid = false;
            }
        } else if (startDateInput.value) {
            if (startDate.getFullYear() !== currentYear) {
                startDateInput.classList.add('border-danger');
                startDateError.textContent = 'Ngày bắt đầu chỉ được chọn trong năm nay.';
                isValid = false;
            }
            if (!endDateInput.value) {
                endDateInput.classList.add('border-danger');
                endDateError.textContent = 'Vui lòng chọn ngày kết thúc.';
                isValid = false;
            }
        } else if (endDateInput.value) {
            if (endDate.getFullYear() !== currentYear) {
                endDateInput.classList.add('border-danger');
                endDateError.textContent = 'Ngày kết thúc chỉ được chọn trong năm nay.';
                isValid = false;
            }
            if (!startDateInput.value) {
                startDateInput.classList.add('border-danger');
                startDateError.textContent = 'Vui lòng chọn ngày bắt đầu.';
                isValid = false;
            }
        }

        return isValid;
    }

    document.getElementById('startDate').addEventListener('focus', function() {
        this.classList.remove('border-danger');
        document.getElementById('startDateError').textContent = '';
    });

    document.getElementById('endDate').addEventListener('focus', function() {
        this.classList.remove('border-danger');
        document.getElementById('endDateError').textContent = '';
    });




</script>


<script src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script>
    // Đoạn mã tích hợp biểu đồ vào đây
    var chartDom = document.getElementById('pie-chart');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '0%',
            left: 'left'
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    { value: 1048, name: 'Search Engine' },
                    { value: 735, name: 'Direct' },
                    { value: 580, name: 'Email' },
                    { value: 484, name: 'Union Ads' },
                    { value: 300, name: 'Video Ads' }
                ]
            }
        ]
    };

    option && myChart.setOption(option);








    // Biểu đò cột doanh thu
    // Đoạn mã tích hợp biểu đồ vào đây
    var revenueChartDom = document.getElementById('revenue-chart');
    var revenueChart = echarts.init(revenueChartDom);
    var option;

    option = {
        title: {
            text: 'Rainfall vs Evaporation',
            subtext: 'Fake Data'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['Rainfall', 'Evaporation']
        },
        // toolbox: {
        //     show: false,
        //     feature: {
        //         // dataView: { show: true, readOnly: false },
        //         magicType: { show: true, type: ['line', 'bar'] },
        //         restore: { show: true },
        //         saveAsImage: { show: true }
        //     }
        // },
        // calculable: true,
        xAxis: [
            {
                type: 'category',
                // prettier-ignore
                data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: 'Rainfall',
                type: 'bar',
                data: [
                    2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
                ],
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            },
            {
                name: 'Evaporation',
                type: 'bar',
                data: [
                    2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
                ],
                markPoint: {
                    data: [
                        { name: 'Max', value: 182.2, xAxis: 7, yAxis: 183 },
                        { name: 'Min', value: 2.3, xAxis: 11, yAxis: 3 }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            }
        ]
    };

    option && revenueChart.setOption(option);
</script>


</body>

</html>