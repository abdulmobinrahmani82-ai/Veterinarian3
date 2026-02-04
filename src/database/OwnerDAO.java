package database;

import model.OwnerEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAO {

    // = INSERT =
    public boolean insertOwner(OwnerEntity owner) {
        String sql = "INSERT INTO owner (id, name, phone, address, age) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, owner.getId());
            stmt.setString(2, owner.getName());
            stmt.setString(3, owner.getPhone());
            stmt.setString(4, owner.getAddress());
            stmt.setInt(5, owner.getAge());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Owner inserted successfully.");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert failed. Maybe the ID already exists.");
            e.printStackTrace();
        }
        return false;
    }

    // = SELECT ALL =
    public void viewAllOwners() {
        String sql = "SELECT * FROM owner ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- OWNERS FROM DATABASE ---");
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Phone: " + rs.getString("phone") +
                                " | Address: " + rs.getString("address") +
                                " | Age: " + rs.getInt("age")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // = GET BY ID =
    public OwnerEntity getOwnerById(int id) {
        String sql = "SELECT * FROM owner WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OwnerEntity(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("age")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // = UPDATE =
    public boolean updateOwner(OwnerEntity owner) {
        String sql = "UPDATE owner SET name = ?, phone = ?, address = ?, age = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, owner.getName());
            stmt.setString(2, owner.getPhone());
            stmt.setString(3, owner.getAddress());
            stmt.setInt(4, owner.getAge());
            stmt.setInt(5, owner.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Owner updated successfully.");
                return true;
            } else {
                System.out.println("❌ No owner found with this ID.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Update failed.");
            e.printStackTrace();
        }
        return false;
    }

    // = DELETE =
    public boolean deleteOwner(int id) {
        String sql = "DELETE FROM owner WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Owner deleted successfully.");
                return true;
            } else {
                System.out.println("❌ No owner found with this ID.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Delete failed.");
            e.printStackTrace();
        }
        return false;
    }

    // = SEARCH BY NAME =
    public List<OwnerEntity> searchByName(String name) {
        List<OwnerEntity> owners = new ArrayList<>();
        String sql = "SELECT * FROM owner WHERE name ILIKE ? ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    owners.add(new OwnerEntity(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("age")
                    ));
                }
            }

            if (owners.isEmpty()) {
                System.out.println("❌ No owners found with name containing '" + name + "'.");
            } else {
                System.out.println("--- Search Results ---");
                owners.forEach(System.out::println);
            }

        } catch (SQLException e) {
            System.out.println("❌ Search failed!");
            e.printStackTrace();
        }

        return owners;
    }

    // = SEARCH BY MIN AGE =
    public List<OwnerEntity> searchByMinAge(int minAge) {
        List<OwnerEntity> owners = new ArrayList<>();
        String sql = "SELECT * FROM owner WHERE age >= ? ORDER BY age";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, minAge);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    owners.add(new OwnerEntity(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("age")
                    ));
                }
            }

            if (owners.isEmpty()) {
                System.out.println("❌ No owners found with age >= " + minAge);
            } else {
                System.out.println("--- Owners with age >= " + minAge + " ---");
                owners.forEach(System.out::println);
            }

        } catch (SQLException e) {
            System.out.println("❌ Search by age failed!");
            e.printStackTrace();
        }

        return owners;
    }
}
