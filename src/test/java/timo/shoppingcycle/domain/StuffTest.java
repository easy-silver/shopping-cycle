package timo.shoppingcycle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import timo.shoppingcycle.domain.calculator.AmountMeasuringCalculator;
import timo.shoppingcycle.domain.calculator.CountMeasuringCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class StuffTest {

    @DisplayName("구매 주기 계산 시 구매일 정보가 2개 미만이면 IllegalArgumentException 발생한다.")
    @Test
    void lessThanTwo() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2022, 1, 10)));

        Stuff stuff = new Stuff("세럼", new AmountMeasuringCalculator(), histories);

        //when, then
        assertThatThrownBy(stuff::calculateNextPurchaseDate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매일로 정렬")
    @Test
    void sort() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2021, 12, 31)));
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2021, 12, 1)));

        //when
        histories.sort(Comparator.comparing(PurchaseHistory::getPurchaseDate));

        //then
        for (PurchaseHistory history : histories) {
            System.out.println("history.getPurchaseDate() = " + history.getPurchaseDate());
        }
    }

    @DisplayName("같은 '용량'의 제품 다음 구매 예정일 예측")
    @Test
    void predictNextPurchaseDateWhenSameVolume() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2021, 12, 10)));
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2021, 12, 20)));
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2021, 12, 30)));
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2022, 1, 9)));

        Stuff stuff = new Stuff("세럼", new AmountMeasuringCalculator(), histories);

        //when
        LocalDate nextDate = stuff.calculateNextPurchaseDate();

        //then
        assertThat(nextDate).isAfterOrEqualTo(LocalDate.now());
        assertThat(nextDate).isEqualTo(LocalDate.of(2022, 1, 19));
    }

    @DisplayName("다른 '용량'의 제품 다음 구매 예정일 예측")
    @Test
    void predictNextPurchaseDateWhenDifferentVolume() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("B", 60, Unit.ml, LocalDate.of(2021, 12, 20)));
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2021, 12, 10)));
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2022, 1, 29)));
        histories.add(new PurchaseHistory("B", 60, Unit.ml, LocalDate.of(2022, 1, 9)));

        Stuff stuff = new Stuff("세럼", new AmountMeasuringCalculator(), histories);

        //when
        LocalDate nextDate = stuff.calculateNextPurchaseDate();

        //then
        assertThat(nextDate).isEqualTo(LocalDate.of(2022, 2, 8));
    }

    @DisplayName("계산된 다음 구매 예정일이 과거일자인 경우 오늘로 표현한다.")
    @Test
    void whenBeforeThanToday() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2021, 1, 1)));
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2021, 1, 31)));

        Stuff stuff = new Stuff("세럼", new AmountMeasuringCalculator(), histories);

        //when
        LocalDate nextPurchaseDate = stuff.calculateNextPurchaseDate();

        //then
        assertThat(nextPurchaseDate).isAfterOrEqualTo(LocalDate.now());
    }

    @DisplayName("구매이력에 단위 추가")
    @Test
    void addUnit() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2022, 1, 1)));
        histories.add(new PurchaseHistory("A", 60, Unit.ml, LocalDate.of(2022, 1, 11)));

        Stuff stuff = new Stuff("세럼", new AmountMeasuringCalculator(), histories);

        //when
        LocalDate nextPurchaseDate = stuff.calculateNextPurchaseDate();

        //then
        assertThat(nextPurchaseDate).isEqualTo(LocalDate.of(2022, 1, 31));
    }

    @DisplayName("다른 단위의 구매이력 계산")
    @Test
    void differentUnit() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2022, 1, 1)));
        histories.add(new PurchaseHistory("B", 0.03, Unit.l, LocalDate.of(2022, 1, 11)));
        histories.add(new PurchaseHistory("A", 30, Unit.ml, LocalDate.of(2022, 1, 21)));

        Stuff stuff = new Stuff("세럼", new AmountMeasuringCalculator(), histories);

        //when
        LocalDate nextPurchaseDate = stuff.calculateNextPurchaseDate();

        //then
        assertThat(nextPurchaseDate).isEqualTo(LocalDate.of(2022, 1, 31));
    }

    @DisplayName("갯수 단위의 물건 구매 예정일 계산")
    @Test
    void calculateCount() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 36, Unit.ea, LocalDate.of(2022, 5, 31)));
        histories.add(new PurchaseHistory("B", 30, Unit.ea, LocalDate.of(2022, 7, 6)));

        Stuff stuff = new Stuff("화장지", new CountMeasuringCalculator(), histories);

        //when
        LocalDate nextPurchaseDate = stuff.calculateNextPurchaseDate();

        //then
        assertThat(nextPurchaseDate).isEqualTo(LocalDate.of(2022, 8, 5));
    }

}