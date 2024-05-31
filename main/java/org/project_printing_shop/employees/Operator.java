package org.project_printing_shop.employees;

public class Operator extends EmployeeImpl{
    public Operator(String name, double baseSalary) {
        super(name, baseSalary);
        if (baseSalary < 0) {
            throw new IllegalArgumentException("The base salary must be non-negative.");
        }
    }

    @Override
    public double calculateSalary() {
        return baseSalary;
    }
}
