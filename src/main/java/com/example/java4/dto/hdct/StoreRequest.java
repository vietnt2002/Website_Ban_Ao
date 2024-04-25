package com.example.java4.dto.hdct;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    private Integer id;
    @NotNull(message = "do not empty this field")
    private String idHoaDon;
    @NotNull(message = "do not empty this field")
    private String idSPCT;
    @NotNull(message = "do not empty this field")
    private int soLuong;
    @NotNull(message = "do not empty this field")
    private int donGia;
    private Timestamp thoiGian;
    @Column(name="TrangThai")
    private Integer trangThai;
}