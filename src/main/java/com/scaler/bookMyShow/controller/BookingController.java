package com.scaler.bookMyShow.controller;

import com.scaler.bookMyShow.models.Booking;
import com.scaler.bookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

//    public BookingResponseDTO makeBooking(BookingRequestDTO requestDTO){
//        BookingResponseDTO responseDTO;
//        Booking booking = bookingService.bookTicket(requestDTO);
//
//        return responseDTO;
//    }
}
