package timo.shoppingcycle.domain.calculator;

import timo.shoppingcycle.domain.PurchaseHistory;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

/**
 * 일당 사용 용량 계산기
 */
public class AmountMeasuringCalculator implements MeasuringCalculator {

    @Override
    public double calculateUsedPerDay(List<PurchaseHistory> histories) {
        if (histories.size() < 2) {
            throw new IllegalArgumentException("구매일 정보가 부족합니다.");
        }

        //1. 구매일 순으로 정렬
        histories.sort(Comparator.comparing(PurchaseHistory::getPurchaseDate));

        int periodCount = histories.size() - 1;
        double sumOfPeriod = 0, sumOfCapacity = 0;

        //2. 총 사용 용량, 구매 간격 합산
        for (int i = 0; i < periodCount; i++) {
            sumOfPeriod += ChronoUnit.DAYS.between(histories.get(i).getPurchaseDate(),
                    histories.get(i + 1).getPurchaseDate());
            sumOfCapacity += histories.get(i).getCapacity();
        }

        System.out.println("sumOfPeriod = " + sumOfPeriod);
        System.out.println("sumOfCapacity = " + sumOfCapacity);

        //3. 용량당 일수로 나눠 1일당 사용랑 계산
        return sumOfCapacity / sumOfPeriod;
    }
}
