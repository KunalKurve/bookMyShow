package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "show_seats")
public class ShowSeat extends BaseModel{


    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    // important for locking and concurrency handling
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSeatStatus;

    private Date lockedAt;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
