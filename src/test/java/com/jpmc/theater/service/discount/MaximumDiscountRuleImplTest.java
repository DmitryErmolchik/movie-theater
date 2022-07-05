package com.jpmc.theater.service.discount;

import com.jpmc.theater.ApplicationConstant;
import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Showing;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaximumDiscountRuleImplTest {

    @Mock
    private DiscountRule minDiscount;

    @Mock
    private DiscountRule mediumDiscount;

    @Mock
    private DiscountRule maxDiscount;

    private MaximumDiscountRuleImpl discountRule;

    @BeforeEach
    void setUp() {
        when(minDiscount.calculateDiscount(any(Showing.class))).thenReturn(Money.of(1, ApplicationConstant.USD));
        when(mediumDiscount.calculateDiscount(any(Showing.class))).thenReturn(Money.of(4, ApplicationConstant.USD));
        when(maxDiscount.calculateDiscount(any(Showing.class))).thenReturn(Money.of(7.5, ApplicationConstant.USD));
        discountRule = new MaximumDiscountRuleImpl(List.of(minDiscount, mediumDiscount, maxDiscount));
    }

    @Test
    void calculateDiscount() {
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD));
        var showing = new Showing(movie, 1, LocalDateTime.now());
        assertEquals(Money.of(7.5, ApplicationConstant.USD), discountRule.calculateDiscount(showing));
    }
}