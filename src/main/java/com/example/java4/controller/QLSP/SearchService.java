package com.example.java4.controller.QLSP;
import com.example.java4.entities.*;
import com.example.java4.entities.noMap.ChiTietSanPhamNoMap;
import com.example.java4.repositories.NoMap.SPCTRepoNoMap;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class SearchService {
    @Autowired
    private SPCTRepoNoMap spctRepo;
    public List<ChiTietSanPhamNoMap> searchChiTietSanPham(Map<String, Object> params) {
        return spctRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            params.forEach((key, value) -> {
                if (value != null && !((String)value).isEmpty()) {
                    Path<Object> path = root.get(key);
                    Class<?> type = path.getJavaType();

                    if (type == String.class) {
                        predicates.add(criteriaBuilder.like(root.get(key), "%" + value + "%"));
                    } else if (type == Integer.class) {
                        try {
                            predicates.add(criteriaBuilder.equal(root.get(key), Integer.valueOf((String)value)));
                        } catch (NumberFormatException e) {
                            // Handle the exception if the value is not a valid integer
                        }
                    } else if (type == Long.class) {
                        try {
                            predicates.add(criteriaBuilder.equal(root.get(key), Long.valueOf((String)value)));
                        } catch (NumberFormatException e) {
                            // Handle the exception if the value is not a valid long
                        }
                    } else if (type == BigDecimal.class) {
                        try {
                            predicates.add(criteriaBuilder.equal(root.get(key), new BigDecimal((String)value)));
                        } catch (NumberFormatException e) {
                            // Handle the exception if the value is not a valid BigDecimal
                        }
                    } else if (type == LocalDateTime.class) {
                        try {
                            predicates.add(criteriaBuilder.equal(root.get(key), LocalDateTime.parse((String)value)));
                        } catch (DateTimeParseException e) {
                            // Handle the exception if the value is not a valid LocalDateTime
                        }
                    } else if (type == MauSac.class || type == KichThuoc.class || type == ChatLieu.class || type == KieuTay.class || type == SanPham.class) {
                        predicates.add(criteriaBuilder.equal(root.get(key), value));
                    }
                }
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
