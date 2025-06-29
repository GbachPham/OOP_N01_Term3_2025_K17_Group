package com.admin.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.api.dto.CouponDTO;
import com.admin.api.service.CouponService;

@RestController
@RequestMapping("/coupons")
@CrossOrigin(origins = "http://localhost:4200")
public class CouponController {

    @Autowired
    private CouponService couponService;

    // Lấy tất cả coupons với tìm kiếm và lọc
    @GetMapping
    public ResponseEntity<List<CouponDTO>> getAllCoupons(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String type) {
        
        List<CouponDTO> coupons = couponService.searchCoupons(search, active, type);
        return ResponseEntity.ok(coupons);
    }

    // Lấy chỉ coupons active
    @GetMapping("/active")
    public ResponseEntity<List<CouponDTO>> getActiveCoupons() {
        List<CouponDTO> coupons = couponService.getActiveCoupons();
        return ResponseEntity.ok(coupons);
    }

    // Lấy coupons có thể sử dụng
    @GetMapping("/usable")
    public ResponseEntity<List<CouponDTO>> getUsableCoupons() {
        List<CouponDTO> coupons = couponService.getUsableCoupons();
        return ResponseEntity.ok(coupons);
    }

    // Lấy coupon theo ID
    @GetMapping("/{id}")
    public ResponseEntity<CouponDTO> getCouponById(@PathVariable String id) {
        Optional<CouponDTO> coupon = couponService.getCouponById(id);
        
        if (coupon.isPresent()) {
            return ResponseEntity.ok(coupon.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy coupon theo code
    @GetMapping("/code/{code}")
    public ResponseEntity<CouponDTO> getCouponByCode(@PathVariable String code) {
        Optional<CouponDTO> coupon = couponService.getCouponByCode(code);
        
        if (coupon.isPresent()) {
            return ResponseEntity.ok(coupon.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Tạo coupon mới
    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody CouponDTO couponDTO) {
        try {
            CouponDTO savedCoupon = couponService.createCoupon(couponDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCoupon);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật coupon
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoupon(@PathVariable String id, @RequestBody CouponDTO couponDTO) {
        try {
            CouponDTO updatedCoupon = couponService.updateCoupon(id, couponDTO);
            return ResponseEntity.ok(updatedCoupon);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa coupon
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable String id) {
        boolean deleted = couponService.deleteCoupon(id);
        
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Coupon deleted successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Toggle active status
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<?> toggleCouponStatus(@PathVariable String id) {
        try {
            CouponDTO updatedCoupon = couponService.toggleCouponStatus(id);
            return ResponseEntity.ok(updatedCoupon);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Sử dụng coupon (tăng usage count)
    @PostMapping("/{code}/use")
    public ResponseEntity<?> useCoupon(@PathVariable String code) {
        try {
            CouponDTO updatedCoupon = couponService.useCoupon(code);
            return ResponseEntity.ok(updatedCoupon);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Validate coupon cho order
    @PostMapping("/validate")
    public ResponseEntity<?> validateCoupon(@RequestBody Map<String, Object> request) {
        try {
            String code = (String) request.get("code");
            Double orderAmount = ((Number) request.get("orderAmount")).doubleValue();
            @SuppressWarnings("unchecked")
            List<String> productIds = (List<String>) request.get("productIds");
            @SuppressWarnings("unchecked")
            List<String> categoryIds = (List<String>) request.get("categoryIds");

            boolean isValid = couponService.validateCouponForOrder(code, orderAmount, productIds, categoryIds);
            
            return ResponseEntity.ok(Map.of(
                "valid", isValid,
                "message", isValid ? "Coupon is valid" : "Coupon is not valid for this order"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "valid", false,
                "error", e.getMessage()
            ));
        }
    }

    // Lấy thống kê coupons
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getCouponStats() {
        List<CouponDTO> allCoupons = couponService.getAllCoupons();
        List<CouponDTO> activeCoupons = couponService.getActiveCoupons();
        List<CouponDTO> usableCoupons = couponService.getUsableCoupons();

        long totalUsage = allCoupons.stream()
                .mapToLong(CouponDTO::getUsedCount)
                .sum();

        Map<String, Object> stats = Map.of(
            "totalCoupons", allCoupons.size(),
            "activeCoupons", activeCoupons.size(),
            "usableCoupons", usableCoupons.size(),
            "totalUsage", totalUsage
        );

        return ResponseEntity.ok(stats);
    }
} 