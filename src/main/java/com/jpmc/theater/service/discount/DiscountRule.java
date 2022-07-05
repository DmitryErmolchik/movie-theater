package com.jpmc.theater.service.discount;

import com.jpmc.theater.domain.Showing;
import org.javamoney.moneta.Money;

public interface DiscountRule {

    Money calculateDiscount(Showing showing);
}
