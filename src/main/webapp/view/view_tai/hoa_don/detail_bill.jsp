<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <%--In ra file PDF    --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>


        <%--     Css phiếu hóa đơn   --%>
        .invoice {
            width: 21cm; /* A4 width */
            min-height: 29.7cm; /* A4 height */
            padding: 1cm;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .logo {
            width: 100px; /* Adjust width as needed */
            height: auto;
        }

        .separator {
            border-top: 1px solid #ccc;
            margin: 20px 0;
        }

        .custom-card-body {
            max-height: 400px;
            overflow-y: auto;
        }

        .custom-table {
            margin-bottom: 0;
        }

        .custom-table img {
            max-width: 100%;
            height: auto;
        }

        .custom-card-body::-webkit-scrollbar {
            width: 8px;
        }

        .custom-card-body::-webkit-scrollbar-thumb {
            background-color: #007bff;
            border-radius: 4px;
        }

        .custom-card-body::-webkit-scrollbar-track {
            background-color: #f1f1f1;
        }

        /* Stepper container */
        .stepper-horizontal {
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: relative;
            margin-bottom: 40px;
        }

        /* Step styling */
        .step {
            text-align: center;
            position: relative;
            flex: 1;
        }

        /* Step icon wrapper */
        .step-icon-wrapper {
            background-color: #007bff; /* Bootstrap primary color */
            border-radius: 50%;
            width: 50px; /* Decreased size */
            height: 50px; /* Decreased size */
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0 auto 10px auto;
            border: 2px solid #007bff; /* Border to make it stand out */
        }

        .step-icon {
            font-size: 24px; /* Decreased icon size */
            color: #fff;
        }

        .step-title {
            font-size: 16px; /* Decreased text size for title */
            font-weight: bold;
            margin-bottom: 5px;
        }

        .step-date {
            font-size: 14px;
            color: #6c757d;
        }

        /* Connector styling */
        .connector {
            height: 2px;
            background-color: #adb5bd; /* Light primary color */
            position: absolute;
            top: 25px; /* Adjusted to align with smaller icon */
            left: 50%;
            z-index: -1;
            width: calc(100% - 50px); /* Adjust width between steps */
        }

        /* Connector position adjustment */
        .step + .connector {
            left: calc(50% - 25px); /* Adjusted center position relative to smaller steps */
        }

        .hide {
            display: none;
        }



        .step-arrow {
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
        }

        .step-arrow i {
            color: #007bff;
            font-size: 40px;
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
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                            <img class="img-profile rounded-circle"
                                 src="img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Settings
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Activity Log
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">
                <%--              Hiển thị thông báo lỗi nếu không tìm thấy hóa đơn      --%>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" role="alert">
                            ${errorMessage}
                    </div>
                </c:if>

                <div class="d-flex justify-content-end">
                    <!-- Nút in ra phiếu hóa đơn khi giao hàng theo dạng file PDF -->
                    <c:if test="${hoaDonDTO.loaiHoaDon == 0}">
                        <div class="d-flex ms-auto">
                            <button id="printDeliveryButton" class="btn btn-primary my-3">
                                <i class="bi bi-printer"></i> In hóa đơn
                            </button>
                        </div>
                    </c:if>
                </div>

                <%--Bảng theo dõi hóa đơn--%>
                    <div class="card mb-3">
                        <div class="card-header">
                            <h5 class="card-title">Quản lý đơn hàng:</h5>
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <%-- Hiển thị stepper cho bán hàng tại quầy (LoaiHD == 1) --%>
                                <c:when test="${hoaDonDTO.loaiHoaDon == 0}">
                                    <div class="stepper-horizontal" id="stepper_offline">
                                        <div class="step" id="step_offline_1">
                                            <div class="step-icon-wrapper">
                                                <i class="bi bi-card-text step-icon"></i>
                                            </div>
                                            <div class="step-title">Tạo hóa đơn</div>
                                            <div class="step-date">${hoaDonDTO.ngayTao}</div>
                                            <div class="connector"></div>
                                        </div>
                                        <div class="step-arrow">
                                            <i class="bi bi-arrow-right"></i>
                                        </div>
                                        <div class="step" id="step_offline_2">
                                            <div class="step-icon-wrapper">
                                                <i class="bi bi-check-circle step-icon"></i>
                                            </div>
                                            <div class="step-title">Đã hoàn thành</div>
                                            <div class="step-date">${hoaDonDTO.ngayThanhToan}</div>
                                        </div>
                                    </div>
                                </c:when>
                                <%-- Hiển thị stepper cho bán hàng online (LoaiHD != 1) --%>
                                <c:otherwise>
                                    <div class="stepper-horizontal" id="stepper_online">
                                        <div class="step" id="step_online_1">
                                            <div class="step-icon-wrapper">
                                                <i class="bi bi-hourglass-split step-icon"></i>
                                            </div>
                                            <div class="step-title">Chờ xác nhận</div>
                                            <div class="step-date">01/01/2023</div>
                                            <div class="connector"></div>
                                        </div>
                                        <div class="step-arrow">
                                            <i class="bi bi-arrow-right"></i>
                                        </div>
                                        <div class="step" id="step_online_2">
                                            <div class="step-icon-wrapper">
                                                <i class="bi bi-check-circle step-icon"></i>
                                            </div>
                                            <div class="step-title">Đã xác nhận</div>
                                            <div class="step-date">02/01/2023</div>
                                            <div class="connector"></div>
                                        </div>
                                        <div class="step-arrow">
                                            <i class="bi bi-arrow-right"></i>
                                        </div>
                                        <div class="step" id="step_online_3">
                                            <div class="step-icon-wrapper">
                                                <i class="bi bi-truck step-icon"></i>
                                            </div>
                                            <div class="step-title">Chờ giao hàng</div>
                                            <div class="step-date">03/01/2023</div>
                                            <div class="connector"></div>
                                        </div>
                                        <div class="step-arrow">
                                            <i class="bi bi-arrow-right"></i>
                                        </div>
                                        <div class="step" id="step_online_5">
                                            <div class="step-icon-wrapper">
                                                <i class="bi bi-box-seam step-icon"></i>
                                            </div>
                                            <div class="step-title">Đang giao hàng</div>
                                            <div class="step-date">05/01/2023</div>
                                            <div class="connector"></div>
                                        </div>
                                        <div class="step-arrow">
                                            <i class="bi bi-arrow-right"></i>
                                        </div>
                                        <div class="step" id="step_online_6">
                                            <div class="step-icon-wrapper">
                                                <i class="bi bi-credit-card-2-back step-icon"></i>
                                            </div>
                                            <div class="step-title">Đã hoàn thành</div>
                                            <div class="step-date">06/01/2023</div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="card-footer">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <%--<c:if test="${hoaDonDTO.loaiHoaDon == 0}">--%>
                                    <button type="button" class="btn btn-primary me-2" data-bs-toggle="modal"
                                            data-bs-target="#confirmModal">
                                        Xác nhận
                                    </button>
                                    <button type="button" class="btn btn-danger" id="cancelButton">
                                        Hủy
                                    </button>
                                    <%--</c:if>--%>
                                </div>
                            </div>
                        </div>
                    </div>



                    <!-- Modal xác nhận -->
                <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <form action="" method="post">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="confirmModalLabel">Xác nhận</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="description" class="form-label">Mô tả</label>
                                        <input type="text" class="form-control" id="description" placeholder="Nhập mô tả">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <button type="button" class="btn btn-primary">Xác nhận</button>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>

                <%--    Bảng lịch sử thanh toán--%>
                <div class="card mb-3">

                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h5 class="card-title mb-0">Lịch sử thanh toán:</h5>


                        <c:if test="${hoaDonDTO.loaiHoaDon == 0}">
                            <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                                    data-bs-target="#paymentModal"
                            >
                                <i class="bi bi-plus-lg"></i> Thanh Toán
                            </button>
                        </c:if>
                    </div>

                    <div class="card-body">
                        <table class="table table-bordered" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <%--                                                    <th>Mã GD</th>--%>
                                <th>Số tiền</th>
                                <th>Trạng thái</th>
                                <th>Thời gian</th>
                                <th>Loại giao dịch</th>
                                <th>Phương thức thanh toán</th>
                                <th>Người xác nhận</th>
                                <th>Ghi chú</th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td>1</td>
                                <td><fmt:formatNumber value="${hoaDonDTO.tongTien}" type="currency" currencySymbol="₫"
                                                      groupingUsed="true"/>
                                </td>
                                <td><span
                                        class="badge rounded-pill bg-success">${hoaDonDTO.trangThai == 0 ? "Chưa hoàn thành" : "Đã hoàn thành"}</span>
                                </td>
                                <td>${hoaDonDTO.ngayTao}</td>
                                <td><span class="badge rounded-pill bg-warning">Thanh toán</span></td>
                                <td>
                                    <span class="badge rounded-pill bg-primary">${hoaDonDTO.phuongThucThanhToan == 0 ? "Tiền mặt" :"Chuyển khoản"}</span>
                                </td>
                                <td>${hoaDonDTO.nhanVien.hoTen}</td>
                                <td></td>

                            </tr>

                            </tbody>
                        </table>

                    </div>

                </div>


                <!-- Modal thanh toán -->
                <div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="paymentModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="paymentModalLabel">Thanh toán</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <%--@declare id="totalAmount"--%>
                                    <label for="totalAmount" class="form-label">Tổng tiền:</label>
                                    <fmt:formatNumber value="${hoaDonDTO.tongTien}" type="currency" currencySymbol="₫"/>
                                </div>
                                <div class="mb-3">
                                    <%--@declare id="paidamount"--%><label for="paidAmount" class="form-label">Đã thanh
                                    toán:</label>
                                    <input type="text" class="form-control" id="paidAmountInput"
                                           placeholder="Nhập số tiền đã thanh toán">
                                </div>
                                <div class="mb-3">
                                    <label class="form-check-label">Hình thức thanh toán:</label><br>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="paymentMethod" id="cashRadio"
                                               value="0" checked>
                                        <label class="form-check-label" for="cashRadio">Tiền mặt</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="paymentMethod"
                                               id="transferRadio" value="1">
                                        <label class="form-check-label" for="transferRadio">Chuyển khoản</label>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <%--@declare id="note"--%>
                                    <label for="note" class="form-label">Ghi chú:</label>
                                    <textarea class="form-control" id="noteInput" rows="3"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                <button type="button" class="btn btn-primary" id="confirmPaymentButton">Xác nhận
                                </button>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Page Heading -->
                <%--Thông tin hóa đơn    --%>
                <%--                <h1 class="h3 mb-3 text-gray-800">Thông tin chi tiết hóa đơn</h1>--%>


                <!-- Thông tin đơn hàng -->

                <div class="row d-flex align-items-stretch">
                    <div class="col-7 mb-3 d-flex align-items-stretch">
                        <div class="card w-100 h-100">
                            <div class="card-header d-flex justify-content-between align-items-center">
                                <h5 class="card-title">Thông tin đơn hàng: ${hoaDonDTO.ma}</h5>
                                <%--                                <c:choose>--%>
                                <%--                                    <c:when test="${hoaDonDTO.loaiHoaDon == 0}">--%>
                                <c:if test="${hoaDonDTO.loaiHoaDon == 0}">
                                    <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                                            data-bs-target="#updateModal"
                                    >
                                        Thay đổi
                                    </button>
                                </c:if>
                                <%--                                    </c:when>--%>
                                <%--                                </c:choose>--%>
                            </div>

                            <div class="card-body">
                                <%--   Thông tin chi tiết hóa đơn --%>
                                <div>
                                    <div class="row mt-1">
                                        <div class="col-6">
                                            <div>
                                                <p class="fw-bold mb-1 pb-3 small ">Trạng thái: <span
                                                        class="fw-normal badge rounded-pill bg-primary">
                                                    ${hoaDonDTO.trangThai == 0 ? "Chưa hoàn thành" : "Đã hoàn thành"}
                                                </span>
                                                </p>
                                                <p class="fw-bold mb-1 pb-3 small">Loại giao dịch: <span
                                                        class=" fw-normal badge rounded-pill  bg-primary">Thanh toán</span>
                                                </p>
                                                <p class="fw-bold mb-1 pb-3 small">Tên người nhận: <span
                                                        class="fw-normal">${hoaDonDTO.khachHang.hoTen}</span></p>
                                                <p class="fw-bold mb-1 pb-3 small">Địa chỉ: <span
                                                        class="fw-normal">${diaChiKhachHang.diaChiChiTiet}, ${diaChiKhachHang.idPhuongXa}, ${diaChiKhachHang.idQuanHuyen}, ${diaChiKhachHang.idTinhThanh}    </span>
                                                </p>
                                                <p class="fw-bold mb-1 pb-3 small">Ghi chú: </p>
                                                <p class="fw-bold mb-1 small">Người tạo: <span
                                                        class="fw-normal">${hoaDonDTO.nhanVien.hoTen}</span></p>

                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <p class="fw-bold mb-1 pb-3 small">Loại hóa đơn: <span
                                                    class=" fw-normal badge rounded-pill ${hoaDonDTO.loaiHoaDon == 0 ? 'bg-primary' : 'bg-success'}">
                                                ${hoaDonDTO.loaiHoaDon == 0 ? "Bán online" :"Bán tại quầy"}</span>
                                            </p>
                                            <p class="fw-bold mb-1 pb-3 small">Phương thức thanh toán: <span
                                                    class="fw-normal badge rounded-pill bg-primary">${hoaDonDTO.phuongThucThanhToan == 0 ? "Tiền mặt" : "Chuyển khoản"}</span>
                                            </p>
                                            <p class="fw-bold mb-1 pb-3 small">Số điện thoại: <span
                                                    class="fw-normal">${hoaDonDTO.khachHang.sdt}</span></p>


                                            <p class="fw-bold mb-1 pb-3 small">Email:
                                                <span
                                                        class="fw-normal"> ${hoaDonDTO.khachHang.email}
                                                </span>
                                            </p>
                                            <p class="fw-bold mb-1 pb-3 small">Ngày tạo: <span
                                                    class="fw-normal">${hoaDonDTO.ngayTao}</span></p>

                                            <c:choose>
                                                <c:when test="${hoaDonDTO.loaiHoaDon == 0}">
                                                    <p class="fw-bold mb-1 small">Ngày dự kiến nhận:</p>
                                                </c:when>
                                            </c:choose>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- Chi phí của đơn hàng--%>
                    <div class="col-5 mb-3 d-flex align-items-stretch">
                        <div class="card w-100 h-100">
                            <div class="card-header">
                                <h5 class="card-title">Chi phí đơn hàng</h5>
                            </div>
                            <div class="card-body">
                                <c:set var="giamGia" value="0"/>
                                <c:set var="phiVanChuyen" value="0"/>
                                <div class="row">
                                    <div class="col-12">
                                        <p class="fw-bold mb-1 pb-3 small d-flex justify-content-between">
                                            <span>Tổng tiền đơn hàng:</span>
                                            <span class="text-danger  "><fmt:formatNumber value="${hoaDonDTO.tongTien}"
                                                                                          type="currency"
                                                                                          currencySymbol="₫"
                                                                                          groupingUsed="true"/></span>
                                        </p>
                                        <p class="fw-bold mb-1 pb-3 small d-flex justify-content-between">
                                            <span> Phí Giảm giá:</span>
                                            <span class="fw-normal">
                                                <fmt:formatNumber value="${giamGia}" type="currency" currencySymbol="₫"
                                                                  groupingUsed="true"/>
                                            </span>
                                        </p>
                                        <p class="fw-bold mb-1 pb-3 small d-flex justify-content-between">
                                            <span>Phí vận chuyển:</span>
                                            <span class="fw-normal">
                                                <fmt:formatNumber value="${phiVanChuyen}" type="currency"
                                                                  currencySymbol="₫" groupingUsed="true"/>
                                             </span>
                                        </p>

                                        <div class="">
                                            <p class="fw-bold mb-1 pb-3 small d-flex justify-content-between">
                                                <span>Tổng tiền giảm:</span>
                                                <span class="fw-normal">
                                                    <fmt:formatNumber
                                                            value="${hoaDonDTO.tongTien - giamGia - phiVanChuyen}"
                                                            type="currency"
                                                            currencySymbol="₫" groupingUsed="true"/>
                                                </span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End General Form Elements -->

                            <div class="card-footer">
                                <p class="fw-bold mb-1 pb-3 small d-flex justify-content-between">
                                    <span>Tổng tiền thanh toán:</span>
                                    <span class="text-danger "><fmt:formatNumber value="${hoaDonDTO.tongTien}"
                                                                                 type="currency" currencySymbol="₫"
                                                                                 groupingUsed="true"/></span>
                                </p>
                            </div>

                        </div>


                    </div><!-- End Recent Activity -->
                </div>
            </div>

            <!-- Modal để cập nhật thông tin khách hàng -->
            <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updateModalLabel">Cập nhật thông tin khách hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="updateForm">
                                <div class="mb-3">
                                    <label for="hoTen" class="form-label">Họ tên</label>
                                    <input type="text" class="form-control" id="hoTen" name="hoTen"
                                           value="${hoaDonDTO.khachHang.hoTen}">
                                </div>
                                <div class="mb-3">
                                    <label for="sdt" class="form-label">Số điện thoại</label>
                                    <input type="text" class="form-control" id="sdt" name="sdt"
                                           value="${hoaDonDTO.khachHang.sdt}">
                                </div>
                                <div class="mb-3">
                                    <label for="diaChi" class="form-label">Địa chỉ</label>
                                    <input type="text" class="form-control" id="diaChi" name="diaChi"
                                           value="${diaChiKhachHang.diaChiChiTiet}">
                                </div>
                                <div class="mb-3">
                                    <label for="tinh" class="form-label">Tỉnh</label>
                                    <select class="form-select" id="tinh" name="tinh">
                                        <!-- Thêm các tùy chọn tỉnh -->
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="huyen" class="form-label">Huyện</label>
                                    <select class="form-select" id="huyen" name="huyen">
                                        <!-- Thêm các tùy chọn huyện -->
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="xa" class="form-label">Xã</label>
                                    <select class="form-select" id="xa" name="xa">
                                        <!-- Thêm các tùy chọn xã -->
                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <button type="button" class="btn btn-primary" id="updateButton">Cập nhật</button>
                        </div>
                    </div>
                </div>
            </div>

            <%-- Thông tin sản phẩm đã mua                --%>
            <div class="card shadow mb-4 mx-2" id="custom-card">
                <div class="card-header py-3 custom-card-header">
                    <h6 class="m-0 font-weight-bold">Thông tin sản phẩm đã mua</h6>
                </div>

                <div class="card-body custom-card-body">
                    <table class="table table-bordered custom-table" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Ảnh sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Giá sản phẩm</th>
                            <th>Màu sắc</th>
                            <th>Kích thước</th>
                            <th>Số lượng</th>
                            <th>Tổng Tiền</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="chiTiet" items="${listHDCT}" varStatus="i">
                            <tr>
                                <td>${i.index + 1}</td>
                                <td><img src="/image/${chiTiet.idCTSP.idSanPham.hinhAnh}" alt="Ảnh sản phẩm" width="50">
                                </td>
                                <td>${chiTiet.idCTSP.idSanPham.ten}</td>
                                <td><fmt:formatNumber value="${chiTiet.donGia}" type="currency" currencySymbol="₫"
                                                      groupingUsed="true"/></td>
                                <td>${chiTiet.idCTSP.idMauSac.ten}</td>
                                <td>${chiTiet.idCTSP.idKichThuoc.ten}</td>
                                <td>${chiTiet.soLuong}</td>
                                <td><fmt:formatNumber value="${chiTiet.donGia * chiTiet.soLuong}" type="currency"
                                                      currencySymbol="₫" groupingUsed="true"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>


            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <%--  Phiếu hóa đơn để giao hàng --%>
        <div class="delivery" style="display: none">
            <div class="container mt-5 p-4 shadow delivery">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <h1>MS-STORE</h1>
                        <p class="mb-1">Mã Hóa Đơn: ${hoaDonDTO.ma}</p>
                        <p class="mb-0">Ngày Đặt Đơn: ${hoaDonDTO.ngayTao}</p>
                    </div>

                </div>
                <div class="separator"></div>


                <div class="row mb-4">
                    <div class="col-md-6">
                        <h5>Thông tin Bên Gửi:</h5>
                        <div class="d-flex flex-column">
                            <p>Người gửi: MS-STORE</p>
                            <p>Địa chỉ: Tòa nhà FPT Polytechnic, Phố Trịnh Văn Bô, Xuân Phương, Nam Từ Liêm, Hà Nội</p>
                            <p>Số điện thoại: 0123 456 789</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h5>Thông tin Bên Nhận:</h5>
                        <div class="d-flex flex-column">
                            <p>Người nhận: ${hoaDonDTO.khachHang.hoTen}</p>
                            <p>Địa
                                chỉ: ${diaChiKhachHang.diaChiChiTiet}, ${diaChiKhachHang.idPhuongXa}, ${diaChiKhachHang.idQuanHuyen}, ${diaChiKhachHang.idTinhThanh}</p>
                            <p>Số điện thoại: ${hoaDonDTO.khachHang.sdt}</p>
                        </div>
                    </div>
                </div>


                <div class="mb-4">
                    <h5>Nội dung đơn hàng</h5>
                    <table class="table table-bordered">
                        <thead class="thead-light">
                        <tr>
                            <th>Tên Sản Phẩm</th>
                            <th>Màu Sắc</th>
                            <th>Kích Thước</th>
                            <th>Số Lượng</th>
                            <th>Đơn Giá</th>
                            <th>Tổng tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="chiTiet" items="${listHDCT}" varStatus="i">
                            <tr>
                                <td>${chiTiet.idCTSP.idSanPham.ten}</td>
                                <td>${chiTiet.idCTSP.idMauSac.ten}</td>
                                <td>${chiTiet.idCTSP.idKichThuoc.ten}</td>
                                <td>${chiTiet.soLuong}</td>
                                <td><fmt:formatNumber value="${chiTiet.donGia}" type="currency" currencySymbol="₫"
                                                      groupingUsed="true"/></td>
                                <td><fmt:formatNumber value="${chiTiet.donGia * chiTiet.soLuong}" type="currency"
                                                      currencySymbol="₫" groupingUsed="true"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="d-flex justify-content-between align-items-start mb-4">
                    <div>
                        <h5 class="mb-0">Tiền thu hộ: <span><fmt:formatNumber value="${hoaDonDTO.tongTien}"
                                                                              type="currency" currencySymbol="₫"
                                                                              groupingUsed="true"/></span></h5>
                    </div>
                    <div class="text-right mr-5">
                        <p class="mb-1 mr-5">Chữ ký người nhận</p>
                        <p class="mb-0">(Xác nhận hàng nguyên vẹn, không móp, méo)</p>
                    </div>
                </div>
            </div>
        </div>


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

    <c:if test="${not empty error}">
    Toast.fire({
        icon: "success",
        title: "${confirmSuccess}"
    });
    </c:if>

    <c:if test="${not empty errorMessage}">
    Toast.fire({
        icon: "error",
        title: "${errorMessage}"
    });
    </c:if>









    // Ẩn hiển trạng thái Hóa đơn
    document.addEventListener("DOMContentLoaded", function () {
        const confirmBtn = document.getElementById('confirmBtn');
        const undoBtn = document.getElementById('undoBtn');
        const modalConfirmBtn = document.getElementById('modalConfirmBtn');
        const stepperOnline = document.getElementById('stepper_online');
        const stepsOnline = stepperOnline.querySelectorAll('.step');

        let currentStep = 0;

        // Show the first step
        if (stepsOnline.length > 0) {
            stepsOnline[currentStep].classList.remove('hide');
        }

        confirmBtn.addEventListener('click', function () {
            const confirmModal = new bootstrap.Modal(document.getElementById('confirmModal'));
            confirmModal.show();
        });

        modalConfirmBtn.addEventListener('click', function () {
            const confirmModal = bootstrap.Modal.getInstance(document.getElementById('confirmModal'));
            confirmModal.hide();

            // Move to the next step
            if (currentStep < stepsOnline.length - 1) {
                stepsOnline[currentStep].classList.add('hide');
                currentStep++;
                stepsOnline[currentStep].classList.remove('hide');
            }

            // Show the undo button
            undoBtn.style.display = 'inline-block';
        });

        undoBtn.addEventListener('click', function () {
            if (currentStep > 0) {
                stepsOnline[currentStep].classList.add('hide');
                currentStep--;
                stepsOnline[currentStep].classList.remove('hide');
            }

            // Hide the undo button if we're back at the first step
            if (currentStep === 0) {
                undoBtn.style.display = 'none';
            }
        });
    });

    // Cofirm hủy hóa đơn
    document.getElementById('cancelButton').addEventListener('click', function () {
        Swal.fire({
            title: 'Bạn có chắc chắn muốn hủy?',
            text: "Bạn sẽ không thể hoàn tác lại thao tác này!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'Có, hủy nó!',
            cancelButtonText: 'Không, giữ lại'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire(
                    'Đã hủy!',
                    'Hóa đơn của bạn đã được hủy.',
                    'success'
                )
            }
        })
    });

    // Nút in hóa đơn để giao hàng
    document.getElementById('printDeliveryButton').addEventListener('click', () => {
        const element = document.querySelector('.container');
        const opt = {
            margin: 1,
            filename: 'phieu_giao_hang.pdf',
            image: {type: 'jpeg', quality: 0.98},
            html2canvas: {scale: 2},
            jsPDF: {unit: 'cm', format: 'a4', orientation: 'portrait'}
        };
        html2pdf().from(element).set(opt).save();
    });


</script>


</body>

</html>