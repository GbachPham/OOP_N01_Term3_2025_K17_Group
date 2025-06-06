import java.util.ArrayList;

public class SinhVienB {
    /**
     * Tính tổng tiền phải trả cho danh sách sản phẩm, bao gồm giảm giá và thuế VAT.
     * @param danhSachSanPham Danh sách sản phẩm trong hóa đơn
     * @return Tổng tiền cuối cùng sau khi áp dụng giảm giá và thuế
     */
    public static double tinhTongTien(ArrayList<SanPham> danhSachSanPham) {
        double tong = 0;
        for (SanPham sp : danhSachSanPham) {
            tong += sp.soLuong * sp.donGia;
        }

        // Áp dụng giảm giá nếu tổng > 500,000
        double giamGia = (tong > 500000) ? tong * 0.05 : 0;

        // Thuế VAT 10%
        double thue = (tong - giamGia) * 0.10;

        double tongSauGiamVaThue = tong - giamGia + thue;

        // In thông tin chi tiết
        System.out.printf("Tổng tiền gốc: %.0f VND\n", tong);
        System.out.printf("Giảm giá: %.0f VND\n", giamGia);
        System.out.printf("Thuế VAT (10%%): %.0f VND\n", thue);
        System.out.printf("Tổng phải trả: %.0f VND\n", tongSauGiamVaThue);

        return tongSauGiamVaThue;
    }
}