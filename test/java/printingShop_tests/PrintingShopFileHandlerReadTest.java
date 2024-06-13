package printingShop_tests;

import org.junit.jupiter.api.Test;
import org.project_printing_shop.printingShop.PrintingShop;
import org.project_printing_shop.printingShop.PrintingShopFileHandlerRead;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the PrintingShopFileHandlerRead class.
 * This class contains unit tests to validate the functionality of reading a PrintingShop instance from a file.
 */
public class PrintingShopFileHandlerReadTest {

    /**
     * Tests the loadPrintingShopFromFile method.
     * Ensures that a PrintingShop instance is correctly loaded from a file.
     *
     * @throws IOException if there is an error creating the test file
     */
    @Test
    public void testLoadPrintingShopFromFile() throws IOException {
        String testFilename = "test_printingshop.txt";
        createTestFile(testFilename);

        PrintingShop shop = PrintingShopFileHandlerRead.loadPrintingShopFromFile(testFilename);

        assertNotNull(shop);
        assertEquals("Test Shop", shop.getName());
        assertEquals(5000, shop.getTotalSales(), 0.001);

        new File(testFilename).delete();
    }

    /**
     * Creates a test file with the specified filename.
     *
     * @param filename the name of the file to create
     * @throws IOException if there is an error writing to the file
     */
    private void createTestFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("PrintingShopID: 123e4567-e89b-12d3-a456-426614174000\n");
            writer.write("Name: Test Shop\n");
            writer.write("Total Sales: 5000.0\n");
            writer.write("Total Expenses: 3000.0\n");
            writer.write("Employee: Manager: John, Base Salary: 1000, Salary: 1100.0\n");
            writer.write("Machine: Max Capacity: 1000, Pages Per Minute: 10, Color Support: true, Current Paper Load: 500\n");
            writer.write("Printed Editions: Edition: Type: Book, Title: Test Book, Number of Pages: 100, Size: A4, Type: STANDARD, Base Price: 0.1, Unit Price: 10.0, Printed Copies: 1=1\n");
            writer.write("Paper Pricing: STANDARD: 0.1\n");
        }
    }
}