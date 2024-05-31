package printable_items_tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Book;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.printable_items.Paper;

import static org.junit.jupiter.api.Assertions.*;

public class EditionTest {

    private Paper standardPaper;

    @BeforeEach
    public void setUp() {
        standardPaper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    @Test
    public void testCreateEditionWithValidData() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        assertNotNull(edition);
        assertEquals("Java Programming", edition.getTitle());
        assertEquals(300, edition.getNumberOfPages());
        assertEquals(standardPaper, edition.getPaper());
        assertEquals(15.00, edition.getUnitPrice());
    }

    @Test
    public void testCreateEditionWithNegativeNumberOfPages() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Java Programming", -300, standardPaper, 15.00);
        });
        assertEquals("The number of pages must not be negative.", exception.getMessage());
    }

    @Test
    public void testCreateEditionWithNegativeUnitPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Java Programming", 300, standardPaper, -15.00);
        });
        assertEquals("Unit price must not be negative.", exception.getMessage());
    }

    @Test
    public void testPrint() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.print();
        assertEquals(1, edition.getPrintedCopies());
    }

    @Test
    public void testPrintMultipleCopies() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        for (int i = 0; i < 50; i++) {
            edition.print();
        }
        assertEquals(50, edition.getPrintedCopies());
    }

    @Test
    public void testCalculatePrintingCosts() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        double expectedCost = 300 * standardPaper.calculatePrice();
        assertEquals(expectedCost, edition.calculatePrintingCosts());
    }

    @Test
    public void testCalculateIncomeWithDiscount() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        for (int i = 0; i < 101; i++) {
            edition.print();
        }
        double expectedIncome = 101 * 15.00 * 0.90; // 10% discount
        assertEquals(expectedIncome, edition.calculateIncome());
    }

    @Test
    public void testCalculateIncomeWithoutDiscount() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        for (int i = 0; i < 100; i++) {
            edition.print();
        }
        double expectedIncome = 100 * 15.00;
        assertEquals(expectedIncome, edition.calculateIncome());
    }

    @Test
    public void testSetAndGetTitle() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.setTitle("Advanced Java Programming");
        assertEquals("Advanced Java Programming", edition.getTitle());
    }

    @Test
    public void testSetAndGetNumberOfPages() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.setNumberOfPages(400);
        assertEquals(400, edition.getNumberOfPages());
    }

    @Test
    public void testSetAndGetPaper() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        Paper glossyPaper = new Paper(PaperSize.A4, PaperType.GLOSSY, 0.20);
        edition.setPaper(glossyPaper);
        assertEquals(glossyPaper, edition.getPaper());
    }

    @Test
    public void testSetAndGetUnitPrice() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.setUnitPrice(20.00);
        assertEquals(20.00, edition.getUnitPrice());
    }

    @Test
    public void testSetAndGetPrintedCopies() {
        Edition edition = new Book("Java Programming", 300, standardPaper, 15.00);
        edition.setPrintedCopies(50);
        assertEquals(50, edition.getPrintedCopies());
    }
}