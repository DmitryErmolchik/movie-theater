package com.jpmc.theater.integreation;

import com.jpmc.theater.ApplicationConstant;
import com.jpmc.theater.CommonTestData;
import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.repository.InMemoryScheduleRepository;
import com.jpmc.theater.service.discount.FirstShowingDiscountRuleImpl;
import com.jpmc.theater.service.discount.FromElevenToFourShowingDiscountRule;
import com.jpmc.theater.service.discount.MaximumDiscountRuleImpl;
import com.jpmc.theater.service.discount.SecondShowingDiscountRuleImpl;
import com.jpmc.theater.service.discount.SeventhShowingDiscountRuleImpl;
import com.jpmc.theater.service.discount.SpecialMovieDiscountRuleImpl;
import com.jpmc.theater.service.price.PriceCalculatorServiceImpl;
import com.jpmc.theater.service.reservation.ReservationServiceImpl;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationPriceIntegrationTest {

    @Test
    void calculateReservationPrice() {
        var reservationService = new ReservationServiceImpl(new InMemoryScheduleRepository(CommonTestData.testSchedule()));
        var customer = new Customer("Test");
        var reservations = List.of(
                reservationService.reserve(customer, 1, 2),
                reservationService.reserve(customer, 2, 3),
                reservationService.reserve(customer, 3, 1));

        var discountRule = new MaximumDiscountRuleImpl(
                List.of(
                        new FirstShowingDiscountRuleImpl(),
                        new SecondShowingDiscountRuleImpl(),
                        new SeventhShowingDiscountRuleImpl(),
                        new SpecialMovieDiscountRuleImpl(),
                        new FromElevenToFourShowingDiscountRule()));
        var priceCalculator = new PriceCalculatorServiceImpl(discountRule);
        var priceCalculationData = priceCalculator.calculate(reservations);
        assertEquals(Money.of(50.875, ApplicationConstant.USD), priceCalculationData.getFinalPrice());
        assertEquals(Money.of(17.625, ApplicationConstant.USD), priceCalculationData.getDiscount());
        assertEquals(Money.of(68.5, ApplicationConstant.USD), priceCalculationData.getOriginalPrice());
        assertEquals(priceCalculationData.getOriginalPrice(), priceCalculationData.getFinalPrice().add(priceCalculationData.getDiscount()));
    }

}
