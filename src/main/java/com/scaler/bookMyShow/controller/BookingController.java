package com.scaler.bookMyShow.controller;

//import com.scaler.bookMyShow.models.Booking;
import com.scaler.bookMyShow.dto.BookingRequestDto;
import com.scaler.bookMyShow.dto.BookingResponseDto;
import com.scaler.bookMyShow.dto.ResponseStatus;
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

    public BookingResponseDto makeBooking(BookingRequestDto requestDto){
        BookingResponseDto responseDto = new BookingResponseDto();
        try{
            Booking booking = bookingService.bookTicket(
                    requestDto.getUserId(),
                    requestDto.getShowId(),
                    requestDto.getShowSeatIds()
            );
            responseDto.setBookingId(booking.getId());
            responseDto.setAmount(booking.getTotalAmount());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILED);
        }
        return responseDto;
    }
}
