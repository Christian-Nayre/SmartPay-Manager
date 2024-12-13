CREATE DATABASE SmartPayManager;

USE SmartPayManager;

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE Employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,          
    type VARCHAR(50) NOT NULL,          
    salary DOUBLE NOT NULL,             
    hoursWorked INT DEFAULT 0,           
    user_id INT NOT NULL,               
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE payrolltransactions (
    id INT AUTO_INCREMENT PRIMARY KEY,                
    user_id INT NOT NULL,                             
    employee_name VARCHAR(255) NOT NULL,              
    total_salary DECIMAL(10, 2) NOT NULL,             
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

