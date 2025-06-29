package com.admin.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.dto.CouponDTO;
import com.admin.api.entity.Coupon;
import com.admin.api.repository.CouponRepository;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    // Lấy tất cả coupons
    public List<CouponDTO> getAllCoupons() {
        return couponRepository.findAll().stream()
                .map(CouponDTO::new)
                .collect(Collectors.toList());
    }

    // Tìm kiếm coupons
    public List<CouponDTO> searchCoupons(String search, Boolean active, String type) {
        List<Coupon> coupons = couponRepository.findAll();
        
        return coupons.stream()
                .filter(coupon -> {
                    boolean matches = true;
                    
                    if (search != null && !search.trim().isEmpty()) {
                        String searchLower = search.toLowerCase();
                        matches = matches && (
                            coupon.getCode().toLowerCase().contains(searchLower) ||
                            coupon.getName().toLowerCase().contains(searchLower) ||
                            (coupon.getDescription() != null && coupon.getDescription().toLowerCase().contains(searchLower))
                        );
                    }
                    
                    if (active != null) {
                        matches = matches && coupon.getActive().equals(active);
                    }
                    
                    if (type != null && !type.trim().isEmpty()) {
                        matches = matches && type.equals(coupon.getType());
                    }
                    
                    return matches;
                })
                .map(CouponDTO::new)
                .collect(Collectors.toList());
    }

    // Lấy coupons active
    public List<CouponDTO> getActiveCoupons() {
        return couponRepository.findByActive(true).stream()
                .map(CouponDTO::new)
                .collect(Collectors.toList());
    }

    // Lấy coupons có thể sử dụng
    public List<CouponDTO> getUsableCoupons() {
        LocalDateTime now = LocalDateTime.now();
        return couponRepository.findByActiveAndStartDateBeforeAndEndDateAfter(true, now, now).stream()
                .filter(coupon -> coupon.getUsageLimit() == null || coupon.getUsedCount() < coupon.getUsageLimit())
                .map(CouponDTO::new)
                .collect(Collectors.toList());
    }

    // Lấy coupon theo ID
    public Optional<CouponDTO> getCouponById(String id) {
        return couponRepository.findById(id)
                .map(CouponDTO::new);
    }

    // Lấy coupon theo code
    public Optional<CouponDTO> getCouponByCode(String code) {
        return couponRepository.findByCode(code)
                .map(CouponDTO::new);
    }

    // Tạo coupon mới
    public CouponDTO createCoupon(CouponDTO couponDTO) {
        // Kiểm tra code đã tồn tại
        if (couponRepository.findByCode(couponDTO.getCode()).isPresent()) {
            throw new RuntimeException("Coupon code already exists");
        }

        Coupon coupon = couponDTO.toEntity();
        coupon.setId(null); // Đảm bảo tạo mới
        coupon.setCreatedAt(LocalDateTime.now());
        coupon.setUpdatedAt(LocalDateTime.now());
        coupon.setUsedCount(0);

        Coupon savedCoupon = couponRepository.save(coupon);
        return new CouponDTO(savedCoupon);
    }

    // Cập nhật coupon
    public CouponDTO updateCoupon(String id, CouponDTO couponDTO) {
        Coupon existingCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        // Kiểm tra code đã tồn tại (ngoại trừ coupon hiện tại)
        Optional<Coupon> duplicateCode = couponRepository.findByCode(couponDTO.getCode());
        if (duplicateCode.isPresent() && !duplicateCode.get().getId().equals(id)) {
            throw new RuntimeException("Coupon code already exists");
        }

        // Cập nhật thông tin
        existingCoupon.setCode(couponDTO.getCode());
        existingCoupon.setName(couponDTO.getName());
        existingCoupon.setDescription(couponDTO.getDescription());
        existingCoupon.setType(couponDTO.getType());
        existingCoupon.setValue(couponDTO.getValue());
        existingCoupon.setMinimumOrderValue(couponDTO.getMinimumOrderValue());
        existingCoupon.setMaximumDiscountAmount(couponDTO.getMaximumDiscountAmount());
        existingCoupon.setUsageLimit(couponDTO.getUsageLimit());
        existingCoupon.setStartDate(couponDTO.getStartDate());
        existingCoupon.setEndDate(couponDTO.getEndDate());
        existingCoupon.setActive(couponDTO.getActive());
        existingCoupon.setUpdatedAt(LocalDateTime.now());

        Coupon savedCoupon = couponRepository.save(existingCoupon);
        return new CouponDTO(savedCoupon);
    }

    // Xóa coupon
    public boolean deleteCoupon(String id) {
        if (couponRepository.existsById(id)) {
            couponRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Toggle active status
    public CouponDTO toggleCouponStatus(String id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        coupon.setActive(!coupon.getActive());
        coupon.setUpdatedAt(LocalDateTime.now());

        Coupon savedCoupon = couponRepository.save(coupon);
        return new CouponDTO(savedCoupon);
    }

    // Sử dụng coupon
    public CouponDTO useCoupon(String code) {
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        if (!coupon.canBeUsed()) {
            throw new RuntimeException("Coupon cannot be used");
        }

        coupon.incrementUsage();
        Coupon savedCoupon = couponRepository.save(coupon);
        return new CouponDTO(savedCoupon);
    }

    // Validate coupon cho order (đơn giản hóa)
    public boolean validateCouponForOrder(String code, Double orderAmount, List<String> productIds, List<String> categoryIds) {
        Optional<Coupon> couponOpt = couponRepository.findByCode(code);
        
        if (!couponOpt.isPresent()) {
            return false;
        }

        Coupon coupon = couponOpt.get();
        
        // Kiểm tra coupon có thể sử dụng
        if (!coupon.canBeUsed()) {
            return false;
        }

        // Kiểm tra minimum order value
        if (coupon.getMinimumOrderValue() != null && !coupon.getMinimumOrderValue().isEmpty()) {
            try {
                double minOrderValue = Double.parseDouble(coupon.getMinimumOrderValue());
                if (orderAmount < minOrderValue) {
                    return false;
                }
            } catch (NumberFormatException e) {
                // Ignore if parsing fails
            }
        }

        return true;
    }
} 