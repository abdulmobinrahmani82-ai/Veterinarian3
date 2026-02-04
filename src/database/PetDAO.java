package database;

import java.sql.*;

public class PetDAO {

    // متد ذخیره حیوان در دیتابیس
    public void insertPet(String name, String species, int age, Integer ownerId) {
        String sql = "INSERT INTO pets (name, species, age, owner_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, species);
            pstmt.setInt(3, age);

            if (ownerId != null) {
                pstmt.setInt(4, ownerId);
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            pstmt.executeUpdate();
            System.out.println("✅ Pet saved to PostgreSQL!");

        } catch (SQLException e) {
            System.out.println("❌ Database Error (Pet): " + e.getMessage());
        }
    }

    // متد نمایش لیست حیوانات
    public void viewAllPets() {
        String sql = "SELECT * FROM pets";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- 🐾 Pets List from Database ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Type: " + rs.getString("species") +
                        " | Age: " + rs.getInt("age") +
                        " | Owner ID: " + rs.getInt("owner_id"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error loading pets: " + e.getMessage());
        }
    }
}