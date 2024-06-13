package org.project_printing_shop.printable_items;

/**
 * Class representing a book in the printing shop.
 * Inherits the class Edition and provides specific implementation for printing and calculating costs for a book.
 */
public class Book extends Edition {

    /**
     * Constructor for the Book class.
     *
     * @param title        the title of the book
     * @param numberOfPages the number of pages in the book
     * @param paper        the type of paper used for the book
     * @param unitPrice    the unit price of the book
     */
    public Book(String title, int numberOfPages, Paper paper, double unitPrice) {
        super(title, numberOfPages, paper, unitPrice);
    }

    /**
     * Prints the book.
     * Calls the print method from the superclass and adds specific print logic for a book.
     */
    @Override
    public void print() {
        super.print();
        System.out.println("Printing book: " + title);
    }

    /**
     * Calculates the printing costs of the book.
     * Uses the price of the paper and the number of pages to calculate the total printing cost.
     *
     * @return the calculated printing costs of the book
     */
    @Override
    public double calculatePrintingCosts() {
        double paperCost = paper.calculatePrice();
        return paperCost * numberOfPages;
    }
}
