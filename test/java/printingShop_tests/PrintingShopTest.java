import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.Manager;
import org.project_printing_shop.employees.Operator;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.exceptions.UnsupportedColorException;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.printable_items.Poster;
import org.project_printing_shop.printingShop.PrintingMachine;
import org.project_printing_shop.printingShop.PrintingShop;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class PrintingShopTest {

    private PrintingShop shop;
    private Paper standardPaper;
    private PrintingMachine colorMachine;

    @BeforeEach
    public void setUp() {
        shop = new PrintingShop("PrintMaster");
        standardPaper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
        colorMachine = new PrintingMachine(1000, 50, true);
        shop.addMachine(colorMachine);
    }

    @Test
    public void testCreatePrintingShopWithValidData() {
        PrintingShop shop = new PrintingShop("PrintMaster");
        assertNotNull(shop);
        assertEquals("PrintMaster", shop.getName());
        assertTrue(shop.getEmployees().isEmpty());
        assertTrue(shop.getMachines().isEmpty());
        assertEquals(0.0, shop.getTotalSales());
    }

    @Test
    public void testAddEmployee() {
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 6000);
        shop.addEmployee(manager);
        assertEquals(1, shop.getEmployees().size());
        assertEquals(manager, shop.getEmployees().get(0));
    }

    @Test
    public void testAddMachine() {
        PrintingMachine machine = new PrintingMachine(1000, 50, true);
        shop.addMachine(machine);
        assertEquals(2, shop.getMachines().size()); // It should be 2 because one machine is added in setUp method.
        assertEquals(machine, shop.getMachines().get(1));
    }

    @Test
    public void testSetAndGetPaperPricing() {
        shop.setPaperPricing(PaperType.STANDARD, 0.10);
        assertEquals(0.10, shop.getPriceForPaper(PaperType.STANDARD));
    }

    @Test
    public void testCalculateTotalSalaries() {
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 6000); // Expected salary: 1100
        Operator operator = new Operator("Jane Smith", 800); // Expected salary: 800
        shop.addEmployee(manager);
        shop.addEmployee(operator);
        assertEquals(1900, shop.calculateTotalSalaries()); // 1100 + 800
    }

    @Test
    public void testCalculatePaperCosts() throws UnsupportedColorException {
        Edition poster = new Poster("Concert Poster", 10, standardPaper, 5.00);
        colorMachine.loadPaper(500);
        colorMachine.printEdition(poster, true);
        assertEquals(10 * standardPaper.calculatePrice(), shop.calculatePaperCosts());
    }

    @Test
    public void testCalculateTotalExpenses() throws UnsupportedColorException {
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 6000);
        shop.addEmployee(manager);
        Edition poster = new Poster("Concert Poster", 10, standardPaper, 5.00);
        colorMachine.loadPaper(500);
        colorMachine.printEdition(poster, true);
        double expectedExpenses = 1100 + 10 * standardPaper.calculatePrice(); // 1100 (manager's salary with bonus) + paper cost
        assertEquals(expectedExpenses, shop.calculateTotalExpenses());
    }

    @Test
    public void testCalculateTotalIncome() throws UnsupportedColorException {
        Edition poster = new Poster("Concert Poster", 10, standardPaper, 5.00);
        colorMachine.loadPaper(100);
        for (int i = 0; i < 10; i++) {
            colorMachine.printEdition(poster, true);
        }
        System.out.println("Total printed copies: " + poster.getPrintedCopies());
        double expectedIncome = 10 * 5.00;
        assertEquals(expectedIncome, shop.calculateTotalIncome());
    }

    @Test
    public void testRecordSale() {
        shop.recordSale(500);
        assertEquals(500, shop.getTotalSales());
    }

    @Test
    public void testCompareTo() {
        PrintingShop anotherShop = new PrintingShop("AnotherPrintMaster");
        shop.recordSale(1000);
        anotherShop.recordSale(1500);
        assertTrue(shop.compareTo(anotherShop) < 0);
        assertTrue(anotherShop.compareTo(shop) > 0);
    }

    @Test
    public void testCompareByTotalSales() {
        PrintingShop anotherShop = new PrintingShop("AnotherPrintMaster");
        shop.recordSale(1000);
        anotherShop.recordSale(1500);
        Comparator<PrintingShop> comparator = PrintingShop.compareByTotalSales();
        assertTrue(comparator.compare(shop, anotherShop) < 0);
    }

    @Test
    public void testCompareByNumberOfEmployees() {
        PrintingShop anotherShop = new PrintingShop("AnotherPrintMaster");
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 6000);
        shop.addEmployee(manager);
        Comparator<PrintingShop> comparator = PrintingShop.compareByNumberOfEmployees();
        assertTrue(comparator.compare(shop, anotherShop) > 0);
    }

    @Test
    public void testCompareByPaperPricing() {
        PrintingShop anotherShop = new PrintingShop("AnotherPrintMaster");
        shop.setPaperPricing(PaperType.STANDARD, 0.10);
        anotherShop.setPaperPricing(PaperType.STANDARD, 0.20);
        Comparator<PrintingShop> comparator = PrintingShop.compareByPaperPricing(PaperType.STANDARD);
        assertTrue(comparator.compare(shop, anotherShop) < 0);
    }

    @Test
    public void testToString() {
        String expected = "PrintingShop{id=" + shop.getId() + ", name='PrintMaster', number of employees=0, total sales=0.0}";
        assertEquals(expected, shop.toString());
    }
}
