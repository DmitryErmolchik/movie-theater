package com.jpmc.theater;

public class MovieTheaterException extends RuntimeException {

    private MovieTheaterException(String message) {
        super(message);
    }

    public static MovieTheaterException getException(String message) {
        return new MovieTheaterException(message);
    }
}
