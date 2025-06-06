public class App {
    public static void main(String[] args) throws Exception {
        // A nhập thông tin
        HoaDon hoaDon = SinhVienA.nhapThongTinKhachHangVaSanPham();

        // B tính toán
        double tongTienPhaiTra = SinhVienB.tinhTongTien(hoaDon.danhSachSanPham);

        // C lưu và in hóa đơn
        SinhVienC.luuVaInHoaDon(hoaDon, tongTienPhaiTra);
    }
}
