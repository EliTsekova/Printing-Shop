package org.project_printing_shop.printable_items;

import org.project_printing_shop.interfaces.Printable;

/**
 * Abstract class representing a printable edition in the printing shop.
 * Implements the Printable interface and provides base properties and methods for editions such as title, number of pages, paper type, and unit price.
 */
public abstract class Edition implements Printable{
    protected String title;
    protected int numberOfPages;
    protected Paper paper;
    protected double unitPrice;
    protected int printedCopies = 0;

    /**
     * Constructor for Edition.
     *
     * @param title        the title of the edition
     * @param numberOfPages the number of pages in the edition
     * @param paper        the type of paper used for the edition
     * @param unitPrice    the unit price of the edition
     * @throws IllegalArgumentException if the number of pages or unit price is negative
     */
    public Edition(String title, int numberOfPages, Paper paper, double unitPrice) {
        if (numberOfPages < 0) {
            throw new IllegalArgumentException("The number of pages must not be negative.");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must not be negative.");
        }
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.paper = paper;
        this.unitPrice = unitPrice;
    }

    /**
     * Prints a copy of the edition.
     * Increments the count of printed copies.
     */
    @Override
    public void print() {
        printedCopies++;
        System.out.println("Printing edition: " + title + ". Total printed copies: " + printedCopies);
    }

    /**
     * Calculates the printing costs of the edition.
     *
     * @return the total printing costs
     */
    @Override
    public abstract double calculatePrintingCosts();

    /**
     * Calculates the total income from the printed copies.
     * Provides a discount for copies beyond 100.
     *
     * @return the total income from the printed copies
     */
    public double calculateIncome() {
        System.out.println("Total printed copies for income calculation: " + printedCopies);
        if (printedCopies > 100) {
            int discountedCopies = printedCopies - 100;
            double discountedTotal = discountedCopies * unitPrice * 0.90;
            return 100 * unitPrice + discountedTotal;
        } else {
            return printedCopies * unitPrice;
        }
    }

    /**
     * Gets the title of the edition.
     *
     * @return the title of the edition
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the edition.
     *
     * @param title the new title of the edition
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the number of pages in the edition.
     *
     * @return the number of pages in the edition
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Sets the number of pages in the edition.
     *
     * @param numberOfPages the new number of pages in the edition
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Gets the type of paper used for the edition.
     *
     * @return the type of paper used for the edition
     */
    public Paper getPaper() {
        return paper;
    }

    /**
     * Sets the type of paper used for the edition.
     *
     * @param paper the new type of paper used for the edition
     */
    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    /**
     * Gets the unit price of the edition.
     *
     * @return the unit price of the edition
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the unit price of the edition.
     *
     * @param unitPrice the new unit price of the edition
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Gets the number of printed copies of the edition.
     *
     * @return the number of printed copies of the edition
     */
    public int getPrintedCopies() {
        return printedCopies;
    }

    /**
     * Sets the number of printed copies of the edition.
     *
     * @param printedCopies the new number of printed copies of the edition
     */
    public void setPrintedCopies(int printedCopies) {
        this.printedCopies = printedCopies;
    }

    /**
     * Modified toString method that returns the data of the class
     *
     * @return a string representation of the paper
     */
    @Override
    public String toString() {
        return "Edition: " +
                "Type: " + this.getClass().getSimpleName() + ", " +
                "Title: " + title + ", " +
                "Number of Pages: " + numberOfPages + ", " +
                "Paper: " + paper + ", " +
                "Unit Price: " + unitPrice + ", " +
                "Printed Copies: " + printedCopies;
    }
}
