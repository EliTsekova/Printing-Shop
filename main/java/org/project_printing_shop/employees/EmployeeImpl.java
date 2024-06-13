package org.project_printing_shop.employees;

import org.project_printing_shop.interfaces.Employee;


/**
 * Abstract class representing an employee in the printing shop.
 * This class provides a base implementation for employees with common properties
 * such as name and base salary for Manager and Operator.
 */
public abstract class EmployeeImpl implements Employee {
    protected String name;
    protected double baseSalary;

    /**
     * Constructor for EmployeeImpl.
     *
     * @param name       the name of the employee, must not be null or empty
     * @param baseSalary the base salary of the employee, must be non-negative
     * @throws IllegalArgumentException if the name is null or empty, or if the base salary is negative
     */
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

    /**
     * Returns the name of the employee.
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the base salary of the employee.
     *
     * @return the base salary of the employee
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Calculates the total salary of the employee.
     * This method must be implemented by subclasses.
     *
     * @return the total salary of the employee
     */
    @Override
    public abstract double calculateSalary();
    /**
     * Modified toString method
     * This method prints the data of the class mainly used for saving the data in file.
     */
    @Override
    public String toString() {
        return "Employee: " + name + ", Base Salary: " + baseSalary;
    }
}
