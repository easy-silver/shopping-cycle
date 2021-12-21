package timo.shoppingcycle.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public long calculateAveragePeriod() {
        if (purchaseDates.size() < 2) {
            throw new IllegalArgumentException("구매일 정보가 부족합니다.");
        }

        //오름차 순으로 정렬
        Collections.sort(purchaseDates);

        int sumOfEachPeriod = 0, periodCount = purchaseDates.size() - 1;

        for (int i = 0; i < periodCount; i++) {
            sumOfEachPeriod += ChronoUnit.DAYS.between(purchaseDates.get(i), purchaseDates.get(i + 1));
        }

        //평균 계산
        return sumOfEachPeriod / periodCount;
    }

}
