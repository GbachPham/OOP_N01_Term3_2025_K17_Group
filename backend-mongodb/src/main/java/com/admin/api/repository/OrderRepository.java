package com.admin.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.api.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    
    @Query("{'customer.phone': ?0}")
    List<Order> findByCustomerPhone(String customerPhone);
    
    @Query("{'customer.email': ?0}")
    List<Order> findByCustomerEmail(String customerEmail);
    
    List<Order> findByStatus(String status);
    
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("{'customer.fullName': {$regex: ?0, $options: 'i'}}")
    List<Order> findByCustomerFullNameContainingIgnoreCase(String customerName);
    
    long countByStatus(String status);
} 