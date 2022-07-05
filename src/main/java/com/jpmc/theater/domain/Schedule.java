package com.jpmc.theater.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Schedule {

    private List<Showing> showings = new ArrayList<>();

    public Schedule() {
    }

    public Schedule(Collection<Showing> showings) {
        this.showings = new ArrayList<>(showings);
    }

    public List<Showing> getShowings() {
        return showings;
    }
}
