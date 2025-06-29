package com.admin.api.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductDTO {
    
    private String id;
    
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 2, max = 200, message = "Tên sản phẩm phải từ 2-200 ký tự")
    private String name;
    
    @Size(max = 1000, message = "Mô tả không được quá 1000 ký tự")
    private String description;
    
    @Min(value = 0, message = "Giá sản phẩm không được âm")
    private Double price;
    
    private String sku;
    
    private Boolean active = true;
    
    private String imageId;
    
    // Multiple categories support
    private List<String> categoryIds = new ArrayList<>();
    
    private List<String> categoryNames = new ArrayList<>(); // For display
    
    // Product variants
    private List<ProductVariantDTO> variants = new ArrayList<>();
    
    // Product tags
    private List<ProductTagDTO> tags = new ArrayList<>();
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private Integer variantCount = 0; // Calculated field
    
    // Constructors
    public ProductDTO() {}
    
    public ProductDTO(String id, String name, String description, Double price, String sku,
                     Boolean active, String imageId, List<String> categoryIds, List<String> categoryNames,
                     List<ProductVariantDTO> variants, List<ProductTagDTO> tags,
                     LocalDateTime createdAt, LocalDateTime updatedAt, Integer variantCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.active = active;
        this.imageId = imageId;
        this.categoryIds = categoryIds != null ? categoryIds : new ArrayList<>();
        this.categoryNames = categoryNames != null ? categoryNames : new ArrayList<>();
        this.variants = variants != null ? variants : new ArrayList<>();
        this.tags = tags != null ? tags : new ArrayList<>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.variantCount = variantCount;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getSku() {
        return sku;
    }
    
    public void setSku(String sku) {
        this.sku = sku;
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
    
    public List<String> getCategoryIds() {
        return categoryIds;
    }
    
    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds != null ? categoryIds : new ArrayList<>();
    }
    
    public List<String> getCategoryNames() {
        return categoryNames;
    }
    
    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames != null ? categoryNames : new ArrayList<>();
    }
    
    public List<ProductVariantDTO> getVariants() {
        return variants;
    }
    
    public void setVariants(List<ProductVariantDTO> variants) {
        this.variants = variants != null ? variants : new ArrayList<>();
    }
    
    public List<ProductTagDTO> getTags() {
        return tags;
    }
    
    public void setTags(List<ProductTagDTO> tags) {
        this.tags = tags != null ? tags : new ArrayList<>();
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
    
    public Integer getVariantCount() {
        return variantCount;
    }
    
    public void setVariantCount(Integer variantCount) {
        this.variantCount = variantCount;
    }
} 