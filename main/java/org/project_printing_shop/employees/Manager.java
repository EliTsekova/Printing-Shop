package org.project_printing_shop.employees;

/**
 * Class representing a manager in the printing shop.
 * Inherits the class EmployeeImpl and adds specific properties and behaviors for managers.
 */
public class Manager extends EmployeeImpl{
    private double revenueLimit;
    private double bonusPercentage;
    private double currentRevenue;

    public Manager(String name, double baseSalary, double bonusPercentage, double revenueLimit, double currentRevenue) {
        super(name, baseSalary);
        if (bonusPercentage < 0 || bonusPercentage > 100) {
            throw new IllegalArgumentException("The bonus percentage must be between 0 and 100.");
        }
        if (revenueLimit< 0 || currentRevenue < 0) {
            throw new IllegalArgumentException("The revenue values must not be negative.");
        }
        this.bonusPercentage = bonusPercentage;
        this.revenueLimit = revenueLimit;
        this.currentRevenue = currentRevenue;
    }

    @Override
    public double calculateSalary() {
        if (currentRevenue > revenueLimit) {
            return baseSalary + (baseSalary * bonusPercentage / 100);
        }
        return baseSalary;
    }
}
