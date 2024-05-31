package org.project_printing_shop.employees;

import org.project_printing_shop.interfaces.Employee;

public abstract class EmployeeImpl implements Employee {
    protected String name;
    protected double baseSalary;

    public EmployeeImpl(String name, double baseSalary) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary must be non-negative.");
        }
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    @Override
    public abstract double calculateSalary();
}
