package printable_items_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Newspaper;
import org.project_printing_shop.printable_items.Paper;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Newspaper class.
 * This class contains unit tests to validate the behavior of the Newspaper class.
 */
public class NewspaperTest {

    private Paper paper;

    /**
     * Sets up the test environment before each test.
     * Initializes a standard paper object for use in the tests.
     */
    @BeforeEach
    public void setUp() {
        paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    /**
     * Test to verify the creation of a Newspaper instance with valid data.
     * Ensures that the Newspaper object is correctly initialized.
     */
    @Test
    public void testCreateNewspaperWithValidData() {
        Newspaper newspaper = new Newspaper("Daily News", 50, paper, 1.00);
        assertNotNull(newspaper);
        assertEquals("Daily News", newspaper.getTitle());
        assertEquals(50, newspaper.getNumberOfPages());
        assertEquals(paper, newspaper.getPaper());
        assertEquals(1.00, newspaper.getUnitPrice());
    }

    /**
     * Test to verify that the constructor of Newspaper throws an IllegalArgumentException
     * when a negative number of pages is provided.
     */
    @Test
    public void testCreateNewspaperWithNegativeNumberOfPages() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Newspaper("Daily News", -50, paper, 1.00);
        });
        assertEquals("The number of pages must not be negative.", exception.getMessage());
    }

    /**
     * Test to verify that the constructor of Newspaper throws an IllegalArgumentException
     * when a negative unit price is provided.
     */
    @Test
    public void testCreateNewspaperWithNegativeUnitPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Newspaper("Daily News", 50, paper, -1.00);
        });
        assertEquals("Unit price must not be negative.", exception.getMessage());
    }

    /**
     * Test to verify the print functionality of the Newspaper class.
     * Ensures that the printed copies count is incremented correctly.
     */
    @Test
    public void testPrint() {
        Newspaper newspaper = new Newspaper("News", 50, paper, 1.00);
        newspaper.print();
        assertEquals(1, newspaper.getPrintedCopies());
    }

    /**
     * Test to verify the calculation of printing costs for a Newspaper instance.
     * Ensures that the printing costs are calculated correctly based on the number of pages and paper price.
     */
    @Test
    public void testCalculatePrintingCosts() {
        Newspaper newspaper = new Newspaper("News", 50, paper, 1.00);
        double expectedCost = 50 * paper.calculatePrice();
        assertEquals(expectedCost, newspaper.calculatePrintingCosts());
    }
}