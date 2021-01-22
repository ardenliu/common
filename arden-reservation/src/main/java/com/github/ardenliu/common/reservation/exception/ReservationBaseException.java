package com.github.ardenliu.common.reservation.exception;

public class ReservationBaseException extends RuntimeException {

    private static final long serialVersionUID = 4423073880461391504L;

    public ReservationBaseException() {
        super();
    }

    public ReservationBaseException(String message) {
        super(message);
    }
}