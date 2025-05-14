public class Hoadon {
    private String maHoaDon;
    private String ngayBan;
    private double tongTien;

    public Hoadon(String maHoaDon, String ngayBan, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayBan = ngayBan;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }
    public String getNgayBan() { return ngayBan; }
    public void setNgayBan(String ngayBan) { this.ngayBan = ngayBan; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    @Override
    public String toString() {
        return "Hoadon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", ngayBan='" + ngayBan + '\'' +
                ", tongTien=" + tongTien +
                '}';
    }
}