package com.jpmc.theater.repository;

import com.jpmc.theater.domain.Schedule;

public class InMemoryScheduleRepository implements ScheduleRepository {

    private final Schedule schedule;

    public InMemoryScheduleRepository(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public Schedule getSchedule() {
        return schedule;
    }

}
