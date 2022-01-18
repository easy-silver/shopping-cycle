package timo.shoppingcycle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import timo.shoppingcycle.domain.calculator.AmountMeasuringCalculator;
import timo.shoppingcycle.domain.calculator.MeasuringCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 용량 계산과 관련된 테스트 케이스
 */
class AmountMeasuringCalculatorTest {

    @DisplayName("같은 용량의 제품 하루당 사용량 계산")
    @Test
    void sumOfPeriodWhenSameVolume() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, LocalDate.of(2021, 12, 10)));
        histories.add(new PurchaseHistory("A", 30, LocalDate.of(2021, 12, 30)));
        histories.add(new PurchaseHistory("A", 30, LocalDate.of(2022, 1, 19)));

        //when
        MeasuringCalculator measuringCalculator = new AmountMeasuringCalculator();
        double amountPerDay = measuringCalculator.calculateUsedPerDay(histories);

        //then
        assertThat(amountPerDay).isEqualTo(1.5);
    }

    @DisplayName("다른 용량의 제품 하루당 사용량 계산")
    @Test
    void sumOfPeriodWhenDifferentVolume() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, LocalDate.of(2021, 12, 10)));
        histories.add(new PurchaseHistory("B", 50, LocalDate.of(2021, 12, 30)));
        histories.add(new PurchaseHistory("C", 20, LocalDate.of(2022, 1, 19)));

        //when
        MeasuringCalculator measuringCalculator = new AmountMeasuringCalculator();
        double amountPerDay = measuringCalculator.calculateUsedPerDay(histories);

        //then
        assertThat(amountPerDay).isEqualTo(2);
    }

}