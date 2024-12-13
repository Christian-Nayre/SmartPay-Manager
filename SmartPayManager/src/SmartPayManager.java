import models.*;
import services.EmployeeService;
import services.UserService;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class SmartPayManager {

    private static boolean isLoggedIn = false;
    private static int loggedInUserId = -1;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("░░      ░░░  ░░░░  ░░░      ░░░       ░░░        ░░       ░░░░      ░░░  ░░░░  ░");
        System.out.println("▒  ▒▒▒▒▒▒▒▒   ▒▒   ▒▒  ▒▒▒▒  ▒▒  ▒▒▒▒  ▒▒▒▒▒  ▒▒▒▒▒  ▒▒▒▒  ▒▒  ▒▒▒▒  ▒▒▒  ▒▒  ▒▒");
        System.out.println("▓▓      ▓▓▓        ▓▓  ▓▓▓▓  ▓▓       ▓▓▓▓▓▓  ▓▓▓▓▓       ▓▓▓  ▓▓▓▓  ▓▓▓▓    ▓▓▓");
        System.out.println("███████  ██  █  █  ██        ██  ███  ██████  █████  ████████        █████  ████");
        System.out.println("██      ███  ████  ██  ████  ██  ████  █████  █████  ████████  ████  █████  ████");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("░░░░░░░  ░░░░  ░░░      ░░░   ░░░  ░░░      ░░░░      ░░░        ░░       ░░░░░░");
        System.out.println("▒▒▒▒▒▒▒   ▒▒   ▒▒  ▒▒▒▒  ▒▒    ▒▒  ▒▒  ▒▒▒▒  ▒▒  ▒▒▒▒▒▒▒▒  ▒▒▒▒▒▒▒▒  ▒▒▒▒  ▒▒▒▒▒");
        System.out.println("▓▓▓▓▓▓▓        ▓▓  ▓▓▓▓  ▓▓  ▓  ▓  ▓▓  ▓▓▓▓  ▓▓  ▓▓▓   ▓▓      ▓▓▓▓       ▓▓▓▓▓▓");
        System.out.println("███████  █  █  ██        ██  ██    ██        ██  ████  ██  ████████  ███  ██████");
        System.out.println("███████  ████  ██  ████  ██  ███   ██  ████  ███      ███        ██  ████  █████");
        System.out.println("================================================================================");
        while (true) {
            if (!isLoggedIn) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showLoginMenu() {
        while (true) {
            try {
                System.out.println("\n🎉 Welcome to SmartPay Manager 🎉");
                System.out.println("1. 🔐 Login");
                System.out.println("2. ✍️ Register");
                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        login();
                        return;
                    case 2:
                        register();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void login() {
        System.out.println("\nLogin:");

        System.out.print("Username(leave blank to go back to menu): ");
        String username = scanner.nextLine();

        if (username.trim().isEmpty()) {
            System.out.println("Returning to the menu.");
            return;
        }

        System.out.print("Password (4-digit numeric): ");
        String password = scanner.nextLine();

        if (password.trim().isEmpty() || !password.matches("\\d{4}")) {
            System.out.println("Incorrect Password, Returning to the menu.");
            return;
        }

        UserService userService = new UserService();
        loggedInUserId = userService.authenticate(username, password);

        if (loggedInUserId != -1) {
            System.out.println("Login successful!");
            isLoggedIn = true;
        } else {
            System.out.println("Invalid credentials, please try again.");
        }
    }



    private static void register() {
        System.out.println("\nRegister a new account:");

        String username;
        while (true) {
            System.out.print("Username (leave blank to go back to menu): ");
            username = scanner.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("Returning to the menu.");
                return;
            }

            UserService userService = new UserService();
            if (userService.isUsernameExists(username)) {
                System.out.println("Username already exists. Please choose a different username.");
            } else {
                break;
            }
        }

        String password;
        while (true) {
            System.out.print("Password (must be 4-digit numeric or leave blank to go back to menu): ");
            password = scanner.nextLine().trim();

            if (password.isEmpty()) {
                System.out.println("Returning to the menu.");
                return;
            }

            if (!password.matches("\\d{4}")) {
                System.out.println("Password must be a 4-digit numeric value. Please try again.");
            } else {
                break;
            }
        }

        UserService userService = new UserService();
        boolean isRegistered = userService.register(username, password);

        if (isRegistered) {
            System.out.println("Registration successful! You can now log in.");
        } else {
            System.out.println("Registration failed. Returning to menu.");
        }
    }



    private static void showMainMenu() {
        while (true) {
            try {
                System.out.println("\n🎉 Welcome to SmartPay Manager! 🎉");
                System.out.println("1. 🧑‍💼 Add Employee");
                System.out.println("2. ❌ Remove Employee");
                System.out.println("3. 👀 View Employees");
                System.out.println("4. ⏰ Log Hours Worked");
                System.out.println("5. 💸 Process Payroll");
                System.out.println("6. 🗂️ View Past Transactions");
                System.out.println("7. 🚪 Logout");
                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        removeEmployee();
                        break;
                    case 3:
                        viewEmployees();
                        break;
                    case 4:
                        logHoursWorked();
                        break;
                    case 5:
                        processPayroll();
                        break;
                    case 6:
                        viewPastTransactions();
                        break;
                    case 7:
                        logout();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    private static void addEmployee() {
        System.out.println("\nAdd Employee");

        var choice = 0;

        while (true) {
            System.out.println("Choose an employee type:");
            System.out.println("1. Full-Time");
            System.out.println("2. Part-Time");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Please enter 1 for Full-Time or 2 for Part-Time.");
                }
            } else {
                System.out.println("Please enter 1 for Full-Time or 2 for Part-Time.");
                scanner.nextLine();
            }
        }

        String name;
        while (true) {
            System.out.print("Enter employee name: ");
            name = scanner.nextLine().trim().toUpperCase();

            if (name.isEmpty()) {
                System.out.println("Employee name cannot be blank. Please try again.");
            } else {
                EmployeeService employeeService = new EmployeeService();

                if (employeeService.isEmployeeExists(name)) {
                    System.out.println("Employee already exists. Please enter a different name.");
                } else {
                    break;
                }
            }
        }

        Employee employee = null;

        if (choice == 1) {
            while (true) {
                System.out.print("Enter fixed salary: ");
                if (scanner.hasNextDouble()) {
                    double fixedSalary = scanner.nextDouble();
                    scanner.nextLine();

                    if (fixedSalary > 0) {
                        employee = new FullTimeEmployee(name, fixedSalary);
                        break;
                    } else {
                        System.out.println("Salary must be a positive number. Please try again.");
                    }
                } else {
                    System.out.println("Salary must be a valid number. Please try again.");
                    scanner.nextLine();
                }
            }
        } else {
            while (true) {
                System.out.print("Enter hourly rate: ");
                if (scanner.hasNextDouble()) {
                    double hourlyRate = scanner.nextDouble();
                    scanner.nextLine();

                    if (hourlyRate > 0) {
                        employee = new PartTimeEmployee(name, hourlyRate);
                        break;
                    } else {
                        System.out.println("Hourly rate must be a positive number. Please try again.");
                    }
                } else {
                    System.out.println("Hourly rate must be a valid number. Please try again.");
                    scanner.nextLine();
                }
            }
        }

        EmployeeService employeeService = new EmployeeService();
        employeeService.addEmployee(employee, loggedInUserId);
    }



    private static void removeEmployee() {
        System.out.println("\nRemove Employee");

        int choice = 0;

        while (choice != 1 && choice != 2) {
            System.out.println("Choose an option:");
            System.out.println("1. Remove a specific employee");
            System.out.println("2. Remove all employees");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.nextLine();
            }
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (choice == 1) {
                System.out.print("Enter the name of the employee to remove: ");
                String name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Employee name cannot be blank.");
                    return;
                }

                String sql = "DELETE FROM Employees WHERE name = ? AND user_id = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, name);
                    stmt.setInt(2, loggedInUserId);

                    int rowsAffected = stmt.executeUpdate();
                    System.out.println(rowsAffected > 0 ? "Employee removed successfully." : "Employee not found or doesn't belong to you.");
                }
            } else {
                String sql = "DELETE FROM Employees WHERE user_id = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, loggedInUserId);

                    int rowsAffected = stmt.executeUpdate();
                    System.out.println(rowsAffected > 0 ? "All employees removed successfully." : "No employees found to remove.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error removing employee: " + e.getMessage());
        }
    }



    private static void viewEmployees() {
        System.out.println("View Employees");

        String query = "SELECT * FROM Employees WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, loggedInUserId);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("Employees:");
            System.out.println("=======================================================================");
            System.out.printf("%-20s %-15s %-20s %-15s\n", "Employee Name", "Employee Type", "Salary", "Hours Worked");
            System.out.println("=======================================================================");

            while (resultSet.next()) {
                String employeeName = resultSet.getString("name");
                String employeeType = resultSet.getString("type");
                double salary = resultSet.getDouble("salary");
                int hoursWorked = resultSet.getInt("hoursWorked");

                String hoursDisplay = employeeType.equals("Part-Time") ? String.valueOf(hoursWorked) : "N/A";

                System.out.printf("%-20s %-15s %-20.2f %-15s\n", employeeName, employeeType, salary, hoursDisplay);
            }


        } catch (SQLException e) {
            System.out.println("Error viewing employees: " + e.getMessage());
        }
    }



    private static void logHoursWorked() {
        System.out.println("\nLog Hours Worked");

        System.out.print("Enter employee name (leave blank to go back to menu): ");
        String name = scanner.nextLine().toUpperCase().trim();

        if (name.isEmpty()) {
            System.out.println("Returning to the menu.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement checkStmt = connection.prepareStatement("SELECT * FROM Employees WHERE name = ? AND type = 'Part-Time' AND user_id = ?");
            checkStmt.setString(1, name);
            checkStmt.setInt(2, loggedInUserId);

            ResultSet resultSet = checkStmt.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No Part-Time employee found with the name '" + name + "' for your account.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error checking employee existence: " + e.getMessage());
            System.out.println("Returning to the main menu...");
            return;
        }

        System.out.print("Enter hours worked (leave blank to go back to menu): ");
        String hoursInput = scanner.nextLine().trim();

        if (hoursInput.isEmpty()) {
            System.out.println("Returning to the menu.");
            return;
        }

        int hoursWorked;
        try {
            hoursWorked = Integer.parseInt(hoursInput);
        } catch (NumberFormatException e) {
            System.out.println("Hours worked must be a valid number. Returning to menu.");
            return;
        }

        if (hoursWorked < 0) {
            System.out.println("Hours worked cannot be negative. Returning to menu.");
            return;
        }

        String query = "UPDATE Employees SET hoursWorked = hoursWorked + ? WHERE name = ? AND type = 'Part-Time' AND user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, hoursWorked);
            stmt.setString(2, name);
            stmt.setInt(3, loggedInUserId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Hours worked updated successfully for " + name + ".");
            } else {
                System.out.println("Unable to update hours worked. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error logging hours worked: " + e.getMessage());
            System.out.println("Returning to the main menu...");
        }
    }



    private static void processPayroll() {
        System.out.println("Processing Payroll...");

        String selectQuery = "SELECT id, name, type, salary, hoursWorked FROM Employees WHERE user_id = ?";
        String insertQuery = "INSERT INTO payrolltransactions (user_id, employee_name, total_salary) VALUES (?, ?, ?)";
        String updateHoursQuery = "UPDATE Employees SET hoursWorked = 0 WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
             PreparedStatement updateStmt = connection.prepareStatement(updateHoursQuery)) {

            selectStmt.setInt(1, loggedInUserId);
            ResultSet resultSet = selectStmt.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No employees found to process payroll.");
                return;
            }

            double totalPayroll = 0;
            System.out.println("\n==================================== PAYROLL RECEIPT ====================================");
            System.out.printf("%-20s %-15s %-20s %-15s\n", "Employee Name", "Employee Type", "Salary", "Date");
            System.out.println("=========================================================================================");

            while (resultSet.next()) {
                // Retrieve employee details
                int employeeId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                double salary = resultSet.getDouble("salary");
                int hoursWorked = resultSet.getInt("hoursWorked");

                Employee employee;
                if (type.equals("Full-Time")) {
                    employee = new FullTimeEmployee(employeeId, name, salary);
                } else if (type.equals("Part-Time")) {
                    employee = new PartTimeEmployee(employeeId, name, salary, hoursWorked);
                } else {
                    System.out.println("Error: Unknown employee type for " + name);
                    continue;
                }

                double totalSalary = employee.calculateSalary();

                insertStmt.setInt(1, loggedInUserId);
                insertStmt.setString(2, name);
                insertStmt.setDouble(3, totalSalary);
                insertStmt.executeUpdate();

                // Print payroll receipt
                System.out.printf("%-20s %-15s %-20.2f %-15s\n", name, type, totalSalary, new java.util.Date());

                if (employee instanceof PartTimeEmployee) {
                    updateStmt.setInt(1, employeeId);
                    updateStmt.executeUpdate();
                }

                totalPayroll += totalSalary;
            }

            System.out.println("=========================================================================================");
            System.out.printf("Total Payroll Processed: %.2f\n", totalPayroll);
            System.out.println("Payroll processed successfully.");

        } catch (SQLException e) {
            System.out.println("Error processing payroll: " + e.getMessage());
        }
    }



    private static void viewPastTransactions() {
        String query = "SELECT * FROM payrolltransactions WHERE user_id = ? ORDER BY transaction_date DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, loggedInUserId);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("\nPast Payroll Transactions:");
            System.out.println("============================================================");
            System.out.printf("%-20s %-15s %-20s\n", "Employee Name", "Total Salary", "Transaction Date");
            System.out.println("============================================================");

            while (resultSet.next()) {
                String employeeName = resultSet.getString("employee_name");
                double totalSalary = resultSet.getDouble("total_salary");
                String transactionDate = resultSet.getString("transaction_date");

                // Output the results in tabular format
                System.out.printf("%-20s %-15.2f %-20s\n", employeeName, totalSalary, transactionDate);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing past transactions: " + e.getMessage());
        }
    }

    private static void logout() {
        System.out.println("Logging out...");
        isLoggedIn = false;
        loggedInUserId = -1;
    }
}
