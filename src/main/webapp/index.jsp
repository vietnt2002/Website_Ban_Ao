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
                        <a class="nav-link" aria-current="page" href="/Home/homeServlet">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            QL sản phẩm
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/Home/sanPham">Sản phẩm</a></li>
                            <li><a class="dropdown-item" href="/Home/chiTietSP">Chi tiết sản phẩm</a></li>
                            <li><a class="dropdown-item" href="/Home/dongSP">Dòng sản phẩm</a></li>
                            <li><a class="dropdown-item" href="/Home/mauSac">Màu sắc</a></li>
                            <li><a class="dropdown-item" href="/Home/nsx">Nsx</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Ql Cửa hàng
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/Home/employee">Nhân viên</a></li>
                            <li><a class="dropdown-item" href="/Home/chucvu">Chức vụ</a></li>
                            <li><a class="dropdown-item" href="/Home/cuaHang">Cửa hàng</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Ql Hóa đơn
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/Home/hoaDon">Hóa đơn</a></li>
                            <li><a class="dropdown-item" href="/Home/hoaDonChiTiet">Hóa đơn chi tiết</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown4" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Ql khách hàng
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/Home/khachHang">Khách hàng</a></li>
                        </ul>
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
    <div id="carouselExampleCaptions" class="carousel slide">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="row">
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;height:250px;"
                             src="https://ts2.space/wp-content/uploads/2023/10/What-will-happen-if-the-spacex-rocket-hits-the-Moon_652a460e34c3d.jpg"
                             alt="Responsive image">
                    </div>
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;;height:250px;"
                             src="https://ts2.space/wp-content/uploads/2023/10/What-will-happen-if-the-spacex-rocket-hits-the-Moon_652a460e34c3d.jpg"
                             alt="Responsive image">
                    </div>
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;;height:250px;"
                             src="https://ts2.space/wp-content/uploads/2023/10/What-will-happen-if-the-spacex-rocket-hits-the-Moon_652a460e34c3d.jpg"
                             alt="Responsive image">
                    </div>
                </div>
                <div class="carousel-caption d-none d-md-block">
                    <h5>Hanoi hustle</h5>
                    <p>If you love street food, love hustle, and classical,So book a ticket to travel that city now
                        <br>
                        <p2 style="font-size:12px;">Click to banner to know detail</p2>
                    </p>
                </div>
            </div>
            <div class="carousel-item">
                <div class="row">
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;height:250px;"
                             src=""
                             alt="Responsive image">
                    </div>
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;height:250px;"
                             src=""
                             alt="Responsive image">
                    </div>
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;height:250px;"
                             src=""
                             alt="Responsive image">
                    </div>
                </div>
                <div class="carousel-caption d-none d-md-block">
                    <h5>Hoi An</h5>
                    <p>What a good vibe place for your vacation.
                        <br>
                        <p2 style="font-size:12px;">Click to banner to know detail</p2>
                    </p>
                </div>
            </div>
            <div class="carousel-item">
                <div class="row">
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;height:250px;"
                             src=""
                             alt="Responsive image">
                    </div>
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;height:250px;"
                             src=""
                             alt="Responsive image">
                    </div>
                    <div class="col-4 md-4 sm-4" style="padding: 0;">
                        <img class="d-block w-100" style="width: auto;height:250px;"
                             src=""
                             alt="Responsive image">
                    </div>
                </div>
                <div class="carousel-caption d-none d-md-block">
                    <h5>New york</h5>
                    <p>If you love the film about wall street drama, let discover the big head finance city of the
                        world
                        <br>
                        <p2 style="font-size:12px;">Click to banner to know detail</p2>
                    </p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <div class="row">
        <div class="col-md-4">
            <h2>Danh sách sản phẩm</h2>
        </div>
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <h3>Xin chào !</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <div class="card" style="width: 18rem;">
                <img src="https://shopdunk.com/images/thumbs/0005888_air-m2-silver_1600.jpeg"
                     class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Beautiful product</h5>
                    <p class="card-text">Price: $priceless$</p>
                    <div class="input-group">
                        <input type="number" class="form-control" aria-label="Enter amount product">
                        <a href="/Home/Cart" class="btn btn-warning">Add to cart</a>
                    </div>
                    <br>
                    <div class="input-group">
                        <input type="number" class="form-control" aria-label="Enter amount product">
                        <a href="/Home/Cart" class="btn btn-primary">Buy product</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card" style="width: 18rem;">
                <img src="https://shopdunk.com/images/thumbs/0005888_air-m2-silver_1600.jpeg"
                     class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Beautiful product</h5>
                    <p class="card-text">Price: $priceless$</p>
                    <div class="input-group">
                        <input type="number" class="form-control" aria-label="Enter amount product">
                        <a href="/Home/Cart" class="btn btn-warning">Add to cart</a>
                    </div>
                    <br>
                    <div class="input-group">
                        <input type="number" class="form-control" aria-label="Enter amount product">
                        <a href="/Home/Cart" class="btn btn-primary">Buy product</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card" style="width: 18rem;">
                <img src="https://shopdunk.com/images/thumbs/0005888_air-m2-silver_1600.jpeg"
                     class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Beautiful product</h5>
                    <p class="card-text">Price: $priceless$</p>
                    <div class="input-group">
                        <input type="number" class="form-control" aria-label="Enter amount product">
                        <a href="/Home/Cart" class="btn btn-warning">Add to cart</a>
                    </div>
                    <br>
                    <div class="input-group">
                        <input type="number" class="form-control" aria-label="Enter amount product">
                        <a href="/Home/Cart" class="btn btn-primary">Buy product</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<footer id="footer" style="margin-top: 60px;">
    <div class="container">
        <div class="row" style="width:750px;margin-left: auto;margin-right: auto;">
            <div class="col-4 md-4">
                <div class="useful-link">
                    <h2 style="color:white;">Useful Links</h2>
                    <div class="use-links">
                        <li><a href=""><i class="fa-solid fa-angles-right"></i> Home</a></li>
                        <li><a href=""><i class="fa-solid fa-angles-right"></i> About Us</a>
                        </li>
                        <li><a href=""><i class="fa-solid fa-angles-right"></i> Gallery</a>
                        </li>
                        <li><a href=""><i class="fa-solid fa-angles-right"></i> Contact</a>
                        </li>
                    </div>
                </div>
            </div>
            <div class="col-4 md-4">
                <div class="social-links">
                    <h2 style="color: white;">Follow Us</h2>
                    <div class="social-icons">
                        <li><a href=""><i class="fa-brands fa-facebook-f"></i> Facebook</a></li>
                        <li><a href=""><i class="fa-brands fa-instagram"></i> Instagram</a></li>
                        <li><a href=""><i class="fa-brands fa-linkedin-in"></i> Linkedin</a></li>
                    </div>
                </div>
            </div>
            <div class="col-4 md-4">
                <div class="address">
                    <h2 style="color: white">Address</h2>
                    <div class="address-links">
                        <li class="address1"><i class="fa-solid fa-location-dot"></i>FPT
                            Nam Từ Liêm-
                            Hà Nội
                            Việt Nam
                        </li>
                        <li><a href=""><i class="fa-solid fa-phone"></i> +84374223222</a></li>
                        <li><a href=""><i class="fa-solid fa-envelope"></i> phucloc@gmail.com</a></li>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>