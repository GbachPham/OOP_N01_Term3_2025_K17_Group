package com.admin.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.api.entity.Order;
import com.admin.api.repository.OrderRepository;
import com.admin.api.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://localhost:8080", "http://127.0.0.1:5500", "http://127.0.0.1:5501", "http://127.0.0.1:5502"})
public class OrderController {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private OrderService orderService;
    
    // Test endpoint to check database info
    @GetMapping("/db-info")
    public ResponseEntity<String> getDatabaseInfo() {
        try {
            String dbName = mongoTemplate.getDb().getName();
            return ResponseEntity.ok("Connected to database: " + dbName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error getting database info: " + e.getMessage());
        }
    }
    
    // Test endpoint
    @GetMapping("/test")
    public ResponseEntity<String> testOrders() {
        return ResponseEntity.ok("OrderController is working!");
    }
    
    // API cho user - Tạo đơn hàng mới
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            // Validate required fields
            if (order.getCustomer() == null || order.getCustomer().getFullName() == null || order.getCustomer().getFullName().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (order.getCustomer().getPhone() == null || order.getCustomer().getPhone().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (order.getShippingAddress() == null || order.getShippingAddress().getStreetAddress() == null || order.getShippingAddress().getStreetAddress().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (order.getItems() == null || order.getItems().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            Order savedOrder = orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("X-Error-Message", e.getMessage())
                .build();
        }
    }
    
    // API cho admin - Lấy tất cả đơn hàng
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String customerName) {
        try {
            List<Order> orders;
            
            if (status != null && !status.trim().isEmpty()) {
                orders = orderRepository.findByStatus(status.toLowerCase());
            } else if (customerName != null && !customerName.trim().isEmpty()) {
                orders = orderRepository.findByCustomerFullNameContainingIgnoreCase(customerName);
            } else {
                orders = orderRepository.findAll();
            }
            
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // API cho admin - Lấy chi tiết đơn hàng
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            if (order.isPresent()) {
                return ResponseEntity.ok(order.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // API cho admin - Cập nhật trạng thái đơn hàng
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable String id, 
            @RequestParam String status) {
        try {
            Order updatedOrder = orderService.updateOrderStatus(id, status);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("X-Error-Message", e.getMessage())
                .build();
        }
    }
    
    // API cho user - Tìm đơn hàng theo số điện thoại
    @GetMapping("/customer/{phone}")
    public ResponseEntity<List<Order>> getOrdersByPhone(@PathVariable String phone) {
        try {
            List<Order> orders = orderRepository.findByCustomerPhone(phone);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // API cho admin - Thống kê đơn hàng
    @GetMapping("/stats")
    public ResponseEntity<OrderStats> getOrderStats() {
        try {
            OrderStats stats = new OrderStats();
            stats.setPendingCount(orderRepository.countByStatus("pending"));
            stats.setConfirmedCount(orderRepository.countByStatus("confirmed"));
            stats.setProcessingCount(orderRepository.countByStatus("processing"));
            stats.setShippingCount(orderRepository.countByStatus("shipping"));
            stats.setDeliveredCount(orderRepository.countByStatus("delivered"));
            stats.setCancelledCount(orderRepository.countByStatus("cancelled"));
            stats.setTotalCount(orderRepository.count());
            
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Inner class for order statistics
    public static class OrderStats {
        private long totalCount;
        private long pendingCount;
        private long confirmedCount;
        private long processingCount;
        private long shippingCount;
        private long deliveredCount;
        private long cancelledCount;
        
        // Getters and Setters
        public long getTotalCount() {
            return totalCount;
        }
        
        public void setTotalCount(long totalCount) {
            this.totalCount = totalCount;
        }
        
        public long getPendingCount() {
            return pendingCount;
        }
        
        public void setPendingCount(long pendingCount) {
            this.pendingCount = pendingCount;
        }
        
        public long getConfirmedCount() {
            return confirmedCount;
        }
        
        public void setConfirmedCount(long confirmedCount) {
            this.confirmedCount = confirmedCount;
        }
        
        public long getProcessingCount() {
            return processingCount;
        }
        
        public void setProcessingCount(long processingCount) {
            this.processingCount = processingCount;
        }
        
        public long getShippingCount() {
            return shippingCount;
        }
        
        public void setShippingCount(long shippingCount) {
            this.shippingCount = shippingCount;
        }
        
        public long getDeliveredCount() {
            return deliveredCount;
        }
        
        public void setDeliveredCount(long deliveredCount) {
            this.deliveredCount = deliveredCount;
        }
        
        public long getCancelledCount() {
            return cancelledCount;
        }
        
        public void setCancelledCount(long cancelledCount) {
            this.cancelledCount = cancelledCount;
        }
    }
} 