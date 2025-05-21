# Hệ Thống Quản Lý Bán Hàng

## Mô tả đề tài
Đây là đồ án lập trình hướng đối tượng với mục tiêu xây dựng hệ thống quản lý bán hàng. Hệ thống cho phép quản lý thông tin sản phẩm, khách hàng và hóa đơn.

## Thành viên nhóm
- **Lữ Trung Anh**: Quản lý
- **Phạm Gia Bách**: Lập trình viên
- **Phùng Thị Hạ Lam**: Kiểm thử

## Cấu trúc dự án
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

## Liên hệ
Nếu có thắc mắc hoặc góp ý, vui lòng liên hệ:
- Email: 23010661@st.phenikaa-uni.edu.vn
- GitHub: https://github.com/GbachPham/OOP_N01_Term3_2025_K17_Group
