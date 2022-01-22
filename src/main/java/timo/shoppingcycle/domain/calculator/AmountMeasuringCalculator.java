package timo.shoppingcycle.domain.calculator;

import timo.shoppingcycle.domain.PurchaseHistory;

/**
 * 일당 사용 용량 계산기
 */
public class AmountMeasuringCalculator extends MeasuringCalculator {

    @Override
    protected void convertIntoSmallerUnit(PurchaseHistory history) {
        history.convertIntoMilliliter();
    }
}
