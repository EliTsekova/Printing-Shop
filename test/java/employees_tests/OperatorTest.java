package employees_tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.Operator;

public class OperatorTest {

    @Test
    public void testCreateOperatorWithValidData() {
        Operator operator = new Operator("Eli", 800);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(800, operator.getBaseSalary());
        assertEquals(800, operator.calculateSalary());
    }

    @Test
    public void testCreateOperatorWithNegativeBaseSalary() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Operator("Eli", -800);
        });
        assertEquals("Base salary must be non-negative.", exception.getMessage());
    }

    @Test
    public void testCalculateSalary() {
        Operator operator = new Operator("Eli", 800);
        assertEquals(800, operator.calculateSalary());
    }

    @Test
    public void testCreateOperatorWithBaseSalaryZero() {
        Operator operator = new Operator("Eli", 0);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(0, operator.getBaseSalary());
        assertEquals(0, operator.calculateSalary());
    }

    @Test
    public void testCreateOperatorWithHighBaseSalary() {
        Operator operator = new Operator("Eli", 10000);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(10000, operator.getBaseSalary());
        assertEquals(10000, operator.calculateSalary());
    }

    @Test
    public void testCreateOperatorWithMaxBaseSalary() {
        Operator operator = new Operator("Eli", Double.MAX_VALUE);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(Double.MAX_VALUE, operator.getBaseSalary());
        assertEquals(Double.MAX_VALUE, operator.calculateSalary());
    }

    @Test
    public void testCreateOperatorWithSpecialCharactersInName() {
        Operator operator = new Operator("Eli", 800);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(800, operator.getBaseSalary());
        assertEquals(800, operator.calculateSalary());
    }
}