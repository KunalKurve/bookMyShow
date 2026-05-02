package com.scaler.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "bookings")
public class Booking extends BaseModel{

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @OneToOne
    private List<ShowSeat> showSeats;
    private double totalAmount;

}
