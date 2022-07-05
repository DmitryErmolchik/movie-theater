package com.jpmc.theater.formatter;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DurationToHumanFormatterTest {

    @Test
    void format() {
        assertEquals("0 hours 1 minute", DurationToHumanFormatter.format(Duration.ofMinutes(1)));
        assertEquals("0 hours 2 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(2)));
        assertEquals("0 hours 10 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(10)));
        assertEquals("0 hours 11 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(11)));
        assertEquals("1 hour 0 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(60)));
        assertEquals("1 hour 1 minute", DurationToHumanFormatter.format(Duration.ofMinutes(61)));
        assertEquals("1 hour 2 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(62)));
        assertEquals("1 hour 10 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(70)));
        assertEquals("1 hour 11 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(71)));
        assertEquals("1 hour 59 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(119)));
        assertEquals("2 hours 0 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(120)));
        assertEquals("2 hours 1 minute", DurationToHumanFormatter.format(Duration.ofMinutes(121)));
        assertEquals("2 hours 2 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(122)));
        assertEquals("10 hours 0 minutes", DurationToHumanFormatter.format(Duration.ofMinutes(600)));
    }
}