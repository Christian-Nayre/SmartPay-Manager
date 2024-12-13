package models;

public class PartTimeEmployee extends Employee {

    //Inheritance
    public PartTimeEmployee(int id, String name, double hourlyRate, int hoursWorked) {
        super(name, "Part-Time");
        setId(id); // Assign unique ID
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    public PartTimeEmployee(String name, double hourlyRate) {
        super(name, "Part-Time");
        setHourlyRate(hourlyRate);
    }

    // Polymorphism
    @Override
    public double calculateSalary() {
        return getHourlyRate() * getHoursWorked();
    }
}
