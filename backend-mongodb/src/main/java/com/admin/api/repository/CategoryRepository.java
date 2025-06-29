package com.admin.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.api.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    
    Optional<Category> findByName(String name);
    
    List<Category> findByActiveTrue();
    
    List<Category> findByActiveFalse();
    
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Category> findByNameContainingIgnoreCase(String name);
    
    @Query("{'name': {$regex: ?0, $options: 'i'}, 'active': ?1}")
    List<Category> findByNameContainingIgnoreCaseAndActive(String name, Boolean active);
    
    boolean existsByName(String name);
    
    boolean existsByNameAndIdNot(String name, String id);
} 