package com.jpmc.theater.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jpmc.theater.formatter.DurationSerializer;
import com.jpmc.theater.formatter.MoneySerializer;
import org.javamoney.moneta.Money;

import java.time.Duration;
import java.util.Objects;

public class Movie {

    private final String title;

    @JsonSerialize(using = DurationSerializer.class)
    private final Duration runningTime;
    @JsonSerialize(using = MoneySerializer.class)

    private final Money ticketPrice;

    private final boolean specialCode;

    public Movie(String title, Duration runningTime, Money ticketPrice) {
        this(title, runningTime, ticketPrice, false);
    }

    public Movie(String title, Duration runningTime, Money ticketPrice, boolean specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public Money getTicketPrice() {
        return ticketPrice;
    }

    public boolean isSpecialCode() {
        return specialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return specialCode == movie.specialCode && Objects.equals(title, movie.title) && Objects.equals(runningTime, movie.runningTime) && Objects.equals(ticketPrice, movie.ticketPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}