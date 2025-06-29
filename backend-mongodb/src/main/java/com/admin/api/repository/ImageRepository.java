package com.admin.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.admin.api.entity.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    
} 