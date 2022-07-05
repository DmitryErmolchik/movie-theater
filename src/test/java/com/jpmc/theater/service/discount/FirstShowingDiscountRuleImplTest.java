package com.jpmc.theater.service.discount;

import com.jpmc.theater.ApplicationConstant;
import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Showing;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstShowingDiscountRuleImplTest {

    private final FirstShowingDiscountRuleImpl discountRule = new FirstShowingDiscountRuleImpl();

    @Test
    void canApplyDiscount() {
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD));
        var showing = new Showing(movie, 1, LocalDateTime.now());
        assertTrue(discountRule.canApplyDiscount(showing));
    }

    @Test
    void cantApplyDiscount() {
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD));
        var showing = new Showing(movie, 2, LocalDateTime.now());
        assertFalse(discountRule.canApplyDiscount(showing));
    }

    @Test
    void calculateDiscountApplied() {
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD));
        var showing = new Showing(movie, 1, LocalDateTime.now());
        assertEquals(Money.of(3, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

    @Test
    void calculateDiscountNotApplied() {
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD));
        var showing = new Showing(movie, 2, LocalDateTime.now());
        assertEquals(Money.of(0, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }

}