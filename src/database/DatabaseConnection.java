package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // اطلاعات اتصال به دیتابیس شما
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mobin";

    /**
     * این متد یک اتصال فعال به دیتابیس برقرار می‌کند
     */
    public static Connection getConnection() throws SQLException {
        try {
            // بارگذاری درایور (در نسخه‌های جدید اختیاری است اما نوشتنش بهتر است)
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found!", e);
        }
    }
}
