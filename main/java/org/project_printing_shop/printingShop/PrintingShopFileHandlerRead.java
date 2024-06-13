package org.project_printing_shop.printingShop;

import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.employees.Manager;
import org.project_printing_shop.employees.Operator;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Class responsible for reading printing shop data from a file.
 * This class contains methods to deserialize and reconstruct the PrintingShop objects from a text file.
 */
public class PrintingShopFileHandlerRead {

    /**
     * Loads a PrintingShop instance from a file.
     *
     * @param filename the name of the file to read from
     * @return the loaded PrintingShop instance
     */
    public static PrintingShop loadPrintingShopFromFile(String filename) {
        PrintingShop shop = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("PrintingShopID:")) {
                    UUID id = UUID.fromString(line.split(":")[1].trim());
                    shop = new PrintingShop("Default Shop");
                    shop.setId(id);
                } else if (line.startsWith("Name:")) {
                    assert shop != null;
                    shop.setName(line.split(":")[1].trim());
                } else if (line.startsWith("Total Sales:")) {
                    double totalSales = Double.parseDouble(line.split(":")[1].trim());
                    assert shop != null;
                    shop.setTotalSales(totalSales);
                } else if (line.startsWith("Total Expenses:")) {
                    double totalExpenses = Double.parseDouble(line.split(":")[1].trim());
                    assert shop != null;
                    shop.calculateTotalExpenses();
                } else if (line.startsWith("Employee:")) {
                    EmployeeImpl employee = deserializeEmployee(line.split("Employee: ")[1]);
                    assert shop != null;
                    shop.addEmployee(employee);
                } else if (line.startsWith("Machine:")) {
                    PrintingMachine machine = deserializeMachine(line.split("Machine: ")[1]);
                    assert shop != null;
                    shop.addMachine(machine);
                } else if (line.startsWith("Edition:")) {
                    String[] editionData = line.split("Edition: ")[1].split("=");
                    if (editionData.length == 2) {
                        Edition edition = deserializeEdition(editionData[0]);
                        int copies = Integer.parseInt(editionData[1]);
                        assert shop != null;
                        for (PrintingMachine machine : shop.getMachines()) {
                            if (!machine.getPrintedEditions().contains(edition)) {
                                machine.getPrintedEditions().add(edition);
                            }
                            machine.getEditionCopies().put(edition, copies);
                        }
                    }
                } else if (line.startsWith("Paper Pricing:")) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        PaperType type = PaperType.valueOf(parts[0].split(":")[1].trim());
                        double price = Double.parseDouble(parts[1].trim());
                        assert shop != null;
                        shop.setPaperPricing(type, price);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return shop;
    }

    /**
     * Deserializes an Employee object from a string.
     *
     * @param data the string data representing the employee
     * @return the deserialized EmployeeImpl object
     */
    private static EmployeeImpl deserializeEmployee(String data) {
        String[] parts = data.split(", ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid employee data format: " + data);
        }
        String[] employeeTypeAndName = parts[0].split(": ");
        String employeeType = employeeTypeAndName[0].trim();
        String name = employeeTypeAndName[1].trim();
        double baseSalary = Double.parseDouble(parts[1].split(": ")[1].trim());

        if (employeeType.equals("Manager")) {
            return new Manager(name, baseSalary, 0, 0, 0);
        } else {
            return new Operator(name, baseSalary);
        }
    }

    /**
     * Deserializes a PrintingMachine object from a string.
     *
     * @param data the string data representing the printing machine
     * @return the deserialized PrintingMachine object
     */
    private static PrintingMachine deserializeMachine(String data) {
        String[] parts = data.split(", ");
        int maxCapacity = Integer.parseInt(parts[0].split(": ")[1].trim());
        int pagesPerMinute = Integer.parseInt(parts[1].split(": ")[1].trim());
        boolean colorSupport = Boolean.parseBoolean(parts[2].split(": ")[1].trim());
        int currentPaperLoad = Integer.parseInt(parts[3].split(": ")[1].trim());

        PrintingMachine machine = new PrintingMachine(maxCapacity, pagesPerMinute, colorSupport);
        machine.setCurrentPaperLoad(currentPaperLoad);

        for (int i = 4; i < parts.length; i++) {
            if (parts[i].startsWith("Edition: ")) {
                String[] editionParts = parts[i].split("=");
                if (editionParts.length == 2) {
                    Edition edition = deserializeEdition(editionParts[0].replace("Edition: ", "").trim());
                    int copies = Integer.parseInt(editionParts[1].trim());
                    machine.getPrintedEditions().add(edition);
                    machine.getEditionCopies().put(edition, copies);
                }
            }
        }
        return machine;
    }

    /**
     * Deserializes an Edition object from a string.
     *
     * @param data the string data representing the edition
     * @return the deserialized Edition object
     */
    private static Edition deserializeEdition(String data) {
        String[] parts = data.split(", ");
        String type = parts[0].split(":")[1].trim();
        String title = parts[1].split(":")[1].trim();
        int numberOfPages = Integer.parseInt(parts[2].split(":")[1].trim());
        Paper paper = deserializePaper(parts[3] + ", " + parts[4] + ", " + parts[5]);
        double unitPrice = Double.parseDouble(parts[6].split(":")[1].trim());
        int printedCopies = Integer.parseInt(parts[7].split(":")[1].trim());

        Edition edition;
        if (type.equals("Poster")) {
            edition = new Poster(title, numberOfPages, paper, unitPrice);
        } else if (type.equals("Book")) {
            edition = new Book(title, numberOfPages, paper, unitPrice);
        } else if (type.equals("Newspaper")) {
            edition = new Newspaper(title, numberOfPages, paper, unitPrice);
        } else {
            throw new IllegalArgumentException("Unknown edition type: " + type);
        }
        edition.setPrintedCopies(printedCopies);
        return edition;
    }

    /**
     * Deserializes a Paper object from a string.
     *
     * @param data the string data representing the paper
     * @return the deserialized Paper object
     */
    private static Paper deserializePaper(String data) {
        String[] parts = data.split(", ");
        PaperSize size = PaperSize.valueOf(parts[0].split(": ")[1].trim());
        PaperType type = PaperType.valueOf(parts[1].split(": ")[1].trim());
        double basePrice = Double.parseDouble(parts[2].split(": ")[1].trim());

        return new Paper(size, type, basePrice);
    }
}
