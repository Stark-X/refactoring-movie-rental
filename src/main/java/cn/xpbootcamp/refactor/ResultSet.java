package cn.xpbootcamp.refactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultSet {
    private List<String> resultBuffer = new ArrayList<>();
    private static final String UNKNOWN = "UNKNOWN";

    public enum ResultTypeEnum {
        FREQUENT_RENTER_POINT, PRICE_AMOUNT, TITLE, TOTAL_PRICE_AMOUNT
    }

    public void append(ResultTypeEnum resultType, Map<String, String> resultData) {
        switch (resultType) {
            case TITLE:
                this.resultBuffer.add("Rental Record for " + resultData.getOrDefault("customerName", UNKNOWN) + "：");
                break;
            case PRICE_AMOUNT:
                this.resultBuffer.add("\t" + resultData.getOrDefault("movieTitle", UNKNOWN) + "\t" + resultData.getOrDefault("priceAmount", UNKNOWN));
                break;
            case TOTAL_PRICE_AMOUNT:
                this.resultBuffer.add("Amount owed is " + resultData.getOrDefault("totalPriceAmount", UNKNOWN));
                break;
            case FREQUENT_RENTER_POINT:
                this.resultBuffer.add("You earned " + resultData.getOrDefault("frequentRenterPoints", UNKNOWN) + " frequent renter points");
                break;
        }
    }

    public String render() {
        return String.join("\n", resultBuffer);
    }
}
