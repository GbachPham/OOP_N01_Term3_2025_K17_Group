public class SanPham {
    private String ma;
    private String ten;
    private double gia;
    private int soLuong;

    public SanPham(String ma, String ten, double gia, int soLuong) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public void setTen(String ten) { this.ten = ten; }
    public void setGia(double gia) { this.gia = gia; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public String getMa() { return ma; }

    @Override
    public String toString() {
        return ma + " - " + ten + " - " + gia + " - " + soLuong;
    }
}