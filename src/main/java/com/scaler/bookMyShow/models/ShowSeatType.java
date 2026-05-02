package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "show_seat_type")
public class ShowSeatType extends BaseModel{

    @ManyToOne
    private Show show;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private double price;

}
