package printingShop_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.exceptions.NoSuitableMachineException;
import org.project_printing_shop.exceptions.UnsupportedColorException;
import org.project_printing_shop.printable_items.Book;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printingShop.PrintingMachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for the PrintingMachine class.
 * This class contains unit tests to validate the behavior of the PrintingMachine class.
 */
public class PrintingMachineTest {

    private PrintingMachine machine;
    private Edition book;

    /**
     * Sets up the test environment before each test.
     * Initializes a PrintingMachine instance and a Book instance with A4 size, STANDARD type, and a base price of 0.10.
     */
    @BeforeEach
    public void setUp() {
        machine = new PrintingMachine(1000, 10, true);
        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.1);
        book = new Book("Test Book", 100, paper, 10.0);
    }

    /**
     * Tests the loadPaper method of the PrintingMachine class.
     * Ensures that the paper load is correctly updated.
     */
    @Test
    public void testLoadPaper() {
        machine.loadPaper(500);
        assertEquals(500, machine.getCurrentPaperLoad());
        machine.loadPaper(500);
        assertEquals(1000, machine.getCurrentPaperLoad());
    }

    /**
     * Tests the loadPaper method of the PrintingMachine class when exceeding the capacity.
     * Ensures that an IllegalArgumentException is thrown when trying to load more paper than the machine's capacity.
     */
    @Test
    public void testLoadPaperExceedingCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            machine.loadPaper(1500);
        });
    }

    /**
     * Tests the printEdition method of the PrintingMachine class.
     * Ensures that an edition is printed correctly and the paper load is updated.
     */
    @Test
    public void testPrintEdition() {
        machine.loadPaper(200);
        try {
            machine.printEdition(book, false);
            assertEquals(1, book.getPrintedCopies());
            assertEquals(100, machine.getCurrentPaperLoad());
        } catch (UnsupportedColorException | NoSuitableMachineException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }

    /**
     * Tests the printEdition method of the PrintingMachine class when color is not supported.
     * Ensures that an UnsupportedColorException is thrown when trying to print in color on a black and white machine.
     */
    @Test
    public void testPrintEditionColorNotSupported() {
        PrintingMachine bwMachine = new PrintingMachine(1000, 10, false);
        bwMachine.loadPaper(200);
        assertThrows(UnsupportedColorException.class, () -> {
            bwMachine.printEdition(book, true);
        });
    }

    /**
     * Tests the printEdition method of the PrintingMachine class when there is not enough paper.
     * Ensures that a NoSuitableMachineException is thrown when there is not enough paper to print the edition.
     */
    @Test
    public void testPrintEditionNotEnoughPaper() {
        machine.loadPaper(50);
        assertThrows(NoSuitableMachineException.class, () -> {
            machine.printEdition(book, false);
        });
    }

    /**
     * Tests the getTotalPrintedPages method of the PrintingMachine class.
     * Ensures that the total printed pages are correctly calculated.
     */
    @Test
    public void testGetTotalPrintedPages() {
        machine.loadPaper(200);
        try {
            machine.printEdition(book, false);
        } catch (UnsupportedColorException | NoSuitableMachineException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
        assertEquals(100, machine.getTotalPrintedPages());
    }

    /**
     * Tests the calculatePrintingTime method of the PrintingMachine class.
     * Ensures that the printing time is correctly calculated based on the number of pages.
     */
    @Test
    public void testCalculatePrintingTime() {
        assertEquals(10, machine.calculatePrintingTime(100));
        assertEquals(15, machine.calculatePrintingTime(150));
    }
}