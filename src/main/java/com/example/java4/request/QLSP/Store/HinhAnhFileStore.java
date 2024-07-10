package com.example.java4.request.QLSP.Store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HinhAnhFileStore {
     private MultipartFile fileHinhAnh1;
     private MultipartFile fileHinhAnh2;
     private MultipartFile fileHinhAnh3;
}
