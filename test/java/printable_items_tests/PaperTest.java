package printable_items_tests;

import  static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Paper;

class PaperTest {

    private Paper standardPaper;

    @BeforeEach
    public void setUp() {
        standardPaper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    @Test
    public void testCreatePaperWithValidData() {
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
        assertNotNull(paper);
        assertEquals(PaperSize.A4, paper.getSize());
        assertEquals(PaperType.STANDARD, paper.getType());
        assertEquals(0.10, paper.getBasePrice());
    }

    @Test
    public void testCalculatePriceForA1() {
        Paper paper = new Paper(PaperSize.A1, PaperType.STANDARD, 0.10);
        assertEquals(0.10 * 4.0, paper.calculatePrice());
    }

    @Test
    public void testCalculatePriceForA2() {
        Paper paper = new Paper(PaperSize.A2, PaperType.STANDARD, 0.10);
        assertEquals(0.10 * 3.0, paper.calculatePrice());
    }

    @Test
    public void testCalculatePriceForA3() {
        Paper paper = new Paper(PaperSize.A3, PaperType.STANDARD, 0.10);
        assertEquals(0.10 * 2.0, paper.calculatePrice());
    }

    @Test
    public void testCalculatePriceForA4() {
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
        assertEquals(0.10 * 1.5, paper.calculatePrice());
    }

    @Test
    public void testCalculatePriceForA5() {
        Paper paper = new Paper(PaperSize.A5, PaperType.STANDARD, 0.10);
        assertEquals(0.10 * 1.0, paper.calculatePrice());
    }

    @Test
    public void testCalculatePriceForDifferentPaperTypes() {
        Paper glossyPaper = new Paper(PaperSize.A4, PaperType.GLOSSY, 0.20);
        assertEquals(0.20 * 1.5, glossyPaper.calculatePrice());

        Paper newsPrintPaper = new Paper(PaperSize.A4, PaperType.NEWS_PRINT, 0.05);
        assertEquals(0.05 * 1.5, newsPrintPaper.calculatePrice());
    }

    @Test
    public void testCalculatePriceWithZeroBasePrice() {
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.0);
        assertEquals(0.0, paper.calculatePrice());
    }

    @Test
    public void testCalculatePriceWithHighBasePrice() {
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 1000.0);
        assertEquals(1000.0 * 1.5, paper.calculatePrice());
    }
}