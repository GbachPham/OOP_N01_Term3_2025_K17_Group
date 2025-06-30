Hệ Thống Thương Mại Điện Tử với Trang Quản Trị
Dự án này là một hệ thống thương mại điện tử hoàn chỉnh, được phát triển theo kiến trúc microservices và phân chia công việc rõ ràng cho ba thành viên.

Kiến Trúc Hệ Thống
Hệ thống được thiết kế theo kiến trúc microservices với sự tách biệt rõ ràng giữa frontend và backend.

Trang Quản Trị (thực hiện bởi Phạm Gia Bách): Sử dụng Angular Admin Dashboard.

API Backend (thực hiện bởi Lữ Trung Anh): Được xây dựng bằng Java Spring Boot REST API kết nối với MongoDB.

Giao Diện Người Dùng (thực hiện bởi Phùng Thị Hạ Lam): Là một HTML/CSS/JS frontend dành cho người dùng cuối.

Các Thành Phần
1. Trang Quản Trị (/admin-website)
Thực hiện bởi: Phạm Gia Bách

Trang quản trị là nơi quản lý toàn bộ hệ thống thương mại điện tử, với các tính năng chính sau:

Quản lý sản phẩm: Bao gồm tạo, đọc, cập nhật, xóa (CRUD), quản lý biến thể và thẻ sản phẩm.

Quản lý danh mục: Tạo, cập nhật, xóa danh mục sản phẩm.

Quản lý đơn hàng: Xem, cập nhật trạng thái đơn hàng.

Quản lý mã giảm giá: Tạo, sửa, xóa các mã giảm giá.

Tải lên tệp: Hỗ trợ tải lên hình ảnh và các tệp khác.

Thống kê dashboard: Cung cấp cái nhìn tổng quan về hoạt động của hệ thống.

Hỗ trợ khách hàng: Các chức năng liên quan đến việc hỗ trợ người dùng.

Công nghệ sử dụng:

Angular 20.0.4

SCSS

Component-based architecture

2. API Backend (/backend-mongodb)
Thực hiện bởi: Lữ Trung Anh

Đây là backend API cho hệ thống quản lý admin được phát triển với Java Spring Boot và MongoDB. API hỗ trợ quản lý danh mục sản phẩm và sản phẩm với các tính năng:

RESTful endpoints: Cung cấp các API tuân thủ chuẩn REST.

MongoDB persistence: Lưu trữ dữ liệu bằng MongoDB.

Tải lên file: Hỗ trợ xử lý các yêu cầu tải lên tệp.

CORS config: Cấu hình CORS để cho phép các ứng dụng frontend truy cập.

Xác thực & phân quyền: Đảm bảo an toàn thông tin và quyền truy cập.

Quản lý danh mục: CRUD operations cho danh mục sản phẩm.

Quản lý sản phẩm: CRUD operations với hỗ trợ multiple categories, variants và tags.

Product Variants: Quản lý các biến thể sản phẩm (màu sắc, kích thước, v.v.).

Product Tags: Hệ thống tag cho sản phẩm (giảm giá, hot, new, featured, v.v.).

Tìm kiếm và lọc: Hỗ trợ tìm kiếm và lọc dữ liệu.

Công nghệ sử dụng:

Java 17

Spring Boot 3.2.0

Spring Data MongoDB

MongoDB (Database)

Maven (Build tool)

3. Giao Diện Người Dùng (/Web-user)
Thực hiện bởi: Phùng Thị Hạ Lam

Giao diện người dùng là phần mà khách hàng tương tác trực tiếp, được thiết kế thân thiện và tương thích với nhiều thiết bị.

Tính năng:

Tìm kiếm, duyệt sản phẩm: Khách hàng có thể dễ dàng tìm kiếm và xem các sản phẩm.

Giỏ hàng: Chức năng thêm, bớt sản phẩm vào giỏ hàng.

Đặt hàng: Quy trình đặt hàng trực tuyến.

Responsive design: Đảm bảo giao diện hiển thị tốt trên cả máy tính và thiết bị di động.

Công nghệ sử dụng:

HTML5

CSS3

JavaScript

Bắt Đầu
Yêu Cầu Hệ Thống
Để chạy hệ thống, cần cài đặt các phần mềm sau:

Node.js và npm (cho Trang Quản Trị)

Java 17+

Maven 3.6+

MongoDB 4.4+

Trình duyệt hiện đại

Cài Đặt và Chạy
1. Cài đặt MongoDB

Đảm bảo MongoDB đã được cài đặt trên hệ thống của bạn.

Bash

# Ubuntu/Debian
sudo apt update
sudo apt install mongodb

# MacOS với Homebrew
brew tap mongodb/brew
brew install mongodb-community

# Windows - tải và cài đặt từ trang chủ MongoDB
2. Khởi động MongoDB

Sau khi cài đặt, hãy khởi động dịch vụ MongoDB.

Bash

# Ubuntu/Debian/MacOS
sudo systemctl start mongod
# hoặc
mongod

# Windows
net start MongoDB
3. Cài đặt và chạy Backend API (Lữ Trung Anh)

Bash

cd backend-mongodb
mvn clean install
mvn spring-boot:run
API sẽ chạy tại: http://localhost:8081/api

4. Cài đặt Trang Quản Trị (Phạm Gia Bách)

Bash

cd admin-website
npm install
ng serve
Admin sẽ chạy tại: http://localhost:4200

5. Cài đặt Giao Diện Người Dùng (Phùng Thị Hạ Lam)

Có thể mở file HTML trực tiếp trong trình duyệt hoặc sử dụng một server cục bộ:

Bash

python -m http.server 8000
Scripts Phát Triển
Các scripts sau được cung cấp để hỗ trợ chạy nhanh toàn bộ hệ thống:

start-dev.bat (dành cho Windows)

start-dev.sh (dành cho Linux/Mac)

Cấu hình
Cấu hình database cho Backend API trong file src/main/resources/application.properties:

Properties

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/admin_db
spring.data.mongodb.auto-index-creation=true

# Server Configuration
server.port=8081
server.servlet.context-path=/api
API Endpoints
Triển khai bởi: Lữ Trung Anh

Categories
Method

Endpoint

Mô tả

GET

/categories

Lấy tất cả danh mục (có hỗ trợ search và filter)

GET

/categories/active

Lấy danh mục đang hoạt động

GET

/categories/{id}

Lấy danh mục theo ID

POST

/categories

Tạo danh mục mới

PUT

/categories/{id}

Cập nhật danh mục

DELETE

/categories/{id}

Xóa danh mục

PATCH

/categories/{id}/toggle-status

Bật/tắt trạng thái danh mục

Query Parameters cho GET /categories:

search: Tìm kiếm theo tên danh mục

active: Lọc theo trạng thái (true/false)

Products
Method

Endpoint

Mô tả

GET

/products

Lấy tất cả sản phẩm (có hỗ trợ search và filter)

GET

/products/active

Lấy sản phẩm đang hoạt động

GET

/products/{id}

Lấy sản phẩm theo ID

POST

/products

Tạo sản phẩm mới

PUT

/products/{id}

Cập nhật sản phẩm

DELETE

/products/{id}

Xóa sản phẩm

PATCH

/products/{id}/toggle-status

Bật/tắt trạng thái sản phẩm

Query Parameters cho GET /products:

search: Tìm kiếm theo tên sản phẩm

categoryId: Lọc theo danh mục

active: Lọc theo trạng thái (true/false)

Product Variants
Method

Endpoint

Mô tả

POST

/products/{productId}/variants

Thêm variant mới cho sản phẩm

PUT

/products/{productId}/variants/{variantId}

Cập nhật variant

DELETE

/products/{productId}/variants/{variantId}

Xóa variant

Mô Hình Dữ liệu
Thiết kế bởi: Lữ Trung Anh

Category

{
  "id": "string",
  "name": "string",
  "description": "string",
  "active": "boolean",
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
Product

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
Đơn Hàng

{
  "id": "string",
  "customer": {
    "name": "string",
    "email": "string",
    "phone": "string"
  },
  "items": [...],
  "status": "string",
  "total": "number"
}
Tính năng nổi bật của Backend API
Multiple Categories Support: Mỗi sản phẩm có thể thuộc về nhiều danh mục hoặc không thuộc danh mục nào. Trường categoryIds chứa một mảng các ID danh mục.

Product Variants: Mỗi sản phẩm có thể có nhiều biến thể. Biến thể có thể có giá bổ sung, tồn kho riêng biệt và hỗ trợ các thuộc tính như màu sắc, kích thước, chất liệu, thông số kỹ thuật.

Product Tags: Hệ thống tag linh hoạt cho sản phẩm với các loại tag như discount, hot, new, bestseller, featured. Mỗi tag có thể có màu sắc và giá trị riêng.

Data Initialization: API được cấu hình để tự động khởi tạo 5 danh mục mẫu và 1 sản phẩm mẫu với variants và tags khi chạy lần đầu.

CORS Configuration: API đã được cấu hình CORS để kết nối với frontend Angular tại http://localhost:4200, đảm bảo tương tác liền mạch giữa các thành phần.

Khắc phục sự cố (Troubleshooting)
Lỗi kết nối MongoDB:

Kiểm tra xem MongoDB đã được khởi động chưa.

Xác minh chuỗi kết nối trong application.properties.

Đảm bảo cơ sở dữ liệu admin_db có quyền truy cập.

Xung đột cổng (Port Conflict):

Mặc định API chạy tại cổng 8081.

Nếu cần, hãy thay đổi cổng trong application.properties:

Properties

server.port=8082
Các vấn đề về CORS:

Kiểm tra xem frontend có đang chạy tại đúng cổng 4200 hay không.

Nếu cần, cập nhật cấu hình CORS trong CorsConfig.java.
