package printingShop_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.EmployeeImpl;
import org.project_printing_shop.employees.Operator;
import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;
import org.project_printing_shop.printable_items.Edition;
import org.project_printing_shop.printable_items.Paper;
import org.project_printing_shop.printable_items.Poster;
import org.project_printing_shop.printingShop.PrintingMachine;
import org.project_printing_shop.printingShop.PrintingShop;
import org.project_printing_shop.printingShop.PrintingShopFileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PrintingShopFileHandlerTest {

    private static final String TEST_FILE = "test_printing_shop.txt";
    private PrintingShop shop;

    @BeforeEach
    void setUp() {
        shop = new PrintingShop("Test Shop");
        shop.setId(UUID.randomUUID());
        EmployeeImpl employee1 = new Operator("Ivan", 2000.0);
        EmployeeImpl employee2 = new Operator("Pesho", 2500.0);
        shop.addEmployee(employee1);
        shop.addEmployee(employee2);
        PrintingMachine machine1 = new PrintingMachine(500, 50, true);
        PrintingMachine machine2 = new PrintingMachine(1000, 30, false);
        shop.addMachine(machine1);
        shop.addMachine(machine2);
        shop.setPaperPricing(PaperType.NEWS_PRINT, 0.05);
        shop.setPaperPricing(PaperType.GLOSSY, 0.10);
        Paper paper = new Paper(PaperSize.A4, PaperType.NEWS_PRINT, 0.05);
        Edition edition1 = new Poster("Concert Poster", 10, paper, 5.0);
        machine1.getPrintedEditions().add(edition1);
        machine1.getEditionCopies().put(edition1, 100);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoadPrintingShopToFile() throws IOException {
        PrintingShopFileHandler.savePrintingShopToFile(shop, TEST_FILE);
        PrintingShop loadedShop = PrintingShopFileHandler.loadPrintingShopFromFile(TEST_FILE);
        assertEquals(shop.getId(), loadedShop.getId());
        assertEquals(shop.getName(), loadedShop.getName());
        assertEquals(shop.getTotalSales(), loadedShop.getTotalSales(), 0.001);
        assertEquals(shop.calculateTotalExpenses(), loadedShop.calculateTotalExpenses(), 0.001);
        assertEquals(shop.getEmployees().size(), loadedShop.getEmployees().size());
        for (int i = 0; i < shop.getEmployees().size(); i++) {
            EmployeeImpl originalEmployee = shop.getEmployees().get(i);
            EmployeeImpl loadedEmployee = loadedShop.getEmployees().get(i);
            assertEquals(originalEmployee.getName(), loadedEmployee.getName());
            assertEquals(originalEmployee.getBaseSalary(), loadedEmployee.getBaseSalary(), 0.001);
        }
        assertEquals(shop.getMachines().size(), loadedShop.getMachines().size());
        for (int i = 0; i < shop.getMachines().size(); i++) {
            PrintingMachine originalMachine = shop.getMachines().get(i);
            PrintingMachine loadedMachine = loadedShop.getMachines().get(i);
            assertEquals(originalMachine.getMaxCapacity(), loadedMachine.getMaxCapacity());
            assertEquals(originalMachine.getPagesPerMinute(), loadedMachine.getPagesPerMinute());
            assertEquals(originalMachine.isColorSupport(), loadedMachine.isColorSupport());
            assertEquals(originalMachine.getCurrentPaperLoad(), loadedMachine.getCurrentPaperLoad());
            assertEquals(originalMachine.getPrintedEditions().size(), loadedMachine.getPrintedEditions().size());
            assertEquals(originalMachine.getEditionCopies(), loadedMachine.getEditionCopies());
        }
        assertEquals(shop.getPaperPricing(), loadedShop.getPaperPricing());
    }

    @Test
    void testSaveAndLoadEmptyPrintingShop() throws IOException {
        PrintingShop emptyShop = new PrintingShop("Empty Shop");
        emptyShop.setId(UUID.randomUUID());
        PrintingShopFileHandler.savePrintingShopToFile(emptyShop, TEST_FILE);
        PrintingShop loadedShop = PrintingShopFileHandler.loadPrintingShopFromFile(TEST_FILE);
        assertEquals(emptyShop.getId(), loadedShop.getId());
        assertEquals(emptyShop.getName(), loadedShop.getName());
        assertEquals(0, loadedShop.getEmployees().size());
        assertEquals(0, loadedShop.getMachines().size());
        assertEquals(0, loadedShop.getPaperPricing().size());
        assertEquals(emptyShop.getTotalSales(), loadedShop.getTotalSales(), 0.001);
        assertEquals(emptyShop.calculateTotalExpenses(), loadedShop.calculateTotalExpenses(), 0.001);
    }

    @Test
    void testSaveAndLoadPrintingShopWithEdgeCases() throws IOException {
        PrintingShop edgeCaseShop = new PrintingShop("Edge Case Shop");
        edgeCaseShop.setId(UUID.randomUUID());
        EmployeeImpl edgeEmployee = new Operator("Edge, Employee", 3000.0);
        edgeCaseShop.addEmployee(edgeEmployee);
        PrintingMachine edgeMachine = new PrintingMachine(Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        edgeCaseShop.addMachine(edgeMachine);
        edgeCaseShop.setPaperPricing(PaperType.NEWS_PRINT, Double.MAX_VALUE);
        edgeCaseShop.setPaperPricing(PaperType.GLOSSY, Double.MIN_VALUE);
        PrintingShopFileHandler.savePrintingShopToFile(edgeCaseShop, TEST_FILE);
        PrintingShop loadedShop = PrintingShopFileHandler.loadPrintingShopFromFile(TEST_FILE);
        assertEquals(edgeCaseShop.getId(), loadedShop.getId());
        assertEquals(edgeCaseShop.getName(), loadedShop.getName());
        assertEquals(edgeCaseShop.getEmployees().size(), loadedShop.getEmployees().size());
        EmployeeImpl loadedEmployee = loadedShop.getEmployees().get(0);
        assertEquals(edgeEmployee.getName(), loadedEmployee.getName());
        assertEquals(edgeEmployee.getBaseSalary(), loadedEmployee.getBaseSalary(), 0.001);
        assertEquals(edgeCaseShop.getMachines().size(), loadedShop.getMachines().size());
        PrintingMachine loadedMachine = loadedShop.getMachines().get(0);
        assertEquals(edgeMachine.getMaxCapacity(), loadedMachine.getMaxCapacity());
        assertEquals(edgeMachine.getPagesPerMinute(), loadedMachine.getPagesPerMinute());
        assertEquals(edgeMachine.isColorSupport(), loadedMachine.isColorSupport());
        assertEquals(edgeCaseShop.getPaperPricing(), loadedShop.getPaperPricing());
    }
}
