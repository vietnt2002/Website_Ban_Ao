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
     MultipartFile fileHinhAnh1;
     MultipartFile fileHinhAnh2;
     MultipartFile fileHinhAnh3;
}
