package database;

import java.sql.*;

public class OwnerDAO {

    // متد برای ذخیره کردن در دیتابیس
    public void insertOwner(String name, String phone, String address) {
        String sql = "INSERT INTO owners (name, phone, address) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Database Insert Error: " + e.getMessage());
        }
    }

    // متد برای نمایش اطلاعات از دیتابیس
    public void viewAllOwners() {
        String sql = "SELECT * FROM owners";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- 📋 Owners List from PostgreSQL ---");
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                System.out.println("ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Phone: " + rs.getString("phone") +
                        " | Address: " + rs.getString("address"));
            }

            if (!hasData) {
                System.out.println("No records found in database.");
            }
            System.out.println("--------------------------------------");

        } catch (SQLException e) {
            System.out.println("❌ Database Select Error: " + e.getMessage());
        }
    }
}