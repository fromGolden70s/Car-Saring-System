package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DataAccess{

    public void check() {
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();
            String text = "SELECT * FROM CUSTOMER WHERE ID = 1";
            ResultSet res = stmt.executeQuery(text);
            res.next();
            System.out.println(res.getInt("RENTED_CAR_ID") + " " + res.getString("NAME"));
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCompany(String name) {
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "INSERT INTO COMPANY (NAME)" +
                    "VALUES ('" + name + "');";
            stmt.executeUpdate(text);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Company> getAllCompanies() {
        List<Company> list = new LinkedList<>();
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "SELECT * FROM COMPANY;";
            ResultSet res = stmt.executeQuery(text);
            while (res.next()) {
                int i = res.getInt("ID");
                String n = res.getString("NAME");
                Company c = new Company(i, n);
                list.add(c);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void createCustomer(String name) {
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "INSERT INTO CUSTOMER (NAME)" +
                    "VALUES ('" + name + "');";
            stmt.executeUpdate(text);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new LinkedList<>();
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "SELECT * FROM CUSTOMER;";
            ResultSet res = stmt.executeQuery(text);
            while (res.next()) {
                int i = res.getInt("ID");
                String n = res.getString("NAME");
                int rentedCarId = res.getInt("RENTED_CAR_ID");
                Customer c = new Customer(i, n, rentedCarId);
                list.add(c);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void createCar(String name, int carCompanyId) {
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "INSERT INTO CAR (NAME, COMPANY_ID)" +
                    "VALUES ('" + name + "'," + carCompanyId + ");";
            stmt.executeUpdate(text);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rent(int carId, int customerId) {
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();
            String text = "UPDATE CUSTOMER SET RENTED_CAR_ID = " + carId + " WHERE ID = " + customerId + ";";
            stmt.executeUpdate(text);
            text = "UPDATE CAR SET RENTED = 1 WHERE ID = " + carId + ";";
            stmt.executeUpdate(text);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnCar(int customerId, int carId) {
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = " + customerId + " ;";
            stmt.executeUpdate(text);
            text = "UPDATE CAR SET RENTED = 0 WHERE ID = " + carId + ";";
            stmt.executeUpdate(text);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars(int companyId) {
        List<Car> list = new LinkedList<>();
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "SELECT * FROM CAR WHERE COMPANY_ID = " + companyId + " AND RENTED = 0;";
            ResultSet res = stmt.executeQuery(text);
            while (res.next()) {
                int i = res.getInt("ID");
                String n = res.getString("NAME");
                int coId = res.getInt("COMPANY_ID");
                Car c = new Car(i, n, coId);
                list.add(c);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Car getCarById(int id) {
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "SELECT * FROM CAR WHERE ID = " + id + ";";
            ResultSet res = stmt.executeQuery(text);
            res.next();

            int i = res.getInt("ID");
            String n = res.getString("NAME");
            int coId = res.getInt("COMPANY_ID");
            Car c = new Car(i, n, coId);

            stmt.close();
            conn.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Company getCompanyById(int id) {
        try {
            Class.forName(CreateDbFile.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(CreateDbFile.PATH);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();;
            String text = "SELECT * FROM COMPANY WHERE ID = " + id + ";";
            ResultSet res = stmt.executeQuery(text);
            res.next();

            int i = res.getInt("ID");
            String n = res.getString("NAME");
            Company c = new Company(i, n);

            stmt.close();
            conn.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
