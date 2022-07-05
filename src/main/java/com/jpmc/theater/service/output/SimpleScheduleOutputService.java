package com.jpmc.theater.service.output;

import com.jpmc.theater.domain.Schedule;
import com.jpmc.theater.formatter.DurationToHumanFormatter;
import com.jpmc.theater.formatter.MoneyToHumanFormatter;
import com.jpmc.theater.service.provider.LocalDateProvider;

import java.util.stream.Collectors;

public class SimpleScheduleOutputService implements ScheduleOutputService {

    @Override
    public String print(Schedule schedule) {
        var currentDate = LocalDateProvider.singleton().currentDate();
        var stringBuilder = new StringBuilder();
        stringBuilder.append(currentDate)
                .append(System.lineSeparator())
                .append("===================================================")
                .append(System.lineSeparator())
                .append(
                        schedule.getShowings().stream().sorted().map(s ->
                                        String.format("%d: %s %s (%s) %s",
                                                s.getSequenceOfTheDay(),
                                                s.getStartTime(),
                                                s.getMovie().getTitle(),
                                                DurationToHumanFormatter.format(s.getMovie().getRunningTime()),
                                                MoneyToHumanFormatter.format(s.getMovie().getTicketPrice()))
                                )
                                .collect(Collectors.joining(System.lineSeparator())))
                .append(System.lineSeparator())
                .append("===================================================");
        return stringBuilder.toString();
    }
}
