package org.project_printing_shop.printable_items;

public class Poster extends Edition {
    public Poster(String title, int numberOfPages, Paper paper, double unitPrice) {
        super(title, numberOfPages, paper, unitPrice);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Printing poster: " + title);
    }

    @Override
    public double calculatePrintingCosts() {
        double paperCost = paper.calculatePrice();
        return paperCost * numberOfPages;
    }
}
