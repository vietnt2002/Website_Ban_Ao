package com.example.java4.dto.nhan_vien;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    @NotBlank(message = "Do not empty id")
    private String id;
    @NotBlank(message = "Do not empty ten")
    private String ten;
    @NotBlank(message = "Do not empty maNV")
    private String maNV;
    @NotBlank(message = "Do not empty ten dang nhap")
    private String tenDangNhap;
    @NotBlank(message = "Do not empty mat khau")
    private String matKhau;
    @NotBlank(message = "Do not empty trang thai")
    private String trangThai;
}