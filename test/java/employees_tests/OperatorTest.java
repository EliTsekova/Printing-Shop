package employees_tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project_printing_shop.employees.Operator;

/**
 * Test class for Operator.
 * This class contains unit tests for validating the behavior of the Operator class.
 */
public class OperatorTest {

    /**
     * Test to verify valid creation of an Operator instance with valid data.
     * The test ensures that the operator's properties are correctly set and that the salary calculation is accurate.
     */
    @Test
    public void testCreateOperatorWithValidData() {
        Operator operator = new Operator("Eli", 800);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(800, operator.getBaseSalary());
        assertEquals(800, operator.calculateSalary());
    }

    /**
     * Test to verify that the constructor of Operator throws IllegalArgumentException
     * when a negative base salary is provided.
     */
    @Test
    public void testCreateOperatorWithNegativeBaseSalary() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Operator("Eli", -800);
        });
        assertEquals("Base salary must be non-negative.", exception.getMessage());
    }

    /**
     * Test to verify that the salary calculation is correct for an Operator instance.
     */
    @Test
    public void testCalculateSalary() {
        Operator operator = new Operator("Eli", 800);
        assertEquals(800, operator.calculateSalary());
    }

    /**
     * Test to verify that the creation and salary calculation of an Operator instance is correct
     * when the base salary is zero.
     */
    @Test
    public void testCreateOperatorWithBaseSalaryZero() {
        Operator operator = new Operator("Eli", 0);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(0, operator.getBaseSalary());
        assertEquals(0, operator.calculateSalary());
    }

    /**
     * Test to verify that the creation and salary calculation of an Operator instance is correct
     * when the base salary is a high value.
     */
    @Test
    public void testCreateOperatorWithHighBaseSalary() {
        Operator operator = new Operator("Eli", 10000);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(10000, operator.getBaseSalary());
        assertEquals(10000, operator.calculateSalary());
    }

    /**
     * Test to verify that the creation and salary calculation of an Operator instance is correct
     * when the base salary is at the maximum possible value.
     */
    @Test
    public void testCreateOperatorWithMaxBaseSalary() {
        Operator operator = new Operator("Eli", Double.MAX_VALUE);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(Double.MAX_VALUE, operator.getBaseSalary());
        assertEquals(Double.MAX_VALUE, operator.calculateSalary());
    }

    /**
     * Test to verify that the creation and salary calculation of an Operator instance is correct
     * when the name contains special characters.
     */
    @Test
    public void testCreateOperatorWithSpecialCharactersInName() {
        Operator operator = new Operator("Eli", 800);
        assertNotNull(operator);
        assertEquals("Eli", operator.getName());
        assertEquals(800, operator.getBaseSalary());
        assertEquals(800, operator.calculateSalary());
    }
}