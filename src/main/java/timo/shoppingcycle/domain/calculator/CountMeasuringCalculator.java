package timo.shoppingcycle.domain.calculator;

import timo.shoppingcycle.domain.PurchaseHistory;

import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 갯수 용량 계산기
 */
public class CountMeasuringCalculator implements MeasuringCalculator {

    @Override
    public double calculateUsagePerDay(List<PurchaseHistory> histories) {
        if (histories.size() < 2) {
            throw new IllegalArgumentException("구매일 정보가 부족합니다.");
        }

        int periodCount = histories.size() - 1;
        double sumOfPeriod = 0, sumOfCapacity = 0;

        for (int i = 0; i < periodCount; i++) {
            PurchaseHistory history = histories.get(i);

            //구매 주기, 사용량 합산
            sumOfPeriod += ChronoUnit.DAYS.between(history.getPurchaseDate(), histories.get(i + 1).getPurchaseDate());
            sumOfCapacity += history.getCapacity();
        }

        System.out.println("sumOfPeriod = " + sumOfPeriod);
        System.out.println("sumOfCapacity = " + sumOfCapacity);

        //전체 사용량을 총 사용일 수로 나눠 1일당 사용랑 계산
        return sumOfCapacity / sumOfPeriod;
    }
}
