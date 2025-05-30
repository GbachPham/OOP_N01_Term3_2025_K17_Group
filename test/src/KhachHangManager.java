import java.util.ArrayList;
import java.util.List;

public class KhachHangManager {
    private List<KhachHang> danhSachKhachHang = new ArrayList<>();

    // Create
    public void themKhachHang(KhachHang kh) {
        danhSachKhachHang.add(kh);
    }

    // Read
    public List<KhachHang> layTatCaKhachHang() {
        return danhSachKhachHang;
    }

    public KhachHang timKhachHangTheoMa(String maKH) {
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getMaKhachHang().equals(maKH)) {
                return kh;
            }
        }
        return null;
    }

    // Update
    public boolean capNhatKhachHang(String maKH, String tenMoi, String diaChiMoi, String sdtMoi) {
        KhachHang kh = timKhachHangTheoMa(maKH);
        if (kh != null) {
            kh.setTenKhachHang(tenMoi);
            kh.setDiaChi(diaChiMoi);
            kh.setSoDienThoai(sdtMoi);
            return true;
        }
        return false;
    }

    // Delete
    public boolean xoaKhachHang(String maKH) {
        KhachHang kh = timKhachHangTheoMa(maKH);
        if (kh != null) {
            danhSachKhachHang.remove(kh);
            return true;
        }
        return false;
    }
}