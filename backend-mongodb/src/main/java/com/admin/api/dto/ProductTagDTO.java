package com.admin.api.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductTagDTO {
    
    @NotBlank(message = "Loại tag không được để trống")
    private String type;
    
    private String value;
    
    private String color;
    
    private Boolean active = true;
    
    // Constructors
    public ProductTagDTO() {}
    
    public ProductTagDTO(String type, String value, String color, Boolean active) {
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