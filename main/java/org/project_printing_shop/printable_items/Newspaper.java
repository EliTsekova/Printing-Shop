package org.project_printing_shop.printable_items;

public class Newspaper extends Edition {
    private double discountRate;
    private int discountThreshold;

    public Newspaper(String title, int numberOfPages, Paper paper, double unitPrice, double discountRate, int discountThreshold) {
        super(title, numberOfPages, paper, unitPrice);
        if (discountRate < 0) {
            throw new IllegalArgumentException("Discount rate must be non-negative.");
        }
        if (discountThreshold < 0) {
            throw new IllegalArgumentException("Discount threshold must be non-negative.");
        }
        this.discountRate = discountRate;
        this.discountThreshold = discountThreshold;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getDiscountThreshold() {
        return discountThreshold;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Printing newspaper: " + getTitle());
    }

    @Override
    public double calculatePrintingCosts() {
        double baseCost = getPaper().calculatePrice() * getNumberOfPages();
        if (getNumberOfPages() > discountThreshold) {
            return baseCost * discountRate;
        } else {
            return baseCost;
        }
    }

}

