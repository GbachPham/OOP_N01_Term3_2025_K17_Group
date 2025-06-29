# Admin Backend API with MongoDB

## Mô tả

Đây là backend API cho hệ thống quản lý admin được phát triển với Java Spring Boot và MongoDB. API hỗ trợ quản lý danh mục sản phẩm và sản phẩm với các tính năng:

- **Quản lý danh mục**: CRUD operations cho danh mục sản phẩm
- **Quản lý sản phẩm**: CRUD operations với hỗ trợ multiple categories, variants và tags
- **Product Variants**: Quản lý các biến thể sản phẩm (màu sắc, kích thước, v.v.)
- **Product Tags**: Hệ thống tag cho sản phẩm (giảm giá, hot, new, featured, v.v.)
- **Tìm kiếm và lọc**: Hỗ trợ tìm kiếm và lọc dữ liệu

## Công nghệ sử dụng

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data MongoDB**
- **MongoDB** (Database)
- **Maven** (Build tool)

## Yêu cầu hệ thống

- Java 17 hoặc cao hơn
- Maven 3.6 hoặc cao hơn
- MongoDB 4.4 hoặc cao hơn

## Cài đặt và chạy

### 1. Cài đặt MongoDB

```bash
# Ubuntu/Debian
sudo apt update
sudo apt install mongodb

# MacOS với Homebrew
brew tap mongodb/brew
brew install mongodb-community

# Windows - tải và cài đặt từ trang chủ MongoDB
```

### 2. Khởi động MongoDB

```bash
# Ubuntu/Debian/MacOS
sudo systemctl start mongod
# hoặc
mongod

# Windows
net start MongoDB
```

### 3. Clone và chạy project

```bash
# Di chuyển vào thư mục project
cd backend-mongodb

# Build project
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

API sẽ chạy tại: `http://localhost:8081/api`

## Cấu hình

Cấu hình database trong file `src/main/resources/application.properties`:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/admin_db
spring.data.mongodb.auto-index-creation=true

# Server Configuration
server.port=8081
server.servlet.context-path=/api
```

## API Endpoints

### Categories

| Method | Endpoint | Mô tả |
|--------|----------|--------|
| GET | `/categories` | Lấy tất cả danh mục (có hỗ trợ search và filter) |
| GET | `/categories/active` | Lấy danh mục đang hoạt động |
| GET | `/categories/{id}` | Lấy danh mục theo ID |
| POST | `/categories` | Tạo danh mục mới |
| PUT | `/categories/{id}` | Cập nhật danh mục |
| DELETE | `/categories/{id}` | Xóa danh mục |
| PATCH | `/categories/{id}/toggle-status` | Bật/tắt trạng thái danh mục |

**Query Parameters cho GET `/categories`:**
- `search`: Tìm kiếm theo tên danh mục
- `active`: Lọc theo trạng thái (true/false)

### Products

| Method | Endpoint | Mô tả |
|--------|----------|--------|
| GET | `/products` | Lấy tất cả sản phẩm (có hỗ trợ search và filter) |
| GET | `/products/active` | Lấy sản phẩm đang hoạt động |
| GET | `/products/{id}` | Lấy sản phẩm theo ID |
| POST | `/products` | Tạo sản phẩm mới |
| PUT | `/products/{id}` | Cập nhật sản phẩm |
| DELETE | `/products/{id}` | Xóa sản phẩm |
| PATCH | `/products/{id}/toggle-status` | Bật/tắt trạng thái sản phẩm |

**Query Parameters cho GET `/products`:**
- `search`: Tìm kiếm theo tên sản phẩm
- `categoryId`: Lọc theo danh mục
- `active`: Lọc theo trạng thái (true/false)

### Product Variants

| Method | Endpoint | Mô tả |
|--------|----------|--------|
| POST | `/products/{productId}/variants` | Thêm variant mới cho sản phẩm |
| PUT | `/products/{productId}/variants/{variantId}` | Cập nhật variant |
| DELETE | `/products/{productId}/variants/{variantId}` | Xóa variant |

## Data Models

### Category
```json
{
  "id": "string",
  "name": "string",
  "description": "string",
  "active": "boolean",
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

### Product
```json
{
  "id": "string",
  "name": "string",
  "description": "string",
  "price": "number",
  "sku": "string",
  "active": "boolean",
  "imageUrl": "string",
  "categoryIds": ["string"],
  "variants": [
    {
      "id": "string",
      "name": "string",
      "color": "string",
      "size": "string",
      "material": "string",
      "specifications": "string",
      "sku": "string",
      "additionalPrice": "number",
      "stock": "number",
      "active": "boolean",
      "imageUrl": "string"
    }
  ],
  "tags": [
    {
      "type": "string",
      "value": "string",
      "color": "string",
      "active": "boolean"
    }
  ],
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

## Features

### Multiple Categories Support
- Mỗi sản phẩm có thể thuộc về nhiều danh mục hoặc không thuộc danh mục nào
- Field `categoryIds` chứa array các ID danh mục

### Product Variants
- Mỗi sản phẩm có thể có nhiều biến thể
- Biến thể có thể có giá bổ sung, tồn kho riêng biệt
- Hỗ trợ các thuộc tính: màu sắc, kích thước, chất liệu, thông số kỹ thuật

### Product Tags
- Hệ thống tag linh hoạt cho sản phẩm
- Các loại tag: `discount`, `hot`, `new`, `bestseller`, `featured`
- Mỗi tag có thể có màu sắc và giá trị riêng

### Data Initialization
- Tự động khởi tạo dữ liệu mẫu khi chạy lần đầu
- 5 danh mục mẫu và 1 sản phẩm mẫu với variants và tags

## CORS Configuration
API đã được cấu hình CORS để kết nối với frontend Angular tại `http://localhost:4200`

## Troubleshooting

### MongoDB Connection Error
- Kiểm tra MongoDB đã được khởi động chưa
- Kiểm tra connection string trong `application.properties`
- Đảm bảo database name `admin_db` có quyền truy cập

### Port Conflict
- Mặc định API chạy tại port 8081
- Thay đổi port trong `application.properties` nếu cần:
```properties
server.port=8082
```

### CORS Issues
- Kiểm tra frontend đang chạy tại đúng port 4200
- Cập nhật CORS configuration trong `CorsConfig.java` nếu cần 