package com.jpmc.theater.service.price;

import com.jpmc.theater.ApplicationConstant;
import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Reservation;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.service.discount.DiscountRule;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceCalculatorServiceImplTest {

    @Mock
    private Customer customer;

    @Mock
    private DiscountRule discountRule;

    @Mock
    private Showing showingOne;

    @Mock
    private Movie movieOne;

    @Mock
    private Showing showingTwo;

    @Mock
    private Movie movieTwo;

    @InjectMocks
    private PriceCalculatorServiceImpl feeCalculator;

    @Test
    void calculateOneReservation() {
        when(discountRule.calculateDiscount(showingOne)).thenReturn(Money.of(1, ApplicationConstant.USD));
        when(showingOne.getMovie()).thenReturn(movieOne);
        when(movieOne.getTicketPrice()).thenReturn(Money.of(10, ApplicationConstant.USD));
        var reservation = new Reservation(customer, showingOne, 3);
        assertEquals(Money.of(27, ApplicationConstant.USD), feeCalculator.calculate(reservation).getFinalPrice());
    }

    @Test
    void calculateManyReservations() {
        when(discountRule.calculateDiscount(showingOne)).thenReturn(Money.of(1, ApplicationConstant.USD));
        when(discountRule.calculateDiscount(showingTwo)).thenReturn(Money.of(2, ApplicationConstant.USD));
        when(showingOne.getMovie()).thenReturn(movieOne);
        when(showingTwo.getMovie()).thenReturn(movieTwo);
        when(movieOne.getTicketPrice()).thenReturn(Money.of(10, ApplicationConstant.USD));
        when(movieTwo.getTicketPrice()).thenReturn(Money.of(8, ApplicationConstant.USD));
        var reservation = List.of(
                new Reservation(customer, showingOne, 3),
                new Reservation(customer, showingTwo, 2));
        assertEquals(Money.of(39, ApplicationConstant.USD), feeCalculator.calculate(reservation).getFinalPrice());
    }
}