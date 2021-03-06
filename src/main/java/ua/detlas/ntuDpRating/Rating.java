package ua.detlas.ntuDpRating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rating {
    static final double ALPHA = 0.94;
    private double budgetRating = 0.0;
    private double averageRating = 0.0;
    private double sumCredit = 0.0;
    private double sumMark = 0.0;
    private double multiplyCreditMark = 0.0;
    private int markCount = 0;

    public List<Number> ratingParsing(String rawRating) {
        String freshRating = rawRating.replaceAll("[^\\s\\d.]", "").replaceAll("\\s+", " ").trim();
        String[] stringNumbers = freshRating.split(" ");
        List<Number> ratingList = new ArrayList<>();

        for (String number : stringNumbers) {
            if (!number.contains(".")) {
                ratingList.add(Integer.parseInt(number));
            } else {
                try {
                    ratingList.add(Double.parseDouble(number));
                } catch (NumberFormatException ignored) { }
            }
        }
        return ratingList;
    }

    public List<Number> ratingCalculate(String rating) {
        if (rating == null || !rating.contains(".")) {
            return Arrays.asList(0.0, 0.0);
        }

        List<Number> ratingList = ratingParsing(rating);

        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).toString().contains(".")) {
                double credit = ratingList.get(i).doubleValue();
                int mark;
                try {
                    mark = ratingList.get(i + 2).intValue();
                } catch (IndexOutOfBoundsException e) {
                    return Arrays.asList(0.0, 0.0);
                }
                sumCredit += credit;
                sumMark += mark;
                multiplyCreditMark += credit * mark;
                markCount++;
            }
        }
        budgetRating = ALPHA * (multiplyCreditMark / sumCredit);
        averageRating = sumMark / markCount;

        return Arrays.asList(budgetRating, averageRating);
    }

    @Override
    public String toString() {
        return "Rating{" + "budgetRating = " + budgetRating + ", averageRating = " + averageRating + ", markCount = " + markCount + "}";
    }
}
