package com.example.java4.request;

import com.example.java4.entities.entity.KichThuoc;
import com.example.java4.entities.entity.MauSac;
import com.example.java4.entities.entity.SanPham;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SanPhamChiTietRequest {

    @NotNull(message = "Không được để trống")
    private Integer id;

    @NotNull(message = "Không được để trống")
    private MauSac idMauSac;

    @NotNull(message = "Không được để trống")
    private KichThuoc idKichThuoc;

    @NotNull(message = "Không được để trống")
    private SanPham idSanPham;

    @NotBlank(message = "Không được để trống")
    private String maSPCT;

    @NotNull(message = "Không được để trống")
    private Integer soLuong;

    @NotNull(message = "Không được để trống")
    private Double donGia;

    @NotNull(message = "Không được để trống")
    private Integer trangThai;
}
