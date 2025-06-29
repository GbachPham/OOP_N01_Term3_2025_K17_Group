package com.admin.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.dto.CategoryDTO;
import com.admin.api.entity.Category;
import com.admin.api.repository.CategoryRepository;
import com.admin.api.repository.ProductRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CategoryDTO> getActiveCategories() {
        List<Category> categories = categoryRepository.findByActiveTrue();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<CategoryDTO> getCategoryById(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(this::convertToDTO);
    }
    
    public List<CategoryDTO> searchCategories(String searchTerm, Boolean active) {
        List<Category> categories;
        
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            if (active != null) {
                categories = categoryRepository.findByNameContainingIgnoreCaseAndActive(searchTerm.trim(), active);
            } else {
                categories = categoryRepository.findByNameContainingIgnoreCase(searchTerm.trim());
            }
        } else {
            if (active != null) {
                categories = active ? categoryRepository.findByActiveTrue() : categoryRepository.findByActiveFalse();
            } else {
                categories = categoryRepository.findAll();
            }
        }
        
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Validate unique name
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new RuntimeException("Tên danh mục đã tồn tại");
        }
        
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }
    
    public CategoryDTO updateCategory(String id, CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Không tìm thấy danh mục");
        }
        
        // Validate unique name (excluding current category)
        if (categoryRepository.existsByNameAndIdNot(categoryDTO.getName(), id)) {
            throw new RuntimeException("Tên danh mục đã tồn tại");
        }
        
        Category category = existingCategory.get();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setActive(categoryDTO.getActive());
        
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }
    
    public void deleteCategory(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new RuntimeException("Không tìm thấy danh mục");
        }
        
        // Check if category has products
        long productCount = productRepository.countByCategoryId(id);
        if (productCount > 0) {
            throw new RuntimeException("Không thể xóa danh mục vì vẫn còn " + productCount + " sản phẩm");
        }
        
        categoryRepository.deleteById(id);
    }
    
    public CategoryDTO toggleCategoryStatus(String id) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy danh mục");
        }
        
        Category category = categoryOpt.get();
        category.setActive(!category.getActive());
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }
    
    private CategoryDTO convertToDTO(Category category) {
        long productCount = productRepository.countByCategoryId(category.getId());
        
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getActive(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                productCount
        );
    }
    
    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setActive(categoryDTO.getActive() != null ? categoryDTO.getActive() : true);
        return category;
    }
} 