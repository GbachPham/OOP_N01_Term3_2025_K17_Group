import java.util.ArrayList;
import java.util.List;

public class HoadonManager {
    private List<Hoadon> danhSachHoaDon = new ArrayList<>();

    // Create
    public void themHoaDon(Hoadon hoadon) {
        try {
            danhSachHoaDon.add(hoadon);
        } catch (Exception e) {
            System.out.println("Lỗi thêm hóa đơn: " + e.getMessage());
        }
    }

    // Read
    public List<Hoadon> layTatCaHoaDon() {
        try {
            return danhSachHoaDon;
        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách hóa đơn: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Hoadon timHoaDonTheoMa(String maHoaDon) {
        try {
            for (Hoadon hd : danhSachHoaDon) {
                if (hd.getMaHoaDon().equals(maHoaDon)) {
                    return hd;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm hóa đơn theo mã: " + e.getMessage());
        }
        return null;
    }

    // Update
    public boolean capNhatHoaDon(String maHoaDon, String ngayBanMoi, double tongTienMoi) {
        try {
            Hoadon hd = timHoaDonTheoMa(maHoaDon);
            if (hd != null) {
                hd.setNgayBan(ngayBanMoi);
                hd.setTongTien(tongTienMoi);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật hóa đơn: " + e.getMessage());
        }
        return false;
    }

    // Delete
    public boolean xoaHoaDon(String maHoaDon) {
        try {
            Hoadon hd = timHoaDonTheoMa(maHoaDon);
            if (hd != null) {
                danhSachHoaDon.remove(hd);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Lỗi xóa hóa đơn: " + e.getMessage());
        }
        return false;
    }
}