package com.github.ardenliu.common.reservation.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReservationService<A extends ReservationObject, B extends Client> {
    
    // For consecutive days 
    String reserve(B client, String reservationObjectId, LocalDate begin, LocalDate end);
    
    String reserve(B client, String reservationObjectId, List<LocalDate> dates);
    
    // The unique booking identifier can be used to cancel the reservation later on
    void cancel(String bookingIdentifier);

    // The unique booking identifier can be used to cancel the reservation later on
    // // For consecutive days
    void modify(String bookingIdentifier, LocalDate begin, LocalDate end);
    
    void modify(String bookingIdentifier, List<LocalDate> dates);

    // The users will need to find out when the campsite is available. So the system should expose an API to provide information of 
    // the availability of the campsite for a given date range with the default being 1 month
    List<LocalDate> availability(String reservationObjectId, LocalDate begin, LocalDate end);
    
    Map<String, List<LocalDate>> availability(List<String> ids);
    
    List<A> allReservationObjects();
}