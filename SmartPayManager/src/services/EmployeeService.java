package services;

import models.Employee;
import models.PartTimeEmployee;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService {

    public void addEmployee(Employee employee, int loggedInUserId) {
        String query = "INSERT INTO Employees (name, type, salary, hoursWorked, user_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getType());

            // If the employee is a part-time employee, use hourly rate as salary
            if (employee instanceof PartTimeEmployee partTimeEmployee) {
                stmt.setDouble(3, partTimeEmployee.getHourlyRate());
            } else {
                stmt.setDouble(3, employee.getFixedSalary());
            }

            stmt.setInt(4, employee instanceof PartTimeEmployee ? employee.getHoursWorked() : 0);
            stmt.setInt(5, loggedInUserId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }


    public boolean isEmployeeExists(String name) {
        String sql = "SELECT COUNT(*) FROM Employees WHERE UPPER(name) = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking employee existence: " + e.getMessage());
        }
        return false;
    }

}
