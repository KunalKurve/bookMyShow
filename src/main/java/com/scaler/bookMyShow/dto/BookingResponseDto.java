package com.scaler.bookMyShow.dto;

import com.scaler.bookMyShow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {

    private int bookingId;
    private ResponseStatus responseStatus;
    private double amount;

    //frontend already has info of the seatIds requested for booking.
    //its not like if one seat is not available, system gives another.
}
