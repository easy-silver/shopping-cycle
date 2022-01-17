package timo.shoppingcycle.domain;

import java.time.LocalDate;
import java.util.List;

public interface MeasuringType {

    double averageAmountPerDay(List<PurchaseHistory> histories);
}
