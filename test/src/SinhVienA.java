import java.util.ArrayList;
import java.util.Scanner;

class SanPham {
    String tenSanPham;
    int soLuong;
    double donGia;

    public SanPham(String tenSanPham, int soLuong, double donGia) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return tenSanPham + ": " + soLuong + " x " + donGia + " VND";
    }
}

class HoaDon {
    String tenKhachHang;
    String soDienThoai;
    ArrayList<SanPham> danhSachSanPham;

    public HoaDon(String tenKhachHang, String soDienThoai, ArrayList<SanPham> danhSachSanPham) {
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.danhSachSanPham = danhSachSanPham;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Khách hàng: ").append(tenKhachHang).append("\n");
        sb.append("SĐT: ").append(soDienThoai).append("\n");
        sb.append("Danh sách sản phẩm:\n");
        for (SanPham sp : danhSachSanPham) {
            sb.append("- ").append(sp).append("\n");
        }
        return sb.toString();
    }
}

public class SinhVienA {

    public static HoaDon nhapThongTinKhachHangVaSanPham() {
        Scanner scanner = new Scanner(System.in);

        // Nhập thông tin khách hàng
        System.out.print("Nhập tên khách hàng: ");
        String tenKhachHang = scanner.nextLine();

        System.out.print("Nhập số điện thoại: ");
        String soDienThoai = scanner.nextLine();

        // Nhập danh sách sản phẩm
        ArrayList<SanPham> danhSachSanPham = new ArrayList<>();
        System.out.print("Nhập số lượng sản phẩm: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Xóa dòng thừa

        for (int i = 0; i < n; i++) {
            System.out.println("Sản phẩm #" + (i + 1));
            System.out.print("Tên sản phẩm: ");
            String ten = scanner.nextLine();

            System.out.print("Số lượng: ");
            int soLuong = scanner.nextInt();

            System.out.print("Đơn giá: ");
            double donGia = scanner.nextDouble();
            scanner.nextLine(); // Xóa dòng thừa

            danhSachSanPham.add(new SanPham(ten, soLuong, donGia));
        }

        // Trả về đối tượng hóa đơn
        return new HoaDon(tenKhachHang, soDienThoai, danhSachSanPham);
    }

    // Hàm main để kiểm thử phương thức
    public static void main(String[] args) {
        HoaDon hoaDon = nhapThongTinKhachHangVaSanPham();

        System.out.println("\n--- Thông tin hóa đơn ---");
        System.out.println(hoaDon);
    }
}
