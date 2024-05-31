package org.project_printing_shop.printingShop;

import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.employees.Operator;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.printable_items.Poster;
import org.project_printing_shop.printingShop.PrintingMachine;

import java.io.*;
import java.util.*;

public class PrintingShopFileHandler {
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
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            System.out.println("Failed to write to file: " + e.getMessage());
        }
    }
    public static PrintingShop loadPrintingShopFromFile(String filename) {
        PrintingShop shop = new PrintingShop("Default Shop");  // Default name which should be overwritten
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("PrintingShopID:")) {
                    UUID id = UUID.fromString(line.split(":")[1].trim());
                    shop.setId(id);
                } else if (line.startsWith("Name:")) {
                    shop.setName(line.split(":")[1].trim());
                } else if (line.startsWith("Total Sales:")) {
                    double totalSales = Double.parseDouble(line.split(":")[1].trim());
                    shop.setTotalSales(totalSales);
                } else if (line.startsWith("Employee:")) {
                    EmployeeImpl employee = deserializeEmployee(line.split("Employee: ")[1]);
                    shop.addEmployee(employee);
                } else if (line.startsWith("Machine:")) {
                    PrintingMachine machine = deserializeMachine(line.split("Machine: ")[1]);
                    shop.addMachine(machine);
                } else if (line.startsWith("Edition:")) {
                    String[] editionData = line.split("Edition: ")[1].split("=");
                    Edition edition = deserializeEdition(editionData[0]);
                    int copies = Integer.parseInt(editionData[1]);
                    // Find the machine that printed this edition and add the edition copies
                    for (PrintingMachine machine : shop.getMachines()) {
                        if (machine.getPrintedEditions().contains(edition)) {
                            machine.getEditionCopies().put(edition, copies);
                        }
                    }
                } else if (line.startsWith("Paper Pricing:")) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        PaperType type = PaperType.valueOf(parts[0].trim());
                        double price = Double.parseDouble(parts[1].trim());
                        shop.setPaperPricing(type, price);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return shop;
    }
    private static String serializeEmployee(EmployeeImpl employee) {
        // Replace these with actual methods or fields from your EmployeeImpl class
        String name = employee.getName();
        double baseSalary = employee.getBaseSalary();
        return name + "," + baseSalary;
    }
    private static EmployeeImpl deserializeEmployee(String data) {
        String[] parts = data.split(",");
        String name = parts[0];
        double baseSalary = Double.parseDouble(parts[1]);
        return new Operator(name, baseSalary);
    }
    private static String serializeMachine(PrintingMachine machine) {
        StringBuilder sb = new StringBuilder();
        sb.append(machine.getMaxCapacity()).append(",");
        sb.append(machine.getPagesPerMinute()).append(",");
        sb.append(machine.isColorSupport()).append(",");
        sb.append(machine.getCurrentPaperLoad()).append(",");
        for (Map.Entry<Edition, Integer> entry : machine.getEditionCopies().entrySet()) {
            sb.append(serializeEdition(entry.getKey())).append("=").append(entry.getValue()).append(";");
        }
        return sb.toString();
    }
    private static PrintingMachine deserializeMachine(String data) {
        String[] parts = data.split(",");
        int maxCapacity = Integer.parseInt(parts[0]);
        int pagesPerMinute = Integer.parseInt(parts[1]);
        boolean colorSupport = Boolean.parseBoolean(parts[2]);
        int currentPaperLoad = Integer.parseInt(parts[3]);
        PrintingMachine machine = new PrintingMachine(maxCapacity, pagesPerMinute, colorSupport);
        machine.setCurrentPaperLoad(currentPaperLoad);
        if (parts.length > 4) {
            String[] editionsData = parts[4].split(";");
            for (String editionData : editionsData) {
                if (!editionData.isEmpty()) {
                    String[] editionParts = editionData.split("=");
                    Edition edition = deserializeEdition(editionParts[0]);
                    int copies = Integer.parseInt(editionParts[1]);
                    machine.getPrintedEditions().add(edition);
                    machine.getEditionCopies().put(edition, copies);
                }
            }
        }

        return machine;
    }
    private static String serializeEdition(Edition edition) {
        String title = edition.getTitle();
        int numberOfPages = edition.getNumberOfPages();
        String serializedPaper = serializePaper(edition.getPaper());
        double unitPrice = edition.getUnitPrice();
        int printedCopies = edition.getPrintedCopies();
        return title + "," + numberOfPages + "," + serializedPaper + "," + unitPrice + "," + printedCopies;
    }
    private static Edition deserializeEdition(String data) {
        String[] parts = data.split(",");
        String title = parts[0];
        int numberOfPages = Integer.parseInt(parts[1]);
        Paper paper = deserializePaper(parts[2] + "," + parts[3] + "," + parts[4]);
        double unitPrice = Double.parseDouble(parts[5]);
        int printedCopies = Integer.parseInt(parts[6]);
        return new Poster(title, numberOfPages, paper, unitPrice);
    }
    private static String serializePaper(Paper paper) {
        PaperSize size = paper.getSize();
        PaperType type = paper.getType();
        double price = paper.getBasePrice();
        return size + "," + type + "," + price;
    }
    private static Paper deserializePaper(String data) {
        String[] parts = data.split(",");
        PaperSize size = PaperSize.valueOf(parts[0]);
        PaperType type = PaperType.valueOf(parts[1]);
        double price = Double.parseDouble(parts[2]);
        return new Paper(size, type, price);
    }
}