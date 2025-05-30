public class App {
    public static void main(String[] args) throws Exception {
        
        KhachHangManager manager = new KhachHangManager();

        // Thêm khách hàng vào manager (kiểm thử phương thức thêm khách hàng)
        KhachHang kh1 = new KhachHang("KH01", "Nguyen Van A", "Ha Noi", "0901234567");
        KhachHang kh2 = new KhachHang("KH02", "Tran Thi B", "Hai Phong", "0912345678");
        manager.themKhachHang(kh1);
        manager.themKhachHang(kh2);

        // Hiển thị danh sách khách hàng ở Hà Nội
        manager.lietKeKhachHangTheoDiaChi("Ha Noi");
    }

}
