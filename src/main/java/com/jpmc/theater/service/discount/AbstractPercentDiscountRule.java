package com.jpmc.theater.service.discount;

import com.jpmc.theater.MovieTheaterException;
import com.jpmc.theater.domain.Showing;
import org.javamoney.moneta.Money;

import javax.validation.constraints.NotNull;

public abstract class AbstractPercentDiscountRule implements DiscountRule {
    @Override
    public Money calculateDiscount(@NotNull Showing showing) {
        var movie = showing.getMovie();
        if (movie != null) {
            if (canApplyDiscount(showing)) {
                return movie.getTicketPrice().multiply(getDiscountAmount());
            }
            return Money.of(0, movie.getTicketPrice().getCurrency());
        }
        throw MovieTheaterException.getException("Movie can't be null");
    }

    protected abstract boolean canApplyDiscount(Showing showing);

    protected abstract double getDiscountAmount();

}
