package com.jpmc.theater.service.discount;

import com.jpmc.theater.MovieTheaterException;
import com.jpmc.theater.domain.Showing;
import org.javamoney.moneta.Money;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MaximumDiscountRuleImpl implements DiscountRule {

    List<DiscountRule> discountRules;

    public MaximumDiscountRuleImpl(List<DiscountRule> discountRules) {
        this.discountRules = discountRules;
    }

    @Override
    public Money calculateDiscount(@NotNull Showing showing) {
        if (discountRules != null) {
            return discountRules.stream()
                    .map(discountRule -> discountRule.calculateDiscount(showing))
                    .max(Money::compareTo)
                    .orElse(Money.zero(showing.getMovie().getTicketPrice().getCurrency()));
        }
        throw MovieTheaterException.getException("Discounts list is null");
    }
}
