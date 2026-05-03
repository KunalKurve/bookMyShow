package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "bookings")
public class Booking extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    // mappedby should be used only when Bidirectional relationship is to be created
    @OneToMany(mappedBy = "booking")
    private List<ShowSeat> showSeats;

    private double totalAmount;

    @OneToMany(mappedBy = "booking")
    private List<Ticket> tickets;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;

    //private Date bookingDate;
}
