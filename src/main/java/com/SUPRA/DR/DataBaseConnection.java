package com.SUPRA.DR;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DataBaseConnection {

    public static void main(String[] args) {
        try {
            // Establish connection
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/supraOne", "root", "1337");

            // Execute a query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Player");

            // Process results
            while (resultSet.next()) {
                // Process each row
            }

            // Close connections
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

}
}
