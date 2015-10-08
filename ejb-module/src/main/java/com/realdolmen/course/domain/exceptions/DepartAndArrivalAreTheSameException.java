package com.realdolmen.course.domain.exceptions;


public class DepartAndArrivalAreTheSameException extends RuntimeException {
    public DepartAndArrivalAreTheSameException(String message) {
        super(message);
    }

    public DepartAndArrivalAreTheSameException(String message, Throwable cause) {
        super(message, cause);
    }
}
