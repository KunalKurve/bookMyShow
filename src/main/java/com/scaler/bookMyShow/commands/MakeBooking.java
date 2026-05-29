package com.scaler.bookMyShow.commands;

import com.scaler.bookMyShow.controller.BookingController;
import com.scaler.bookMyShow.dto.requests.BookingRequestDto;
import com.scaler.bookMyShow.dto.responses.BookingResponseDto;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MakeBooking implements Command{

    private String COMMAND_NAME = "MakeBooking";
    private BookingController bookingController;

    @Autowired
    private MakeBooking(BookingController bookingController){
        this.bookingController = bookingController;
    }

    @Override
    public boolean matches(String input) {
        String[] commandFragments = input.split(" ");
        if(commandFragments[0].equalsIgnoreCase(COMMAND_NAME)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        String[] commandFragments = input.split(" ");
        try{
            BookingRequestDto bookingRequestDto = getBookingRequestDto(commandFragments);

            BookingResponseDto bookingResponseDto = bookingController.bookTicket(bookingRequestDto);

		    System.out.println("Booking created with ID: " + bookingResponseDto.getBookingId());
            System.out.println("Please complete payment in 15 minutes " + bookingResponseDto.getAmount());
            System.out.println(bookingResponseDto.getResponseStatus());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    private static BookingRequestDto getBookingRequestDto(String[] commandFragments) {
        int userId = Integer.parseInt(commandFragments[1]);
        int showId = Integer.parseInt(commandFragments[2]);

        List<Integer> showSeatIds = new ArrayList<>();
        for (int i = 3; i < commandFragments.length; i++) {
            showSeatIds.add(Integer.parseInt(commandFragments[i]));
        }

        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.setUserId(userId);
        bookingRequestDto.setShowId(showId);
        bookingRequestDto.setShowSeatIds(showSeatIds);
        return bookingRequestDto;
    }
}
