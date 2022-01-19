package timo.shoppingcycle.domain;

import java.time.LocalDate;

public class PurchaseHistory {

    private final String name;
    private double capacity;
    private Unit unit;

    private final LocalDate purchaseDate;

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

    public Unit getUnit() {
        return unit;
    }

    public void convertIntoMilliliter() {
        if (unit != Unit.l) {
            throw new IllegalArgumentException("밀리리터로 변환할 수 없는 단위입니다.");
        }

        if (capacity < 0) {
            throw new IllegalArgumentException("용량이 유효하지 않습니다.");
        }

        unit = Unit.ml;
        capacity *= 1000;
    }
}
