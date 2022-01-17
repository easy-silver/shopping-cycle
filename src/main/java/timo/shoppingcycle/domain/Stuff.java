package timo.shoppingcycle.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stuff {

    private String categoryName;
    private MeasuringType measuringType;
    private List<PurchaseHistory> histories = new ArrayList<>();

    public Stuff(String categoryName, MeasuringType measuringType, List<PurchaseHistory> histories) {
        this.categoryName = categoryName;
        this.measuringType = measuringType;
        this.histories = histories;
    }

    public LocalDate calculateNextPurchaseDate() {
        if (histories.size() < 2) {
            throw new IllegalArgumentException("구매일 정보가 부족합니다.");
        }

        double amountPerDay = measuringType.averageAmountPerDay(histories);

        //TODO. 마지막 히스토리의 용량 및, 다음 구매 예정일 계산

        return null;
    }

}
