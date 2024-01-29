package com.example.java4.dto.spct;
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
    @NotNull(message = "Do not empty idKichThuoc")
    private Integer idKichThuoc;
    @NotNull(message = "Do not empty idMauSac")
    private Integer idMauSac;
    @NotNull(message = "Do not empty idSanPham")
    private Integer idSanPham;
    @NotBlank(message = "Do not empty maSPCT")
    private String maSPCT;
    private int soLuong;
    private double donGia;
    private int trangThai;
}