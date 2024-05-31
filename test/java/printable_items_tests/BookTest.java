package printable_items_tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Book;
import org.project_printing_shop.printable_items.Paper;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Paper standardPaper;

    @BeforeEach
    public void setUp() {
        standardPaper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    @Test
    public void testCreateBookWithValidData() {
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        assertNotNull(book);
        assertEquals("Java Programming", book.getTitle());
        assertEquals(300, book.getNumberOfPages());
        assertEquals(standardPaper, book.getPaper());
        assertEquals(15.00, book.getUnitPrice());
    }

    @Test
    public void testCreateBookWithNegativeNumberOfPages() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Java Programming", -300, standardPaper, 15.00);
        });
        assertEquals("The number of pages must not be negative.", exception.getMessage());
    }

    @Test
    public void testCreateBookWithNegativeUnitPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Java Programming", 300, standardPaper, -15.00);
        });
        assertEquals("Unit price must not be negative.", exception.getMessage());
    }

    @Test
    public void testPrint() {
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        book.print();
        assertEquals(1, book.getPrintedCopies());
    }

    @Test
    public void testCalculatePrintingCosts() {
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        double expectedCost = 300 * standardPaper.calculatePrice();
        assertEquals(expectedCost, book.calculatePrintingCosts());
    }

    @Test
    public void testCalculatePrintingCostsWithHighNumberOfPages() {
        Book book = new Book("Big Book", Integer.MAX_VALUE, standardPaper, 20.00);
        double expectedCost = Integer.MAX_VALUE * standardPaper.calculatePrice();
        assertEquals(expectedCost, book.calculatePrintingCosts());
    }

    @Test
    public void testCalculateIncomeWithDiscount() {
        Book book = new Book("Java Programming", 300, standardPaper, 15.00);
        for (int i = 0; i < 101; i++) {
            book.print();
        }
        double expectedIncome = 101 * 15.00 * 0.90; // 10% discount
        assertEquals(expectedIncome, book.calculateIncome());
    }

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
