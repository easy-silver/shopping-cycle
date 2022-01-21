package timo.shoppingcycle.domain;

import timo.shoppingcycle.domain.calculator.MeasuringCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Stuff {

    private String categoryName;
    private MeasuringCalculator measuringCalculator;
    private List<PurchaseHistory> histories = new ArrayList<>();

    public Stuff(String categoryName, MeasuringCalculator measuringCalculator) {
        this.categoryName = categoryName;
        this.measuringCalculator = measuringCalculator;
    }

    public Stuff(String categoryName, MeasuringCalculator measuringCalculator, List<PurchaseHistory> histories) {
        this.categoryName = categoryName;
        this.measuringCalculator = measuringCalculator;
        this.histories = histories;
        //날짜순으로 정렬
        this.histories.sort(Comparator.comparing(PurchaseHistory::getPurchaseDate));
    }

    /** 구매내역 단 건 추가 */
    public void addHistory(PurchaseHistory history) {
        histories.add(history);
        histories.sort(Comparator.comparing(PurchaseHistory::getPurchaseDate));
    }

    /** 구매내역 복수 건 추가 */
    public void addHistory(PurchaseHistory ... histories) {
        this.histories.addAll(Arrays.asList(histories));
        this.histories.sort(Comparator.comparing(PurchaseHistory::getPurchaseDate));
    }

    /**
     * 하루당 사용량과 마지막 구매 이력으로 다음 구매 예정일 계산
     * @return 다음 구매 예정일
     */
    public LocalDate predictNextPurchaseDate() {

        double useAmountPerDay = measuringCalculator.calculateUsagePerDay(histories);

        PurchaseHistory lastPurchase = getLastPurchaseHistory();

        int countOfDay = calculateCountOfDay(useAmountPerDay, lastPurchase.getCapacity());

        return calculateNextPurchaseDate(lastPurchase, countOfDay);
    }

    /** 마지막 구매 이력 조회*/
    private PurchaseHistory getLastPurchaseHistory() {
        if (histories.isEmpty()) {
            throw new IllegalArgumentException("구매이력이 없습니다.");
        }

        return histories.get(histories.size() - 1);
    }

    /** 하루 평균 사용량과 마지막 구매량으로 전부 사용되기까지 소요되는 일 수 계산 */
    private int calculateCountOfDay(double useAmountPerDay, double lastPurchaseCapacity) {
        return (int) Math.floor(lastPurchaseCapacity / useAmountPerDay);
    }

    /** 예상 사용일 수에 따른 다음 구메 예정일 계산 */
    private LocalDate calculateNextPurchaseDate(PurchaseHistory lastPurchase, int countOfDay) {
        LocalDate nextDate = lastPurchase.getPurchaseDate().plusDays(countOfDay);

        //계산된 구매 예정일이 오늘보다 작으면 오늘로 표현
        return nextDate.isBefore(LocalDate.now()) ? LocalDate.now() : nextDate;
    }

}
