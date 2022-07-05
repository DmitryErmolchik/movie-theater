package com.jpmc.theater.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class Showing implements Comparable<Showing> {
    private final Movie movie;
    private final int sequenceOfTheDay;
    private final LocalDateTime showStartTime;

    public Showing(@NotNull Movie movie, int sequenceOfTheDay, @NotNull LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showing showing = (Showing) o;
        return sequenceOfTheDay == showing.sequenceOfTheDay && Objects.equals(movie, showing.movie) && Objects.equals(showStartTime, showing.showStartTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, sequenceOfTheDay, showStartTime);
    }

    @Override
    public int compareTo(Showing showing) {
        return Integer.compare(this.sequenceOfTheDay, showing.sequenceOfTheDay);
    }
}
