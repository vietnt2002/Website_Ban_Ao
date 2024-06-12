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

                <%--                Nút in ra Hóa Đơn theo dạng file PDF --%>
                <div class="d-flex justify-content-end">
                    <a href="/in-hoa-don/pdf/${hoaDonDTO.id}" class="btn btn-primary my-3"><i class="bi bi-printer"></i>
                        In hóa đơn</a>
                </div>


                <%--Bảng theo dõi hóa đơn--%>
                <%--                    <div class="card mb-3">--%>

                <%--                        <div class="card-header">--%>
                <%--                            <h5 class="card-title ">Theo dõi đơn hàng:</h5>--%>
                <%--                        </div>--%>

                <%--                        <div class="card-body">--%>


                <%--                        </div>--%>
                <%--                        <div class="card-footer">--%>
                <%--                            <div>--%>
                <%--                                <div>--%>
                <%--                                    <button type="button" class="btn btn-primary">Xác nhận</button>--%>
                <%--                                </div>--%>
                <%--                                <div>--%>
                <%--                                    <button type="button" class="btn btn-danger">Hủy</button>--%>
                <%--                                </div>--%>
                <%--                                <div class="float-end">--%>
                <%--                                    <button type="button" class="btn btn-success ">Lịc sử</button>--%>
                <%--                                </div>--%>


                <%--                            </div>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                <%--Bảng lịch sử thanh toán                --%>
                <%--                    <div class="card mb-3">--%>

                <%--                        <div class="card-header">--%>
                <%--                            <h5 class="card-title ">Lịch sử thanh toán:</h5>--%>
                <%--                        </div>--%>

                <%--                        <div class="card-body">--%>
                <%--                            <table class="table table-bordered" width="100%" cellspacing="0">--%>
                <%--                                <thead>--%>
                <%--                                <tr>--%>
                <%--                                    <th>STT</th>--%>
                <%--                                    <th>Mã GD</th>--%>
                <%--                                    <th>Số tiền</th>--%>
                <%--                                    <th>Trạng thái</th>--%>
                <%--                                    <th>Thời gian</th>--%>
                <%--                                    <th>Loại giao dịch</th>--%>
                <%--                                    <th>Phương thức thanh toán</th>--%>
                <%--                                    <th>Người tạo</th>--%>
                <%--                                    <th>Ghi chú</th>--%>
                <%--                                </tr>--%>
                <%--                                </thead>--%>

                <%--                                <tbody>--%>
                <%--                                &lt;%&ndash;                            <c:forEach var="hoaDon" items="${pageHD.content}" varStatus="i">&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                <tr>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>${i.index + 1}</td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>${hoaDon.ma}</td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>${hoaDon.idNhanVien.hoTen}</td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>${hoaDon.idKhachHang.hoTen}</td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>${hoaDon.idKhachHang.sdt}</td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>Bán tại quầy</td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>${hoaDon.tongTien}</td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                        &lt;%&ndash;${hoaDon.ngayTao}&ndash;%&gt;&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    </td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                                 <span&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                                         class="badge rounded-pill ${hoaDon.trangThai == 0 ? 'bg-danger' : 'bg-success'}">&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                                         ${hoaDon.trangThai == 0 ? 'Chưa thanh toán' : 'Đã thanh toán'}&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                                 </span>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    </td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    <td>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                        <a href="/ban-hang-tai-quay/detail-hoa-don/${hoaDon.id}"&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                           class="btn btn-primary">View</a>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                                    </td>&ndash;%&gt;--%>

                <%--                                &lt;%&ndash;                                </tr>&ndash;%&gt;--%>
                <%--                                &lt;%&ndash;                            </c:forEach>&ndash;%&gt;--%>

                <%--                                </tbody>--%>
                <%--                            </table>--%>

                <%--                        </div>--%>

                <%--                    </div>--%>


                <!-- Page Heading -->
                <%--Thông tin hóa đơn    --%>
                <%--                <h1 class="h3 mb-3 text-gray-800">Thông tin chi tiết hóa đơn</h1>--%>


                <!-- Thông tin đơn hàng -->

                <div class="row d-flex align-items-stretch">
                    <div class="col-7 mb-3 d-flex align-items-stretch">
                        <div class="card  w-100 h-100">

                            <div class="card-header">
                                <h5 class="card-title ">Thông tin đơn hàng:${hoaDonDTO.ma}</h5>
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
                                                <p class="fw-bold mb-1 pb-3 small">Loại giao dịch: </p>
                                                <p class="fw-bold mb-1 pb-3 small">Tên người nhận: <span
                                                        class="fw-normal">${hoaDonDTO.khachHang.hoTen}</span></p>
                                                <p class="fw-bold mb-1 pb-3 small">Địa chỉ: <span
                                                        class="fw-normal">${diaChiKhachHang.diaChiChiTiet}</span> </p>
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
                                                <c:when test=" ${hoaDonDTO.loaiHoaDon == 1}">

                                                </c:when>
                                                <c:otherwise>
                                                    <p class="fw-bold mb-1  small">Ngày dự kiến nhận:</p>
                                                </c:otherwise>
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
                                            <span>Phí giao hàng:</span>
                                            <span class="fw-normal  "><fmt:formatNumber value="0" type="currency"
                                                                                        currencySymbol="₫"
                                                                                        groupingUsed="true"/></span>
                                        </p>
                                        <p class="fw-bold mb-1 pb-3 small d-flex justify-content-between">
                                            <span>Phiếu giảm giá:</span>
                                            <span class="fw-normal  "><fmt:formatNumber value="0" type="currency"
                                                                                        currencySymbol="₫"
                                                                                        groupingUsed="true"/></span>
                                        </p>
                                        <div class="">
                                            <p class="fw-bold mb-1 pb-3 small d-flex justify-content-between">
                                                <span>Tổng tiền giảm:</span>
                                                <span class="fw-normal  "><fmt:formatNumber
                                                        value="0" type="currency" currencySymbol="₫"
                                                        groupingUsed="true"/></span>
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

            <div>

            </div>

            <%-- Thông tin sản phẩm đã mua                --%>
            <div class="card shadow mb-4 mx-2">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold ">Thông tin sản phẩm đã mua </h6>
                </div>

                <div class="card-body">

                    <table class="table table-bordered" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Ảnh sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Giá sản phẩm</th>
                            <th>Màu sắc</th>
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
                                <td>${chiTiet.donGia}</td>
                                <td>${chiTiet.idCTSP.idMauSac.ten}</td>
                                <td>${chiTiet.soLuong}</td>
                                <td>${chiTiet.donGia * chiTiet.soLuong}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div>

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
    $(document).ready(function () {
        // Thêm lớp bg-warning cho tab "Tất Cả" khi trang được tải
        $('#all-tab').addClass('bg-warning');

        // Khi click vào tab
        $('#myTab .nav-link').click(function () {
            // Loại bỏ lớp bg-warning từ tất cả các tab
            $('#myTab .nav-link').removeClass('bg-warning');
            // Thêm lớp bg-warning cho tab được nhấn
            $(this).addClass('bg-warning');
        });
    });


</script>


</body>

</html>