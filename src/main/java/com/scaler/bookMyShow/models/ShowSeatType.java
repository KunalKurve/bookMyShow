package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "show_seat_types")
@Table(
        name = "show_seat_types",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"show_id", "seatType"})
        }
)
public class ShowSeatType extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private double price;

}
