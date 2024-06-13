package employees_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.EmployeeImpl;

/**
 * Test class for EmployeeImpl.
 * This class contains unit tests for validating the behavior of the EmployeeImpl class.
 */
class EmployeeImplTest {

    /**
     * Test to verify valid creation of an EmployeeImpl instance.
     * The test ensures that the employee's name and base salary are correctly set.
     */
    @Test
    void validEmployeeCreation() {
        EmployeeImpl employee = new EmployeeImpl("Ivan", 3000) {
            @Override
            public double calculateSalary() {
                return baseSalary; // Simple implementation for testing
            }
        };
        assertEquals("Ivan", employee.getName());
        assertEquals(3000, employee.getBaseSalary());
    }

    /**
     * Test to verify that the constructor of EmployeeImpl throws IllegalArgumentException
     * when a null name is provided.
     */
    @Test
    void constructorThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EmployeeImpl(null, 3000) {
                @Override
                public double calculateSalary() {
                    return baseSalary;
                }
            };
        }, "Should throw IllegalArgumentException for null name");
    }

    /**
     * Test to verify that the constructor of EmployeeImpl throws IllegalArgumentException
     * when a negative base salary is provided.
     */
    @Test
    void constructorNegativeBaseSalaryThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EmployeeImpl("Ivan", -100) {
                @Override
                public double calculateSalary() {
                    return baseSalary;
                }
            };
        }, "Should throw IllegalArgumentException for negative base salary");
    }

    /**
     * Test to verify that the constructor of EmployeeImpl throws IllegalArgumentException
     * when an empty name is provided.
     */
    @Test
    void constructorThrowsIllegalArgumentExceptionForEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EmployeeImpl("", 3000) {
                @Override
                public double calculateSalary() {
                    return baseSalary;
                }
            };
        }, "Should throw IllegalArgumentException for empty name");
    }

    /**
     * Test to verify that the constructor of EmployeeImpl allows a base salary of zero.
     */
    @Test
    void constructorAllowsZeroBaseSalary() {
        EmployeeImpl employee = new EmployeeImpl("Ivan", 0) {
            @Override
            public double calculateSalary() {
                return baseSalary;
            }
        };
        assertEquals(0, employee.getBaseSalary());
    }
}
