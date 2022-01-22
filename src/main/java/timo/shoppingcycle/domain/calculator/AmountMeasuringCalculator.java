package timo.shoppingcycle.domain.calculator;

import timo.shoppingcycle.domain.PurchaseHistory;
import timo.shoppingcycle.domain.Unit;

/**
 * 일당 사용 용량 계산기
 */
public class AmountMeasuringCalculator extends MeasuringCalculator {

    @Override
    protected void convertIntoSameUnit(PurchaseHistory history) {
        if (history.getUnit() != Unit.ml) {
            history.convertIntoMilliliter();
        }
    }
}
