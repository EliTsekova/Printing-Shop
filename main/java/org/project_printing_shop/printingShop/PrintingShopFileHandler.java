package org.project_printing_shop.printingShop;
import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.enums.PaperType;

import java.io.*;
import java.util.*;

    public class PrintingShopFileHandler {
        // Saves the state of the PrintingShop to a text file
        public static void savePrintingShopToFile(PrintingShop shop, String filename) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("PrintingShopID: " + shop.getId());
                writer.newLine();
                writer.write("Name: " + shop.getName());
                writer.newLine();
                writer.write("Total Sales: " + shop.getTotalSales());
                writer.newLine();

                writer.write("Employees:");
                writer.newLine();
                for (EmployeeImpl employee : shop.getEmployees()) {
                    writer.write(employee.toString());
                    writer.newLine();
                }

                writer.write("Machines:");
                writer.newLine();
                for (PrintingMachine machine : shop.getMachines()) {
                    writer.write(machine.toString());
                    writer.newLine();
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

        // Loads the state of the PrintingShop from a text file
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
                        // Parse employee data and add to shop
                    } else if (line.startsWith("Machine:")) {
                        // Parse machine data and add to shop
                    } else if (line.startsWith("Paper Pricing:")) {
                        // Load paper pricing
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
    }

