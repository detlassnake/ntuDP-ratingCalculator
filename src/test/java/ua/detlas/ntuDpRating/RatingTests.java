package ua.detlas.ntuDpRating;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RatingTests {
    Rating rating;
    String actualString;
    List<Number> actualList1;
    List<Number> actualList2;

    @Before
    public void setUp() {
        rating = new Rating();
        actualString = "1qwe 2 qwer 3 qwertyu. 5.0 5 100";
        actualList1 = Arrays.asList(1, 2, 3, 5.0, 5, 100);
        actualList2 = Arrays.asList(94.0, 100.0);
    }

    @Test
    public void testRatingParsed() {
        setUp();

        List<Number> expectedList = rating.ratingParsing(actualString);

        assertEquals(expectedList, actualList1);
    }

    @Test
    public void testRatingCalculated() {
        setUp();

        List<Number> expectedList = rating.ratingCalculate(actualString);

        assertEquals(expectedList, actualList2);
    }
}
