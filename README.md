# Hệ Thống Quản Lý Bán Hàng

## Mô tả đề tài
Đây là đồ án lập trình hướng đối tượng với mục tiêu xây dựng hệ thống quản lý bán hàng. Hệ thống cho phép quản lý thông tin sản phẩm, khách hàng và hóa đơn.

## Thành viên nhóm
- **Lữ Trung Anh**: Quản lý
- **Phạm Gia Bách**: Lập trình viên
- **Phùng Thị Hạ Lam**: Kiểm thử

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

## Chức năng chính
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

## Tiến độ dự án
- [x] Phân tích đối tượng (SanPham, KhachHang, HoaDon)
- [x] Viết các class chính
- [x] Viết class kiểm thử
- [x] Cập nhật tài liệu

## Yêu cầu hệ thống
- Java Development Kit (JDK) 8 trở lên
- NetBeans IDE (khuyến nghị)

## Hướng dẫn cài đặt và chạy
1. Clone repository về máy local
2. Mở project trong NetBeans IDE
3. Build project bằng Ant (F11)
4. Chạy project (F6)

## Công nghệ sử dụng
- Java
- JUnit (cho kiểm thử)
- Ant (cho build)
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

## Công nghệ sử dụng:
Spring Boot: Phát triển ứng dụng web với các API RESTful cho việc quản lý.

JPA/Hibernate: Quản lý cơ sở dữ liệu (nếu ứng dụng sử dụng cơ sở dữ liệu SQL).

File lưu trữ dữ liệu: Lưu trữ dữ liệu trong file nhị phân khi người dùng muốn lưu trạng thái của hệ thống.

JUnit: Kiểm thử ứng dụng (kiểm thử đơn vị cho các lớp quản lý sản phẩm, khách hàng, và hóa đơn).

## Các bước thực hiện:
Xây dựng các lớp đối tượng: Tạo các class SanPham, KhachHang, HoaDon.

Xây dựng các lớp dịch vụ: Viết các dịch vụ cho việc thêm, sửa, xóa và liệt kê thông tin.

Xây dựng các controller: Xây dựng API cho các chức năng qua Spring Boot.

Cập nhật tài liệu và kiểm thử: Viết tài liệu hướng dẫn sử dụng và thực hiện các bài kiểm thử.
## Nội dung 2
https://drive.google.com/file/d/1fVUrKdy0wAAp2NzcNmFW9QYr8DmlI_G_/view
## Nội dung 3 
https://drive.google.com/drive/folders/16AszyIfwY9w7SGOBOKqGMg_51zVb8xwS?usp=sharing

## Liên hệ
Nếu có thắc mắc hoặc góp ý, vui lòng liên hệ:
- Email: 23010661@st.phenikaa-uni.edu.vn
- GitHub: https://github.com/GbachPham/OOP_N01_Term3_2025_K17_Group
