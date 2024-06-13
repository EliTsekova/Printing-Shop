package org.project_printing_shop.employees;

/**
 * Class representing a manager in the printing shop.
 * Inherits the class EmployeeImpl and adds specific properties and behaviors for managers.
 */
public class Manager extends EmployeeImpl {
    protected double revenueLimit;
    protected double bonusPercentage;
    protected double currentRevenue;

    /**
     * Constructor for Manager.
     *
     * @param name             the name of the manager, must not be null or empty
     * @param baseSalary       the base salary of the manager, must be non-negative
     * @param bonusPercentage  the bonus percentage for the manager, must be between 0 and 100
     * @param revenueLimit     the revenue limit for the manager to receive the bonus, must be non-negative
     * @param currentRevenue   the current revenue achieved by the manager, must be non-negative
     * @throws IllegalArgumentException if any of the input data is invalid
     */
    public Manager(String name, double baseSalary, double bonusPercentage, double revenueLimit, double currentRevenue) {
        super(name, baseSalary);
        if (bonusPercentage < 0 || bonusPercentage > 100) {
            throw new IllegalArgumentException("The bonus percentage must be between 0 and 100.");
        }
        if (revenueLimit < 0 || currentRevenue < 0) {
            throw new IllegalArgumentException("The revenue values must not be negative.");
        }
        this.bonusPercentage = bonusPercentage;
        this.revenueLimit = revenueLimit;
        this.currentRevenue = currentRevenue;
    }
    /**
     * Sets the current revenue achieved by the manager.
     *
     * @param currentRevenue the new current revenue, must be non-negative
     * @throws IllegalArgumentException if the current revenue is negative
     */
    public void setCurrentRevenue(double currentRevenue) {
        if (currentRevenue < 0) {
            throw new IllegalArgumentException("Current revenue must be non-negative.");
        }
        this.currentRevenue = currentRevenue;
    }

    /**
     * Calculates the salary of the manager.
     * If the current revenue exceeds the revenue limit, the manager receives a bonus.
     *
     * @return the calculated salary of the manager
     */
    @Override
    public double calculateSalary() {
        if (currentRevenue > revenueLimit) {
            return baseSalary + ((baseSalary * bonusPercentage) / 100);
        }
        return baseSalary;
    }
    /**
     * Modified toString method
     * This method prints the data of the class mainly used for saving the data in file.
     */
    @Override
    public String toString() {
        return "Manager: " + super.toString() +
                ", Revenue Limit: " + revenueLimit +
                ", Bonus Percentage: " + bonusPercentage +
                ", Current Revenue: " + currentRevenue;
    }

}
