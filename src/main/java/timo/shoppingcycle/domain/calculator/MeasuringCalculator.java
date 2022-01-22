package timo.shoppingcycle.domain.calculator;

import timo.shoppingcycle.domain.PurchaseHistory;

import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 하루당 사용량 계산 추상 클래스
 */
public abstract class MeasuringCalculator {

    /** 하루 사용량 계산 */
    public double calculateUsagePerDay(List<PurchaseHistory> histories) {
        validateHistories(histories);

        int periodCount = histories.size() - 1;
        double sumOfPeriod = 0, sumOfCapacity = 0;

        for (int i = 0; i < periodCount; i++) {
            PurchaseHistory history = histories.get(i);

            convertIntoSmallerUnit(history);

            sumOfPeriod += ChronoUnit.DAYS.between(history.getPurchaseDate(), histories.get(i + 1).getPurchaseDate());
            sumOfCapacity += history.getCapacity();
        }

        return sumOfCapacity / sumOfPeriod;
    }

    /** 구매이력 유효성 검증 */
    private void validateHistories(List<PurchaseHistory> histories) {
        if (histories.size() < 2) {
            throw new IllegalArgumentException("구매일 정보가 부족합니다.");
        }
    }

    /**
     * 단위 일관화(더 작은 단위로 변경)
     * @param history
     */
    protected abstract void convertIntoSmallerUnit(PurchaseHistory history);
}
