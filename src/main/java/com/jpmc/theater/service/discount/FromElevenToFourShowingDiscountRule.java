package com.jpmc.theater.service.discount;

import com.jpmc.theater.domain.Showing;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class FromElevenToFourShowingDiscountRule extends AbstractPercentDiscountRule {

    private static final double DISCOUNT_PERCENT = 0.25;

    private static final LocalTime DISCOUNT_START_TIME = LocalTime.of(11, 0);

    private static final LocalTime DISCOUNT_END_TIME = LocalTime.of(16, 0);

    @Override
    protected boolean canApplyDiscount(@NotNull Showing showing) {
        var movieTime = LocalTime.from(showing.getStartTime());
        return (DISCOUNT_START_TIME.equals(movieTime) || DISCOUNT_START_TIME.isBefore(movieTime))
                && (DISCOUNT_END_TIME.equals(movieTime) || DISCOUNT_END_TIME.isAfter(movieTime));
    }

    @Override
    protected double getDiscountAmount() {
        return DISCOUNT_PERCENT;
    }
}
