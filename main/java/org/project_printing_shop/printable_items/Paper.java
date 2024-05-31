package org.project_printing_shop.printable_items;

import org.project_printing_shop.enums.PaperSize;
import org.project_printing_shop.enums.PaperType;

public class Paper {
    private PaperSize size;
    private PaperType type;
    private double basePrice; // Базова цена за A5

    public Paper(PaperSize size, PaperType type, double basePrice) {

        this.size = size;
        this.type = type;
        this.basePrice = basePrice;
    }

    public PaperSize getSize() {
        return size;
    }

    public PaperType getType() {
        return type;
    }

    public double getBasePrice() {
        return basePrice;
    }
    private double getSizePricePercentage(PaperSize paperSize) {
        switch (paperSize) {
            case A1:
                return 4.0;
            case A2:
                return 3.0;
            case A3:
                return 2.0;
            case A4:
                return 1.5;
            case A5:
                return 1.0;
            default:
                return 1.0;
        }
    }
    public double calculatePrice() {
        double sizePricePercentage = getSizePricePercentage(size);
        return basePrice * sizePricePercentage;
    }
}