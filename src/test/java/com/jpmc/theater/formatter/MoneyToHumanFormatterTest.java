package com.jpmc.theater.formatter;

import com.jpmc.theater.ApplicationConstant;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyToHumanFormatterTest {

    @Test
    void format() {
        assertEquals("$2.50", MoneyToHumanFormatter.format(Money.of(2.50, ApplicationConstant.USD)));
    }
}