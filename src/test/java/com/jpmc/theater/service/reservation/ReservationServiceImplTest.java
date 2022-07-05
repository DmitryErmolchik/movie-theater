package com.jpmc.theater.service.reservation;

import com.jpmc.theater.CommonTestData;
import com.jpmc.theater.MovieTheaterException;
import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.repository.InMemoryScheduleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReservationServiceImplTest {

    private static ReservationServiceImpl reservationService;

    @BeforeAll
    static void beforeAll() {
        var scheduleRepository = new InMemoryScheduleRepository(CommonTestData.testSchedule());
        reservationService = new ReservationServiceImpl(scheduleRepository);
    }

    @Test
    void reserve() {
        var customer = new Customer("Test");
        var reservation = reservationService.reserve(customer, 1, 3);
        assertEquals(CommonTestData.testSchedule().getShowings().get(0), reservation.getShowing());
        assertEquals(3, reservation.getAudienceCount());
    }

    @Test
    void reserveZeroSequenceShow() {
        try {
            var customer = new Customer("Test");
            reservationService.reserve(customer, 0, 3);
            fail("Expecting exception");
        } catch (MovieTheaterException exception) {
            assertEquals("Not able to find any showing for given sequence 0", exception.getMessage());
        }
    }

    @Test
    void reserveNonExistsSequenceShow() {
        try {
            var customer = new Customer("Test");
            reservationService.reserve(customer, 4, 3);
            fail("Expecting exception");
        } catch (MovieTheaterException exception) {
            assertEquals("Not able to find any showing for given sequence 4", exception.getMessage());
        }
    }

}