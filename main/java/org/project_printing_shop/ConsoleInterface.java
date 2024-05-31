package org.project_printing_shop;

import org.project_printing_shop.employees.Manager;
import org.project_printing_shop.employees.Operator;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printingShop.PrintingShop;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleInterface {
   /* private Scanner scanner;
    private HashMap<String, PrintingShop> shops = new HashMap<>();

    public ConsoleInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create a Printing Shop");
            System.out.println("2. Add Employee to a Shop");
            System.out.println("3. Print Edition in a Shop");
            System.out.println("4. Compare Two Shops");
            System.out.println("5. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createShop();
                    break;
                case "2":
                    addEmployee();
                    break;
                case "3":
                    handlePrinting();
                    break;
                case "4":
                    compareShops();
                    break;
                case "5":
                    keepRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    private void createShop() {
        System.out.println("Enter the name of the Printing Shop:");
        String name = scanner.nextLine();
        HashMap<PaperType, Double> markups = new HashMap<>();
        markups.put(PaperType.STANDARD, 1.0);  // Example markup
        PrintingShop shop = new PrintingShop(name, markups);
        shops.put(name, shop);
        System.out.println("Printing Shop '" + name + "' created successfully.");
    }

    private void addEmployee() {
        System.out.println("Enter the name of the shop:");
        String shopName = scanner.nextLine();
        if (shops.containsKey(shopName)) {
            PrintingShop shop = shops.get(shopName);
            System.out.println("Enter employee name:");
            String name = scanner.nextLine();
            System.out.println("Enter base salary:");
            double salary = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter type (Manager/Operator):");
            String type = scanner.nextLine();
            if (type.equalsIgnoreCase("Manager")) {
                System.out.println("Enter bonus percentage:");
                double bonus = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter revenue threshold:");
                double threshold = Double.parseDouble(scanner.nextLine());
                Manager manager = new Manager(name, salary, bonus, threshold, 0);
                shop.addEmployee(manager);
            } else if (type.equalsIgnoreCase("Operator")) {
                Operator operator = new Operator(name, salary);
                shop.addEmployee(operator);
            } else {
                System.out.println("Invalid type.");
                return;
            }
            System.out.println("Employee added successfully to " + shopName);
        } else {
            System.out.println("Shop not found.");
        }
    }

    private void handlePrinting() {
        // Implement functionality similar to addEmployee for printing tasks
    }

    private void compareShops() {
        System.out.println("Enter the name of the first shop:");
        String firstShopName = scanner.nextLine();
        System.out.println("Enter the name of the second shop:");
        String secondShopName = scanner.nextLine();
        if (shops.containsKey(firstShopName) && shops.containsKey(secondShopName)) {
            PrintingShop firstShop = shops.get(firstShopName);
            PrintingShop secondShop = shops.get(secondShopName);
            // Example comparison by total revenue
            double firstRevenue = firstShop.getTotalRevenue();
            double secondRevenue = secondShop.getTotalRevenue();
            System.out.println("Total Revenue of " + firstShopName + ": " + firstRevenue);
            System.out.println("Total Revenue of " + secondShopName + ": " + secondRevenue);
            if (firstRevenue > secondRevenue) {
                System.out.println(firstShopName + " has higher revenue.");
            } else if (firstRevenue < secondRevenue) {
                System.out.println(secondShopName + " has higher revenue.");
            } else {
                System.out.println("Both shops have the same revenue.");
            }
        } else {
            System.out.println("One or both shops not found.");
        }
    }*/
}

