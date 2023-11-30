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
    <section class="h-100 h-custom" style="background-color: #eee;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col">
                    <div class="card">
                        <div class="card-body p-4">
                            <div class="row">
                                <div class="col-lg-7">
                                    <h5 class="mb-3"><a href="#!" class="text-body"><i
                                            class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a></h5>
                                    <hr>
                                    <div class="card mb-3">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <div class="d-flex flex-row align-items-center">
                                                    <div>
                                                        <img
                                                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img1.webp"
                                                                class="img-fluid rounded-3" alt="Shopping item"
                                                                style="width: 65px;">
                                                    </div>
                                                    <div class="ms-3">
                                                        <h5>Iphone 11 pro</h5>
                                                        <p class="small mb-0">256GB, Navy Blue</p>
                                                    </div>
                                                </div>
                                                <div class="d-flex flex-row align-items-center">
                                                    <div style="width: 50px;">
                                                        <h5 class="fw-normal mb-0">2</h5>
                                                    </div>
                                                    <div style="width: 80px;">
                                                        <h5 class="mb-0">$900</h5>
                                                    </div>
                                                    <a href="#!" style="color: #cecece;"><i
                                                            class="fas fa-trash-alt"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card mb-3">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <div class="d-flex flex-row align-items-center">
                                                    <div>
                                                        <img
                                                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img2.webp"
                                                                class="img-fluid rounded-3" alt="Shopping item"
                                                                style="width: 65px;">
                                                    </div>
                                                    <div class="ms-3">
                                                        <h5>Samsung galaxy Note 10 </h5>
                                                        <p class="small mb-0">256GB, Navy Blue</p>
                                                    </div>
                                                </div>
                                                <div class="d-flex flex-row align-items-center">
                                                    <div style="width: 50px;">
                                                        <h5 class="fw-normal mb-0">2</h5>
                                                    </div>
                                                    <div style="width: 80px;">
                                                        <h5 class="mb-0">$900</h5>
                                                    </div>
                                                    <a href="#!" style="color: #cecece;"><i
                                                            class="fas fa-trash-alt"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card mb-3">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <div class="d-flex flex-row align-items-center">
                                                    <div>
                                                        <img
                                                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img3.webp"
                                                                class="img-fluid rounded-3" alt="Shopping item"
                                                                style="width: 65px;">
                                                    </div>
                                                    <div class="ms-3">
                                                        <h5>Canon EOS M50</h5>
                                                        <p class="small mb-0">Onyx Black</p>
                                                    </div>
                                                </div>
                                                <div class="d-flex flex-row align-items-center">
                                                    <div style="width: 50px;">
                                                        <h5 class="fw-normal mb-0">1</h5>
                                                    </div>
                                                    <div style="width: 80px;">
                                                        <h5 class="mb-0">$1199</h5>
                                                    </div>
                                                    <a href="#!" style="color: #cecece;"><i
                                                            class="fas fa-trash-alt"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card mb-3 mb-lg-0">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <div class="d-flex flex-row align-items-center">
                                                    <div>
                                                        <img
                                                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img4.webp"
                                                                class="img-fluid rounded-3" alt="Shopping item"
                                                                style="width: 65px;">
                                                    </div>
                                                    <div class="ms-3">
                                                        <h5>MacBook Pro</h5>
                                                        <p class="small mb-0">1TB, Graphite</p>
                                                    </div>
                                                </div>
                                                <div class="d-flex flex-row align-items-center">
                                                    <div style="width: 50px;">
                                                        <h5 class="fw-normal mb-0">1</h5>
                                                    </div>
                                                    <div style="width: 80px;">
                                                        <h5 class="mb-0">$1799</h5>
                                                    </div>
                                                    <a href="#!" style="color: #cecece;"><i
                                                            class="fas fa-trash-alt"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="col-lg-5">

                                    <div class="card bg-primary text-white rounded-3">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between align-items-center mb-4">
                                                <h5 class="mb-0">Card details</h5>
                                                <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-6.webp"
                                                     class="img-fluid rounded-3" style="width: 45px;" alt="Avatar">
                                            </div>

                                            <p class="small mb-2">Card type</p>
                                            <a href="#!" type="submit" class="text-white"><i
                                                    class="fab fa-cc-mastercard fa-2x me-2"></i></a>
                                            <a href="#!" type="submit" class="text-white"><i
                                                    class="fab fa-cc-visa fa-2x me-2"></i></a>
                                            <a href="#!" type="submit" class="text-white"><i
                                                    class="fab fa-cc-amex fa-2x me-2"></i></a>
                                            <a href="#!" type="submit" class="text-white"><i
                                                    class="fab fa-cc-paypal fa-2x"></i></a>

                                            <form class="mt-4">
                                                <div class="form-outline form-white mb-4">
                                                    <input type="text" id="typeName"
                                                           class="form-control form-control-lg" siez="17"
                                                           placeholder="Cardholder's Name"/>
                                                    <label class="form-label" for="typeName">Cardholder's Name</label>
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <input type="text" id="typeText1"
                                                           class="form-control form-control-lg" siez="17"
                                                           placeholder="1234 5678 9012 3457" minlength="19"
                                                           maxlength="19"/>
                                                    <label class="form-label" for="typeText">Card Number</label>
                                                </div>

                                                <div class="row mb-4">
                                                    <div class="col-md-6">
                                                        <div class="form-outline form-white">
                                                            <input type="text" id="typeExp"
                                                                   class="form-control form-control-lg"
                                                                   placeholder="MM/YYYY" size="7" id="exp" minlength="7"
                                                                   maxlength="7"/>
                                                            <label class="form-label" for="typeExp">Expiration</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-outline form-white">
                                                            <input type="password" id="typeText"
                                                                   class="form-control form-control-lg"
                                                                   placeholder="&#9679;&#9679;&#9679;" size="1"
                                                                   minlength="3" maxlength="3"/>
                                                            <label class="form-label" for="typeText">Cvv</label>
                                                        </div>
                                                    </div>
                                                </div>

                                            </form>

                                            <hr class="my-4">

                                            <div class="d-flex justify-content-between">
                                                <p class="mb-2">Subtotal</p>
                                                <p class="mb-2">$4798.00</p>
                                            </div>

                                            <div class="d-flex justify-content-between">
                                                <p class="mb-2">Shipping</p>
                                                <p class="mb-2">$20.00</p>
                                            </div>

                                            <div class="d-flex justify-content-between mb-4">
                                                <p class="mb-2">Total(Incl. taxes)</p>
                                                <p class="mb-2">$4818.00</p>
                                            </div>
                                            <button type="button" class="btn btn-success">
                                                <a href="/Home/bill">Checkout</a></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
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