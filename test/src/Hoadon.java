public class Hoadon {
    private String maHoaDon;
    private String ngayBan;
    private double tongTien;

    public Hoadon(String maHoaDon, String ngayBan, double tongTien) {
        try {
            this.maHoaDon = maHoaDon;
            this.ngayBan = ngayBan;
            this.tongTien = tongTien;
        } catch (Exception e) {
            System.out.println("Lỗi khởi tạo Hoadon: " + e.getMessage());
        }
    }

    public String getMaHoaDon() {
        try {
            return maHoaDon;
        } catch (Exception e) {
            System.out.println("Lỗi getMaHoaDon: " + e.getMessage());
            return null;
        }
    }

    public void setMaHoaDon(String maHoaDon) {
        try {
            this.maHoaDon = maHoaDon;
        } catch (Exception e) {
            System.out.println("Lỗi setMaHoaDon: " + e.getMessage());
        }
    }

    public String getNgayBan() {
        try {
            return ngayBan;
        } catch (Exception e) {
            System.out.println("Lỗi getNgayBan: " + e.getMessage());
            return null;
        }
    }

    public void setNgayBan(String ngayBan) {
        try {
            this.ngayBan = ngayBan;
        } catch (Exception e) {
            System.out.println("Lỗi setNgayBan: " + e.getMessage());
        }
    }

    public double getTongTien() {
        try {
            return tongTien;
        } catch (Exception e) {
            System.out.println("Lỗi getTongTien: " + e.getMessage());
            return 0;
        }
    }

    public void setTongTien(double tongTien) {
        try {
            this.tongTien = tongTien;
        } catch (Exception e) {
            System.out.println("Lỗi setTongTien: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        try {
            return "Hoadon{" +
                    "maHoaDon='" + maHoaDon + '\'' +
                    ", ngayBan='" + ngayBan + '\'' +
                    ", tongTien=" + tongTien +
                    '}';
        } catch (Exception e) {
            System.out.println("Lỗi toString: " + e.getMessage());
            return "";
        }
    }
}