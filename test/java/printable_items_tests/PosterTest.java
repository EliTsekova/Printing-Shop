package printable_items_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.printable_items.Poster;
/**
 * Test class for the Poster class.
 * This class contains unit tests to validate the behavior of the Poster class.
 */
class PosterTest {

    private Paper paper;

    /**
     * Sets up the test environment before each test.
     * Initializes a Paper instance with A4 size, STANDARD type, and a base price of 0.10.
     */
    @BeforeEach
    public void setUp() {
        paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    /**
     * Tests the creation of a Poster instance with valid data.
     * Ensures that the Poster object is correctly initialized.
     */
    @Test
    public void testCreatePosterWithValidData() {
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        assertNotNull(poster);
        assertEquals("Concert Poster", poster.getTitle());
        assertEquals(1, poster.getNumberOfPages());
        assertEquals(paper, poster.getPaper());
        assertEquals(5.00, poster.getUnitPrice());
    }

    /**
     * Tests the print method of the Poster class.
     * Ensures that the printed copies count is incremented correctly.
     */
    @Test
    public void testPrint() {
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        poster.print();
        assertEquals(1, poster.getPrintedCopies());
    }

    /**
     * Tests the calculation of printing costs for a Poster.
     * Ensures that the printing costs are calculated correctly based on the paper price.
     */
    @Test
    public void testCalculatePrintingCosts() {
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        double expectedCost = 1 * paper.calculatePrice();
        assertEquals(expectedCost, poster.calculatePrintingCosts());
    }

    /**
     * Tests the calculation of income for a Poster with a discount.
     * Ensures that the income is calculated correctly with a 10% discount for copies beyond 100.
     */
    @Test
    public void testCalculateIncomeWithDiscount() {
        Paper paper = new Paper(PaperSize.A4, PaperType.GLOSSY, 0.05);
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        for (int i = 0; i < 101; i++) {
            poster.print();
        }
        double expectedIncome = 100 * 5.00 + 1 * 5.00 * 0.90; // 10% discount for copies beyond 100
        assertEquals(expectedIncome, poster.calculateIncome(), 0.001);
    }

    /**
     * Tests the calculation of income for a Poster without a discount.
     * Ensures that the income is calculated correctly for up to 100 copies.
     */
    @Test
    public void testCalculateIncomeWithoutDiscount() {
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        for (int i = 0; i < 100; i++) {
            poster.print();
        }
        double expectedIncome = 100 * 5.00;
        assertEquals(expectedIncome, poster.calculateIncome());
    }
}
