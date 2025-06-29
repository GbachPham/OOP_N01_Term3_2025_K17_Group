package com.admin.api.dto;

import java.time.LocalDateTime;

import com.admin.api.entity.Coupon;

public class CouponDTO {
    
    private String id;
    private String code;
    private String name;
    private String description;
    private String type; // PERCENTAGE, FIXED_AMOUNT
    private String value;
    private String minimumOrderValue;
    private String maximumDiscountAmount;
    private Integer usageLimit;
    private Integer usedCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public CouponDTO() {}

    // Convert from entity
    public CouponDTO(Coupon coupon) {
        this.id = coupon.getId();
        this.code = coupon.getCode();
        this.name = coupon.getName();
        this.description = coupon.getDescription();
        this.type = coupon.getType();
        this.value = coupon.getValue();
        this.minimumOrderValue = coupon.getMinimumOrderValue();
        this.maximumDiscountAmount = coupon.getMaximumDiscountAmount();
        this.usageLimit = coupon.getUsageLimit();
        this.usedCount = coupon.getUsedCount();
        this.startDate = coupon.getStartDate();
        this.endDate = coupon.getEndDate();
        this.active = coupon.getActive();
        this.createdAt = coupon.getCreatedAt();
        this.updatedAt = coupon.getUpdatedAt();
    }

    // Convert to entity
    public Coupon toEntity() {
        Coupon coupon = new Coupon();
        coupon.setId(this.id);
        coupon.setCode(this.code);
        coupon.setName(this.name);
        coupon.setDescription(this.description);
        coupon.setType(this.type);
        coupon.setValue(this.value);
        coupon.setMinimumOrderValue(this.minimumOrderValue);
        coupon.setMaximumDiscountAmount(this.maximumDiscountAmount);
        coupon.setUsageLimit(this.usageLimit);
        coupon.setUsedCount(this.usedCount);
        coupon.setStartDate(this.startDate);
        coupon.setEndDate(this.endDate);
        coupon.setActive(this.active);
        coupon.setCreatedAt(this.createdAt);
        coupon.setUpdatedAt(this.updatedAt);
        return coupon;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMinimumOrderValue() {
        return minimumOrderValue;
    }

    public void setMinimumOrderValue(String minimumOrderValue) {
        this.minimumOrderValue = minimumOrderValue;
    }

    public String getMaximumDiscountAmount() {
        return maximumDiscountAmount;
    }

    public void setMaximumDiscountAmount(String maximumDiscountAmount) {
        this.maximumDiscountAmount = maximumDiscountAmount;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 