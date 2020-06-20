package ua.detlas.ntuDpRating;

import java.util.ArrayList;
import java.util.List;

public class Rating {

    public List<Number> ratingParsing(String rawRating) {
        String freshRating = rawRating.replaceAll("[^\\s\\d.]", "").replaceAll("\\s+", " ").trim();
        String[] arr = freshRating.split(" ");
        List<Number> ratingList = new ArrayList<>();
        for (String s : arr) {
            if (!s.contains(".")) {
                ratingList.add(Integer.parseInt(s));
            } else {
                try {
                    ratingList.add(Double.parseDouble(s));
                } catch (NumberFormatException ignored) { }
            }
        }
        return ratingList;
    }

    public double budgetRatingCalculate(String rating) {
        if (rating == null) {
            return 0.0;
        } else if (!rating.contains(".")) {
            return 0.0;
        }
        List<Number> ratingList = ratingParsing(rating);
        final double ALPHA = 0.94;
        double sumCredit = 0.0;
        int multiplyCreditMark = 0;
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).toString().contains(".")) {
                double credit = ratingList.get(i).doubleValue();
                int mark;
                try {
                    mark = ratingList.get(i + 2).intValue();
                } catch (IndexOutOfBoundsException exception) {
                    return 0.0;
                }
                sumCredit += credit;
                multiplyCreditMark += (credit * mark);
            }
        }
        return ALPHA * (multiplyCreditMark / sumCredit);
    }

    public double averageRatingCalculate(String rating) {
        if (rating == null) {
            return 0.0;
        } else if (!rating.contains(".")) {
            return 0.0;
        }
        List<Number> ratingList = ratingParsing(rating);
        double markCount = 0.0;
        int mark = 0;
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).toString().contains(".")) {
                try {
                    mark += ratingList.get(i + 2).intValue();
                } catch (IndexOutOfBoundsException exception) {
                    return 0.0;
                }
                markCount++;
            }
        }
        return (mark / markCount);
    }
}
