package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tickets")
public class Ticket extends BaseModel{

    @OneToOne
    private Booking booking;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;

}
