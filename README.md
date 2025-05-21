Hệ Thống Quản Lý Bán Hàng
Mô tả đề tài
Đây là đồ án lập trình hướng đối tượng với mục tiêu xây dựng hệ thống quản lý bán hàng. Hệ thống cho phép quản lý thông tin sản phẩm, khách hàng và hóa đơn. Ứng dụng sử dụng Java Spring Boot làm giao diện chính và lưu trữ dữ liệu dưới dạng file nhị phân. Dữ liệu trong bộ nhớ được quản lý bằng các Collection như ArrayList, HashMap,...

Thành viên nhóm
Lữ Trung Anh: Quản lý

Phạm Gia Bách: Lập trình viên

Phùng Thị Hạ Lam: Kiểm thử

Cấu trúc dự án
bash
Copy
Edit
├── src/                    
│   ├── model/
│   │   ├── Sanpham.java       
│   │   ├── Khachhang.java     
│   │   ├── Hoadon.java        
│   │   └── DataManager.java   # Đọc/ghi file nhị phân
│   ├── controller/           
│   │   ├── SanphamController.java
│   │   ├── KhachhangController.java
│   │   └── HoadonController.java
│   ├── service/
│   │   ├── SanphamService.java
│   │   ├── KhachhangService.java
│   │   └── HoadonService.java
│   └── Application.java       # Spring Boot main class
├── resources/
│   └── application.properties
├── binary/                   # Dữ liệu nhị phân
│   └── data.dat
├── diagrams/                 # Sơ đồ UML
│   ├── class_diagram.png
│   ├── sequence_diagram_1.png
│   ├── sequence_diagram_2.png
│   └── activity_diagram.png
├── test/                    
│   └── SanphamTest.java
├── build/
├── build.xml
└── manifest.mf
Chức năng chính
Quản lý Sản phẩm

Thêm, sửa, xóa thông tin sản phẩm

Liệt kê danh sách sản phẩm

Tìm kiếm sản phẩm theo mã, tên, loại,...

Dữ liệu được lưu và đọc từ file nhị phân

Quản lý Khách hàng

Thêm, sửa, xóa thông tin khách hàng

Liệt kê danh sách khách hàng

Tìm kiếm khách hàng theo tên, mã, số điện thoại

Quản lý Hóa đơn

Tạo hóa đơn mới (gán sản phẩm cho khách hàng)

Liệt kê danh sách hóa đơn

Tìm kiếm hóa đơn theo mã hóa đơn, khách hàng, ngày mua

Lưu trữ và xử lý dữ liệu

Dữ liệu được lưu trữ bằng các lớp:

DataManager.java: đọc/ghi file nhị phân

SanphamService.java, KhachhangService.java, HoadonService.java: thao tác dữ liệu trong RAM bằng ArrayList hoặc HashMap

Tiến độ dự án
 Phân tích đối tượng (Sanpham, Khachhang, Hoadon)

 Xây dựng sơ đồ UML

 Viết các class mô hình, điều khiển và dịch vụ

 Giao diện Spring Boot

 Lưu trữ dữ liệu nhị phân

 Viết class kiểm thử

 Cập nhật tài liệu và biểu đồ

Yêu cầu hệ thống
Java Development Kit (JDK) 8 trở lên

Spring Boot (>= 2.0)

NetBeans IDE hoặc IntelliJ IDEA

Apache Ant

JUnit

Hướng dẫn cài đặt và chạy
Clone repository về máy:

bash
Copy
Edit
git clone https://github.com/GbachPham/OOP_N01_Term3_2025_K17_Group.git
Mở project trong NetBeans hoặc IntelliJ

Build project bằng Ant hoặc Spring Boot

NetBeans: Nhấn F11 để build

IntelliJ: Run mvn clean install

Chạy ứng dụng:

NetBeans: Nhấn F6

IntelliJ hoặc terminal: mvn spring-boot:run

Công nghệ sử dụng
Java

Java Spring Boot (giao diện)

JUnit (kiểm thử)

Java IO (ghi file nhị phân)

Java Collections (ArrayList, HashMap, LinkedList,...)

Apache Ant (build)

UML & Sơ đồ hành vi
Class Diagram
Sơ đồ thể hiện mối quan hệ giữa các lớp Sanpham, Khachhang, Hoadon, DataManager, Controller, Service.

Sequence Diagrams
Tạo hóa đơn

Thêm sản phẩm mới

Activity Diagram
Quy trình xử lý đơn hàng từ khi tạo đến lưu file

Các sơ đồ đều được lưu trong thư mục diagrams/.

Liên hệ
Nếu có thắc mắc hoặc góp ý, vui lòng liên hệ:

Email: 23010661@st.phenikaa-uni.edu.vn

GitHub: github.com/GbachPham/OOP_N01_Term3_2025_K17_Group

