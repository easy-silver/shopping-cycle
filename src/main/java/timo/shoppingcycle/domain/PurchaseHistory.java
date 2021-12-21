package timo.shoppingcycle.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 구매 주기, 다음 구매 예정일 계산을 담당하는 클래스
 */
public class PurchaseHistory {

    private final Stuff stuff;
    private List<LocalDate> purchaseDates = new ArrayList<>();

    public PurchaseHistory(Stuff stuff, LocalDate lastPurchaseDate) {
        this.stuff = stuff;
        purchaseDates.add(lastPurchaseDate);
    }

    public PurchaseHistory(Stuff stuff, LocalDate ... purchaseDates) {
        this.stuff = stuff;
        this.purchaseDates = Arrays.asList(purchaseDates);
    }

    public void addPurchaseDate(LocalDate purchaseDate) {
        purchaseDates.add(purchaseDate);
    }

    /**
     * 평균 구매 주기 계산
     * @return averagePeriodDays
     */
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

    /**
     * 다음 구매 예정일 계산
     * @return nextPurchaseDate
     */
    public LocalDate expectNextPurchaseDate() {
        long averagePeriod = calculateAveragePeriod();

        LocalDate lastPurchaseDate = purchaseDates.get(purchaseDates.size() - 1);
        LocalDate nextDate = lastPurchaseDate.plusDays(averagePeriod);

        //오늘 또는 오늘 이후가 될 때까지 평균 주기를 더해준다.
        while (nextDate.isBefore(LocalDate.now())) {
            nextDate = nextDate.plusDays(averagePeriod);
        }

        return nextDate;
    }

}
