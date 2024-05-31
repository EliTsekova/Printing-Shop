package employees_tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.EmployeeImpl;

class EmployeeImplTest {

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