package com.jpmc.theater.service.discount;

import com.jpmc.theater.ApplicationConstant;
import com.jpmc.theater.domain.Showing;
import org.javamoney.moneta.Money;

import javax.validation.constraints.NotNull;

public class SeventhShowingDiscountRuleImpl extends AbstractNaturalDiscountRule {

    private static final Money DISCOUNT_AMOUNT = Money.of(1, ApplicationConstant.USD);

    protected boolean canApplyDiscount(@NotNull Showing showing) {
        return showing.getSequenceOfTheDay() == 7;
    }

    @Override
    protected Money getDiscountAmount() {
        return DISCOUNT_AMOUNT;
    }

}
