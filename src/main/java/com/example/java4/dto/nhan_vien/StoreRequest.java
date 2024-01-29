package com.example.java4.dto.nhan_vien;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    private Integer id;
    @NotBlank(message = "Do not empty ten")
    private String ten;
    @NotBlank(message = "Do not empty maNV")
    private String ma;
    @NotBlank(message = "Do not empty ten dang nhap")
    private String tenDangNhap;
    @NotBlank(message = "Do not empty mat khau")
    private String matKhau;
    @NotNull(message = "Do not empty trang thai")
    private Integer trangThai;
}