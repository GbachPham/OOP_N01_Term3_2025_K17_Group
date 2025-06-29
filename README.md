# Hệ Thống Quản Lý Bán Hàng

## Mô tả đề tài
Đây là đồ án lập trình hướng đối tượng với mục tiêu xây dựng hệ thống quản lý bán hàng. Hệ thống cho phép quản lý thông tin sản phẩm, khách hàng và hóa đơn.

## Thành viên nhóm
- **Lữ Trung Anh**
- **Phạm Gia Bách**
- **Phùng Thị Hạ Lam**

## Cấu trúc dự án
```
├── src/                    # Thư mục chứa mã nguồn
│   ├── Sanpham.java       # Class quản lý thông tin sản phẩm
│   ├── Khachhang.java     # Class quản lý thông tin khách hàng
│   ├── Hoadon.java        # Class quản lý thông tin hóa đơn
│   └── Sanphamtest.java   # Class kiểm thử cho Sanpham
├── test/                  # Thư mục chứa các test case
├── build/                 # Thư mục chứa các file biên dịch
├── nbproject/            # Cấu hình NetBeans
├── build.xml             # File cấu hình Ant build
└── manifest.mf           # File manifest cho JAR
```

## Các đối tượng 
1. **Quản lý Sản phẩm**
   - Thêm, sửa, xóa thông tin sản phẩm
   - Xem danh sách sản phẩm
   - Tìm kiếm sản phẩm

2. **Quản lý Khách hàng**
   - Thêm, sửa, xóa thông tin khách hàng
   - Xem danh sách khách hàng
   - Tìm kiếm khách hàng

3. **Quản lý Hóa đơn**
   - Tạo hóa đơn mới
   - Xem danh sách hóa đơn
   - Tìm kiếm hóa đơn
#### Phân tích chức năng chính: 
- Nhập thông tin khách hàng
- Nhập danh sách sản phẩm
- Tính tổng tiền + thuế + giảm giá
- Lưu hóa đơn
- In hoặc hiển thị hóa đơn

#### Phân công công việc:
- Lữ Trung Anh: Nhập thông tin khách hàng và sản phẩm
- Phạm Gia Bách: Tính tổng tiền, thuế, giảm giá
- Phùng Thị Hạ Lam: Lưu và in hóa đơn
- Cả nhóm: Gọi tất cả các phần tạo thành hàm `main()`

## Tiến độ dự án
- [x] Phân tích đối tượng (SanPham, KhachHang, HoaDon)
- [x] Viết các class chính
- [x] Viết class kiểm thử
- [x] Cập nhật tài liệu
      

#### Phân công công việc:
- Lữ Trung Anh: Nhập thông tin khách hàng và sản phẩm
- Phạm Gia Bách: Tính tổng tiền, thuế, giảm giá
- Phùng Thị Hạ Lam: Lưu và in hóa đơn
- Cả nhóm: Gọi tất cả các phần tạo thành hàm `main()`
🧾 Phân công chức năng & Miêu tả phương thức
👤 Lữ Trung Anh – Nhập thông tin khách hàng và sản phẩm
Phương thức: nhapThongTinKhachHangVaSanPham()

Chức năng: Nhập tên khách hàng, SĐT, danh sách sản phẩm (tên, số lượng, đơn giá).

Trả về: Đối tượng HoaDon.

👤 Phạm Gia Bách – Tính tổng tiền, giảm giá và thuế
Phương thức: tinhTongTien(ArrayList<SanPham> danhSachSanPham)

Chức năng:

Tính tổng tiền gốc.

Giảm 5% nếu > 500.000đ.

Tính thuế VAT 10%.

Trả về: Tổng tiền cuối cùng (double).

👤 Phùng Thị Hạ Lam – Lưu và in hóa đơn
Phương thức: luuVaInHoaDon(HoaDon hoaDon, double tongTienCuoiCung)

Chức năng:

In hóa đơn ra màn hình.

Ghi hóa đơn vào file hoadon.txt.

Trả về: Không (void).

👥 Cả nhóm – Hàm main()
Gọi lần lượt các chức năng:

nhapThongTinKhachHangVaSanPham()

tinhTongTien(...)

luuVaInHoaDon(...)

Điều phối toàn bộ chương trình quản lý bán hàng.

## Nội dung 01: Xây dựng ứng dụng với yêu cầu:
Giao diện Java Spring Boot:
Ứng dụng có thể được xây dựng trên nền tảng Java Spring Boot, với các tính năng quản lý sản phẩm, khách hàng, và hóa đơn. Các yêu cầu chi tiết gồm:

## Quản lý sản phẩm:

Thêm, sửa, xóa thông tin sản phẩm.

Liệt kê danh sách sản phẩm và có thể lọc theo các tiêu chí như tên sản phẩm, loại sản phẩm, giá tiền.

## Quản lý khách hàng:

Thêm, sửa, xóa thông tin khách hàng.

Liệt kê danh sách khách hàng và có thể tìm kiếm theo các tiêu chí như tên khách hàng, địa chỉ, điện thoại.

## Quản lý hóa đơn:

Tạo hóa đơn mới cho các khách hàng đã có.

Liệt kê các hóa đơn đã tạo và có thể tìm kiếm theo các tiêu chí như ngày hóa đơn, số hóa đơn, khách hàng.

## Chức năng liên kết giữa các đối tượng:

Gán sản phẩm cho hóa đơn (sản phẩm có thể được thêm vào hóa đơn khi bán).

Dữ liệu sẽ được lưu trữ dưới dạng file nhị phân (ví dụ: sử dụng ObjectOutputStream hoặc Serializable để lưu trữ đối tượng).

## Lưu trữ dữ liệu trong bộ nhớ:

Dữ liệu sẽ được lưu trong các Collection (ArrayList, LinkedList, Map, v.v.) để quản lý các đối tượng.

Cập nhật và kiểm thử để đảm bảo rằng dữ liệu được lưu trữ đúng và dễ dàng xử lý.

## Các lớp liên quan:
SanPham: Quản lý thông tin sản phẩm (ID, tên, loại, giá, số lượng).

KhachHang: Quản lý thông tin khách hàng (ID, tên, địa chỉ, điện thoại).

HoaDon: Quản lý thông tin hóa đơn (ID hóa đơn, danh sách sản phẩm, ngày tạo, khách hàng).

### Chức năng này cho phép nhân viên thu ngân tạo hóa đơn khi khách thanh toán, lưu thông tin vào hệ thống và in ra.

## Nội dung 2
![Sơ đồ lớp quản lý bán hàng](sơ-đồ-Class-Diagram.jpg)
## Nội dung 3 
![Sơ đồ hoạt động](activity_diagram.jpg)
![Sơ đồ tuần tự](sequence_diagram.jpg)


## Liên hệ
Nếu có thắc mắc hoặc góp ý, vui lòng liên hệ:
- Email: 23010339@st.phenikaa-uni.edu.vn 
- GitHub: https://github.com/GbachPham/OOP_N01_Term3_2025_K17_Group


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



# AdminWebsite

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 20.0.4.

## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Karma](https://karma-runner.github.io) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
