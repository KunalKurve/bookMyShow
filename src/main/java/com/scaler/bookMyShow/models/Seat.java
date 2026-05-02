package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.SeatType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seats")
public class Seat extends BaseModel{
    private int seat_no;
    private SeatType seatType;
}
