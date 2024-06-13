package org.project_printing_shop.printable_items;

/**
 * Class representing a newspaper edition in the printing shop.
 * Inherits from the Edition class and provides specific behavior for newspapers.
 */
public class Newspaper extends Edition {

    /**
     * Constructor for the Newspaper class.
     *
     * @param title         the title of the newspaper
     * @param numberOfPages the number of pages in the newspaper
     * @param paper         the type of paper used for the newspaper
     * @param unitPrice     the unit price of the newspaper
     */
    public Newspaper(String title, int numberOfPages, Paper paper, double unitPrice) {
        super(title, numberOfPages, paper, unitPrice);
    }

    /**
     * Prints the newspaper.
     * Increments the printed copies count and prints a message specific to newspapers.
     */
    @Override
    public void print() {
        super.print();
        System.out.println("Printing newspaper: " + getTitle());
    }

    /**
     * Calculates the printing costs of the newspaper.
     * Uses the paper's price per unit and the number of pages to calculate the total printing cost.
     *
     * @return the calculated printing costs of the newspaper
     */
    @Override
    public double calculatePrintingCosts() {
        double paperCost = paper.calculatePrice();
        return paperCost * numberOfPages;
    }
}

