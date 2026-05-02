package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "tickets")
public class Ticket extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "show_seat_id")
    private ShowSeat showSeat;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;



}
