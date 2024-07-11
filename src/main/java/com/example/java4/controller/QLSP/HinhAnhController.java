package com.example.java4.controller.QLSP;
import com.example.java4.entities.HinhAnh;
import com.example.java4.repositories.HinhAnhRepository;
import com.example.java4.repositories.SPCTRepository;
import com.example.java4.request.QLSP.Store.HinhAnhStore;
import com.example.java4.request.QLSP.Update.HinhAnhUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("hinh-anh")
public class HinhAnhController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    @Autowired
    HinhAnhRepository hinhAnhRepo;
    @Autowired
    SPCTRepository spctRepo;
    @Value("${file.upload-dir}")
    private String uploadDir;
    public HinhAnhController() {
    }

    @CrossOrigin
    @GetMapping("/index")
    public ResponseEntity<List<HinhAnh>> index(@RequestParam("page") Optional<Integer> pageParam){
        int page = pageParam.orElse(1);
        Pageable pageale = PageRequest.of(page-1, 20);
        return ResponseEntity.ok(hinhAnhRepo.findByTrangThai(1,pageale).getContent());
    }

    @CrossOrigin
    @GetMapping("/get-all")
    public ResponseEntity<List<HinhAnh>> getAll(@RequestParam("page") Optional<Integer> pageParam){
        int page = pageParam.orElse(1);
        Pageable pageale = PageRequest.of(page-1, 20);
        return ResponseEntity.ok(hinhAnhRepo.findAllByPage(pageale).getContent());
    }
    @CrossOrigin
    @GetMapping("count")
    public ResponseEntity<Integer> getCount() {
        return ResponseEntity.ok(hinhAnhRepo.getCount());
    }
    @CrossOrigin
    @GetMapping("count-stt1")
    public ResponseEntity<Integer> getCountstt1() {
        return ResponseEntity.ok(hinhAnhRepo.getCountStt1());
    }
    @CrossOrigin
    @GetMapping("count-stt0")
    public ResponseEntity<Integer> getCountstt0() {
        return ResponseEntity.ok(hinhAnhRepo.getCountStt0());
    }
    @CrossOrigin
    @GetMapping("/detail/{id}")
    public ResponseEntity<HinhAnh> getDetail(@PathVariable(value = "id") HinhAnh hinhAnh){
        return ResponseEntity.ok(hinhAnh);
    }
    @GetMapping("/detail-byidspct/{idspct}")
    public ResponseEntity<HinhAnh> getDetailByIdspct(@PathVariable(value = "idspct") String idspct){
        HinhAnh hinhAnh = hinhAnhRepo.findByIdCTSP(idspct);
        return ResponseEntity.ok(hinhAnh);
    }
    @CrossOrigin
    @PostMapping("/update/{id}")
    public ResponseEntity<Boolean> doUpdate(@RequestBody @Valid HinhAnhUpdate newHinhAnh, BindingResult result,
                                            @PathVariable(value ="id") HinhAnh hinhAnh){
        if (result.hasErrors()) {
            System.out.println("error temp:" + result);
            return ResponseEntity.ok(false);
        }
        else{
            hinhAnh.setHinhAnh1(newHinhAnh.getHinhAnh1());
            hinhAnh.setHinhAnh2(newHinhAnh.getHinhAnh2());
            hinhAnh.setHinhAnh3(newHinhAnh.getHinhAnh3());
            hinhAnh.setNgayTao(newHinhAnh.getNgayTao());
            hinhAnh.setTrangThai(newHinhAnh.getTrangThai());
            hinhAnhRepo.save(hinhAnh);
            return ResponseEntity.ok(true);
        }
    }

    @CrossOrigin
    @PostMapping("/update-byidctsp/{idctsp}")
    public ResponseEntity<Boolean> doUpdateByIdCTSP(@RequestBody @Valid HinhAnhUpdate newHinhAnh,@PathVariable("idctsp") String idctsp, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("error temp:" + result);
            return ResponseEntity.ok(false);
        }
        else{
            if(!hinhAnhRepo.findByIdCTSP(idctsp).equals(null)){
                HinhAnh hinhAnh  = hinhAnhRepo.findByIdCTSP(idctsp);
                hinhAnh.setHinhAnh1(newHinhAnh.getHinhAnh1());
                hinhAnh.setHinhAnh2(newHinhAnh.getHinhAnh2());
                hinhAnh.setHinhAnh3(newHinhAnh.getHinhAnh3());
                hinhAnh.setNgayTao(newHinhAnh.getNgayTao());
                hinhAnh.setTrangThai(newHinhAnh.getTrangThai());
                hinhAnhRepo.save(hinhAnh);
            }
            else{
                HinhAnh hinhAnh = new HinhAnh();
                hinhAnh.setIdCTSP(spctRepo.findByIdCTSP(idctsp));
                hinhAnh.setHinhAnh1(newHinhAnh.getHinhAnh1());
                hinhAnh.setHinhAnh2(newHinhAnh.getHinhAnh2());
                hinhAnh.setHinhAnh3(newHinhAnh.getHinhAnh3());
                hinhAnh.setNgayTao(newHinhAnh.getNgayTao());
                hinhAnh.setTrangThai(newHinhAnh.getTrangThai());
                hinhAnhRepo.save(hinhAnh);
            }
            return ResponseEntity.ok(true);
        }
    }


    @CrossOrigin
    @PostMapping("/enable-status/{id}")
    public ResponseEntity<Integer> enableStatus(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(hinhAnhRepo.enableStt(id));
    }

    @CrossOrigin
    @PostMapping("/disable-status/{id}")
    public ResponseEntity<Integer> disableStatus(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(hinhAnhRepo.enableStt(id));
    }

    @CrossOrigin
    @PostMapping("save")
    public ResponseEntity<Boolean> save(
            @RequestBody @Valid HinhAnhStore newHinhAnh,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            System.out.println("error temp: " + result);
            return ResponseEntity.ok(false);
        }
        else{
            LocalDateTime localNow = LocalDateTime.now();
            HinhAnh hinhAnh = new HinhAnh();
            hinhAnh.setIdCTSP(spctRepo.findByIdCTSP(newHinhAnh.getIdSPCT()));
            hinhAnh.setHinhAnh1(newHinhAnh.getHinhAnh1());
            hinhAnh.setHinhAnh2(newHinhAnh.getHinhAnh2());
            hinhAnh.setHinhAnh3(newHinhAnh.getHinhAnh3());
            hinhAnh.setNgayTao(newHinhAnh.getNgayTao());
            hinhAnh.setTrangThai(newHinhAnh.getTrangThai());
            hinhAnhRepo.save(hinhAnh);
            return ResponseEntity.ok(true);
        }
    }

    @CrossOrigin
    @PostMapping("/upload")
    public void saveUploadFile(
            @RequestParam("hinhAnh1File") MultipartFile newHinhAnh1File,
            @RequestParam("hinhAnh2File") MultipartFile newHinhAnh2File,
            @RequestParam("hinhAnh3File") MultipartFile newHinhAnh3File,
            Model model
    ) {
        if (newHinhAnh1File.isEmpty()) {
            model.addAttribute("message", "Vui lòng chọn hình ảnh 1.");
        }
        if(newHinhAnh2File.isEmpty()){
            model.addAttribute("message", "Vui lòng chọn hình ảnh 2.");
        }
        if(newHinhAnh3File.isEmpty()){
            model.addAttribute("message", "Vui lòng chọn hình ảnh 3.");
        }
        try {
            byte[] bytesHinhAnh1 = newHinhAnh1File.getBytes();
            byte[] bytesHinhAnh2 = newHinhAnh2File.getBytes();
            byte[] bytesHinhAnh3 = newHinhAnh3File.getBytes();
            Path path1 = Paths.get(uploadDir + File.separator +newHinhAnh1File.getOriginalFilename());
            Path path2 = Paths.get(uploadDir + File.separator +newHinhAnh2File.getOriginalFilename());
            Path path3 = Paths.get(uploadDir + File.separator +newHinhAnh3File.getOriginalFilename());
            Files.write(path1, bytesHinhAnh1);
            Files.write(path2, bytesHinhAnh2);
            Files.write(path3, bytesHinhAnh3);
            model.addAttribute("message", "You successfully uploaded");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload");
        }
    }
}