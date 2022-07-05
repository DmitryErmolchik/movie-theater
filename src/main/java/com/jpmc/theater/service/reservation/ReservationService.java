package com.jpmc.theater.service.reservation;

import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.domain.Reservation;

public interface ReservationService {

    Reservation reserve(Customer customer, int sequence, int howManyTickets);

}
