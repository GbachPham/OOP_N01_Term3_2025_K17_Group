package com.admin.api.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    
    private String orderNumber;
    
    // Customer Information
    private Customer customer;
    
    // Shipping Address
    private ShippingAddress shippingAddress;
    
    // Order Information
    private List<OrderItem> items;
    private Payment payment;
    private List<ProductInfo> productInfo;
    private Double subtotal;
    private Double discountTotal;
    private Double shippingFee;
    private Double total;
    private String status;
    
    @CreatedDate
    private LocalDateTime orderDate;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    // Inner classes
    public static class Customer {
        private String fullName;
        private String phone;
        private String email;
        
        // Getters and Setters
        public String getFullName() {
            return fullName;
        }
        
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
        
        public String getPhone() {
            return phone;
        }
        
        public void setPhone(String phone) {
            this.phone = phone;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
    }
    
    public static class ShippingAddress {
        private String province;
        private String district;
        private String ward;
        private String streetAddress;
        
        // Getters and Setters
        public String getProvince() {
            return province;
        }
        
        public void setProvince(String province) {
            this.province = province;
        }
        
        public String getDistrict() {
            return district;
        }
        
        public void setDistrict(String district) {
            this.district = district;
        }
        
        public String getWard() {
            return ward;
        }
        
        public void setWard(String ward) {
            this.ward = ward;
        }
        
        public String getStreetAddress() {
            return streetAddress;
        }
        
        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }
    }
    
    public static class OrderItem {
        private String productId;
        private String productName;
        private Double basePrice;
        private String variantName;
        private Map<String, String> variantSpecs;
        private Double variantPrice;
        private Integer variantDiscountPercent;
        private String colorName;
        private String colorCode;
        private Double colorPriceAdjustment;
        private Double colorDiscountAdjustment;
        private Integer quantity;
        private Double unitPrice;
        private Double discountedPrice;
        private Double subtotal;
        private String thumbnailUrl;
        
        // Getters and Setters
        public String getProductId() {
            return productId;
        }
        
        public void setProductId(String productId) {
            this.productId = productId;
        }
        
        public String getProductName() {
            return productName;
        }
        
        public void setProductName(String productName) {
            this.productName = productName;
        }
        
        public Double getBasePrice() {
            return basePrice;
        }
        
        public void setBasePrice(Double basePrice) {
            this.basePrice = basePrice;
        }
        
        public String getVariantName() {
            return variantName;
        }
        
        public void setVariantName(String variantName) {
            this.variantName = variantName;
        }
        
        public Map<String, String> getVariantSpecs() {
            return variantSpecs;
        }
        
        public void setVariantSpecs(Map<String, String> variantSpecs) {
            this.variantSpecs = variantSpecs;
        }
        
        public Double getVariantPrice() {
            return variantPrice;
        }
        
        public void setVariantPrice(Double variantPrice) {
            this.variantPrice = variantPrice;
        }
        
        public Integer getVariantDiscountPercent() {
            return variantDiscountPercent;
        }
        
        public void setVariantDiscountPercent(Integer variantDiscountPercent) {
            this.variantDiscountPercent = variantDiscountPercent;
        }
        
        public String getColorName() {
            return colorName;
        }
        
        public void setColorName(String colorName) {
            this.colorName = colorName;
        }
        
        public String getColorCode() {
            return colorCode;
        }
        
        public void setColorCode(String colorCode) {
            this.colorCode = colorCode;
        }
        
        public Double getColorPriceAdjustment() {
            return colorPriceAdjustment;
        }
        
        public void setColorPriceAdjustment(Double colorPriceAdjustment) {
            this.colorPriceAdjustment = colorPriceAdjustment;
        }
        
        public Double getColorDiscountAdjustment() {
            return colorDiscountAdjustment;
        }
        
        public void setColorDiscountAdjustment(Double colorDiscountAdjustment) {
            this.colorDiscountAdjustment = colorDiscountAdjustment;
        }
        
        public Integer getQuantity() {
            return quantity;
        }
        
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
        
        public Double getUnitPrice() {
            return unitPrice;
        }
        
        public void setUnitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
        }
        
        public Double getDiscountedPrice() {
            return discountedPrice;
        }
        
        public void setDiscountedPrice(Double discountedPrice) {
            this.discountedPrice = discountedPrice;
        }
        
        public Double getSubtotal() {
            return subtotal;
        }
        
        public void setSubtotal(Double subtotal) {
            this.subtotal = subtotal;
        }
        
        public String getThumbnailUrl() {
            return thumbnailUrl;
        }
        
        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }
    }
    
    public static class Payment {
        private String method;
        private String status;
        
        // Getters and Setters
        public String getMethod() {
            return method;
        }
        
        public void setMethod(String method) {
            this.method = method;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
    }
    
    public static class ProductInfo {
        private String title;
        private String content;
        
        // Getters and Setters
        public String getTitle() {
            return title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public String getContent() {
            return content;
        }
        
        public void setContent(String content) {
            this.content = content;
        }
    }
    
    // Getters and Setters for Order class
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }
    
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public Payment getPayment() {
        return payment;
    }
    
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public List<ProductInfo> getProductInfo() {
        return productInfo;
    }
    
    public void setProductInfo(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }
    
    public Double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    public Double getDiscountTotal() {
        return discountTotal;
    }
    
    public void setDiscountTotal(Double discountTotal) {
        this.discountTotal = discountTotal;
    }
    
    public Double getShippingFee() {
        return shippingFee;
    }
    
    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 