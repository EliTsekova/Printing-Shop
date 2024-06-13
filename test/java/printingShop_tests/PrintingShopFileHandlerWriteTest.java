package printingShop_tests;

import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.Manager;
import org.project_printing_shop.printingShop.PrintingMachine;
import org.project_printing_shop.printingShop.PrintingShop;
import org.project_printing_shop.printingShop.PrintingShopFileHandlerWrite;
import org.project_printing_shop.printable_items.Book;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the PrintingShopFileHandlerWrite class.
 * This class contains unit tests to validate the functionality of writing a PrintingShop instance to a file.
 */
public class PrintingShopFileHandlerWriteTest {

    /**
     * Tests the savePrintingShopToFile method.
     * Ensures that a PrintingShop instance is correctly saved to a file.
     *
     * @throws IOException if there is an error creating or reading the test file
     */
    @Test
    public void testSavePrintingShopToFile() throws IOException {
        String testFilename = "test_output.txt";
        PrintingShop shop = new PrintingShop("Test Shop");
        shop.setTotalSales(5000.0);

        Manager manager = new Manager("John", 1000, 10, 3000, 4000);
        shop.addEmployee(manager);

        Paper paper = new Paper(PaperSize.A4, PaperType.STANDARD, 0.1);
        Book book = new Book("Test Book", 100, paper, 10.0);
        shop.addMachine(new PrintingMachine(1000, 10, true));

        PrintingShopFileHandlerWrite.savePrintingShopToFile(shop, testFilename);

        try (BufferedReader reader = new BufferedReader(new FileReader(testFilename))) {
            assertEquals("PrintingShopID: " + shop.getId(), reader.readLine());
            assertEquals("Name: Test Shop", reader.readLine());
            assertEquals("Total Sales: 5000.0", reader.readLine());
        }

        new File(testFilename).delete();
    }
}
