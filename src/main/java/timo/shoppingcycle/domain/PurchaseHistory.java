package timo.shoppingcycle.domain;

import java.time.LocalDate;

public class PurchaseHistory {

    private final String name;
    private final int capacity;
    private Unit unit;

    private final LocalDate purchaseDate;

    public PurchaseHistory(String name, int capacity, LocalDate purchaseDate) {
        this.name = name;
        this.capacity = capacity;
        this.purchaseDate = purchaseDate;
    }

    public PurchaseHistory(String name, int capacity, Unit unit, LocalDate purchaseDate) {
        this.name = name;
        this.capacity = capacity;
        this.unit = unit;
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public int getCapacity() {
        return capacity;
    }

}
