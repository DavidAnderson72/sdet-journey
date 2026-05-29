package org.example.Week3;

public class InvalidRentException extends RuntimeException {

    // Constructor with message
    public InvalidRentException(String message){
        super(message);
    }

    // Constructor with message and cause
    public InvalidRentException(String message, Throwable cause) {
        super(message, cause);
    }
}
