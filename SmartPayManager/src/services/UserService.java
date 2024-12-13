package services;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public int authenticate(String username, String password) {
        String query = "SELECT id FROM Users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
        return -1;
    }

    public boolean register(String username, String password) {
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
        return false;
    }

    public boolean isUsernameExists(String username) {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Return true if username exists
            }

        } catch (SQLException e) {
            System.out.println("Error checking username existence: " + e.getMessage());
        }
        return false; // Default to false if an error occurs or username doesn't exist
    }
}

