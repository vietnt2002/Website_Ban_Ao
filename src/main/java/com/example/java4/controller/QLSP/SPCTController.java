package com.example.java4.controller.QLSP;
import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.entities.noMap.ChiTietSanPhamNoMap;
import com.example.java4.repositories.*;
import com.example.java4.repositories.NoMap.SPCTRepoNoMap;
import com.example.java4.request.QLSP.Store.SPCTStore;
import com.example.java4.request.QLSP.Update.SPCTUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Controller
@RequestMapping("chi-tiet-sp")
public class SPCTController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    @Autowired
    KichThuocRepository kichThuocRepo;
    @Autowired
    MauSacRepository mauSacRepo;
    @Autowired
    ChatLieuRepository chatLieuRepo;
    @Autowired
    KieuTayRepository kieuTayRepo;
    @Autowired
    SPCTRepository chiTietSPRepository;
    @Autowired
    SPCTRepoNoMap spctRepoNoMap;
    @Autowired
    private SearchService search;
    public SPCTController() {
    }

    @CrossOrigin
    @GetMapping("/index")
    public ResponseEntity<List<ChiTietSanPham>> index(@RequestParam("page")Optional<Integer> pageParam) {
        int page = pageParam.orElse(1);
        Pageable pageable = PageRequest.of(page-1,20);
        return ResponseEntity.ok(chiTietSPRepository.findByTrangThai(1,pageable).getContent());
    }

    @CrossOrigin
    @GetMapping("/detail-byidsp-stt1/{idSP}")
    public ResponseEntity<List<ChiTietSanPham>> GetIndexByIdSP1(@PathVariable(value = "idSP") String idSP,@RequestParam("page")Optional<Integer> pageParam) {
        int page = pageParam.orElse(1);
        Pageable pageable = PageRequest.of(page-1,20);
        return ResponseEntity.ok(chiTietSPRepository.findByIdSP(1,idSP,pageable).getContent());
    }
    @CrossOrigin
    @GetMapping("/detail-byidsp-all/{idSP}")
    public ResponseEntity<List<ChiTietSanPham>> GetIndexByIdSPAll(@PathVariable(value = "idSP") String idSP,@RequestParam("page")Optional<Integer> pageParam) {
        int page = pageParam.orElse(1);
        Pageable pageable = PageRequest.of(page-1,20);
        return ResponseEntity.ok(chiTietSPRepository.findByIdSPAll(idSP,pageable).getContent());
    }
    @CrossOrigin
    @GetMapping("/get-all")
    public ResponseEntity<List<ChiTietSanPham>> getAll(@RequestParam("page")Optional<Integer> pageParam) {
        int page = pageParam.orElse(1);
        Pageable pageable = PageRequest.of(page-1,20);
        return ResponseEntity.ok(chiTietSPRepository.findAllByPage(pageable).getContent());
    }
    @CrossOrigin
    @GetMapping("count")
    public ResponseEntity<Integer> getCount() {
        return ResponseEntity.ok(chiTietSPRepository.getCount());
    }
    @CrossOrigin
    @GetMapping("count-stt1")
    public ResponseEntity<Integer> getCountstt1() {
        return ResponseEntity.ok(chiTietSPRepository.getCountStt1());
    }
    @CrossOrigin
    @GetMapping("count-stt0")
    public ResponseEntity<Integer> getCountstt0() {
        return ResponseEntity.ok(chiTietSPRepository.getCountStt0());
    }
    @CrossOrigin
    @GetMapping("count-byidsp/{idsp}")
    public ResponseEntity<Integer> getCountByidsp(@PathVariable("idsp") String idSP) {
        return ResponseEntity.ok(chiTietSPRepository.getCountByidsp(idSP));
    }
    @CrossOrigin
    @GetMapping("count-stt1-byidsp/{idsp}")
    public ResponseEntity<Integer> getCountstt1ByIdsp(@PathVariable("idsp") String idSP) {
        return ResponseEntity.ok(chiTietSPRepository.getCountStt1Byidsp(idSP));
    }
    @CrossOrigin
    @GetMapping("count-stt0-byidsp/{idsp}")
    public ResponseEntity<Integer> getCountstt0ByIdsp(@PathVariable("idsp") String idSP) {
        return ResponseEntity.ok(chiTietSPRepository.getCountStt0Byidsp(idSP));
    }
    @CrossOrigin
    @GetMapping("/detail/{id}")
    public ResponseEntity<ChiTietSanPham> getDetail(@PathVariable(value = "id") ChiTietSanPham chiTietSP) {
        return ResponseEntity.ok(chiTietSP);
    }
    @CrossOrigin
    @PostMapping("/update/{id}")
    public ResponseEntity<Boolean> doUpdate(@PathVariable(value = "id") ChiTietSanPhamNoMap chiTietSP,
                                            @RequestBody @Valid SPCTUpdate newChiTietSP,
                                            BindingResult rs) {
        if(rs.hasErrors()){
            System.out.println("update error: "+rs);
            return ResponseEntity.ok(false);
        } else{
            chiTietSP.setIdMauSac(newChiTietSP.getIdMauSac());
            chiTietSP.setIdKichThuoc(newChiTietSP.getIdKichThuoc());
            chiTietSP.setIdChatLieu(newChiTietSP.getIdChatLieu());
            chiTietSP.setIdKieuTay(newChiTietSP.getIdKieuTay());
            chiTietSP.setMoTa(newChiTietSP.getMoTa());
            chiTietSP.setSoLuong(Integer.valueOf(newChiTietSP.getSoLuong()));
            chiTietSP.setGiaNhap(BigDecimal.valueOf(Long.valueOf(newChiTietSP.getGiaNhap())));
            chiTietSP.setGiaBan(BigDecimal.valueOf(Long.valueOf(newChiTietSP.getGiaBan())));
            chiTietSP.setTrangThai(Integer.valueOf(newChiTietSP.getTrangThai()));
            spctRepoNoMap.save(chiTietSP);
            return  ResponseEntity.ok(true);
        }
    }

    @CrossOrigin
    @PostMapping("/enable-status/{id}")
    public ResponseEntity<Integer> enableStatus(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(chiTietSPRepository.enableStt(id));
    }


    @CrossOrigin
    @PostMapping("/disable-status/{id}")
    public ResponseEntity<Integer> disableStatus(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(chiTietSPRepository.enableStt(id));
    }

    @CrossOrigin
    @PostMapping("save")
    public ResponseEntity<Boolean> Store(
            @RequestBody @Valid SPCTStore newChiTietSP,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            System.out.println("temp error: " + result);
            return ResponseEntity.ok(false);
        } else {
            LocalDateTime localNow = LocalDateTime.now();
            ChiTietSanPhamNoMap chiTietSP = new ChiTietSanPhamNoMap();
            chiTietSP.setIdSanPham(newChiTietSP.getIdSp());
            chiTietSP.setIdMauSac(newChiTietSP.getIdMauSac());
            chiTietSP.setIdKichThuoc(newChiTietSP.getIdKichThuoc());
            chiTietSP.setIdChatLieu(newChiTietSP.getIdChatLieu());
            chiTietSP.setIdKieuTay(newChiTietSP.getIdKieuTay());
            chiTietSP.setMoTa(newChiTietSP.getMoTa());
            chiTietSP.setSoLuong(Integer.valueOf(newChiTietSP.getSoLuong()));
            chiTietSP.setGiaBan(BigDecimal.valueOf(Long.valueOf(newChiTietSP.getGiaNhap())));
            chiTietSP.setGiaBan(BigDecimal.valueOf(Long.valueOf(newChiTietSP.getGiaBan())));
            chiTietSP.setTrangThai(Integer.valueOf(newChiTietSP.getTrangThai()));
            chiTietSP.setNgayTao(localNow);
            spctRepoNoMap.save(chiTietSP);
            return ResponseEntity.ok(true);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ChiTietSanPham>> searchChiTietSanPham(@RequestParam Map<String, Object> params) {
        List<ChiTietSanPham> chiTietSanPhams = search.searchChiTietSanPham(params);
        return ResponseEntity.ok(chiTietSanPhams);
    }
}