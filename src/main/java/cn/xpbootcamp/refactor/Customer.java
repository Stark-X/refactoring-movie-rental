package cn.xpbootcamp.refactor;

import java.util.Iterator;
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

        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "：\n");
        while (rentalIterator.hasNext()) {
            Rental each = rentalIterator.next();
            Calculator calculator = new Calculator(each);

            double priceAmount = 0d;
            priceAmount = calculator.calcAmount(priceAmount);
            frequentRenterPoints = calculator.calcFrequentRenterPoints(frequentRenterPoints);

            //show figures for this rental
            result.append("\t")
                  .append(each.getMovie().getTitle())
                  .append("\t")
                  .append(priceAmount).append("\n");
            totalPriceAmount += priceAmount;
        }
        //add footer lines
        result.append("Amount owed is ").append(totalPriceAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

}
