package org.project_printing_shop.employees;

public class Manager extends EmployeeImpl{
    private double revenueThreshold;
    private double bonusPercentage;
    private double currentRevenue;

    public Manager(String name, double baseSalary, double bonusPercentage, double revenueThreshold, double currentRevenue) {
        super(name, baseSalary);
        if (bonusPercentage < 0 || bonusPercentage > 100) {
            throw new IllegalArgumentException("Bonus percentage must be between 0 and 100.");
        }
        if (revenueThreshold < 0 || currentRevenue < 0) {
            throw new IllegalArgumentException("Revenue values must not be negative.");
        }
        this.bonusPercentage = bonusPercentage;
        this.revenueThreshold = revenueThreshold;
        this.currentRevenue = currentRevenue;
    }

    @Override
    public double calculateSalary() {
        if (currentRevenue > revenueThreshold) {
            return baseSalary + (baseSalary * bonusPercentage / 100);
        }
        return baseSalary;
    }
}
