package com.admin.api.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    
    @Id
    private String id;
    
    @Indexed
    private String name;
    
    private String description;
    
    private Double price;
    
    @Indexed(unique = true)
    private String sku;
    
    private Boolean active = true;
    
    private String imageId;
    
    // Multiple categories support
    private List<String> categoryIds = new ArrayList<>();
    
    // Product variants
    private List<ProductVariant> variants = new ArrayList<>();
    
    // Product tags for promotions, features, etc.
    private List<ProductTag> tags = new ArrayList<>();
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    // Constructors
    public Product() {}
    
    public Product(String name, String description, Double price, String sku, 
                  Boolean active, String imageId, List<String> categoryIds) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.active = active;
        this.imageId = imageId;
        this.categoryIds = categoryIds != null ? categoryIds : new ArrayList<>();
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
    
    public List<ProductVariant> getVariants() {
        return variants;
    }
    
    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants != null ? variants : new ArrayList<>();
    }
    
    public List<ProductTag> getTags() {
        return tags;
    }
    
    public void setTags(List<ProductTag> tags) {
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
} 