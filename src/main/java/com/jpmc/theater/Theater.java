package com.jpmc.theater;

import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Schedule;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.service.output.JsonScheduleOutputService;
import com.jpmc.theater.service.output.SimpleScheduleOutputService;
import com.jpmc.theater.service.provider.LocalDateProvider;
import org.javamoney.moneta.Money;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Theater {

    private final Schedule schedule;
    LocalDateProvider provider;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, ApplicationConstant.USD), true);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), Money.of(11, ApplicationConstant.USD));
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), Money.of(9, ApplicationConstant.USD));
        var showings = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
        this.schedule = new Schedule(showings);
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }

    public void printSchedule() {
        System.out.println(new SimpleScheduleOutputService().print(this.schedule));
        System.out.println();
        System.out.println(new JsonScheduleOutputService().print(this.schedule));
    }
}
