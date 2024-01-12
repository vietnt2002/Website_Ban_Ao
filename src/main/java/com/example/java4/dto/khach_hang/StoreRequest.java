package com.example.java4.dto.khach_hang;
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
    @NotBlank(message = "Do not empty te")
    private String ten;
    @NotBlank(message = "Do not empty sdt")
    private String sdt;
    @NotBlank(message = "Do not empty maKH")
    private String maKH;
    @NotBlank(message = "Do not empty trang thai")
    private String trangThai;
}