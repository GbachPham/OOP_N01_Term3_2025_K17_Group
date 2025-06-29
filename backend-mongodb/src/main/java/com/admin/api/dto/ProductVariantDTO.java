package com.admin.api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductVariantDTO {
    
    private String id;
    
    @NotBlank(message = "Tên biến thể không được để trống")
    private String name;
    
    private String color;
    
    private String size;
    
    private String material;
    
    private String specifications;
    
    private String sku;
    
    @Min(value = 0, message = "Giá bổ sung không được âm")
    private Double additionalPrice = 0.0;
    
    @Min(value = 0, message = "Số lượng tồn kho không được âm")
    private Integer stock = 0;
    
    private Boolean active = true;
    
    private String imageId;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Constructors
    public ProductVariantDTO() {}
    
    public ProductVariantDTO(String id, String name, String color, String size, String material,
                            String specifications, String sku, Double additionalPrice, Integer stock,
                            Boolean active, String imageId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.material = material;
        this.specifications = specifications;
        this.sku = sku;
        this.additionalPrice = additionalPrice;
        this.stock = stock;
        this.active = active;
        this.imageId = imageId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    
    public String getSpecifications() {
        return specifications;
    }
    
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
    
    public String getSku() {
        return sku;
    }
    
    public void setSku(String sku) {
        this.sku = sku;
    }
    
    public Double getAdditionalPrice() {
        return additionalPrice;
    }
    
    public void setAdditionalPrice(Double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public String getImageId() {
        return imageId;
    }
    
    public void setImageId(String imageId) {
        this.imageId = imageId;
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