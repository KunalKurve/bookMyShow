package com.scaler.bookMyShow.service;

import com.scaler.bookMyShow.models.Booking;
import com.scaler.bookMyShow.models.ShowSeat;
import com.scaler.bookMyShow.models.Ticket;
import com.scaler.bookMyShow.models.enums.TicketStatus;
import com.scaler.bookMyShow.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public void generateTickets(Booking booking) {

        List<Ticket> tickets = new ArrayList<>();

        for (ShowSeat showSeat : booking.getShowSeats()){
             Ticket ticket = new Ticket();

             ticket.setBooking(booking);
             ticket.setShowSeat(showSeat);
             ticket.setTicketStatus(TicketStatus.CONFIRMED);
             ticket.setShow(booking.getShow());
             tickets.add(ticket);
        }

        booking.setTickets(tickets);
        ticketRepository.saveAll(tickets);
        System.out.println("Tickets generated");

    }
}
