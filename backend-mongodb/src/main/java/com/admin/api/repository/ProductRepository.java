package com.admin.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.api.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    
    Optional<Product> findBySku(String sku);
    
    List<Product> findByActiveTrue();
    
    List<Product> findByActiveFalse();
    
    @Query("{'categoryIds': ?0}")
    List<Product> findByCategoryId(String categoryId);
    
    @Query("{'categoryIds': {$in: ?0}}")
    List<Product> findByCategoryIds(List<String> categoryIds);
    
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Product> findByNameContainingIgnoreCase(String name);
    
    @Query("{'name': {$regex: ?0, $options: 'i'}, 'active': ?1}")
    List<Product> findByNameContainingIgnoreCaseAndActive(String name, Boolean active);
    
    @Query("{'name': {$regex: ?0, $options: 'i'}, 'categoryIds': ?1}")
    List<Product> findByNameContainingIgnoreCaseAndCategoryId(String name, String categoryId);
    
    @Query("{'name': {$regex: ?0, $options: 'i'}, 'categoryIds': ?1, 'active': ?2}")
    List<Product> findByNameContainingIgnoreCaseAndCategoryIdAndActive(String name, String categoryId, Boolean active);
    
    @Query("{'categoryIds': ?0, 'active': ?1}")
    List<Product> findByCategoryIdAndActive(String categoryId, Boolean active);
    
    boolean existsBySku(String sku);
    
    boolean existsBySkuAndIdNot(String sku, String id);
    
    @Query(value = "{'categoryIds': ?0}", count = true)
    long countByCategoryId(String categoryId);
} 