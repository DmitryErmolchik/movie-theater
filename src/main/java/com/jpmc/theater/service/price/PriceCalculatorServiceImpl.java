package com.jpmc.theater.service.price;

import com.jpmc.theater.MovieTheaterException;
import com.jpmc.theater.domain.Reservation;
import com.jpmc.theater.dto.PriceCalculationData;
import com.jpmc.theater.service.discount.DiscountRule;

import java.util.Collection;

public class PriceCalculatorServiceImpl implements PriceCalculatorService {

    private final DiscountRule discountRule;

    public PriceCalculatorServiceImpl(DiscountRule discountRule) {
        this.discountRule = discountRule;
    }

    @Override
    public PriceCalculationData calculate(Reservation reservation) {
        return calculateShowingFee(reservation);
    }

    @Override
    public PriceCalculationData calculate(Collection<Reservation> reservation) {
        return reservation.stream()
                .map(this::calculateShowingFee)
                .reduce((accumulator, priceCalculationData) -> new PriceCalculationData(
                        accumulator.getOriginalPrice().add(priceCalculationData.getOriginalPrice()),
                        accumulator.getDiscount().add(priceCalculationData.getDiscount()),
                        accumulator.getFinalPrice().add(priceCalculationData.getFinalPrice())
                ))
                .orElseThrow(() -> MovieTheaterException.getException("Can't calculate reservation fee"));
    }

    private PriceCalculationData calculateShowingFee(Reservation reservation) {
        var showing = reservation.getShowing();
        var moviePrice = showing.getMovie().getTicketPrice();
        var audienceCount = reservation.getAudienceCount();
        return new PriceCalculationData(
                moviePrice.multiply(audienceCount),
                discountRule.calculateDiscount(showing).multiply(audienceCount),
                moviePrice.subtract(discountRule.calculateDiscount(showing)).multiply(audienceCount));
    }
}
