package printingShop_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.employees.Manager;
import org.project_printing_shop.employees.Operator;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Book;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.printingShop.PrintingMachine;
import org.project_printing_shop.printingShop.PrintingShop;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the PrintingShop class.
 * This class contains unit tests to validate the functionality of the PrintingShop class.
 */
public class PrintingShopTest {

    private PrintingShop shop;

    /**
     * Sets up the test environment by creating a new PrintingShop instance before each test.
     */
    @BeforeEach
    public void setUp() {
        shop = new PrintingShop("Test Shop");
    }
    /**
     * Tests adding an employee to the printing shop.
     */
    @Test
    public void testAddEmployee() {
        EmployeeImpl employee = new Operator("John", 3000);
        shop.addEmployee(employee);
        assertEquals(1, shop.getEmployees().size());
        assertEquals("John", shop.getEmployees().get(0).getName());
    }
    /**
     * Tests adding a machine to the printing shop.
     */
    @Test
    public void testAddMachine() {
        PrintingMachine machine = new PrintingMachine(1000, 10, true);
        shop.addMachine(machine);
        assertEquals(1, shop.getMachines().size());
        assertEquals(1000, shop.getMachines().get(0).getMaxCapacity());
    }
    /**
     * Tests calculating the total salaries of all employees in the printing shop.
     */
    @Test
    public void testCalculateTotalSalaries() {
        EmployeeImpl manager = new Manager("Jane", 5000, 10, 10000, 12000);
        EmployeeImpl operator = new Operator("John", 3000);
        shop.addEmployee(manager);
        shop.addEmployee(operator);
        double totalSalaries = shop.calculateTotalSalaries();
        assertEquals(8500, totalSalaries, 0.001); // 5000 + (5000 * 0.1) + 3000
    }
    /**
     * Tests calculating the total paper costs in the printing shop.
     */
    @Test
    public void testCalculatePaperCosts() {
        PrintingMachine machine = new PrintingMachine(1000, 10, true);
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.1);
        Edition book = new Book("Test Book", 100, paper, 10.0);
        machine.loadPaper(1000);

        try {
            machine.printEdition(book, false);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }

        shop.addMachine(machine);
        double paperCosts = shop.calculatePaperCosts();
        assertEquals(15, paperCosts, 0.001); // 100 pages * 0.15 (A4 price adjustment)
    }
    /**
     * Tests calculating the total expenses of the printing shop.
     */
    @Test
    public void testCalculateTotalExpenses() {
        PrintingShop shop = new PrintingShop("Test Shop");
        Manager manager = new Manager("Jane", 5000, 10, 10000, 50000);
        shop.addEmployee(manager);
        PrintingMachine machine = new PrintingMachine(1000, 10, true);
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.1);
        Edition book = new Book("Test Book", 100, paper, 10.0);
        machine.loadPaper(1000);

        try {
            machine.printEdition(book, false);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
        shop.addMachine(machine);
        double paperCosts = shop.calculatePaperCosts();
        System.out.println("Total Paper Costs: " + paperCosts);

        // Calculate total expenses
        double totalExpenses = shop.calculateTotalExpenses();
        System.out.println("Total Expenses: " + totalExpenses);

        assertEquals(5515, totalExpenses, 0.001); // 5500 (salaries) + 15 (paper costs)
    }

    /**
     * Tests calculating the total income from all printed editions in the printing shop.
     */
    @Test
    public void testCalculateTotalIncome() {
        PrintingMachine machine = new PrintingMachine(20000, 10, true); // Ensure enough capacity
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.1);
        Edition book = new Book("Test Book", 100, paper, 10.0);

        machine.loadPaper(20000); // Load enough paper for 150 copies of 100 pages each (150 * 100 = 15000 sheets)

        try {
            // Print 150 copies
            for (int i = 0; i < 150; i++) {
                machine.printEdition(book, false);
            }
        } catch (Exception e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }

        shop.addMachine(machine);

        // Debugging statements to verify printed copies
        System.out.println("Total printed copies after printing: " + book.getPrintedCopies());

        double totalIncome = shop.calculateTotalIncome();
        assertEquals(1450, totalIncome, 0.001);
    }
    /**
     * Tests recording a sale in the printing shop.
     */
    @Test
    public void testRecordSale() {
        shop.recordSale(1000);
        assertEquals(1000, shop.getTotalSales(), 0.001);
    }
    /**
     * Tests comparing two printing shops by their total sales.
     */
    @Test
    public void testCompareByTotalSales() {
        PrintingShop shop1 = new PrintingShop("Shop1");
        shop1.recordSale(500);

        PrintingShop shop2 = new PrintingShop("Shop2");
        shop2.recordSale(1000);

        Comparator<PrintingShop> comparator = PrintingShop.compareByTotalSales();
        assertTrue(comparator.compare(shop1, shop2) < 0);
        assertTrue(comparator.compare(shop2, shop1) > 0);
    }

    /**
     * Tests comparing two printing shops by the number of employees they have.
     */
    @Test
    public void testCompareByNumberOfEmployees() {
        PrintingShop shop1 = new PrintingShop("Shop1");
        shop1.addEmployee(new Operator("Operator1", 3000));

        PrintingShop shop2 = new PrintingShop("Shop2");
        shop2.addEmployee(new Operator("Operator1", 3000));
        shop2.addEmployee(new Operator("Operator2", 3000));

        Comparator<PrintingShop> comparator = PrintingShop.compareByNumberOfEmployees();
        assertTrue(comparator.compare(shop1, shop2) < 0);
        assertTrue(comparator.compare(shop2, shop1) > 0);
    }
}


