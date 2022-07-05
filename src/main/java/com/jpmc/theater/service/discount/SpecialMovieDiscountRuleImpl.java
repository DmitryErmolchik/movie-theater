package com.jpmc.theater.service.discount;

import com.jpmc.theater.domain.Showing;

import javax.validation.constraints.NotNull;

public class SpecialMovieDiscountRuleImpl extends AbstractPercentDiscountRule {

    private static final double DISCOUNT_PERCENT = 0.2;

    @Override
    protected boolean canApplyDiscount(@NotNull Showing showing) {
        return showing.getMovie().isSpecialCode();
    }

    @Override
    protected double getDiscountAmount() {
        return DISCOUNT_PERCENT;
    }
}
