package com.example.java4.restcontrollers;

import com.example.java4.repositories.ThongKeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thong-ke")
public class ThongKeRestController {

    @Autowired
    ThongKeRepository thongKeRepository;


    @GetMapping("/thang-hien-tai")
    public ResponseEntity<List<Map<String, Object>>> getCurrentMonthStatistics() {
        LocalDateTime now = LocalDateTime.now();
        List<Object[]> results = thongKeRepository.getCurrentMonthStatistics(now);
        List<Map<String, Object>> statistics = mapResultsToStatistics(results);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/ngay-hien-tai")
    public ResponseEntity<List<Map<String, Object>>> getCurrentDayStatistics() {
        LocalDateTime now = LocalDateTime.now();
        List<Object[]> results = thongKeRepository.getCurrentDailyMonthStatistics(now);
        List<Map<String, Object>> statistics = mapResultsToStatistics(results);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/tuan-hien-tai")
    public ResponseEntity<List<Map<String, Object>>> getCurrentWeekStatistics() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate startDate = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate();
        LocalDate endDate = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).toLocalDate();
        List<Object[]> results = thongKeRepository.getCurrentWeeklyStatistics(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
        List<Map<String, Object>> statistics = mapResultsToStatistics(results);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/nam-hien-tai")
    public ResponseEntity<List<Map<String, Object>>> getCurrentYearStatistics() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate startDate = now.with(TemporalAdjusters.firstDayOfYear()).toLocalDate();
        LocalDate endDate = now.with(TemporalAdjusters.lastDayOfYear()).toLocalDate();
        List<Object[]> results = thongKeRepository.getCurrentYearlyStatistics(startDate.atStartOfDay());
        List<Map<String, Object>> statistics = mapResultsToStatistics(results);
        return ResponseEntity.ok(statistics);
    }

    private List<Map<String, Object>> mapResultsToStatistics(List<Object[]> results) {
        List<Map<String, Object>> statistics = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("day", result[0]);
            map.put("soLuongHoaDon", result[1]);
            map.put("soLuongSanPham", result[2]);
            statistics.add(map);
        }
        return statistics;
    }


    @GetMapping("/tu-ngay-den-ngay")
    public ResponseEntity<List<Map<String, Object>>> getCustomDateRangeStatistics(
            @RequestParam String startDateStr, @RequestParam String endDateStr) {
        try {
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            List<Object[]> results = thongKeRepository.getCustomDateRangeStatistics(startDate, endDate);
            List<Map<String, Object>> statistics = mapResultsToStatistics(results);
            return ResponseEntity.ok(statistics);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/tuy-chinh")
    public ResponseEntity<Map<String, Object>> getCustomStatistics(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {

        if (startDateStr == null || startDateStr.trim().isEmpty() || endDateStr == null || endDateStr.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Start date and end date are required."));
        }

        LocalDate startDate;
        LocalDate endDate;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            // Parse start and end dates
            startDate = LocalDate.parse(startDateStr);
            endDate = LocalDate.parse(endDateStr);

            // Convert LocalDate to LocalDateTime with default times
            startDateTime = startDate.atStartOfDay(); // Start of the day
            endDateTime = endDate.atTime(23, 59, 59); // End of the day

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid date format."));
        }

        if (startDateTime.isAfter(endDateTime)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Start date cannot be after end date."));
        }

        List<Object[]> results = thongKeRepository.getCustomStatistics(startDateTime, endDateTime);
        List<Map<String, Object>> statistics = mapResultsCustomToStatistics(results);

        return ResponseEntity.ok(Map.of("data", statistics));
    }



    private List<Map<String, Object>> mapResultsCustomToStatistics(List<Object[]> results) {
        List<Map<String, Object>> statistics = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("doanhThu", result[0]);
            map.put("soLuongSanPhamDaBan", result[1]);
            map.put("donHangThanhCong", result[2]);
            map.put("donHangDaHuy", result[3]);
            statistics.add(map);
        }
        return statistics;
    }
}
