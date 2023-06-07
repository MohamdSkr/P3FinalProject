package Modul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClinicDatabase {

    private static final String DB = "clinic_appointments";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE_HOST = "localhost";
    private Connection conn;
    private static ClinicDatabase instance;

    private ClinicDatabase() throws SQLException {
    }

    public static ClinicDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ClinicDatabase();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_appointments", USER, PASSWORD);
        return conn;
    }

}
