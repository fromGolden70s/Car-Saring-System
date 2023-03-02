package carsharing;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDbFile {
    public static void create(String fileName) {
        File file = new File("src/carsharing/db/" + fileName + ".mv.db");
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String PATH = "jdbc:h2:./src/carsharing/db/carsharing";

    public static void createTable() {

        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String createCompanyTable = "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR UNIQUE NOT NULL);";
            stmt.executeUpdate(createCompanyTable);
            String createCarTable = "CREATE TABLE IF NOT EXISTS CAR " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR UNIQUE NOT NULL, RENTED BOOLEAN DEFAULT 0, " +
                    "COMPANY_ID INT NOT NULL, FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (ID));";
            stmt.executeUpdate(createCarTable);
            String createCustomer = "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR UNIQUE NOT NULL, " +
                    "RENTED_CAR_ID INT, FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR (ID) " +
                    "ON DELETE SET NULL ON UPDATE SET NULL);";
            stmt.executeUpdate(createCustomer);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
