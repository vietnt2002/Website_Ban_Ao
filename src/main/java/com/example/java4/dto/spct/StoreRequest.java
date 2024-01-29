package com.example.java4.dto.spct;
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
    @NotBlank(message = "Do not empty idKichThuoc")
    private Integer idKichThuoc;
    @NotBlank(message = "Do not empty idMauSac")
    private Integer idMauSac;
    @NotBlank(message = "Do not empty idSanPham")
    private Integer idSanPham;
    @NotBlank(message = "Do not empty maSPCT")
    private String maSPCT;
    private int soLuong;
    private double donGia;
    private int trangThai;
}