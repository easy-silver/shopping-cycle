package timo.shoppingcycle.domain;

import java.time.LocalDate;

public class PurchaseHistory {

    private final String name;
    private final double capacity;
    private Unit unit;

    private final LocalDate purchaseDate;

    public PurchaseHistory(String name, double capacity, LocalDate purchaseDate) {
        this.name = name;
        this.capacity = capacity;
        this.purchaseDate = purchaseDate;
    }

    public PurchaseHistory(String name, double capacity, Unit unit, LocalDate purchaseDate) {
        this.name = name;
        this.capacity = capacity;
        this.unit = unit;
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public double getCapacity() {
        return capacity;
    }

}
