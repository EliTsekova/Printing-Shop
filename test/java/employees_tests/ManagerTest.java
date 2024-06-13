package employees_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.Manager;

/**
 * Test class for Manager.
 * This class contains unit tests for validating the behavior of the Manager class.
 */
class ManagerTest {

    /**
     * Test to verify valid creation of a Manager instance with valid data.
     * The test ensures that the manager's properties are correctly set and that the salary calculation is accurate.
     */
    @Test
    public void testCreateManagerWithValidData() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 6000);
        assertNotNull(manager);
        assertEquals("Marina", manager.getName());
        assertEquals(1000, manager.getBaseSalary());
        assertEquals(1100, manager.calculateSalary());
    }

    /**
     * Test to verify that the constructor of Manager throws IllegalArgumentException
     * when an invalid bonus percentage (greater than 100) is provided.
     */
    @Test
    public void testCreateManagerWithInvalidBonusPercentage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("Marina", 1000, 110, 5000, 6000);
        });
        assertEquals("The bonus percentage must be between 0 and 100.", exception.getMessage());
    }

    /**
     * Test to verify that the constructor of Manager throws IllegalArgumentException
     * when a negative revenue limit is provided.
     */
    @Test
    public void testCreateManagerWithNegativeRevenueLimit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("Marina", 1000, 10, -5000, 6000);
        });
        assertEquals("The revenue values must not be negative.", exception.getMessage());
    }

    /**
     * Test to verify that the constructor of Manager throws IllegalArgumentException
     * when a negative current revenue is provided.
     */
    @Test
    public void testCreateManagerWithNegativeCurrentRevenue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager("Marina", 1000, 10, 5000, -6000);
        });
        assertEquals("The revenue values must not be negative.", exception.getMessage());
    }

    /**
     * Test to verify that the salary calculation is correct when the current revenue is above the revenue limit.
     */
    @Test
    public void testCalculateSalaryWithRevenueAboveLimit() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 6000);
        assertEquals(1100, manager.calculateSalary());
    }

    /**
     * Test to verify that the salary calculation is correct when the current revenue is below the revenue limit.
     */
    @Test
    public void testCalculateSalaryWithRevenueBelowLimit() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 4000);
        assertEquals(1000, manager.calculateSalary());
    }

    /**
     * Test to verify that the salary calculation is correct when the current revenue is equal to the revenue limit.
     */
    @Test
    public void testCalculateSalaryWithRevenueEqualToLimit() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 5000);
        assertEquals(1000, manager.calculateSalary());
    }

    /**
     * Test to verify that the salary calculation is correct when the bonus percentage is zero.
     */
    @Test
    public void testCreateManagerWithBonusPercentageZero() {
        Manager manager = new Manager("Marina", 1000, 0, 5000, 6000);
        assertEquals(1000, manager.calculateSalary());
    }

    /**
     * Test to verify that the salary calculation is correct when the bonus percentage is 100.
     */
    @Test
    public void testCreateManagerWithBonusPercentageHundred() {
        Manager manager = new Manager("Marina", 1000, 100, 5000, 6000);
        assertEquals(2000, manager.calculateSalary());
    }

    /**
     * Test to verify that the creation and salary calculation of a Manager instance is correct
     * when the bonus percentage is at the boundary value (100).
     */
    @Test
    public void testCreateManagerWithBonusPercentageBoundary() {
        Manager manager = new Manager("Marina", 1000, 100, 5000, 6000);
        assertNotNull(manager);
        assertEquals("Marina", manager.getName());
        assertEquals(1000, manager.getBaseSalary());
        assertEquals(2000, manager.calculateSalary());
    }

    /**
     * Test to verify that the creation and salary calculation of a Manager instance is correct
     * when the base salary is zero.
     */
    @Test
    public void testCreateManagerWithBaseSalaryZero() {
        Manager manager = new Manager("Marina", 0, 10, 5000, 6000);
        assertEquals(0, manager.calculateSalary());
    }

    /**
     * Test to verify that the creation and salary calculation of a Manager instance is correct
     * when the base salary is positive.
     */
    @Test
    public void testCreateManagerWithBaseSalaryPositive() {
        Manager manager = new Manager("Marina", 1000, 10, 5000, 6000);
        assertEquals(1100, manager.calculateSalary());
    }
}