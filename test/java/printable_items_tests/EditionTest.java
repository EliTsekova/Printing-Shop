package printable_items_tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Book;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.printable_items.Poster;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Edition class and its subclasses.
 * This class contains unit tests to validate the behavior of the Edition class.
 */
public class EditionTest {

    private Paper standardPaper;
    private Edition edition;

    /**
     * Sets up the test environment before each test.
     * Initializes a standard paper object and an edition object for use in the tests.
     */
    @BeforeEach
    public void setUp() {
        standardPaper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
        edition = new Poster("Test Poster", 10, standardPaper, 5.0);
    }

    /**
     * Test to verify the creation of an Edition instance with valid data.
     * Ensures that the Edition object is correctly initialized.
     */
    @Test
    public void testCreateEditionWithValidData() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        assertNotNull(edition);
        assertEquals("Java Programming", edition.getTitle());
        assertEquals(300, edition.getNumberOfPages());
        assertEquals(standardPaper, edition.getPaper());
        assertEquals(15.00, edition.getUnitPrice());
    }

    /**
     * Test to verify that the constructor of Edition throws an IllegalArgumentException
     * when a negative number of pages is provided.
     */
    @Test
    public void testCreateEditionWithNegativeNumberOfPages() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Java Programming", -300, standardPaper, 15.00);
        });
        assertEquals("The number of pages must not be negative.", exception.getMessage());
    }

    /**
     * Test to verify that the constructor of Edition throws an IllegalArgumentException
     * when a negative unit price is provided.
     */
    @Test
    public void testCreateEditionWithNegativeUnitPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Java Programming", 300, standardPaper, -15.00);
        });
        assertEquals("Unit price must not be negative.", exception.getMessage());
    }

    /**
     * Test to verify the print functionality of the Edition class.
     * Ensures that the printed copies count is incremented correctly.
     */
    @Test
    public void testPrint() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.print();
        assertEquals(1, edition.getPrintedCopies());
    }

    /**
     * Test to verify the print functionality of the Edition class for multiple copies.
     * Ensures that the printed copies count is incremented correctly for multiple prints.
     */
    @Test
    public void testPrintMultipleCopies() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        for (int i = 0; i < 50; i++) {
            edition.print();
        }
        assertEquals(50, edition.getPrintedCopies());
    }

    /**
     * Test to verify the calculation of printing costs for an Edition instance.
     * Ensures that the printing costs are calculated correctly based on the number of pages and paper price.
     */
    @Test
    public void testCalculatePrintingCosts() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        double expectedCost = 300 * standardPaper.calculatePrice();
        assertEquals(expectedCost, edition.calculatePrintingCosts());
    }

    /**
     * Test to verify the calculation of income for less than 100 printed copies.
     * Ensures that the income is calculated correctly without any discount.
     */
    @Test
    public void testCalculateIncome_LessThan100Copies() {
        edition.setPrintedCopies(50);
        double expectedIncome = 50 * 5.0;
        assertEquals(expectedIncome, edition.calculateIncome());
    }

    /**
     * Test to verify the calculation of income for exactly 100 printed copies.
     * Ensures that the income is calculated correctly without any discount.
     */
    @Test
    public void testCalculateIncome_Exactly100Copies() {
        edition.setPrintedCopies(100);
        double expectedIncome = 100 * 5.0;
        assertEquals(expectedIncome, edition.calculateIncome());
    }

    /**
     * Test to verify the calculation of income for more than 100 printed copies.
     * Ensures that the income is calculated correctly with a discount for copies beyond 100.
     */
    @Test
    public void testCalculateIncome_MoreThan100Copies() {
        edition.setPrintedCopies(150);
        double discountedCopies = 50;
        double discountedTotal = discountedCopies * 5.0 * 0.90;
        double expectedIncome = 100 * 5.0 + discountedTotal;
        assertEquals(expectedIncome, edition.calculateIncome());
    }

    /**
     * Test to verify the calculation of income for zero printed copies.
     * Ensures that the income is zero when no copies are printed.
     */
    @Test
    public void testCalculateIncome_ZeroCopies() {
        edition.setPrintedCopies(0);
        double expectedIncome = 0;
        assertEquals(expectedIncome, edition.calculateIncome());
    }

    /**
     * Test to verify the setting and getting of the title for an Edition instance.
     * Ensures that the title is set and retrieved correctly.
     */
    @Test
    public void testSetAndGetTitle() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.setTitle("Advanced Java Programming");
        assertEquals("Advanced Java Programming", edition.getTitle());
    }

    /**
     * Test to verify the setting and getting of the number of pages for an Edition instance.
     * Ensures that the number of pages is set and retrieved correctly.
     */
    @Test
    public void testSetAndGetNumberOfPages() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.setNumberOfPages(400);
        assertEquals(400, edition.getNumberOfPages());
    }

    /**
     * Test to verify the setting and getting of the paper for an Edition instance.
     * Ensures that the paper is set and retrieved correctly.
     */
    @Test
    public void testSetAndGetPaper() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        Paper glossyPaper = new Paper(PaperSize.A4, PaperType.GLOSSY, 0.20);
        edition.setPaper(glossyPaper);
        assertEquals(glossyPaper, edition.getPaper());
    }

    /**
     * Test to verify the setting and getting of the unit price for an Edition instance.
     * Ensures that the unit price is set and retrieved correctly.
     */
    @Test
    public void testSetAndGetUnitPrice() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.setUnitPrice(20.00);
        assertEquals(20.00, edition.getUnitPrice());
    }

    /**
     * Test to verify the setting and getting of the printed copies for an Edition instance.
     * Ensures that the printed copies count is set and retrieved correctly.
     */
    @Test
    public void testSetAndGetPrintedCopies() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.setPrintedCopies(50);
        assertEquals(50, edition.getPrintedCopies());
    }
}