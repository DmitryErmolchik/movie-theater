package com.jpmc.theater.service.output;

import com.jpmc.theater.CommonTestData;
import com.jpmc.theater.service.provider.LocalDateProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleScheduleOutputServiceTest {

    private final SimpleScheduleOutputService output = new SimpleScheduleOutputService();

    @Test
    void print() {
        var result = output.print(CommonTestData.testSchedule());
        assertTrue(result.contains(LocalDateProvider.singleton().currentDate() + "T09:00 Turning Red (1 hour 25 minutes) $11.00"));
        assertTrue(result.contains(LocalDateProvider.singleton().currentDate() + "T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.50"));
        assertTrue(result.contains(LocalDateProvider.singleton().currentDate() + "T12:50 The Batman (1 hour 35 minutes) $9.00"));
    }
}