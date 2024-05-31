
 package org.project_printing_shop.printingShop;

import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.enums.PaperType;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

 public class PrintingShop implements Comparable<PrintingShop> {
     private UUID id;
     private String name;
     private List<EmployeeImpl> employees;
     private List<PrintingMachine> machines;
     private Map<PaperType, Double> paperPricing;
     private double totalSales;

     public PrintingShop(String name) {
         this.id = UUID.randomUUID();
         this.name = name;
         this.employees = new ArrayList<>();
         this.machines = new ArrayList<>();
         this.paperPricing = new HashMap<>();
         this.totalSales = 0.0;
     }

     // Getter and Setter
     public String getName() {
         return name;
     }

     public UUID getId() {
         return id;
     }

     public Map<PaperType, Double> getPaperPricing() {
         return paperPricing;
     }

     public void setId(UUID id) {
         this.id = id;
     }

     public void setName(String name) {
         this.name = name;
     }

     public void setTotalSales(double totalSales) {
         this.totalSales = totalSales;
     }

     public void setPaperPricing(Map<PaperType, Double> paperPricing) {
         this.paperPricing = paperPricing;
     }

     public void addEmployee(EmployeeImpl employee) {
         employees.add(employee);
     }

     public void addMachine(PrintingMachine machine) {
         machines.add(machine);
     }

     public void setPaperPricing(PaperType type, double price) {
         paperPricing.put(type, price);
     }

     public double getPriceForPaper(PaperType type) {
         return paperPricing.getOrDefault(type, 0.0);
     }
     public double calculateTotalSalaries() {
         double totalSalaries = 0;
         for (EmployeeImpl employee : employees) {
             totalSalaries += employee.calculateSalary();
         }
         return totalSalaries;
     }

     public double calculatePaperCosts() {
         double totalPaperCost = 0;
         for (PrintingMachine machine : machines) {
             for (Edition edition : machine.getPrintedEditions()) {
                 totalPaperCost += edition.getNumberOfPages() * edition.getPaper().calculatePrice();
             }
         }
         return totalPaperCost;
     }

     public double calculateTotalExpenses() {
         return calculateTotalSalaries() + calculatePaperCosts();
     }

     public double calculateTotalIncome() {
         double totalIncome = 0;
         for (PrintingMachine machine : machines) {
             for (Edition edition : machine.getPrintedEditions()) {
                 totalIncome += edition.calculateIncome();
             }
         }
         return totalIncome;
     }
     public void recordSale(double amount) {
         totalSales += amount;
     }

     public double getTotalSales() {
         return totalSales;
     }

     public List<EmployeeImpl> getEmployees() {
         return employees;
     }

     public List<PrintingMachine> getMachines() {
         return machines;
     }

     @Override
     public int compareTo(PrintingShop other) {
         return Double.compare(this.totalSales, other.totalSales);
     }
     public static Comparator<PrintingShop> compareByTotalSales() {
         return Comparator.comparingDouble(PrintingShop::getTotalSales);
     }
     public static Comparator<PrintingShop> compareByPaperPricing(PaperType type) {
         return Comparator.comparingDouble(shop -> shop.getPriceForPaper(type));
     }
     public static Comparator<PrintingShop> compareByNumberOfEmployees() {
         return Comparator.comparingInt(shop -> shop.getEmployees().size());
     }
     @Override
     public String toString() {
         return "PrintingShop{" +
                 "id=" + id +
                 ", name='" + name + '\'' +
                 ", number of employees=" + employees.size() +
                 ", total sales=" + totalSales +
                 '}';
     }
 }