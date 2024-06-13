package org.project_printing_shop.printingShop;

import org.project_printing_shop.exceptions.NoSuitableMachineException;
import org.project_printing_shop.exceptions.UnsupportedColorException;
import org.project_printing_shop.printable_items.Edition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a printing machine in the printing shop.
 * This class provides functionality for loading paper, printing editions, and calculating printing time.
 */
public class PrintingMachine {
    protected int maxCapacity;
    protected int pagesPerMinute;
    protected boolean colorSupport;
    protected List<Edition> printedEditions;
    protected Map<Edition, Integer> editionCopies;
    private int currentPaperLoad;

    /**
     * Constructor for the PrintingMachine class.
     *
     * @param maxCapacity     the maximum paper capacity of the machine
     * @param pagesPerMinute  the number of pages the machine can print per minute
     * @param colorSupport    whether the machine supports color printing
     */
    public PrintingMachine(int maxCapacity, int pagesPerMinute, boolean colorSupport) {
        this.maxCapacity = maxCapacity;
        this.pagesPerMinute = pagesPerMinute;
        this.colorSupport = colorSupport;
        this.printedEditions = new ArrayList<>();
        this.editionCopies = new HashMap<>();
        this.currentPaperLoad = 0;
    }

    /**
     * Gets the maximum paper capacity of the machine.
     *
     * @return the maximum paper capacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Gets the number of pages the machine can print per minute.
     *
     * @return the number of pages per minute
     */
    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    /**
     * Checks if the machine supports color printing.
     *
     * @return true if the machine supports color printing, false otherwise
     */
    public boolean isColorSupport() {
        return colorSupport;
    }

    /**
     * Gets the list of editions printed by the machine.
     *
     * @return the list of printed editions
     */
    public List<Edition> getPrintedEditions() {
        return printedEditions;
    }

    /**
     * Gets the map of edition copies.
     *
     * @return the map of edition copies
     */
    public Map<Edition, Integer> getEditionCopies() {
        return editionCopies;
    }

    /**
     * Gets the current paper load in the machine.
     *
     * @return the current paper load
     */
    public int getCurrentPaperLoad() {
        return currentPaperLoad;
    }

    /**
     * Sets the current paper load in the machine.
     *
     * @param currentPaperLoad the new current paper load
     */
    public void setCurrentPaperLoad(int currentPaperLoad) {
        this.currentPaperLoad = currentPaperLoad;
    }

    /**
     * Loads paper into the machine.
     *
     * @param sheets the number of sheets to load
     * @throws IllegalArgumentException if loading the given number of sheets exceeds the machine's capacity
     */
    public void loadPaper(int sheets) {
        if (currentPaperLoad + sheets > maxCapacity) {
            throw new IllegalArgumentException("Cannot load paper, because the machine capacity is exceeded.");
        }
        currentPaperLoad += sheets;
    }

    /**
     * Prints an edition.
     *
     * @param edition the edition to print
     * @param isColor whether the printing is in color
     * @throws UnsupportedColorException if color printing is requested but the machine does not support it
     * @throws NoSuitableMachineException if there is not enough paper to print the edition
     */
    public void printEdition(Edition edition, boolean isColor) throws UnsupportedColorException, NoSuitableMachineException {
        if (isColor && !colorSupport) {
            throw new UnsupportedColorException("This machine does not support color printing.");
        }
        int totalSheetsRequired = edition.getNumberOfPages();
        if (currentPaperLoad < totalSheetsRequired) {
            throw new NoSuitableMachineException("Not enough paper to print the publication.");
        }
        double printingTime = calculatePrintingTime(totalSheetsRequired);
        System.out.printf("Printing will take approximately %.2f minutes.%n", printingTime);
        currentPaperLoad -= totalSheetsRequired;
        edition.print();
        if (!printedEditions.contains(edition)) {
            printedEditions.add(edition);
        }
        int currentCopies = editionCopies.getOrDefault(edition, 0);
        currentCopies++;
        editionCopies.put(edition, currentCopies);
        edition.setPrintedCopies(currentCopies);
        System.out.println("Printed publication: " + edition.getTitle() + ". Total copies: " + currentCopies);
    }

    /**
     * Gets the total number of printed pages.
     *
     * @return the total number of printed pages
     */
    public int getTotalPrintedPages() {
        int total = 0;
        for (Edition ed : printedEditions) {
            total += ed.getNumberOfPages() * editionCopies.get(ed);
        }
        return total;
    }

    /**
     * Calculates the printing time for a given number of pages.
     *
     * @param totalPages the total number of pages to print
     * @return the estimated printing time in minutes
     */
    public double calculatePrintingTime(int totalPages) {
        return Math.ceil((double) totalPages / pagesPerMinute);
    }
    /**
     * Modified toString method that returns the data of the class
     *
     * @return a string representation of the paper
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Machine Details: \n");
        sb.append("Max Capacity: ").append(maxCapacity).append("\n");
        sb.append("Pages Per Minute: ").append(pagesPerMinute).append("\n");
        sb.append("Color Support: ").append(colorSupport).append("\n");
        sb.append("Current Paper Load: ").append(currentPaperLoad).append("\n");
        sb.append("Printed Editions: \n");
        for (Map.Entry<Edition, Integer> entry : editionCopies.entrySet()) {
            sb.append(entry.getKey().toString()).append(" = ").append(entry.getValue()).append(" copies\n");
        }
        return sb.toString();
    }
}
