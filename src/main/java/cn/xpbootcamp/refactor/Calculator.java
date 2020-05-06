package cn.xpbootcamp.refactor;

public class Calculator {
    private final Rental rental;

    public Calculator(Rental rental) {
        this.rental = rental;
    }

    public double calcAmount(double amount) {
        switch (rental.getMovie().getPriceCode()) {
            case Movie.HISTORY:
                amount += 2;
                if (isRentedLargerThan(2))
                    amount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += rental.getDaysRented() * 3;
                break;
            case Movie.CAMPUS:
                amount += 1.5;
                if (isRentedLargerThan(3))
                    amount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return amount;
    }

    public int calcFrequentRenterPoints(int frequentRenterPoints) {
        frequentRenterPoints++;
        if (isMovieNewRelease() && isRentedLargerThan(1))
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private boolean isRentedLargerThan(int days) {
        return rental.getDaysRented() > days;
    }

    private boolean isMovieNewRelease() {
        return rental.getMovie().getPriceCode() == Movie.NEW_RELEASE;
    }
}
