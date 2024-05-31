package printable_items_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.printable_items.Poster;

class PosterTest {

    private Paper paper;

    @BeforeEach
    public void setUp() {
        paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    @Test
    public void testCreatePosterWithValidData() {
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        assertNotNull(poster);
        assertEquals("Concert Poster", poster.getTitle());
        assertEquals(1, poster.getNumberOfPages());
        assertEquals(paper, poster.getPaper());
        assertEquals(5.00, poster.getUnitPrice());
    }

    @Test
    public void testPrint() {
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        poster.print();
        assertEquals(1, poster.getPrintedCopies());
    }

    @Test
    public void testCalculatePrintingCosts() {
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        double expectedCost = 1 * paper.calculatePrice();
        assertEquals(expectedCost, poster.calculatePrintingCosts());
    }

    @Test
    public void testCalculateIncomeWithDiscount() {
        Poster poster = new Poster("Concert Poster", 1, paper, 5.00);
        for (int i = 0; i < 101; i++) {
            poster.print();
        }
        double expectedIncome = 101 * 5.00 * 0.90; // 10% discount
        assertEquals(expectedIncome, poster.calculateIncome());
    }

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
