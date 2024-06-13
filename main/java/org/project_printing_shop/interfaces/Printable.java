package org.project_printing_shop.interfaces;

/**
 * Interface representing a printable item in the printing shop.
 * This interface defines methods for printing the item and calculating printing costs.
 */
public interface Printable {

    /**
     * Prints the printable item.
     * This method is responsible for the printing process of the item.
     */
    void print();

    /**
     * Calculates the printing costs of the printable item.
     *
     * @return the calculated printing costs of the item.
     */
    double calculatePrintingCosts();
}
