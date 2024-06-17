
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phiếu Giao Hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 21cm; /* Chiều rộng A4 */
            height: auto; /* Chiều cao tự động điều chỉnh */
            padding: 20px;
            margin: auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .section {
            margin-bottom: 20px;
        }
        .section-title {
            font-weight: bold;
            margin-bottom: 10px;
        }
        .info {
            margin-bottom: 10px;
        }
        .table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        .table th, .table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .footer {
            text-align: right;
            margin-top: 20px;
        }
        .signature {
            float: right;
            margin-top: 20px;
            text-align: center;
        }
        .payment {
            font-weight: bold;
            display: flex;
            margin-top: 20px;
            /* justify-content: space-between; */
        }
        .payment .monney{
            padding-left: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Phiếu Giao Hàng</h1>
    </div>

    <div class="section">
        <div class="section-title">Thông tin Bên Gửi:</div>
        <div class="info">Người gửi: MS-STORE</div>
        <div class="info">Địa chỉ: Tòa nhà FPT Polytechnic,Phố Trịnh Văn Bô, Xuân Phương, Nam Từ Liêm, Hà Nội</div>
        <div class="info">Số điện thoại: 0123 456 789</div>
    </div>

    <div class="section">
        <div class="section-title">Thông tin Bên Nhận:</div>
        <div class="info">Người nhận: ${hoaDonDTO.khachHang.hoTen}</div>
        <div class="info">Địa chỉ: ${diaChiKhachHang.diaChiChiTiet}, ${diaChiKhachHang.idPhuongXa}, ${diaChiKhachHang.idQuanHuyen}, ${diaChiKhachHang.idTinhThanh}</div>
        <div class="info">Số điện thoại: ${hoaDonDTO.khachHang.sdt}</div>
    </div>

    <div class="section">
        <div class="section-title">Nội dung đơn hàng</div>
        <table class="table">
            <thead>
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

        <div class="section payment">
            <div class="section-title my-5 pr-3">Tiền thu hộ:</div>
            <div class="pl-3 monney"><fmt:formatNumber value="${hoaDonDTO.tongTien}"
                                                       type="currency" currencySymbol="₫"
                                                       groupingUsed="true"/>
            </div>
        </div>

    </div>

    <div class="footer">
        <div class="signature">
            <p class="m-0 p-0">Chữ ký người nhận</p>
            <p class="m-0 p-0">(Xác nhận hàng nguyên vẹn, không móp, méo)</p>
        </div>
    </div>
</div>

<button id="downloadPdf">Download PDF</button>

<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.min.js"></script>
<%--<script>--%>
<%--    document.getElementById('downloadPdf').addEventListener('click', () => {--%>
<%--        const element = document.querySelector('.container');--%>
<%--        const opt = {--%>
<%--            margin: 1,--%>
<%--            filename: 'phieu_giao_hang.pdf',--%>
<%--            image: { type: 'jpeg', quality: 0.98 },--%>
<%--            html2canvas: { scale: 2 },--%>
<%--            jsPDF: { unit: 'cm', format: 'a4', orientation: 'landscape' }--%>
<%--        };--%>
<%--        html2pdf().from(element).set(opt).save();--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>

