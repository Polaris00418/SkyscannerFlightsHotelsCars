package com.github.khaliullin.skyscanner.exception;

public class FlightClientException extends RuntimeException {
    public FlightClientException() {
        super();
    }

    public FlightClientException(String message) {
        super(message);
    }

    public FlightClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlightClientException(Throwable cause) {
        super(cause);
    }

    protected FlightClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
