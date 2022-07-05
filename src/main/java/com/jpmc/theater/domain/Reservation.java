package com.jpmc.theater.domain;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final Showing showing;
    private final int audienceCount;

    public Reservation(@NotNull Customer customer, @NotNull Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public Showing getShowing() {
        return showing;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return audienceCount == that.audienceCount && Objects.equals(customer, that.customer) && Objects.equals(showing, that.showing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, showing, audienceCount);
    }
}