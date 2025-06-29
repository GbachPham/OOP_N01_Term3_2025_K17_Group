package com.admin.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.api.entity.Coupon;

@Repository
public interface CouponRepository extends MongoRepository<Coupon, String> {
    
    // Tìm kiếm theo code (unique)
    Optional<Coupon> findByCode(String code);
    
    // Tìm kiếm theo trạng thái active
    List<Coupon> findByActive(boolean active);
    
    // Tìm kiếm theo name (có thể tìm một phần)
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Coupon> findByNameContainingIgnoreCase(String name);
    
    // Tìm kiếm theo code (có thể tìm một phần)
    @Query("{'code': {$regex: ?0, $options: 'i'}}")
    List<Coupon> findByCodeContainingIgnoreCase(String code);
    
    // Tìm kiếm coupon active và trong thời gian hợp lệ
    List<Coupon> findByActiveAndStartDateBeforeAndEndDateAfter(Boolean active, LocalDateTime startDate, LocalDateTime endDate);
    
    // Tìm kiếm theo loại coupon
    List<Coupon> findByType(String type);
    
    // Tìm kiếm coupon hết hạn
    @Query("{'endDate': {$lt: ?0}}")
    List<Coupon> findExpiredCoupons(LocalDateTime currentTime);
    
    // Tìm kiếm coupon sắp hết hạn (trong X ngày tới)
    @Query("{'endDate': {$gte: ?0, $lte: ?1}}")
    List<Coupon> findExpiringCoupons(LocalDateTime fromDate, LocalDateTime toDate);
} 