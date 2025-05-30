public class TestSanPhamManager {
    public static void main(String[] args) {
        SanPhamManager manager = new SanPhamManager();

        // Test Create
        SanPham sp1 = new SanPham("SP01", "Laptop", 15000000, 10);
        SanPham sp2 = new SanPham("SP02", "Smartphone", 8000000, 20);
        manager.themSanPham(sp1);
        manager.themSanPham(sp2);

        // Test Read
        System.out.println("Danh sách sản phẩm:");
        for (SanPham sp : manager.layTatCaSanPham()) {
            System.out.println(sp);
        }

        // Test Update
        boolean updated = manager.capNhatSanPham("SP01", "Laptop Gaming", 18000000, 8);
        System.out.println("\nCập nhật sản phẩm SP01: " + (updated ? "Thành công" : "Thất bại"));
        System.out.println(manager.timSanPhamTheoMa("SP01"));

        // Test Delete
        boolean deleted = manager.xoaSanPham("SP02");
        System.out.println("\nXóa sản phẩm SP02: " + (deleted ? "Thành công" : "Thất bại"));
        System.out.println("Danh sách sản phẩm sau khi xóa:");
        for (SanPham sp : manager.layTatCaSanPham()) {
            System.out.println(sp);
        }
    }
}
