package com.example.java4.controller.QLSP;
import com.example.java4.entities.HinhAnh;
import com.example.java4.entities.SanPham;
import com.example.java4.repositories.HinhAnhRepository;
import com.example.java4.repositories.SPCTRepository;
import com.example.java4.repositories.SanPhamRepository;
import com.example.java4.request.QLSP.Store.HinhAnhStore;
import com.example.java4.request.QLSP.Store.SanPhamStore;
import com.example.java4.request.QLSP.Update.HinhAnhUpdate;
import com.example.java4.request.QLSP.Update.SanPhamUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
            hinhAnh.setHinhAnh4(newHinhAnh.getHinhAnh4());
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
            HinhAnh hinhAnh  = hinhAnhRepo.findByIdCTSP(idctsp);
            hinhAnh.setHinhAnh1(newHinhAnh.getHinhAnh1());
            hinhAnh.setHinhAnh2(newHinhAnh.getHinhAnh2());
            hinhAnh.setHinhAnh3(newHinhAnh.getHinhAnh3());
            hinhAnh.setHinhAnh4(newHinhAnh.getHinhAnh4());
            hinhAnh.setNgayTao(newHinhAnh.getNgayTao());
            hinhAnh.setTrangThai(newHinhAnh.getTrangThai());
            hinhAnhRepo.save(hinhAnh);
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
            hinhAnh.setHinhAnh4(newHinhAnh.getHinhAnh4());
            hinhAnh.setNgayTao(newHinhAnh.getNgayTao());
            hinhAnh.setTrangThai(newHinhAnh.getTrangThai());
            hinhAnhRepo.save(hinhAnh);
            return ResponseEntity.ok(true);
        }
    }
}