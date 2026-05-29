package com.scaler.bookMyShow.service;

import com.scaler.bookMyShow.models.Booking;
import com.scaler.bookMyShow.models.ShowSeat;
import com.scaler.bookMyShow.models.enums.BookingStatus;
import com.scaler.bookMyShow.models.enums.ShowSeatStatus;
import com.scaler.bookMyShow.repository.BookingRepository;
import com.scaler.bookMyShow.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BookingExpiryScheduler {

    private final BookingRepository bookingRepository;
    private final ShowSeatRepository showSeatRepository;

    @Autowired
    public BookingExpiryScheduler(
            BookingRepository bookingRepository,
            ShowSeatRepository showSeatRepository
    ){
        this.bookingRepository = bookingRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void expireBookings() {

        List<Booking> bookings = bookingRepository
                .findByBookingStatus(BookingStatus.PENDING);

        for(Booking booking : bookings) {

            if (booking.getExpiresAt().before(new Date())) {

                booking.setBookingStatus(BookingStatus.EXPIRED);

                for (ShowSeat showSeat : booking.getShowSeats()) {

                    showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
                    showSeat.setLockedAt(null);
                }

                showSeatRepository.saveAll(booking.getShowSeats());
                bookingRepository.save(booking);

                System.out.println("Booking expired: " + booking.getId());

            }
        }

    }
}
