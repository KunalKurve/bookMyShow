package com.scaler.bookMyShow.repository;

import com.scaler.bookMyShow.models.Booking;
import com.scaler.bookMyShow.models.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookingStatus(BookingStatus bookingStatus);
}
