package com.jpmc.theater.service.provider;

import java.time.LocalDate;

public final class LocalDateProvider {
    private static final LocalDateProvider INSTANCE = new LocalDateProvider();

    private LocalDateProvider() {
    }

    public static LocalDateProvider singleton() {
        return INSTANCE;
    }

    public LocalDate currentDate() {
        return LocalDate.now();
    }
}
