package com.example.java4.request.req_tai;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangDTO {
    @NotEmpty(message = "Vui lòng nhập tài khoản.")
    private String taiKhoan;
    @NotEmpty(message = "Vui lòng nhập mật khẩu.")
    private String matKhau;
}
