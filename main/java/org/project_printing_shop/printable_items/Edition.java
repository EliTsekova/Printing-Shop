package org.project_printing_shop.printable_items;

import org.project_printing_shop.interfaces.Printable;

public abstract class Edition implements Printable {
    protected String title;
    protected int numberOfPages;
    protected Paper paper;
    protected double unitPrice;
    protected int printedCopies = 0;

    public Edition(String title, int numberOfPages, Paper paper, double unitPrice) {
        if (numberOfPages < 0) {
            throw new IllegalArgumentException("Number of pages must be non-negative.");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must be non-negative.");
        }
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.paper = paper;
        this.unitPrice = unitPrice;
    }

    @Override
    public void print() {
        printedCopies++;
        System.out.println("Printing edition: " + title + ". Total printed copies: " + printedCopies);
    }

    @Override
    public double calculatePrintingCosts() {
        return unitPrice * numberOfPages;
    }

    public double calculateIncome() {
        if (printedCopies > 100) {
            double total = printedCopies * unitPrice;
            double discountedTotal = total - (total * 0.10);
            return discountedTotal;
        }
        return printedCopies * unitPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPrintedCopies() {
        return printedCopies;
    }

    public void setPrintedCopies(int printedCopies) {
        this.printedCopies = printedCopies;
    }
}