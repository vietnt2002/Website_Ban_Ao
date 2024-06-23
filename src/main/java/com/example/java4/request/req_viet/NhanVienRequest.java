package com.example.java4.request.req_viet;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienRequest {

    private String hoTen;

    private Integer gioiTinh;

    private String sdt;

    private String taiKhoan;

    private String matKhau;

    private String anhDaiDien;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private Integer trangThai;
}
