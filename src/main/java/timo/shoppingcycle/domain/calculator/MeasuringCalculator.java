package timo.shoppingcycle.domain.calculator;

import timo.shoppingcycle.domain.PurchaseHistory;

import java.util.List;

/**
 * 하루당 사용량 계산기 인터페이스
 */
public interface MeasuringCalculator {

    double calculateUsagePerDay(List<PurchaseHistory> histories);
}
