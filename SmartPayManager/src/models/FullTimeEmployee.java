package models;

public class FullTimeEmployee extends Employee {

    //Inheritance
    public FullTimeEmployee(int id, String name, double fixedSalary) {
        super(name, "Full-Time");
        setId(id);
        setFixedSalary(fixedSalary);
    }

    public FullTimeEmployee(String name, double fixedSalary) {
        super(name, "Full-Time");
        setFixedSalary(fixedSalary);
    }

    // Polymorphism
    @Override
    public double calculateSalary() {
        return getFixedSalary();
    }
}
