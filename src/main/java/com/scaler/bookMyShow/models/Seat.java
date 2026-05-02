package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seats")
public class Seat extends BaseModel{

    private int seat_no;

    //deliberate bug
    private int row_no;
    private int col_no;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
