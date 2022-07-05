package com.jpmc.theater.formatter;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;

import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

public class MoneyToHumanFormatter {

    private static final MonetaryAmountFormat MONETARY_AMOUNT_FORMAT = MonetaryFormats.getAmountFormat(
            AmountFormatQueryBuilder.of(Locale.US)
                    .set(CurrencyStyle.SYMBOL)
                    .build()
    );

    private MoneyToHumanFormatter() {
    }

    public static String format(Money money) {
        return MONETARY_AMOUNT_FORMAT.format(money);
    }
}
