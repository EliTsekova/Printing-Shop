package org.project_printing_shop.printable_items;

public class Newspaper extends Edition {
    private double discountRate;
    private int discountLimit;

    public Newspaper(String title, int numberOfPages, Paper paper, double unitPrice, double discountRate, int discountLimit) {
        super(title, numberOfPages, paper, unitPrice);
        if (discountRate < 0) {
            throw new IllegalArgumentException("The discount rate must not be negative.");
        }
        if (discountLimit < 0) {
            throw new IllegalArgumentException("The discount limit must not be negative.");
        }
        this.discountRate = discountRate;
        this.discountLimit = discountLimit;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getDiscountLimit() {
        return discountLimit;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Printing newspaper: " + getTitle());
    }

    @Override
    public double calculatePrintingCosts() {
        double baseCost = getPaper().calculatePrice() * getNumberOfPages();
        if (getNumberOfPages() > discountLimit) {
            return baseCost * discountRate;
        } else {
            return baseCost;
        }
    }
}

