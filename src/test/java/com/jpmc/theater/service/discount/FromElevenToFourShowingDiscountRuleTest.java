package com.jpmc.theater.service.discount;

import com.jpmc.theater.ApplicationConstant;
import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.service.provider.LocalDateProvider;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FromElevenToFourShowingDiscountRuleTest {

    private static final Movie MOVIE = new Movie("The Batman", Duration.ofMinutes(95), Money.of(9, ApplicationConstant.USD));
    private final FromElevenToFourShowingDiscountRule discountRule = new FromElevenToFourShowingDiscountRule();


    @Test
    void cantApplyDiscount_10_00() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 0)));
        assertFalse(discountRule.canApplyDiscount(showing));
    }

    @Test
    void cantApplyDiscount_10_59_59() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 59, 59)));
        assertFalse(discountRule.canApplyDiscount(showing));
    }

    @Test
    void cantApplyDiscount_16_00_01() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(16, 0, 1)));
        assertFalse(discountRule.canApplyDiscount(showing));
    }

    @Test
    void cantApplyDiscount_17_00() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(17, 0)));
        assertFalse(discountRule.canApplyDiscount(showing));
    }

    @Test
    void canApplyDiscount_12_00() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(12, 0)));
        assertTrue(discountRule.canApplyDiscount(showing));
    }

    @Test
    void canApplyDiscount_11_00() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(11, 0)));
        assertTrue(discountRule.canApplyDiscount(showing));
    }

    @Test
    void canApplyDiscount_16_00() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(16, 0)));
        assertTrue(discountRule.canApplyDiscount(showing));
    }

    @Test
    void calculateDiscountApplied() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(12, 0)));
        assertEquals(Money.of(2.25, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

    @Test
    void calculateDiscountNotApplied() {
        var showing = new Showing(MOVIE, 0, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(10, 0)));
        assertEquals(Money.of(0, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

}