package timo.shoppingcycle.domain;

import timo.shoppingcycle.domain.calculator.MeasuringCalculator;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Stuff {

    private String categoryName;
    private MeasuringCalculator measuringCalculator;
    private List<PurchaseHistory> histories;

    public Stuff(String categoryName, MeasuringCalculator measuringCalculator, List<PurchaseHistory> histories) {
        this.categoryName = categoryName;
        this.measuringCalculator = measuringCalculator;
        this.histories = histories;
        //날짜순으로 정렬
        this.histories.sort(Comparator.comparing(PurchaseHistory::getPurchaseDate));
    }

    /** 구매내역 추가 */
    public void addHistory(PurchaseHistory history) {
        histories.add(history);
        histories.sort(Comparator.comparing(PurchaseHistory::getPurchaseDate));
    }

    /** 다음 구매 예정일 계산 */
    public LocalDate calculateNextPurchaseDate() {
        //1일당 사용량 계산
        double useAmountPerDay = measuringCalculator.calculateUsedPerDay(histories);

        PurchaseHistory lastPurchase = histories.get(histories.size() - 1);
        LocalDate lastPurchaseDate = lastPurchase.getPurchaseDate();
        double lastPurchaseCapacity = lastPurchase.getCapacity();

        //마지막 구매건 용량을 하루당 사용량으로 나눈다.
        int countOfDay = (int) Math.floor(lastPurchaseCapacity / useAmountPerDay);
        LocalDate nextDate = lastPurchaseDate.plusDays(countOfDay);

        //계산된 구매 예정일이 오늘보다 작으면 오늘로 표현
        return nextDate.isBefore(LocalDate.now()) ? LocalDate.now() : nextDate;
    }

}
