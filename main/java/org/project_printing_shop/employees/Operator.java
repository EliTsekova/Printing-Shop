package org.project_printing_shop.employees;

/**
 * Class representing an operator in the printing shop.
 * Inherits the class EmployeeImpl and provides a specific implementation for calculating the operator's salary.
 */
public class Operator extends EmployeeImpl {

    /**
     * Constructor for Operator.
     *
     * @param name       the name of the operator, must not be null or empty
     * @param baseSalary the base salary of the operator, must be non-negative
     * @throws IllegalArgumentException if the baseSalary is negative
     */
    public Operator(String name, double baseSalary) {
        super(name, baseSalary);
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary must be non-negative.");
        }
    }

    /**
     * Calculates the salary of the operator.
     * Since operators do not receive bonuses, their salary is simply their base salary.
     *
     * @return the base salary of the operator
     */
    @Override
    public double calculateSalary() {
        return baseSalary;
    }
    public String toString() {
        return "Operator: " + super.toString();
    }
}
