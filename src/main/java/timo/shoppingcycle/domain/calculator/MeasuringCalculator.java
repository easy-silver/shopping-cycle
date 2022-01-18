package timo.shoppingcycle.domain.calculator;

import timo.shoppingcycle.domain.PurchaseHistory;

import java.util.List;

/**
 * 일당 사용량 계산기 인터페이스
 */
public interface MeasuringCalculator {

    double calculateUsedPerDay(List<PurchaseHistory> histories);
}
