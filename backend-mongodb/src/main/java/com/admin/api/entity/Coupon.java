package com.admin.api.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coupons")
public class Coupon {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String code;
    
    private String name;
    private String description;
    private String type; // PERCENTAGE, FIXED_AMOUNT
    private String value; // Giá trị giảm giá (% hoặc số tiền cố định)
    private String minimumOrderValue; // Đơn hàng tối thiểu để áp dụng
    private String maximumDiscountAmount; // Số tiền giảm tối đa (cho % discount)
    private Integer usageLimit; // Giới hạn số lần sử dụng
    private Integer usedCount; // Số lần đã sử dụng
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Coupon() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.usedCount = 0;
        this.active = true;
    }

    public Coupon(String code, String name, String type, String value) {
        this();
        this.code = code;
        this.name = name;
        this.type = type;
        this.value = value;
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

    // Helper methods
    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now();
        return active && 
               startDate != null && startDate.isBefore(now) && 
               endDate != null && endDate.isAfter(now);
    }

    public boolean canBeUsed() {
        return isValid() && (usageLimit == null || usedCount < usageLimit);
    }

    public void incrementUsage() {
        this.usedCount++;
        this.updatedAt = LocalDateTime.now();
    }
} 