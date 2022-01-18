package timo.shoppingcycle.domain;

import timo.shoppingcycle.domain.calculator.MeasuringCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stuff {

    private String categoryName;
    private MeasuringCalculator measuringCalculator;
    private List<PurchaseHistory> histories = new ArrayList<>();

    public Stuff(String categoryName, MeasuringCalculator measuringCalculator, List<PurchaseHistory> histories) {
        this.categoryName = categoryName;
        this.measuringCalculator = measuringCalculator;
        this.histories = histories;
    }

    public LocalDate calculateNextPurchaseDate() {
        if (histories.size() < 2) {
            throw new IllegalArgumentException("구매일 정보가 부족합니다.");
        }

        double useAmountPerDay = measuringCalculator.calculateUsedPerDay(histories);

        PurchaseHistory lastPurchase = histories.get(histories.size() - 1);
        LocalDate lastDate = lastPurchase.getPurchaseDate();
        double lastPurchaseVolume = lastPurchase.getVolume();

        //마지막 구매건 용량을 하루당 사용량으로 나눈다.
        int countOfDay = (int) Math.ceil(lastPurchaseVolume / useAmountPerDay);
        return lastDate.plusDays(countOfDay);
    }

}
