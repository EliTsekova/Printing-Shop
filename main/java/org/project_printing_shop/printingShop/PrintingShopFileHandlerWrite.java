package org.project_printing_shop.printingShop;

import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.employees.Manager;
import org.project_printing_shop.printable_items.Edition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Class responsible for writing printing shop data to a file.
 */
public class PrintingShopFileHandlerWrite {

    /**
     * Saves the PrintingShop data to a file.
     *
     * @param shop     the printing shop instance to save
     * @param filename the name of the file to write to
     */
    public static void savePrintingShopToFile(PrintingShop shop, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("PrintingShopID: " + shop.getId());
            writer.newLine();
            writer.write("Name: " + shop.getName());
            writer.newLine();
            writer.write("Total Sales: " + shop.getTotalSales());
            writer.newLine();
            writer.write("Total Expenses: " + shop.calculateTotalExpenses());
            writer.newLine();

            writer.write("Employees:");
            writer.newLine();
            for (EmployeeImpl employee : shop.getEmployees()) {
                writer.write("Employee: " + serializeEmployee(employee));
                writer.newLine();
            }

            writer.write("Machines:");
            writer.newLine();
            for (PrintingMachine machine : shop.getMachines()) {
                writer.write("Machine: " + serializeMachine(machine));
                writer.newLine();
            }

            writer.write("Printed Editions:");
            writer.newLine();
            for (PrintingMachine machine : shop.getMachines()) {
                for (Map.Entry<Edition, Integer> entry : machine.getEditionCopies().entrySet()) {
                    writer.write("Edition: " + serializeEdition(entry.getKey()) + "=" + entry.getValue());
                    writer.newLine();
                }
            }

            writer.write("Paper Pricing:");
            writer.newLine();
            shop.getPaperPricing().forEach((type, price) -> {
                try {
                    writer.write(type + ": " + price);
                    writer.newLine();
                } catch (IOException e) {
                    System.err.println("Failed to write to file: " + e.getMessage());
                }
            });

        } catch (IOException e) {
            System.out.println("Failed to write to file: " + e.getMessage());
        }
    }

    /**
     * Serializes an EmployeeImpl object to a string.
     *
     * @param employee the employee to serialize
     * @return the serialized employee string
     */
    public static String serializeEmployee(EmployeeImpl employee) {
        String employeeType = employee instanceof Manager ? "Manager" : "Operator";
        String name = employee.getName();
        double baseSalary = employee.getBaseSalary();
        String result = employeeType + ": " + name + ", Base Salary: " + baseSalary + ", Salary: " + employee.calculateSalary();

        System.out.println("Serialized employee: " + result);
        return result;
    }

    /**
     * Serializes a PrintingMachine object to a string.
     *
     * @param machine the machine to serialize
     * @return the serialized machine string
     */
    public static String serializeMachine(PrintingMachine machine) {
        StringBuilder sb = new StringBuilder();
        sb.append("Max Capacity: ").append(machine.getMaxCapacity()).append(", ");
        sb.append("Pages Per Minute: ").append(machine.getPagesPerMinute()).append(", ");
        sb.append("Color Support: ").append(machine.isColorSupport()).append(", ");
        sb.append("Current Paper Load: ").append(machine.getCurrentPaperLoad());
        for (Map.Entry<Edition, Integer> entry : machine.getEditionCopies().entrySet()) {
            sb.append(", Edition: ").append(serializeEdition(entry.getKey())).append("=").append(entry.getValue());
        }
        return sb.toString();
    }

    /**
     * Serializes an Edition object to a string.
     *
     * @param edition the edition to serialize
     * @return the serialized edition string
     */
    public static String serializeEdition(Edition edition) {
        return "Type: " + edition.getClass().getSimpleName() + ", " +
                "Title: " + edition.getTitle() + ", " +
                "Number of Pages: " + edition.getNumberOfPages() + ", " +
                "Size: " + edition.getPaper().getSize() + ", " +
                "Type: " + edition.getPaper().getType() + ", " +
                "Base Price: " + edition.getPaper().getBasePrice() + ", " +
                "Unit Price: " + edition.getUnitPrice() + ", " +
                "Printed Copies: " + edition.getPrintedCopies();
    }
}

