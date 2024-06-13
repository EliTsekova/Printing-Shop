package org.project_printing_shop.printingShop;

import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.exceptions.NoSuitableMachineException;
import org.project_printing_shop.exceptions.UnsupportedColorException;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.enums.PaperType;

import java.util.*;

/**
 * Class representing a printing shop.
 * This class provides functionality for managing employees, machines, paper pricing, and calculating total sales and expenses.
 */
public class PrintingShop {
    // Unique identifier for the printing shop
    private UUID id;
    private String name;
    protected List<EmployeeImpl> employees;
    protected List<PrintingMachine> machines;
    private Map<PaperType, Double> paperPricing;
    private double totalSales;

    /**
     * Constructor for the PrintingShop class.
     *
     * @param name the name of the printing shop
     */
    public PrintingShop(String name) {
        this.id = UUID.randomUUID(); // Generates a unique identifier for each printing shop
        this.name = name;
        this.employees = new ArrayList<>();
        this.machines = new ArrayList<>();
        this.paperPricing = new HashMap<>();
        this.totalSales = 0.0;
    }

    /**
     * Gets the name of the printing shop.
     *
     * @return the name of the printing shop
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique identifier of the printing shop.
     *
     * @return the unique identifier of the printing shop
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the map of paper types to their prices.
     *
     * @return the map of paper pricing
     */
    public Map<PaperType, Double> getPaperPricing() {
        return paperPricing;
    }

    /**
     * Sets the unique identifier of the printing shop.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Sets the name of the printing shop.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the total sales made by the printing shop.
     *
     * @param totalSales the total sales to set
     */
    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * Sets the map of paper types to their prices.
     *
     * @param paperPricing the paper pricing map to set
     */
    public void setPaperPricing(Map<PaperType, Double> paperPricing) {
        this.paperPricing = paperPricing;
    }

    /**
     * Adds an employee to the printing shop.
     *
     * @param employee the employee to add
     */
    public void addEmployee(EmployeeImpl employee) {
        employees.add(employee);
    }

    /**
     * Adds a printing machine to the printing shop.
     *
     * @param machine the printing machine to add
     */
    public void addMachine(PrintingMachine machine) {
        machines.add(machine);
    }

    /**
     * Sets the price for a specific type of paper.
     *
     * @param type  the type of paper
     * @param price the price to set
     */
    public void setPaperPricing(PaperType type, double price) {
        paperPricing.put(type, price);
    }

    /**
     * Gets the price for a specific type of paper.
     *
     * @param type the type of paper
     * @return the price for the specified type of paper
     */
    public double getPriceForPaper(PaperType type) {
        return paperPricing.getOrDefault(type, 0.0);
    }

    /**
     * Calculates the total salaries of all employees in the printing shop.
     *
     * @return the total salaries
     */
    public double calculateTotalSalaries() {
        double totalSalaries = 0;
        for (EmployeeImpl employee : employees) {
            totalSalaries += employee.calculateSalary();
        }
        return totalSalaries;
    }

    /**
     * Calculates the total cost of paper used by all printing machines in the printing shop.
     *
     * @return the total paper cost
     */
    public double calculatePaperCosts() {
        double totalPaperCost = 0.0;
        for (PrintingMachine machine : machines) {
            for (Map.Entry<Edition, Integer> entry : machine.getEditionCopies().entrySet()) {
                Edition edition = entry.getKey();
                int printedCopies = entry.getValue();
                int pagesPerCopy = edition.getNumberOfPages();
                double paperPricePerPage = edition.getPaper().calculatePrice();

                // Debug statements
                System.out.println("Edition: " + edition.getTitle());
                System.out.println("Printed Copies: " + printedCopies);
                System.out.println("Pages per Copy: " + pagesPerCopy);
                System.out.println("Paper Price per Page: " + paperPricePerPage);
                System.out.println("Subtotal: " + (printedCopies * pagesPerCopy * paperPricePerPage));

                totalPaperCost += printedCopies * pagesPerCopy * paperPricePerPage;
            }
        }
        System.out.println("Total Paper Cost: " + totalPaperCost);
        return totalPaperCost;
    }

    /**
     * Calculates the total expenses of the printing shop, including salaries and paper costs.
     *
     * @return the total expenses
     */
    public double calculateTotalExpenses() {
        return calculateTotalSalaries() + calculatePaperCosts();
    }

    /**
     * Calculates the total income from all printed editions in the printing shop.
     *
     * @return the total income
     */
    public double calculateTotalIncome() {
        double totalIncome = 0;
        for (PrintingMachine machine : machines) {
            for (Edition edition : machine.getPrintedEditions()) {
                totalIncome += edition.calculateIncome();
            }
        }
        return totalIncome;
    }

    /**
     * Records a sale by adding the amount to the total sales.
     *
     * @param amount the amount of the sale
     */
    public void recordSale(double amount) {
        totalSales += amount;

    }

    /**
     * Gets the total sales made by the printing shop.
     *
     * @return the total sales
     */
    public double getTotalSales() {
        return totalSales;
    }

    /**
     * Gets the list of employees in the printing shop.
     *
     * @return the list of employees
     */
    public List<EmployeeImpl> getEmployees() {
        return employees;
    }

    /**
     * Gets the list of printing machines in the printing shop.
     *
     * @return the list of machines
     */
    public List<PrintingMachine> getMachines() {
        return machines;
    }

    /**
     * Returns a comparator for comparing printing shops by total sales.
     *
     * @return a comparator for comparing printing shops by total sales
     */
    public static Comparator<PrintingShop> compareByTotalSales() {
        return Comparator.comparingDouble(PrintingShop::getTotalSales);
    }

    /**
     * Returns a comparator for comparing printing shops by the number of employees.
     *
     * @return a comparator for comparing printing shops by the number of employees
     */
    public static Comparator<PrintingShop> compareByNumberOfEmployees() {
        return Comparator.comparingInt(shop -> shop.getEmployees().size());
    }

    /**
     * Returns a string representation of the printing shop.
     *
     * @return a string representation of the printing shop
     */
    @Override
    public String toString() {
        return "PrintingShop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", machines=" + machines +
                ", paperPricing=" + paperPricing +
                ", totalSales=" + totalSales +
                '}';
    }
}
