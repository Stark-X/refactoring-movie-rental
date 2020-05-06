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
        double totalAmount = 0d;
        int frequentRenterPoints = 0;

        Iterator<Rental> rentalIterator = this.rentals.iterator();

        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "：\n");
        while (rentalIterator.hasNext()) {
            Rental each = rentalIterator.next();
            //show figures for this rental
            //determine amounts for each line
            double thisAmount = 0d;
            thisAmount = calcAmount(each, thisAmount);
            //add frequent renter points
            frequentRenterPoints++;
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;

            //show figures for this rental
            result.append("\t")
                  .append(each.getMovie().getTitle())
                  .append("\t")
                  .append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
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
