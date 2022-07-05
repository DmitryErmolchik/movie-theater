package com.jpmc.theater.service.provider;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDateProviderTest {

    @Test
    void singleton() {
        assertEquals(LocalDateProvider.singleton(), LocalDateProvider.singleton());
    }

    @Test
    void currentDate() {
        assertEquals(LocalDate.now(), LocalDateProvider.singleton().currentDate());
    }
}