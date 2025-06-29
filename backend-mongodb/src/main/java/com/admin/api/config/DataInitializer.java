package com.admin.api.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.admin.api.entity.Category;
import com.admin.api.entity.Coupon;
import com.admin.api.entity.Order;
import com.admin.api.entity.Product;
import com.admin.api.entity.ProductTag;
import com.admin.api.entity.ProductVariant;
import com.admin.api.repository.CategoryRepository;
import com.admin.api.repository.CouponRepository;
import com.admin.api.repository.OrderRepository;
import com.admin.api.repository.ProductRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CouponRepository couponRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Chỉ khởi tạo dữ liệu nếu chưa có
        if (categoryRepository.count() == 0) {
            initializeCategories();
        }
        
        if (productRepository.count() == 0) {
            initializeProducts();
        }
        
        if (couponRepository.count() == 0) {
            initializeCoupons();
        }
        
        if (orderRepository.count() == 0) {
            initializeOrders();
        }
    }
    
    private void initializeCategories() {
        List<Category> categories = Arrays.asList(
            new Category("Điện thoại", "Điện thoại thông minh các loại", true),
            new Category("Laptop", "Máy tính xách tay", true),
            new Category("Phụ kiện", "Phụ kiện điện tử", true),
            new Category("Tablet", "Máy tính bảng", true),
            new Category("Đồng hồ thông minh", "Smart watch và fitness tracker", true)
        );
        
        categoryRepository.saveAll(categories);
        System.out.println("Đã khởi tạo " + categories.size() + " danh mục mẫu");
    }
    
    private void initializeProducts() {
        // Lấy categories đã tạo
        List<Category> categories = categoryRepository.findAll();
        
        if (categories.isEmpty()) {
            return;
        }
        
        // Tạo sản phẩm mẫu
        List<Product> products = new ArrayList<>();
        
        // iPhone 15 Pro
        Product iphone = new Product(
            "iPhone 15 Pro",
            "iPhone 15 Pro với chip A17 Pro, camera 48MP, và khung titan",
            25000000.0,
            "IPH15PRO001",
            true,
            null,
            Arrays.asList(categories.get(0).getId()) // Điện thoại
        );
        
        // Thêm variants cho iPhone
        List<ProductVariant> iphoneVariants = Arrays.asList(
            createVariant("iPhone 15 Pro 128GB", "Titan Tự Nhiên", "128GB", "Titan", "A17 Pro, 8GB RAM", "IPH15PRO-128-TN", 0.0, 10),
            createVariant("iPhone 15 Pro 256GB", "Titan Xanh", "256GB", "Titan", "A17 Pro, 8GB RAM", "IPH15PRO-256-TX", 3000000.0, 8),
            createVariant("iPhone 15 Pro 512GB", "Titan Trắng", "512GB", "Titan", "A17 Pro, 8GB RAM", "IPH15PRO-512-TT", 6000000.0, 5)
        );
        iphone.setVariants(iphoneVariants);
        
        // Thêm tags cho iPhone
        List<ProductTag> iphoneTags = Arrays.asList(
            new ProductTag("hot", "HOT", "#ff4444", true),
            new ProductTag("new", "MỚI", "#2196f3", true)
        );
        iphone.setTags(iphoneTags);
        
        products.add(iphone);
        
        productRepository.saveAll(products);
        System.out.println("Đã khởi tạo " + products.size() + " sản phẩm mẫu");
    }
    
    private void initializeCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        
        // Coupon mẫu theo cấu trúc mới
        Coupon coupon1 = new Coupon();
        coupon1.setCode("NEWYEAR2024");
        coupon1.setName("Giảm giá Tết 2024");
        coupon1.setDescription("Giảm 15% cho đơn hàng từ 1.000.000đ trong dịp Tết Nguyên Đán");
        coupon1.setType("PERCENTAGE");
        coupon1.setValue("15");
        coupon1.setMinimumOrderValue("1000000");
        coupon1.setMaximumDiscountAmount("500000");
        coupon1.setUsageLimit(100);
        coupon1.setUsedCount(0);
        coupon1.setStartDate(LocalDateTime.now());
        coupon1.setEndDate(LocalDateTime.now().plusDays(30));
        coupon1.setActive(true);
        coupons.add(coupon1);
        
        Coupon coupon2 = new Coupon();
        coupon2.setCode("SAVE200K");
        coupon2.setName("Giảm 200K");
        coupon2.setDescription("Giảm 200.000đ cho đơn hàng từ 2.000.000đ");
        coupon2.setType("FIXED_AMOUNT");
        coupon2.setValue("200000");
        coupon2.setMinimumOrderValue("2000000");
        coupon2.setMaximumDiscountAmount("200000");
        coupon2.setUsageLimit(50);
        coupon2.setUsedCount(0);
        coupon2.setStartDate(LocalDateTime.now());
        coupon2.setEndDate(LocalDateTime.now().plusDays(15));
        coupon2.setActive(true);
        coupons.add(coupon2);
        
        couponRepository.saveAll(coupons);
        System.out.println("Đã khởi tạo " + coupons.size() + " coupon mẫu");
    }
    
    private void initializeOrders() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return;
        }
        
        List<Order> orders = new ArrayList<>();
        
        // Tạo order mẫu 1
        Order order1 = new Order();
        order1.setOrderNumber("TS-" + LocalDateTime.now().getYear() + String.format("%02d", LocalDateTime.now().getMonthValue()) + String.format("%02d", LocalDateTime.now().getDayOfMonth()) + "-001");
        
        // Set customer info
        Order.Customer customer1 = new Order.Customer();
        customer1.setFullName("Nguyễn Văn A");
        customer1.setPhone("0901234567");
        customer1.setEmail("nguyenvana@example.com");
        order1.setCustomer(customer1);
        
        // Set shipping address
        Order.ShippingAddress address1 = new Order.ShippingAddress();
        address1.setProvince("Thành phố Hà Nội");
        address1.setDistrict("Quận Cầu Giấy");
        address1.setWard("Phường Dịch Vọng");
        address1.setStreetAddress("123 Xuân Thủy");
        order1.setShippingAddress(address1);
        
        // Set order items
        Product product = products.get(0);
        ProductVariant variant = product.getVariants().get(0);
        
        Order.OrderItem item1 = new Order.OrderItem();
        item1.setProductId(product.getId());
        item1.setProductName(product.getName());
        item1.setBasePrice(product.getPrice());
        item1.setVariantName(variant.getName());
        
        Map<String, String> specs = new HashMap<>();
        specs.put("cpu", "A17 Pro");
        specs.put("ram", "8GB");
        specs.put("storage", "128GB");
        item1.setVariantSpecs(specs);
        
        item1.setVariantPrice(product.getPrice() + variant.getAdditionalPrice());
        item1.setVariantDiscountPercent(5);
        item1.setColorName(variant.getColor());
        item1.setColorCode("#000000");
        item1.setColorPriceAdjustment(0.0);
        item1.setColorDiscountAdjustment(0.0);
        item1.setQuantity(1);
        item1.setUnitPrice(item1.getVariantPrice());
        item1.setDiscountedPrice(item1.getUnitPrice() * (100 - item1.getVariantDiscountPercent()) / 100);
        item1.setSubtotal(item1.getDiscountedPrice() * item1.getQuantity());
        item1.setThumbnailUrl(product.getImageId());
        
        order1.setItems(Arrays.asList(item1));
        
        // Set payment info
        Order.Payment payment1 = new Order.Payment();
        payment1.setMethod("COD");
        payment1.setStatus("pending");
        order1.setPayment(payment1);
        
        // Set product info
        List<Order.ProductInfo> productInfo1 = Arrays.asList(
            createProductInfo("Bảo hành", "Bảo hành 12 tháng chính hãng"),
            createProductInfo("Vận chuyển", "Miễn phí vận chuyển toàn quốc")
        );
        order1.setProductInfo(productInfo1);
        
        // Set order totals
        order1.setSubtotal(item1.getSubtotal());
        order1.setDiscountTotal(item1.getUnitPrice() - item1.getDiscountedPrice());
        order1.setShippingFee(0.0);
        order1.setTotal(order1.getSubtotal() - order1.getDiscountTotal() + order1.getShippingFee());
        
        // Set order status
        order1.setStatus("pending");
        
        // Set dates
        order1.setOrderDate(LocalDateTime.now());
        order1.setUpdatedAt(LocalDateTime.now());
        
        orders.add(order1);
        
        // Tạo order mẫu 2 với status khác
        Order order2 = createSimilarOrder(
            "Trần Thị B", 
            "0909876543", 
            "tranthib@example.com",
            "Thành phố Hồ Chí Minh",
            "Quận 1",
            "Phường Bến Nghé",
            "456 Lê Lợi",
            products.get(0),
            "delivered"
        );
        orders.add(order2);
        
        // Tạo order mẫu 3 với status khác
        Order order3 = createSimilarOrder(
            "Lê Văn C", 
            "0908765432", 
            "levanc@example.com",
            "Thành phố Đà Nẵng",
            "Quận Hải Châu",
            "Phường Hòa Cường Nam",
            "789 Nguyễn Văn Linh",
            products.get(0),
            "shipping"
        );
        orders.add(order3);
        
        orderRepository.saveAll(orders);
        System.out.println("Đã khởi tạo " + orders.size() + " đơn hàng mẫu");
    }
    
    private Order.ProductInfo createProductInfo(String title, String content) {
        Order.ProductInfo info = new Order.ProductInfo();
        info.setTitle(title);
        info.setContent(content);
        return info;
    }
    
    private Order createSimilarOrder(
            String customerName, 
            String phone, 
            String email,
            String province,
            String district,
            String ward,
            String streetAddress,
            Product product,
            String status) {
        Order order = new Order();
        order.setOrderNumber("TS-" + LocalDateTime.now().getYear() + String.format("%02d", LocalDateTime.now().getMonthValue()) + String.format("%02d", LocalDateTime.now().getDayOfMonth()) + "-" + UUID.randomUUID().toString().substring(0, 3));
        
        Order.Customer customer = new Order.Customer();
        customer.setFullName(customerName);
        customer.setPhone(phone);
        customer.setEmail(email);
        order.setCustomer(customer);
        
        Order.ShippingAddress address = new Order.ShippingAddress();
        address.setProvince(province);
        address.setDistrict(district);
        address.setWard(ward);
        address.setStreetAddress(streetAddress);
        order.setShippingAddress(address);
        
        ProductVariant variant = product.getVariants().get(0);
        
        Order.OrderItem item = new Order.OrderItem();
        item.setProductId(product.getId());
        item.setProductName(product.getName());
        item.setBasePrice(product.getPrice());
        item.setVariantName(variant.getName());
        
        Map<String, String> specs = new HashMap<>();
        specs.put("cpu", "A17 Pro");
        specs.put("ram", "8GB");
        specs.put("storage", "128GB");
        item.setVariantSpecs(specs);
        
        item.setVariantPrice(product.getPrice() + variant.getAdditionalPrice());
        item.setVariantDiscountPercent(5);
        item.setColorName(variant.getColor());
        item.setColorCode("#000000");
        item.setColorPriceAdjustment(0.0);
        item.setColorDiscountAdjustment(0.0);
        item.setQuantity(1);
        item.setUnitPrice(item.getVariantPrice());
        item.setDiscountedPrice(item.getUnitPrice() * (100 - item.getVariantDiscountPercent()) / 100);
        item.setSubtotal(item.getDiscountedPrice() * item.getQuantity());
        item.setThumbnailUrl(product.getImageId());
        
        order.setItems(Arrays.asList(item));
        
        Order.Payment payment = new Order.Payment();
        payment.setMethod("COD");
        payment.setStatus("pending");
        order.setPayment(payment);
        
        List<Order.ProductInfo> productInfo = Arrays.asList(
            createProductInfo("Bảo hành", "Bảo hành 12 tháng chính hãng"),
            createProductInfo("Vận chuyển", "Miễn phí vận chuyển toàn quốc")
        );
        order.setProductInfo(productInfo);
        
        order.setSubtotal(item.getSubtotal());
        order.setDiscountTotal(item.getUnitPrice() - item.getDiscountedPrice());
        order.setShippingFee(0.0);
        order.setTotal(order.getSubtotal() - order.getDiscountTotal() + order.getShippingFee());
        
        order.setStatus(status);
        
        order.setOrderDate(LocalDateTime.now().minusDays(1)); // Đặt thời gian đặt hàng là 1 ngày trước
        order.setUpdatedAt(LocalDateTime.now());
        
        return order;
    }
    
    private ProductVariant createVariant(String name, String color, String size, String material, 
                                       String specifications, String sku, Double additionalPrice, Integer stock) {
        ProductVariant variant = new ProductVariant();
        variant.setId(UUID.randomUUID().toString());
        variant.setName(name);
        variant.setColor(color);
        variant.setSize(size);
        variant.setMaterial(material);
        variant.setSpecifications(specifications);
        variant.setSku(sku);
        variant.setAdditionalPrice(additionalPrice);
        variant.setStock(stock);
        variant.setActive(true);
        return variant;
    }
} 