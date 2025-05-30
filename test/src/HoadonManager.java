import java.util.ArrayList;
import java.util.List;

public class HoadonManager {
    private List<Hoadon> danhSachHoaDon = new ArrayList<>();

    // Create
    public void themHoaDon(Hoadon hoadon) {
        danhSachHoaDon.add(hoadon);
    }

    // Read
    public List<Hoadon> layTatCaHoaDon() {
        return danhSachHoaDon;
    }

    public Hoadon timHoaDonTheoMa(String maHoaDon) {
        for (Hoadon hd : danhSachHoaDon) {
            if (hd.getMaHoaDon().equals(maHoaDon)) {
                return hd;
            }
        }
        return null;
    }

    // Update
    public boolean capNhatHoaDon(String maHoaDon, String ngayBanMoi, double tongTienMoi) {
        Hoadon hd = timHoaDonTheoMa(maHoaDon);
        if (hd != null) {
            hd.setNgayBan(ngayBanMoi);
            hd.setTongTien(tongTienMoi);
            return true;
        }
        return false;
    }

    // Delete
    public boolean xoaHoaDon(String maHoaDon) {
        Hoadon hd = timHoaDonTheoMa(maHoaDon);
        if (hd != null) {
            danhSachHoaDon.remove(hd);
            return true;
        }
        return false;
    }
}