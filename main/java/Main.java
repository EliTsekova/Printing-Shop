import org.project_printing_shop.employees.Manager;
import org.project_printing_shop.employees.Operator;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.exceptions.NoSuitableMachineException;
import org.project_printing_shop.exceptions.UnsupportedColorException;
import org.project_printing_shop.printable_items.*;
import org.project_printing_shop.printingShop.*;


public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Creating Shop 1");

            PrintingShop shop1 = new PrintingShop("Shop 1");
            Manager manager1 = new Manager("Gabi", 3000, 10, 5000, 725);
            Operator operator1 = new Operator("Ivancho", 3500);

            shop1.addEmployee(manager1);
            shop1.addEmployee(operator1);

            PrintingMachine machine1 = new PrintingMachine(1500, 50, true);
            shop1.addMachine(machine1);

            shop1.setPaperPricing(PaperType.GLOSSY, 0.05);
            shop1.setPaperPricing(PaperType.STANDARD, 0.02);
            shop1.setPaperPricing(PaperType.NEWS_PRINT, 0.01);

            Paper glossyPaper = new Paper(PaperSize.A4, PaperType.GLOSSY, 0.05);
            Edition poster = new Poster("Concert Poster", 10, glossyPaper, 5.0);

            try {
                machine1.loadPaper(1500);
                for (int i = 0; i < 150; i++) {
                    machine1.printEdition(poster, true);
                }
            } catch (NoSuitableMachineException | UnsupportedColorException e) {
                System.err.println("Printing error in Shop 1: " + e.getMessage());
            }

            double totalIncome1 = shop1.calculateTotalIncome();
            shop1.recordSale(totalIncome1);

            PrintingShopFileHandlerWrite.savePrintingShopToFile(shop1, "shop1.txt");

            System.out.println("Creating Shop 2");

            PrintingShop shop2 = new PrintingShop("Shop 2");
            Manager manager2 = new Manager("Eli", 3000, 10, 5000, 725);
            Operator operator2 = new Operator("Ivan", 3500);

            shop2.addEmployee(manager2);
            shop2.addEmployee(operator2);

            PrintingMachine machine2 = new PrintingMachine(1500, 50, true);
            shop2.addMachine(machine2);

            shop2.setPaperPricing(PaperType.GLOSSY, 0.05);
            shop2.setPaperPricing(PaperType.STANDARD, 0.03);
            shop2.setPaperPricing(PaperType.NEWS_PRINT, 0.015);

            try {
                machine2.loadPaper(1500);
                poster.setPrintedCopies(0);
                for (int i = 0; i < 150; i++) {
                    machine2.printEdition(poster, true);
                }
            } catch (NoSuitableMachineException | UnsupportedColorException e) {
                System.err.println("Printing error in Shop 2: " + e.getMessage());
            }

            double totalIncome2 = shop2.calculateTotalIncome();
            shop2.recordSale(totalIncome2);

            PrintingShopFileHandlerWrite.savePrintingShopToFile(shop2, "shop2.txt");

            PrintingShop loadedShop1 = PrintingShopFileHandlerRead.loadPrintingShopFromFile("shop1.txt");
            PrintingShop loadedShop2 = PrintingShopFileHandlerRead.loadPrintingShopFromFile("shop2.txt");

            System.out.println("Loaded Shop 1: " + loadedShop1);
            System.out.println("Loaded Shop 2: " + loadedShop2);

            // Comparing based on total sales
            System.out.println("Comparing by Total Sales:");
            int comparisonResult = PrintingShop.compareByTotalSales().compare(loadedShop1, loadedShop2);
            if (comparisonResult > 0) {
                System.out.println(loadedShop1.getName() + " has higher total sales than " + loadedShop2.getName());
            } else if (comparisonResult < 0) {
                System.out.println(loadedShop2.getName() + " has higher total sales than " + loadedShop1.getName());
            } else {
                System.out.println(loadedShop1.getName() + " and " + loadedShop2.getName() + " have equal total sales");
            }

            // Comparing based on number of employees
            System.out.println("Comparing by Number of Employees:");
            comparisonResult = PrintingShop.compareByNumberOfEmployees().compare(loadedShop1, loadedShop2);
            if (comparisonResult > 0) {
                System.out.println(loadedShop1.getName() + " has more employees than " + loadedShop2.getName());
            } else if (comparisonResult < 0) {
                System.out.println(loadedShop2.getName() + " has more employees than " + loadedShop1.getName());
            } else {
                System.out.println(loadedShop1.getName() + " and " + loadedShop2.getName() + " have the same number of employees");
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}