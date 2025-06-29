package com.admin.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admin.api.entity.Order;
import com.admin.api.entity.Product;
import com.admin.api.entity.ProductVariant;
import com.admin.api.repository.OrderRepository;
import com.admin.api.repository.ProductRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Transactional
    public Order createOrder(Order order) {
        // Validate and update stock for each item
        for (Order.OrderItem item : order.getItems()) {
            Optional<Product> productOpt = productRepository.findById(item.getProductId());
            if (productOpt.isEmpty()) {
                throw new RuntimeException("Sản phẩm không tồn tại: " + item.getProductId());
            }
            
            Product product = productOpt.get();
            
            // Find matching variant if variantName is provided
            if (item.getVariantName() != null && !item.getVariantName().isEmpty()) {
                Optional<ProductVariant> variantOpt = product.getVariants().stream()
                    .filter(v -> v.getName().equals(item.getVariantName()))
                    .findFirst();
                
                if (variantOpt.isEmpty()) {
                    throw new RuntimeException("Biến thể không tồn tại: " + item.getVariantName());
                }
                
                ProductVariant variant = variantOpt.get();
                
                // Check stock
                if (variant.getStock() < item.getQuantity()) {
                    throw new RuntimeException("Số lượng trong kho không đủ cho sản phẩm: " + item.getProductName());
                }
                
                // Update stock
                variant.setStock(variant.getStock() - item.getQuantity());
            } else {
                // If no variant specified, throw error as all products should have variants
                throw new RuntimeException("Vui lòng chọn biến thể cho sản phẩm: " + item.getProductName());
            }
            
            // Save product with updated variant stock
            productRepository.save(product);
        }
        
        // Set default status if not provided
        if (order.getStatus() == null) {
            order.setStatus("pending");
        }
        
        // Save order
        return orderRepository.save(order);
    }
    
    @Transactional
    public Order updateOrderStatus(String orderId, String newStatus) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đơn hàng");
        }
        
        Order order = orderOpt.get();
        String oldStatus = order.getStatus();
        
        // Handle stock updates based on status changes
        if (oldStatus.equals("pending") && newStatus.equals("cancelled")) {
            // Return stock when order is cancelled
            returnStockToInventory(order);
        }
        
        order.setStatus(newStatus.toLowerCase());
        return orderRepository.save(order);
    }
    
    private void returnStockToInventory(Order order) {
        for (Order.OrderItem item : order.getItems()) {
            Optional<Product> productOpt = productRepository.findById(item.getProductId());
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                
                if (item.getVariantName() != null && !item.getVariantName().isEmpty()) {
                    Optional<ProductVariant> variantOpt = product.getVariants().stream()
                        .filter(v -> v.getName().equals(item.getVariantName()))
                        .findFirst();
                    
                    if (variantOpt.isPresent()) {
                        ProductVariant variant = variantOpt.get();
                        variant.setStock(variant.getStock() + item.getQuantity());
                        productRepository.save(product);
                    }
                }
            }
        }
    }
} 