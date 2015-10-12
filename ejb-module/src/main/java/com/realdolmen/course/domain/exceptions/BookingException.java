package com.realdolmen.course.domain.exceptions;


public class BookingException extends RuntimeException {
    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingException(String message) {
        super(message);
    }
}
