package employees_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.Manager;

class ManagerTest {

    @Test
    public void testCreateManagerWithValidData() {
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 6000);
        assertNotNull(manager);
        assertEquals("John Doe", manager.getName());
        assertEquals(1000, manager.getBaseSalary());
        assertEquals(1100, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithInvalidBonusPercentage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("John Doe", 1000, 110, 5000, 6000);
        });
        assertEquals("Bonus percentage must be between 0 and 100.", exception.getMessage());
    }

    @Test
    public void testCreateManagerWithNegativeRevenueThreshold() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("John Doe", 1000, 10, -5000, 6000);
        });
        assertEquals("Revenue values must not be negative.", exception.getMessage());
    }

    @Test
    public void testCreateManagerWithNegativeCurrentRevenue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("John Doe", 1000, 10, 5000, -6000);
        });
        assertEquals("Revenue values must not be negative.", exception.getMessage());
    }

    @Test
    public void testCalculateSalaryWithRevenueAboveThreshold() {
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 6000);
        assertEquals(1100, manager.calculateSalary());
    }

    @Test
    public void testCalculateSalaryWithRevenueBelowThreshold() {
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 4000);
        assertEquals(1000, manager.calculateSalary());
    }

    @Test
    public void testCalculateSalaryWithRevenueEqualToThreshold() {
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 5000);
        assertEquals(1000, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBonusPercentageZero() {
        Manager manager = new Manager("John Doe", 1000, 0, 5000, 6000);
        assertEquals(1000, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBonusPercentageHundred() {
        Manager manager = new Manager("John Doe", 1000, 100, 5000, 6000);
        assertEquals(2000, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBonusPercentageBoundary() {
        Manager manager = new Manager("John Doe", 1000, 100, 5000, 6000);
        assertNotNull(manager);
        assertEquals("John Doe", manager.getName());
        assertEquals(1000, manager.getBaseSalary());
        assertEquals(2000, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBaseSalaryZero() {
        Manager manager = new Manager("John Doe", 0, 10, 5000, 6000);
        assertEquals(0, manager.calculateSalary());
    }

    @Test
    public void testCreateManagerWithBaseSalaryPositive() {
        Manager manager = new Manager("John Doe", 1000, 10, 5000, 6000);
        assertEquals(1100, manager.calculateSalary());
    }
}