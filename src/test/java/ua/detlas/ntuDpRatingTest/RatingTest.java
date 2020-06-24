package ua.detlas.ntuDpRatingTest;

import org.junit.Before;
import org.junit.Test;
import ua.detlas.ntuDpRating.Rating;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RatingTest {
    Rating rating;
    String testString;
    List<Number> testResult1;
    List<Number> testResult2;

    @Before
    public void setUp() {
        rating = new Rating();
        testString = "1qwe 2 qwer 3 qwertyu. 5.0 5 100";
        testResult1 = Arrays.asList(1, 2, 3, 5.0, 5, 100);
        testResult2 = Arrays.asList(94.0, 100.0);
    }

    @Test
    public void ratingParsingTest() {
        setUp();
        List<Number> result = rating.ratingParsing(testString);
        assertEquals(result, testResult1);
    }

    @Test
    public void ratingCalculateTest() {
        setUp();
        List<Number> result = rating.ratingCalculate(testString);
        assertEquals(result, testResult2);
    }
}
