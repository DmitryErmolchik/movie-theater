package com.jpmc.theater.dto;

import org.javamoney.moneta.Money;

public class PriceCalculationData {

    private final Money originalPrice;

    private final Money discount;

    private final Money finalPrice;

    public PriceCalculationData(Money originalPrice, Money discount, Money finalPrice) {
        this.originalPrice = originalPrice;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }

    public Money getOriginalPrice() {
        return originalPrice;
    }

    public Money getDiscount() {
        return discount;
    }

    public Money getFinalPrice() {
        return finalPrice;
    }
}
