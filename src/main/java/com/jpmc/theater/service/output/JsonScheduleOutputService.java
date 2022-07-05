package com.jpmc.theater.service.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jpmc.theater.MovieTheaterException;
import com.jpmc.theater.domain.Schedule;

public class JsonScheduleOutputService implements ScheduleOutputService {

    private final ObjectMapper objectMapper = new JsonMapper();

    public JsonScheduleOutputService() {
        objectMapper.findAndRegisterModules();
    }

    @Override
    public String print(Schedule schedule) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(schedule);
        } catch (JsonProcessingException exception) {
            throw MovieTheaterException.getException("Can't write schedule in the JSON format. Cause: " + exception.getMessage());
        }
    }
}
