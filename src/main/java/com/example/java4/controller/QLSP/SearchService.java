package com.example.java4.controller.QLSP;
import com.example.java4.entities.*;
import com.example.java4.repositories.SPCTRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class SearchService {
    @Autowired
    private SPCTRepository spctRepo;
    public List<ChiTietSanPham> searchChiTietSanPham(Map<String, Object> params) {
        return spctRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            params.forEach((key, value) -> {
                if (value != null) {
                    if (value instanceof String) {
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), "%" + value + "%"));
                    } else if (value instanceof Integer) {
                        predicates.add(criteriaBuilder.equal(root.get(key), value));
                    } else if (value instanceof BigDecimal) {
                        predicates.add(criteriaBuilder.equal(root.get(key), value));
                    } else if (value instanceof LocalDateTime) {
                        predicates.add(criteriaBuilder.equal(root.get(key), value));
                    } else if (value instanceof MauSac) {
                        predicates.add(criteriaBuilder.equal(root.get("idMauSac").get("id"), ((MauSac) value).getId()));
                    } else if (value instanceof KichThuoc) {
                        predicates.add(criteriaBuilder.equal(root.get("idKichThuoc").get("id"), ((KichThuoc) value).getId()));
                    } else if (value instanceof ChatLieu) {
                        predicates.add(criteriaBuilder.equal(root.get("idChatLieu").get("id"), ((ChatLieu) value).getId()));
                    } else if (value instanceof KieuTay) {
                        predicates.add(criteriaBuilder.equal(root.get("idKieuTay").get("id"), ((KieuTay) value).getId()));
                    } else if (value instanceof SanPham) {
                        predicates.add(criteriaBuilder.equal(root.get("idSanPham").get("id"), ((SanPham) value).getId()));
                    }
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
    public List<ChiTietSanPham> searchChiTietSPCT(Map<String, Object> params) {
        return spctRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            params.forEach((key, value) -> {
                if (value != null) {
                    switch (key) {
                        case "moTa":
                            predicates.add(criteriaBuilder.like(root.get(key), "%" + value + "%"));
                            break;
                        case "soLuong":
                            if (value instanceof Integer) {
                                predicates.add(criteriaBuilder.equal(root.get(key), value));
                            }
                            break;
                        case "giaBanMin":
                            if (value instanceof String) {
                                try {
                                    BigDecimal minPrice = new BigDecimal((String) value);
                                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("giaBan"), minPrice));
                                } catch (NumberFormatException e) {
                                    // Handle the case where the string is not a valid BigDecimal
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case "giaBanMax":
                            if (value instanceof String) {
                                try {
                                    BigDecimal maxPrice = new BigDecimal((String) value);
                                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("giaBan"), maxPrice));
                                } catch (NumberFormatException e) {
                                    // Handle the case where the string is not a valid BigDecimal
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case "ngayTaoAfter":
                            if (value instanceof String) {
                                try {
                                    LocalDateTime dateTime = LocalDateTime.parse((String) value);
                                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("ngayTao"), dateTime));
                                } catch (Exception e) {
                                    // Handle the case where the string is not a valid LocalDateTime
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case "ngayTaoBefore":
                            if (value instanceof String) {
                                try {
                                    LocalDateTime dateTime = LocalDateTime.parse((String) value);
                                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("ngayTao"), dateTime));
                                } catch (Exception e) {
                                    // Handle the case where the string is not a valid LocalDateTime
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case "mauSac":
                            if (value instanceof MauSac) {
                                predicates.add(criteriaBuilder.equal(root.get("idMauSac").get("id"), ((MauSac) value).getId()));
                            }
                            break;
                        case "kichThuoc":
                            if (value instanceof KichThuoc) {
                                predicates.add(criteriaBuilder.equal(root.get("idKichThuoc").get("id"), ((KichThuoc) value).getId()));
                            }
                            break;
                        case "chatLieu":
                            if (value instanceof ChatLieu) {
                                predicates.add(criteriaBuilder.equal(root.get("idChatLieu").get("id"), ((ChatLieu) value).getId()));
                            }
                            break;
                        case "kieuTay":
                            if (value instanceof KieuTay) {
                                predicates.add(criteriaBuilder.equal(root.get("idKieuTay").get("id"), ((KieuTay) value).getId()));
                            }
                            break;
                        case "sanPham":
                            if (value instanceof SanPham) {
                                predicates.add(criteriaBuilder.equal(root.get("idSanPham").get("id"), ((SanPham) value).getId()));
                            }
                            break;
                        // Add more cases as needed
                    }
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
