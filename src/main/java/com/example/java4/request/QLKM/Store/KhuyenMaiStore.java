package com.example.java4.request.QLKM.Store;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhuyenMaiStore {
    private String id;
    @NotEmpty
    private String ma;
    @NotEmpty
    private String ten;
    @NotEmpty
    private Date ngayBatDau;
    @NotEmpty
    private Date ngayKetThuc;
    @NotEmpty
    private BigDecimal soTienGiam;
    @NotEmpty
    private Integer soLuong;
    @NotEmpty
    private Integer trangThai;
}
