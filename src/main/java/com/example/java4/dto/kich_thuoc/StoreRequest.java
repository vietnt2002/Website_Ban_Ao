package com.example.java4.dto.kich_thuoc;
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
    @NotBlank(message = "Do not empty ma")
    private String ma;
    @NotBlank(message = "Do not empty ten")
    private String ten;
    @NotNull(message = "Do not empty trang thai")
    private Integer trangThai;
}