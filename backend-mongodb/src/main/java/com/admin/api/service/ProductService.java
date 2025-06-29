package com.admin.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.dto.ProductDTO;
import com.admin.api.dto.ProductTagDTO;
import com.admin.api.dto.ProductVariantDTO;
import com.admin.api.entity.Category;
import com.admin.api.entity.Product;
import com.admin.api.entity.ProductTag;
import com.admin.api.entity.ProductVariant;
import com.admin.api.repository.CategoryRepository;
import com.admin.api.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ProductDTO> getActiveProducts() {
        List<Product> products = productRepository.findByActiveTrue();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<ProductDTO> getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO);
    }
    
    public List<ProductDTO> searchProducts(String searchTerm, String categoryId, Boolean active) {
        List<Product> products;
        
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            if (categoryId != null && !categoryId.trim().isEmpty()) {
                if (active != null) {
                    products = productRepository.findByNameContainingIgnoreCaseAndCategoryIdAndActive(
                            searchTerm.trim(), categoryId, active);
                } else {
                    products = productRepository.findByNameContainingIgnoreCaseAndCategoryId(
                            searchTerm.trim(), categoryId);
                }
            } else {
                if (active != null) {
                    products = productRepository.findByNameContainingIgnoreCaseAndActive(searchTerm.trim(), active);
                } else {
                    products = productRepository.findByNameContainingIgnoreCase(searchTerm.trim());
                }
            }
        } else {
            if (categoryId != null && !categoryId.trim().isEmpty()) {
                if (active != null) {
                    products = productRepository.findByCategoryIdAndActive(categoryId, active);
                } else {
                    products = productRepository.findByCategoryId(categoryId);
                }
            } else {
                if (active != null) {
                    products = active ? productRepository.findByActiveTrue() : productRepository.findByActiveFalse();
                } else {
                    products = productRepository.findAll();
                }
            }
        }
        
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Validate SKU uniqueness if provided
        if (productDTO.getSku() != null && !productDTO.getSku().trim().isEmpty()) {
            if (productRepository.existsBySku(productDTO.getSku())) {
                throw new RuntimeException("SKU đã tồn tại");
            }
        }
        
        // Validate category IDs
        validateCategoryIds(productDTO.getCategoryIds());
        
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
        
        // Validate SKU uniqueness if provided (excluding current product)
        if (productDTO.getSku() != null && !productDTO.getSku().trim().isEmpty()) {
            if (productRepository.existsBySkuAndIdNot(productDTO.getSku(), id)) {
                throw new RuntimeException("SKU đã tồn tại");
            }
        }
        
        // Validate category IDs
        validateCategoryIds(productDTO.getCategoryIds());
        
        Product product = existingProduct.get();
        updateProductFields(product, productDTO);
        
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
        productRepository.deleteById(id);
    }
    
    public ProductDTO toggleProductStatus(String id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
        
        Product product = productOpt.get();
        product.setActive(!product.getActive());
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    public ProductDTO addVariant(String productId, ProductVariantDTO variantDTO) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
        
        Product product = productOpt.get();
        ProductVariant variant = convertVariantToEntity(variantDTO);
        variant.setId(UUID.randomUUID().toString());
        
        product.getVariants().add(variant);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    public ProductDTO updateVariant(String productId, String variantId, ProductVariantDTO variantDTO) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
        
        Product product = productOpt.get();
        Optional<ProductVariant> variantOpt = product.getVariants().stream()
                .filter(v -> v.getId().equals(variantId))
                .findFirst();
        
        if (variantOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy biến thể");
        }
        
        ProductVariant variant = variantOpt.get();
        updateVariantFields(variant, variantDTO);
        
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    public ProductDTO removeVariant(String productId, String variantId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
        
        Product product = productOpt.get();
        product.getVariants().removeIf(v -> v.getId().equals(variantId));
        
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    private void validateCategoryIds(List<String> categoryIds) {
        if (categoryIds != null && !categoryIds.isEmpty()) {
            for (String categoryId : categoryIds) {
                if (!categoryRepository.existsById(categoryId)) {
                    throw new RuntimeException("Danh mục không tồn tại: " + categoryId);
                }
            }
        }
    }
    
    private ProductDTO convertToDTO(Product product) {
        // Get category names
        List<String> categoryNames = new ArrayList<>();
        if (product.getCategoryIds() != null && !product.getCategoryIds().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(product.getCategoryIds());
            categoryNames = categories.stream()
                    .map(Category::getName)
                    .collect(Collectors.toList());
        }
        
        // Convert variants
        List<ProductVariantDTO> variantDTOs = product.getVariants().stream()
                .map(this::convertVariantToDTO)
                .collect(Collectors.toList());
        
        // Convert tags
        List<ProductTagDTO> tagDTOs = product.getTags().stream()
                .map(this::convertTagToDTO)
                .collect(Collectors.toList());
        
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.getActive(),
                product.getImageId(),
                product.getCategoryIds(),
                categoryNames,
                variantDTOs,
                tagDTOs,
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getVariants().size()
        );
    }
    
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        updateProductFields(product, productDTO);
        return product;
    }
    
    private void updateProductFields(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setSku(productDTO.getSku());
        product.setActive(productDTO.getActive() != null ? productDTO.getActive() : true);
        product.setImageId(productDTO.getImageId());
        product.setCategoryIds(productDTO.getCategoryIds());
        
        // Convert and set variants
        List<ProductVariant> variants = productDTO.getVariants().stream()
                .map(this::convertVariantToEntity)
                .collect(Collectors.toList());
        product.setVariants(variants);
        
        // Convert and set tags
        List<ProductTag> tags = productDTO.getTags().stream()
                .map(this::convertTagToEntity)
                .collect(Collectors.toList());
        product.setTags(tags);
    }
    
    private ProductVariantDTO convertVariantToDTO(ProductVariant variant) {
        return new ProductVariantDTO(
                variant.getId(),
                variant.getName(),
                variant.getColor(),
                variant.getSize(),
                variant.getMaterial(),
                variant.getSpecifications(),
                variant.getSku(),
                variant.getAdditionalPrice(),
                variant.getStock(),
                variant.getActive(),
                variant.getImageId(),
                variant.getCreatedAt(),
                variant.getUpdatedAt()
        );
    }
    
    private ProductVariant convertVariantToEntity(ProductVariantDTO variantDTO) {
        ProductVariant variant = new ProductVariant();
        updateVariantFields(variant, variantDTO);
        if (variant.getId() == null) {
            variant.setId(UUID.randomUUID().toString());
        }
        return variant;
    }
    
    private void updateVariantFields(ProductVariant variant, ProductVariantDTO variantDTO) {
        variant.setName(variantDTO.getName());
        variant.setColor(variantDTO.getColor());
        variant.setSize(variantDTO.getSize());
        variant.setMaterial(variantDTO.getMaterial());
        variant.setSpecifications(variantDTO.getSpecifications());
        variant.setSku(variantDTO.getSku());
        variant.setAdditionalPrice(variantDTO.getAdditionalPrice());
        variant.setStock(variantDTO.getStock());
        variant.setActive(variantDTO.getActive() != null ? variantDTO.getActive() : true);
        variant.setImageId(variantDTO.getImageId());
    }
    
    private ProductTagDTO convertTagToDTO(ProductTag tag) {
        return new ProductTagDTO(
                tag.getType(),
                tag.getValue(),
                tag.getColor(),
                tag.getActive()
        );
    }
    
    private ProductTag convertTagToEntity(ProductTagDTO tagDTO) {
        return new ProductTag(
                tagDTO.getType(),
                tagDTO.getValue(),
                tagDTO.getColor(),
                tagDTO.getActive() != null ? tagDTO.getActive() : true
        );
    }
} 