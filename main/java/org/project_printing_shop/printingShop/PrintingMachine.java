package org.project_printing_shop.printingShop;

import org.project_printing_shop.exceptions.UnsupportedColorException;
import org.project_printing_shop.printable_items.Edition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintingMachine {
    private int maxCapacity;
    private int pagesPerMinute;
    private boolean colorSupport;
    private List<Edition> printedEditions;
    private Map<Edition, Integer> editionCopies;
    private int currentPaperLoad;

    public PrintingMachine(int maxCapacity, int pagesPerMinute, boolean colorSupport) {
        this.maxCapacity = maxCapacity;
        this.pagesPerMinute = pagesPerMinute;
        this.colorSupport = colorSupport;
        this.printedEditions = new ArrayList<>();
        this.editionCopies = new HashMap<>();
        this.currentPaperLoad = 0;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    public void setPagesPerMinute(int pagesPerMinute) {
        this.pagesPerMinute = pagesPerMinute;
    }

    public boolean isColorSupport() {
        return colorSupport;
    }

    public void setColorSupport(boolean colorSupport) {
        this.colorSupport = colorSupport;
    }

    public List<Edition> getPrintedEditions() {
        return printedEditions;
    }

    public void setPrintedEditions(List<Edition> printedEditions) {
        this.printedEditions = printedEditions;
    }

    public Map<Edition, Integer> getEditionCopies() {
        return editionCopies;
    }

    public void setEditionCopies(Map<Edition, Integer> editionCopies) {
        this.editionCopies = editionCopies;
    }

    public int getCurrentPaperLoad() {
        return currentPaperLoad;
    }

    public void setCurrentPaperLoad(int currentPaperLoad) {
        this.currentPaperLoad = currentPaperLoad;
    }

    public void loadPaper(int sheets) {
        if (currentPaperLoad + sheets > maxCapacity) {
            throw new IllegalArgumentException("Cannot load paper: exceeds machine capacity.");
        }
        currentPaperLoad += sheets;
    }

    public void printEdition(Edition edition, boolean isColor) throws UnsupportedColorException, IllegalArgumentException {
        if (isColor && !colorSupport) {
            throw new UnsupportedColorException("This machine does not support color printing.");
        }
        int totalSheetsRequired = edition.getNumberOfPages();
        if (currentPaperLoad < totalSheetsRequired) {
            throw new IllegalArgumentException("Not enough paper to print the publication.");
        }
        double printingTime = calculatePrintingTime(totalSheetsRequired);
        System.out.printf("Printing will take approximately %.2f minutes.%n", printingTime);
        currentPaperLoad -= totalSheetsRequired;
        edition.print();
        printedEditions.add(edition);
        editionCopies.put(edition, editionCopies.getOrDefault(edition, 0) + 1);
        System.out.println("Printed publication: " + edition.getTitle() + ". Total copies: " + editionCopies.get(edition));
    }

    public int getTotalPrintedPages() {
        int total = 0;
        for (Edition ed : printedEditions) {
            total += ed.getNumberOfPages();
        }
        return total;
    }

    public double calculatePrintingTime(int totalPages) {
        return Math.ceil((double) totalPages / pagesPerMinute);
    }

}