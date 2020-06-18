package ua.detlas.ntuDpRating;

public class Main {
    public static void main(String[] args) {
        Rating rating = new Rating();
        double studentRating = rating.ratingCalculate("");
        System.out.println("Rating is " + studentRating);
    }
}
