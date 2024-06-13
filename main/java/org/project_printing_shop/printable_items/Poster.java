package org.project_printing_shop.printable_items;

/**
 * Class representing a poster in the printing shop.
 * Inherits from the Edition class and provides specific implementations for printing and calculating printing costs.
 */
public class Poster extends Edition {

    /**
     * Constructor for the Poster class.
     *
     * @param title        the title of the poster
     * @param numberOfPages the number of pages in the poster
     * @param paper        the paper used for the poster
     * @param unitPrice    the unit price for printing the poster
     */
    public Poster(String title, int numberOfPages, Paper paper, double unitPrice) {
        super(title, numberOfPages, paper, unitPrice);
    }

    /**
     * Prints the poster and increments the printed copies count.
     * Overrides the print method in the Edition class.
     */
    @Override
    public void print() {
        super.print();
        System.out.println("Printing poster: " + title);
    }

    /**
     * Calculates the printing costs for the poster.
     * Overrides the calculatePrintingCosts method in the Edition class.
     *
     * @return the total printing costs for the poster
     */
    @Override
    public double calculatePrintingCosts() {
        double paperCost = paper.calculatePrice();
        return paperCost * numberOfPages;
    }
}
