package printingShop_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.printable_items.Poster;
import org.project_printing_shop.printingShop.PrintingMachine;
import org.project_printing_shop.exceptions.UnsupportedColorException;

import static org.junit.jupiter.api.Assertions.*;

public class PrintingMachineTest {

    private PrintingMachine colorMachine;
    private PrintingMachine bwMachine;
    private Paper standardPaper;

    @BeforeEach
    public void setUp() {
        colorMachine = new PrintingMachine(1000, 50, true);
        bwMachine = new PrintingMachine(1000, 50, false);
        standardPaper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.10);
    }

    @Test
    public void testCreatePrintingMachineWithValidData() {
        PrintingMachine machine = new PrintingMachine(1000, 50, true);
        assertNotNull(machine);
        assertEquals(1000, machine.getMaxCapacity());
        assertEquals(50, machine.getPagesPerMinute());
        assertTrue(machine.isColorSupport());
    }

    @Test
    public void testLoadPaperWithinCapacity() {
        colorMachine.loadPaper(500);
        assertEquals(500, colorMachine.getCurrentPaperLoad());
    }

    @Test
    public void testLoadPaperExceedsCapacity() {
        try {
            colorMachine.loadPaper(1500);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot load paper, because the machine capacity is exceeded.", e.getMessage());
        }
    }

    @Test
    public void testPrintEditionWithSufficientPaper() {
        Edition poster = new Poster("Concert Poster", 10, standardPaper, 5.00);
        colorMachine.loadPaper(500);
        try {
            colorMachine.printEdition(poster, true);
        } catch (UnsupportedColorException | IllegalArgumentException e) {
            fail("Exception should not be thrown");
        }
        assertEquals(490, colorMachine.getCurrentPaperLoad());
        assertEquals(1, colorMachine.getEditionCopies().get(poster));
        assertEquals(10, colorMachine.getTotalPrintedPages());
    }

    @Test
    public void testPrintEditionWithInsufficientPaper() {
        Edition poster = new Poster("Concert Poster", 10, standardPaper, 5.00);
        colorMachine.loadPaper(5);
        try {
            colorMachine.printEdition(poster, true);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("There isn't enough paper to print the publication.", e.getMessage());
        } catch (UnsupportedColorException e) {
            fail("Unexpected UnsupportedColorException was thrown.");
        }
    }

    @Test
    public void testPrintEditionWithUnsupportedColor() {
        Edition poster = new Poster("Concert Poster", 10, standardPaper, 5.00);
        bwMachine.loadPaper(500);
        try {
            bwMachine.printEdition(poster, true);
            fail("Expected UnsupportedColorException was not thrown.");
        } catch (UnsupportedColorException e) {
            assertEquals("This machine does not support color printing.", e.getMessage());
        } catch (IllegalArgumentException e) {
            fail("Unexpected IllegalArgumentException was thrown.");
        }
    }

    @Test
    public void testGetTotalPrintedPages() {
        Edition poster1 = new Poster("Concert Poster 1", 10, standardPaper, 5.00);
        Edition poster2 = new Poster("Concert Poster 2", 20, standardPaper, 5.00);
        colorMachine.loadPaper(1000);
        try {
            colorMachine.printEdition(poster1, true);
            colorMachine.printEdition(poster2, true);
        } catch (UnsupportedColorException | IllegalArgumentException e) {
            fail("Exception should not be thrown");
        }
        assertEquals(970, colorMachine.getCurrentPaperLoad());
        assertEquals(30, colorMachine.getTotalPrintedPages());
    }

    @Test
    public void testCalculatePrintingTime() {
        double printingTime = colorMachine.calculatePrintingTime(100);
        assertEquals(2.0, printingTime);
    }
}