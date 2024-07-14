<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán thành công</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 50px;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            margin: 0 auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
        }

        h1 {
            color: #4CAF50;
        }

        .order-info {
            margin: 20px 0;
        }

        .order-info p {
            line-height: 1.6;
        }

        .back-home {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .back-home:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>Thanh toán thành công!</h1>
    <p>Cảm ơn bạn đã mua hàng.</p>
    <div class="order-info">
        <p><strong>Mã đơn hàng:</strong> ${maHD}</p>
        <p><strong>Ngày đặt hàng:</strong> ${ngayTao}</p>
        <p><strong>Số tiền thanh toán:</strong> ${amount} VND</p>
    </div>
    <a href="/ban-hang-tai-quay" class="back-home">Quay về trang chủ</a>
</div>
</body>

</html>