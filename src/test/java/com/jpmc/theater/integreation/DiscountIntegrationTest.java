package com.jpmc.theater.integreation;

import com.jpmc.theater.ApplicationConstant;
import com.jpmc.theater.MovieTheaterException;
import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.service.discount.DiscountRule;
import com.jpmc.theater.service.discount.FirstShowingDiscountRuleImpl;
import com.jpmc.theater.service.discount.FromElevenToFourShowingDiscountRule;
import com.jpmc.theater.service.discount.MaximumDiscountRuleImpl;
import com.jpmc.theater.service.discount.SecondShowingDiscountRuleImpl;
import com.jpmc.theater.service.discount.SeventhShowingDiscountRuleImpl;
import com.jpmc.theater.service.discount.SpecialMovieDiscountRuleImpl;
import com.jpmc.theater.service.provider.LocalDateProvider;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DiscountIntegrationTest {

    DiscountRule discountRule = new MaximumDiscountRuleImpl(
            List.of(
                    new FirstShowingDiscountRuleImpl(),
                    new SecondShowingDiscountRuleImpl(),
                    new SeventhShowingDiscountRuleImpl(),
                    new SpecialMovieDiscountRuleImpl(),
                    new FromElevenToFourShowingDiscountRule()));

    @Test
    void calculateFirstMovieDiscount() {
        var movie = new Movie("The Batman", Duration.ofMinutes(95), Money.of(9, ApplicationConstant.USD), true);
        var showing = new Showing(movie, 1, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(14, 0)));
        assertEquals(Money.of(3, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

    @Test
    void calculateSecondMovieDiscount() {
        var movie = new Movie("The Batman", Duration.ofMinutes(95), Money.of(9, ApplicationConstant.USD));
        var showing = new Showing(movie, 2, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 0)));
        assertEquals(Money.of(2, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

    @Test
    void calculateSevenMovieDiscount() {
        var movie = new Movie("The Batman", Duration.ofMinutes(95), Money.of(9, ApplicationConstant.USD));
        var showing = new Showing(movie, 7, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 0)));
        assertEquals(Money.of(1, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

    @Test
    void calculateFromElevenToFourDiscount() {
        var movie = new Movie("The Batman", Duration.ofMinutes(95), Money.of(9, ApplicationConstant.USD), true);
        var showing = new Showing(movie, 3, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(14, 0)));
        assertEquals(Money.of(2.25, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

    @Test
    void calculateSpecialMovieDiscount() {
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD), true);
        var showing = new Showing(movie, 3, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 0)));
        assertEquals(Money.of(2.5, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

    @Test
    void calculateZeroDiscount() {
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD));
        var showing = new Showing(movie, 3, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 0)));
        assertEquals(Money.of(0, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

    @Test
    void calculateEmptyDiscounts() {
        DiscountRule emptyDiscountRulesList = new MaximumDiscountRuleImpl(List.of());
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD));
        var showing = new Showing(movie, 3, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 0)));
        assertEquals(Money.of(0, ApplicationConstant.USD), emptyDiscountRulesList.calculateDiscount(showing));
    }

    @Test
    void calculateException() {
        try {
            DiscountRule nullDiscountRulesList = new MaximumDiscountRuleImpl(null);
            var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD));
            var showing = new Showing(movie, 3, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 0)));
            nullDiscountRulesList.calculateDiscount(showing);
            fail("Exception expected");
        } catch (MovieTheaterException exception) {
            assertEquals("Discounts list is null", exception.getMessage());
        }
    }

}
