package com.jpmc.theater.service.reservation;

import com.jpmc.theater.MovieTheaterException;
import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.domain.Reservation;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.repository.ScheduleRepository;

public class ReservationServiceImpl implements ReservationService {

    private final ScheduleRepository scheduleRepository;

    public ReservationServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = scheduleRepository.getSchedule().getShowings().get(sequence - 1);
        } catch (RuntimeException ex) {
            throw MovieTheaterException.getException("Not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

}
