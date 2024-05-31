package printable_items_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Newspaper;
import org.project_printing_shop.printable_items.Paper;

import static org.junit.jupiter.api.Assertions.*;

public class NewspaperTest {

    private Paper paper;

    @BeforeEach
    public void setUp() {
        paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    @Test
    public void testCreateNewspaperWithValidData() {
        Newspaper newspaper = new Newspaper("Daily News", 50, paper, 1.00, 0.90, 100);
        assertNotNull(newspaper);
        assertEquals("Daily News", newspaper.getTitle());
        assertEquals(50, newspaper.getNumberOfPages());
        assertEquals(paper, newspaper.getPaper());
        assertEquals(1.00, newspaper.getUnitPrice());
        assertEquals(0.90, newspaper.getDiscountRate());
        assertEquals(100, newspaper.getDiscountThreshold());
    }

    @Test
    public void testCreateNewspaperWithNegativeNumberOfPages() {
        try {
            new Newspaper("Daily News", -50, paper, 1.00, 0.90, 100);
            fail("Expected IllegalArgumentException for negative number of pages");
        } catch (IllegalArgumentException e) {
            assertEquals("Number of pages must be non-negative.", e.getMessage());
        }
    }

    @Test
    public void testCreateNewspaperWithNegativeUnitPrice() {
        try {
            new Newspaper("Daily News", 50, paper, -1.00, 0.90, 100);
            fail("Expected IllegalArgumentException for negative unit price");
        } catch (IllegalArgumentException e) {
            assertEquals("Unit price must be non-negative.", e.getMessage());
        }
    }

    @Test
    public void testCreateNewspaperWithNegativeDiscountRate() {
        try {
            new Newspaper("Daily News", 50, paper, 1.00, -0.90, 100);
            fail("Expected IllegalArgumentException for negative discount rate");
        } catch (IllegalArgumentException e) {
            assertEquals("Discount rate must be non-negative.", e.getMessage());
        }
    }

    @Test
    public void testCreateNewspaperWithNegativeDiscountThreshold() {
        try {
            new Newspaper("Daily News", 50, paper, 1.00, 0.90, -100);
            fail("Expected IllegalArgumentException for negative discount threshold");
        } catch (IllegalArgumentException e) {
            assertEquals("Discount threshold must be non-negative.", e.getMessage());
        }
    }

    @Test
    public void testPrint() {
        Newspaper newspaper = new Newspaper("Daily News", 50, paper, 1.00, 0.90, 100);
        newspaper.print();
        assertEquals(1, newspaper.getPrintedCopies());
    }

    @Test
    public void testCalculatePrintingCosts() {
        Newspaper newspaper = new Newspaper("Daily News", 50, paper, 1.00, 0.90, 100);
        double expectedCost = 50 * paper.calculatePrice();
        assertEquals(expectedCost, newspaper.calculatePrintingCosts());
    }

    @Test
    public void testCalculatePrintingCostsWithDiscount() {
        Newspaper newspaper = new Newspaper("Daily News", 150, paper, 1.00, 0.90, 100);
        double baseCost = 150 * paper.calculatePrice();
        double expectedCost = baseCost * 0.90; // 10% discount
        assertEquals(expectedCost, newspaper.calculatePrintingCosts());
    }

    @Test
    public void testCalculateIncomeWithDiscount() {
        Newspaper newspaper = new Newspaper("Daily News", 50, paper, 1.00, 0.90, 100);
        for (int i = 0; i < 101; i++) {
            newspaper.print();
        }
        double expectedIncome = 101 * 1.00 * 0.90; // 10% discount
        assertEquals(expectedIncome, newspaper.calculateIncome());
    }

    @Test
    public void testCalculateIncomeWithoutDiscount() {
        Newspaper newspaper = new Newspaper("Daily News", 50, paper, 1.00, 0.90, 100);
        for (int i = 0; i < 100; i++) {
            newspaper.print();
        }
        double expectedIncome = 100 * 1.00;
        assertEquals(expectedIncome, newspaper.calculateIncome());
    }
}
