package org.project_printing_shop.printable_items;

import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;

/**
 * Class representing a paper used in the printing shop.
 * Contains details about the paper size, type, and base price.
 */
public class Paper {
    protected PaperSize size;
    protected PaperType type;
    protected double basePrice;

    /**
     * Constructor for Paper.
     *
     * @param size      the size of the paper
     * @param type      the type of the paper
     * @param basePrice the base price of the paper
     * @throws IllegalArgumentException if the base price is negative
     */
    public Paper(PaperSize size, PaperType type, double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Base price must be non-negative.");
        }
        this.size = size;
        this.type = type;
        this.basePrice = basePrice;
    }

    /**
     * Gets the size of the paper.
     *
     * @return the size of the paper
     */
    public PaperSize getSize() {
        return size;
    }

    /**
     * Gets the type of the paper.
     *
     * @return the type of the paper
     */
    public PaperType getType() {
        return type;
    }

    /**
     * Gets the base price of the paper.
     *
     * @return the base price of the paper
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Calculates the price percentage based on the size of the paper.
     *
     * @param paperSize the size of the paper
     * @return the price percentage based on the paper size
     */
    private double getSizePricePercentage(PaperSize paperSize) {
        if (paperSize == PaperSize.A1) {
            return 4.0;
        } else if (paperSize == PaperSize.A2) {
            return 3.0;
        } else if (paperSize == PaperSize.A3) {
            return 2.0;
        } else if (paperSize == PaperSize.A4) {
            return 1.5;
        } else if (paperSize == PaperSize.A5) {
            return 1.0;
        } else {
            return 1.0;
        }
    }

    /**
     * Calculates the price of the paper based on its size and base price.
     *
     * @return the calculated price of the paper
     */
    public double calculatePrice() {
        double sizePricePercentage = getSizePricePercentage(size);
        return basePrice * sizePricePercentage;
    }

    /**
     * toString method that returns the data of the class
     *
     * @return a string representation of the paper
     */
    @Override
    public String toString() {
        return "Paper{" +
                "size=" + size +
                ", type=" + type +
                ", basePrice=" + basePrice +
                '}';
    }
}