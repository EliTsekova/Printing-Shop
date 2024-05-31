package employees_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.Manager;

class ManagerTest {

    @Test
    public void testCreateManagerWithValidData() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 6000);
        assertNotNull(manager);
        assertEquals("Marina", manager.getName());
        assertEquals(1000, manager.getBaseSalary());
        assertEquals(1100, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithInvalidBonusPercentage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("Marina", 1000, 110, 5000, 6000);
        });
        assertEquals("The bonus percentage must be between 0 and 100.", exception.getMessage());
    }

    @Test
    public void testCreateManagerWithNegativeRevenueLimit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("Marina", 1000, 10, -5000, 6000);
        });
        assertEquals("The revenue values must not be negative.", exception.getMessage());
    }

    @Test
    public void testCreateManagerWithNegativeCurrentRevenue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("Marina", 1000, 10, 5000, -6000);
        });
        assertEquals("The revenue values must not be negative.", exception.getMessage());
    }

    @Test
    public void testCalculateSalaryWithRevenueAboveLimit() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 6000);
        assertEquals(1100, manager.calculateSalary());
    }

    @Test
    public void testCalculateSalaryWithRevenueBelowLimit() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 4000);
        assertEquals(1000, manager.calculateSalary());
    }

    @Test
    public void testCalculateSalaryWithRevenueEqualToLimit() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 5000);
        assertEquals(1000, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBonusPercentageZero() {
        Manager manager = new Manager("Marina", 1000, 0, 5000, 6000);
        assertEquals(1000, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBonusPercentageHundred() {
        Manager manager = new Manager("Marina", 1000, 100, 5000, 6000);
        assertEquals(2000, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBonusPercentageBoundary() {
        Manager manager = new Manager("Marina", 1000, 100, 5000, 6000);
        assertNotNull(manager);
        assertEquals("Marina", manager.getName());
        assertEquals(1000, manager.getBaseSalary());
        assertEquals(2000, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBaseSalaryZero() {
        Manager manager = new Manager("Marina", 0, 10, 5000, 6000);
        assertEquals(0, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBaseSalaryPositive() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 6000);
        assertEquals(1100, manager.calculateSalary());
    }
}