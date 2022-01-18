package timo.shoppingcycle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseHistoryTest {

    @DisplayName("구매 주기 계산 시 구매일 정보가 2개 미만이면 IllegalArgumentException 발생한다.")
    @Test
    void calculatePeriodThrowException() {
        //given
        StuffOld shampoo = new StuffOld("샴푸");
        PurchaseHistoryOld shampooPurchaseHistory = new PurchaseHistoryOld(shampoo, LocalDate.now());

        //when, then
        assertThatThrownBy(shampooPurchaseHistory::calculateAveragePeriod)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두 날짜의 평균 주기를 계산한다.")
    @Test
    void calculatePeriodBetweenTwo() {
        //given
        StuffOld shampoo = new StuffOld("샴푸");
        PurchaseHistoryOld history = new PurchaseHistoryOld(shampoo, LocalDate.of(2021, 12, 21));
        history.addPurchaseDate(LocalDate.of(2021, 11, 21));

        //when
        long periodDays = history.calculateAveragePeriod();

        //then
        assertThat(periodDays).isEqualTo(30);
    }

    @DisplayName("세 날짜의 평균 주기를 계산한다.")
    @Test
    void calculatePeriodBetweenThree() {
        //given
        StuffOld toner = new StuffOld("토너");
        PurchaseHistoryOld history = new PurchaseHistoryOld(toner,
                LocalDate.of(2021, 11, 1),
                LocalDate.of(2021, 11, 11),
                LocalDate.of(2021, 11, 21));

        //when
        long period = history.calculateAveragePeriod();

        //then
        assertThat(period).isEqualTo(10);
    }

    @DisplayName("구매일이 뒤죽박죽 입력된 날짜들의 평균 주기 계산하기")
    @Test
    void calculatePeriodOfMixDays() {
        //given
        StuffOld apple = new StuffOld("사과");
        LocalDate date1 = LocalDate.of(2021, 12, 14);
        LocalDate date2 = LocalDate.of(2021, 11, 14);
        LocalDate date3 = LocalDate.of(2021, 10, 15);
        LocalDate date4 = LocalDate.of(2021, 11, 29);
        LocalDate date5 = LocalDate.of(2021, 10, 30);
        PurchaseHistoryOld history = new PurchaseHistoryOld(apple, date1, date2, date3, date4, date5);

        //when
        long period = history.calculateAveragePeriod();

        //then
        assertThat(period).isEqualTo(15L);
    }

    @DisplayName("다음 구매 예정일을 예상한다.")
    @Test
    void expectNextPurchaseDate() {
        //given
        StuffOld shampoo = new StuffOld("샴푸");
        PurchaseHistoryOld history = new PurchaseHistoryOld(shampoo,
                LocalDate.of(2021, 12, 20),
                LocalDate.of(2021, 11, 20));

        //when
        LocalDate nextPurchaseDate = history.expectNextPurchaseDate();

        //then
        assertThat(nextPurchaseDate).isEqualTo(LocalDate.of(2022, 1, 19));
    }

    @DisplayName("다음 구매 예정일은 오늘이거나 오늘 이후의 날짜이다.")
    @Test
    void expectNextPurchaseDateWhenOld() {
        //given
        StuffOld shampoo = new StuffOld("샴푸");
        PurchaseHistoryOld history = new PurchaseHistoryOld(shampoo,
                LocalDate.of(2021, 10, 2),
                LocalDate.of(2021, 11, 1));

        //when
        LocalDate nextPurchaseDate = history.expectNextPurchaseDate();

        //then
        assertThat(nextPurchaseDate).isAfter(LocalDate.now());
    }
}