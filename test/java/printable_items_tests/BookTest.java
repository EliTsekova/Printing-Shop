package printable_items_tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Book;
import org.project_printing_shop.printable_items.Paper;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Book class.
 * This class contains unit tests to validate the behavior of the Book class.
 */
class BookTest {

    private Paper standardPaper;

    /**
     * Sets up the test environment before each test.
     * Initializes a standard paper object for use in the tests.
     */
    @BeforeEach
    public void setUp() {
        standardPaper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    /**
     * Test to verify the creation of a Book instance with valid data.
     * Ensures that the Book object is correctly initialized.
     */
    @Test
    public void testCreateBookWithValidData() {
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        assertNotNull(book);
        assertEquals("Java Programming", book.getTitle());
        assertEquals(300, book.getNumberOfPages());
        assertEquals(standardPaper, book.getPaper());
        assertEquals(15.00, book.getUnitPrice());
    }

    /**
     * Test to verify that the constructor of Book throws an IllegalArgumentException
     * when a negative number of pages is provided.
     */
    @Test
    public void testCreateBookWithNegativeNumberOfPages() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Java Programming", -300, standardPaper, 15.00);
        });
        assertEquals("The number of pages must not be negative.", exception.getMessage());
    }

    /**
     * Test to verify that the constructor of Book throws an IllegalArgumentException
     * when a negative unit price is provided.
     */
    @Test
    public void testCreateBookWithNegativeUnitPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Java Programming", 300, standardPaper, -15.00);
        });
        assertEquals("Unit price must not be negative.", exception.getMessage());
    }

    /**
     * Test to verify the print functionality of the Book class.
     * Ensures that the printed copies count is incremented correctly.
     */
    @Test
    public void testPrint() {
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        book.print();
        assertEquals(1, book.getPrintedCopies());
    }

    /**
     * Test to verify the calculation of printing costs for a Book instance.
     * Ensures that the printing costs are calculated correctly based on the number of pages and paper price.
     */
    @Test
    public void testCalculatePrintingCosts() {
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        double expectedCost = 300 * standardPaper.calculatePrice();
        assertEquals(expectedCost, book.calculatePrintingCosts());
    }

    /**
     * Test to verify the calculation of printing costs for a Book instance with a high number of pages.
     * Ensures that the printing costs are calculated correctly for a large number of pages.
     */
    @Test
    public void testCalculatePrintingCostsWithHighNumberOfPages() {
        Book book = new Book("Big Book", Integer.MAX_VALUE, standardPaper, 20.00);
        double expectedCost = Integer.MAX_VALUE * standardPaper.calculatePrice();
        assertEquals(expectedCost, book.calculatePrintingCosts());
    }

    /**
     * Test to verify the calculation of income with a discount applied.
     * Ensures that the income is calculated correctly with a 10% discount for copies beyond 100.
     */
    @Test
    public void testCalculateIncomeWithDiscount() {
        Paper standardPaper = new Paper(PaperSize.A4, PaperType.NEWS_PRINT, 0.05);
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        for (int i = 0; i < 101; i++) {
            book.print();
        }
        double expectedIncome = 100 * 15.00 + 1 * 15.00 * 0.90; // 10% discount for copies beyond 100
        assertEquals(expectedIncome, book.calculateIncome(), 0.001);
    }

    /**
     * Test to verify the calculation of income without a discount.
     * Ensures that the income is calculated correctly when there is no discount applied.
     */
    @Test
    public void testCalculateIncomeWithoutDiscount() {
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        for (int i = 0; i < 100; i++) {
            book.print();
        }
        double expectedIncome = 100 * 15.00;
        assertEquals(expectedIncome, book.calculateIncome());
    }
}