package com.admin.api.entity;

public class ProductTag {
    
    private String type; // "discount", "hot", "new", "bestseller", "featured"
    private String value; // Giá trị của tag (ví dụ: "10%" cho discount)
    private String color; // Màu hiển thị của tag
    private Boolean active = true;
    
    // Constructors
    public ProductTag() {}
    
    public ProductTag(String type, String value, String color, Boolean active) {
        this.type = type;
        this.value = value;
        this.color = color;
        this.active = active;
    }
    
    // Getters and Setters
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
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
} 