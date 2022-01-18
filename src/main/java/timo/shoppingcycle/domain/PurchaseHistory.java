package timo.shoppingcycle.domain;

import java.time.LocalDate;

public class PurchaseHistory {

    private final String name;
    private final int volume;
    private final LocalDate purchaseDate;

    public PurchaseHistory(String name, int volume, LocalDate purchaseDate) {
        this.name = name;
        this.volume = volume;
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
}
