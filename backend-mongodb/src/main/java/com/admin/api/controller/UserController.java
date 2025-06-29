package com.admin.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.api.dto.CategoryDTO;
import com.admin.api.dto.ProductDTO;
import com.admin.api.service.CategoryService;
import com.admin.api.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://localhost:8080", "http://127.0.0.1:5500", "http://127.0.0.1:5501", "http://127.0.0.1:5502"})
public class UserController {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductService productService;
    
    // API cho user - Lấy tất cả danh mục active
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getActiveCategories() {
        try {
            // Lấy tất cả danh mục (bao gồm cả không active để test)
            List<CategoryDTO> allCategories = categoryService.getAllCategories();
            
            // Nếu không có danh mục active, lấy tất cả để test
            List<CategoryDTO> categories = categoryService.getActiveCategories();
            if (categories.isEmpty()) {
                categories = allCategories;
            }
            
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi để debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // API cho user - Lấy chi tiết danh mục
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryDetails(@PathVariable String id) {
        try {
            Optional<CategoryDTO> category = categoryService.getCategoryById(id);
            if (category.isPresent() && Boolean.TRUE.equals(category.get().getActive())) {
                return ResponseEntity.ok(category.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // API cho user - Lấy tất cả sản phẩm active
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getActiveProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "12") int size) {
        try {
            List<ProductDTO> products;
            if (search != null || categoryId != null) {
                products = productService.searchProducts(search, categoryId, true);
            } else {
                products = productService.getActiveProducts();
            }
            
            // Phân trang đơn giản
            int start = page * size;
            int end = Math.min(start + size, products.size());
            
            if (start < products.size()) {
                products = products.subList(start, end);
            } else {
                products = List.of();
            }
            
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // API cho user - Lấy sản phẩm nổi bật (PHẢI ĐẶT TRƯỚC /products/{id})
    @GetMapping("/products/featured")
    public ResponseEntity<List<ProductDTO>> getFeaturedProducts(
            @RequestParam(required = false, defaultValue = "8") int limit) {
        try {
            // Lấy tất cả sản phẩm (bao gồm cả không active để test)
            List<ProductDTO> allProducts = productService.getAllProducts();
            
            // Nếu không có sản phẩm active, lấy tất cả để test
            List<ProductDTO> products = productService.getActiveProducts();
            if (products.isEmpty()) {
                products = allProducts;
            }
            
            // Giới hạn số lượng sản phẩm
            if (products.size() > limit) {
                products = products.subList(0, limit);
            }
            
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi để debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // API cho user - Lấy sản phẩm theo danh mục
    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(
            @PathVariable String categoryId,
            @RequestParam(required = false, defaultValue = "8") int limit) {
        try {
            List<ProductDTO> products = productService.searchProducts(null, categoryId, true);
            
            // Giới hạn số lượng sản phẩm
            if (products.size() > limit) {
                products = products.subList(0, limit);
            }
            
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Test endpoint để debug orders
    @GetMapping("/orders-test")
    public ResponseEntity<String> testOrdersInUserController() {
        return ResponseEntity.ok("Orders test endpoint working in UserController!");
    }

    // API cho user - Lấy chi tiết sản phẩm (PHẢI ĐẶT SAU CÁC ENDPOINT CỤ THỂ)
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductDetails(@PathVariable String id) {
        try {
            Optional<ProductDTO> product = productService.getProductById(id);
            if (product.isPresent() && Boolean.TRUE.equals(product.get().getActive())) {
                return ResponseEntity.ok(product.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 