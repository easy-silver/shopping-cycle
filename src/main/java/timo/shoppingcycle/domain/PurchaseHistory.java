package timo.shoppingcycle.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 구매 이력을 관리하는 클래스
 */
public class PurchaseHistory {

    private Stuff stuff;
    private List<LocalDate> purchaseDates = new ArrayList<>();

    public PurchaseHistory(Stuff stuff, LocalDate lastPurchaseDate) {
        this.stuff = stuff;
        purchaseDates.add(lastPurchaseDate);
    }

    public PurchaseHistory(Stuff stuff, LocalDate ... purchaseDates) {
        this.stuff = stuff;
        this.purchaseDates = Arrays.asList(purchaseDates);
    }

    public List<LocalDate> getPurchaseDates() {
        return purchaseDates;
    }

    public void addPurchaseDate(LocalDate purchaseDate) {
        purchaseDates.add(purchaseDate);
    }

    public Duration calculateDuration() {
        if (purchaseDates.size() < 2) {
            throw new IllegalArgumentException("구매일 정보가 부족합니다.");
        }

        //TODO. 주기 계산 기능 구현 필요

        return null;
    }

}
