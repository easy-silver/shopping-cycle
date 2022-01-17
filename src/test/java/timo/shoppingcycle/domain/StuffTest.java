package timo.shoppingcycle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StuffTest {

    @DisplayName("구매 주기 계산 시 구매일 정보가 2개 미만이면 IllegalArgumentException 발생한다.")
    @Test
    void test() {
        //given
        List<PurchaseHistory> histories = new ArrayList<>();
        histories.add(new PurchaseHistory("A", 30, LocalDate.of(2022, 1, 10)));
        Stuff stuff = new Stuff("세럼", null, histories);

        //when, then
        assertThatThrownBy(stuff::calculateNextPurchaseDate)
                .isInstanceOf(IllegalArgumentException.class);
    }

}