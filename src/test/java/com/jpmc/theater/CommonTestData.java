package com.jpmc.theater;

import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Schedule;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.service.provider.LocalDateProvider;
import org.javamoney.moneta.Money;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CommonTestData {

    public static Schedule testSchedule() {
        var currentDate = LocalDateProvider.singleton().currentDate();
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD), true);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), Money.of(11, ApplicationConstant.USD));
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), Money.of(9, ApplicationConstant.USD));
        var schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(currentDate, LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(currentDate, LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(currentDate, LocalTime.of(12, 50)))
        );
        return new Schedule(schedule);
    }
}
