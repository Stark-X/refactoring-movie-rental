package cn.xpbootcamp.refactor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class Customer {

    private String name;
    private Vector<Rental> rentals = new Vector<>();

    Customer(String name) {
        this.name = name;
    }

    void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return name;
    }

    String statement() {
        double totalPriceAmount = 0d;
        int frequentRenterPoints = 0;

        Iterator<Rental> rentalIterator = this.rentals.iterator();

        ResultSet resultSet = new ResultSet();
        Map<String, String> resultData = new HashMap<>();

        resultData.put("customerName", getName());
        resultSet.append(ResultSet.ResultTypeEnum.TITLE, resultData);

        while (rentalIterator.hasNext()) {
            Rental each = rentalIterator.next();
            Calculator calculator = new Calculator(each);

            double priceAmount = 0d;
            priceAmount = calculator.calcAmount(priceAmount);
            frequentRenterPoints = calculator.calcFrequentRenterPoints(frequentRenterPoints);

            Movie movie = each.getMovie();
            resultData.put("movieTitle", movie.getTitle());
            resultData.put("priceAmount", String.valueOf(priceAmount));
            resultSet.append(ResultSet.ResultTypeEnum.PRICE_AMOUNT, resultData);

            //show figures for this rental
            totalPriceAmount += priceAmount;
        }
        //add footer lines
        resultData.put("totalPriceAmount", String.valueOf(totalPriceAmount));
        resultSet.append(ResultSet.ResultTypeEnum.TOTAL_PRICE_AMOUNT, resultData);

        resultData.put("frequentRenterPoints", String.valueOf(frequentRenterPoints));
        resultSet.append(ResultSet.ResultTypeEnum.FREQUENT_RENTER_POINT, resultData);
        return resultSet.render();
    }

}
