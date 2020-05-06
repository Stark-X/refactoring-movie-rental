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
            //show figures for this rental
            //determine amounts for each line
            double priceAmount = 0d;
            priceAmount = calcAmount(each, priceAmount);
            //add frequent renter points
            frequentRenterPoints = calcFrequentRenterPoints(each, frequentRenterPoints);

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

    private int calcFrequentRenterPoints(Rental rental, int frequentRenterPoints) {
        frequentRenterPoints++;
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private double calcAmount(Rental rental, double amount) {
        switch (rental.getMovie().getPriceCode()) {
            case Movie.HISTORY:
                amount += 2;
                if (rental.getDaysRented() > 2)
                    amount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += rental.getDaysRented() * 3;
                break;
            case Movie.CAMPUS:
                amount += 1.5;
                if (rental.getDaysRented() > 3)
                    amount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return amount;
    }

}
