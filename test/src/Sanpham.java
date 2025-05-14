public class Sanpham {
    private String maSanPham;
    private String ten;
    private double gia;
    private int soLuong;

    public Sanpham(String maSanPham, String ten, double gia, int soLuong) {
        this.maSanPham = maSanPham;
        this.ten = ten;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getMaSanPham() { return maSanPham; }
    public void setMaSanPham(String maSanPham) { this.maSanPham = maSanPham; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    @Override
    public String toString() {
        return "Sanpham{" +
                "maSanPham='" + maSanPham + '\'' +
                ", ten='" + ten + '\'' +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                '}';
    }
}