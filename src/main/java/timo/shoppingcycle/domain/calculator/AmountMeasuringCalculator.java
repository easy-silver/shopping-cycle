package timo.shoppingcycle.domain.calculator;

import timo.shoppingcycle.domain.PurchaseHistory;
import timo.shoppingcycle.domain.Unit;

import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 일당 사용 용량 계산기
 */
public class AmountMeasuringCalculator implements MeasuringCalculator {

    @Override
    public double calculateUsagePerDay(List<PurchaseHistory> histories) {
        if (histories.size() < 2) {
            throw new IllegalArgumentException("구매일 정보가 부족합니다.");
        }

        int periodCount = histories.size() - 1;
        double sumOfPeriod = 0, sumOfCapacity = 0;

        for (int i = 0; i < periodCount; i++) {
            PurchaseHistory history = histories.get(i);

            //2. 용량 단위 통일 (l -> ml로 변경)
            if (history.getUnit() != Unit.ml) {
                history.convertIntoMilliliter();
            }

            //3. 구매 주기, 사용량 합산
            sumOfPeriod += ChronoUnit.DAYS.between(history.getPurchaseDate(), histories.get(i + 1).getPurchaseDate());
            sumOfCapacity += history.getCapacity();
        }

        System.out.println("sumOfPeriod = " + sumOfPeriod);
        System.out.println("sumOfCapacity = " + sumOfCapacity);

        //3. 전체 사용량을 총 사용일 수로 나눠 1일당 사용랑 계산
        return sumOfCapacity / sumOfPeriod;
    }
}
