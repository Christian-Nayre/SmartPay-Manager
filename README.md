![SmartPay_Manager](https://github.com/user-attachments/assets/f115549d-d7b5-4967-b666-8b8f76dfec45)


# 💼 SmartPay Manager
## I. Project Overview

SmartPay Manager is a Java console-based payroll management system. It enables businesses to:

- **🔑 Authenticate Access:** Secure user authentication for managing employee records.
- **🗂️ Employee Management:** Handle full-time and part-time employee records seamlessly.
- **💵 Salary Processing:** Automatically calculate salaries based on employee type and hours worked.
- **📊 Financial Tracking:** Generate detailed transaction histories for payroll transparency.

This application demonstrates robust use of Object-Oriented Programming (OOP) principles and scalable design to accommodate future business needs.

# II. Key Features
## User Authentication 🔒
Secure login system ensures that only authorized users can access payroll data.

- **Employee Management 👥**
Add, update, and remove employee records with ease.

- **Payroll Processing 💰**
Calculate fixed monthly salaries for full-time employees and hourly wages for part-time staff.

- **Transaction History 📑**
Maintain a log of all salary payouts for accurate bookkeeping and transparency.

## III. Application of OOP Principles
### **🔒 Encapsulation**
- **Employee Class:** Encapsulates employee details like name, type, and salary with private attributes, accessed through public methods. Subclasses like FullTimeEmployee and PartTimeEmployee extend this functionality.
- **DatabaseConnection Class:** Handles all database interactions through a single, reusable getConnection() method, keeping connection logic isolated.
- **UserService Class:** Manages user authentication (login, registration) securely within its class.
  
### **✨ Abstraction**
- **Abstract Employee Class:** Provides a template for all employees with shared methods like calculateSalary(). Specific behaviors for full-time and part-time employees are implemented in their respective subclasses.
- **Payroll Process:** Abstracts the complex operations of salary calculations and payroll processing into manageable methods in the EmployeeService class.
- **DatabaseConnection Class:** Hides the details of database configuration, exposing only a high-level getConnection() method for usage.
  
### **🧬 Inheritance**
- **Employee Subclasses:** The base Employee class is inherited by FullTimeEmployee and PartTimeEmployee, sharing common attributes and methods while adding specific functionality for different employee types.
- **Service Layer:** Classes like UserService and EmployeeService follow a shared structure for better reuse and extension.
  
### **🌀 Polymorphism**
- **Overriding Methods:** Specialized implementations of calculateSalary() ensure correct behavior for different employee types.
- **Dynamic Dispatch:** Automatically selects the correct method implementation during runtime based on the object type.

## IV. Integration of the Sustainable Development Goals (SDGs) 🌍
### **SDG 8: Decent Work and Economic Growth**
- ⚖️ Promotes equitable payroll management by supporting distinct salary structures for full-time and part-time employees.
- ✅ Reduces payroll errors and ensures timely compensation.
- 📈 Provides a scalable framework for future features like tracking employee hours or generating performance metrics.

### **SDG 9: Industry, Innovation, and Infrastructure**
- 💻 Encourages innovation in payroll systems by leveraging modular and scalable software architecture.
- 🔧 Simplifies administrative tasks through efficient data handling and robust database integration.
- 🌐 Demonstrates how technology can enhance workplace infrastructure, supporting sustainable economic development.

### **SDG 10: Reduced Inequalities**
- 👥 Ensures fair treatment of employees by supporting diverse payroll structures tailored to individual needs.
- 📊 Enhances transparency in salary calculations, promoting trust and reducing workplace inequalities.
- 🌟 Enables equitable opportunities for part-time workers by ensuring fair pay and accurate record-keeping.


## 🤍 Acknowledgements

- **Subject Instructor:** Ms. Fatima Marie P. Agdon

## ⌨️ Author

| Name                             | GitHub                                                | SR Code     |
|----------------------------------|-------------------------------------------------------|-------------|
| Nayre, Christian B. | [ChristianNayre](https://github.com/Christian-Nayre) | 23-07542   |
