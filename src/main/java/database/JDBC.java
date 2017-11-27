package database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBC {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/itis",
                "admin",
                "????????"
        );
        return connection;
    }

    public static void main(String[] args) {

        System.out.println("-------- PostgreSQL "
                + "database.JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL database.JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL database.JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/itis", "admin",
                    "asd123");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("Connection is OK!");
        } else {
            System.out.println("Failed tМo make connection!");
        }
    }

}