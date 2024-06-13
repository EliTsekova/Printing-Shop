package printable_items_tests;

import  static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Paper;


/**
 * Test class for the Paper class.
 * This class contains unit tests to validate the behavior of the Paper class.
 */
class PaperTest {

    /**
     * Test to verify the creation of a Paper instance with valid data.
     * Ensures that the Paper object is correctly initialized.
     */
    @Test
    public void testCreatePaperWithValidData() {
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
        assertNotNull(paper);
        assertEquals(PaperSize.A4, paper.getSize());
        assertEquals(PaperType.STANDARD, paper.getType());
        assertEquals(0.10, paper.getBasePrice());
    }

    /**
     * Test to verify the calculation of paper prices for different sizes.
     * Ensures that the prices are calculated correctly based on the paper size and base price.
     */
    @Test
    void testCalculatePrice() {
        Paper paperA5 = new Paper(PaperSize.A5, PaperType.STANDARD, 0.05);
        assertEquals(0.05, paperA5.calculatePrice());

        Paper paperA4 = new Paper(PaperSize.A4, PaperType.STANDARD, 0.05);
        assertEquals(0.07500000000000001, paperA4.calculatePrice());

        Paper paperA3 = new Paper(PaperSize.A3, PaperType.STANDARD, 0.05);
        assertEquals(0.10, paperA3.calculatePrice());

        Paper paperA2 = new Paper(PaperSize.A2, PaperType.STANDARD, 0.05);
        assertEquals(0.15000000000000002, paperA2.calculatePrice());

        Paper paperA1 = new Paper(PaperSize.A1, PaperType.STANDARD, 0.05);
        assertEquals(0.20, paperA1.calculatePrice());
    }

    /**
     * Test to verify the calculation of paper prices with a zero base price.
     * Ensures that the calculated price is zero.
     */
    @Test
    public void testCalculatePriceWithZeroBasePrice() {
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.0);
        assertEquals(0.0, paper.calculatePrice());
    }

    /**
     * Test to verify the calculation of paper prices with a high base price.
     * Ensures that the calculated price is correctly scaled according to the paper size.
     */
    @Test
    public void testCalculatePriceWithHighBasePrice() {
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 1000.0);
        assertEquals(1000.0 * 1.5, paper.calculatePrice());
    }
}