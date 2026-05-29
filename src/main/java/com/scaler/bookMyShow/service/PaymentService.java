package com.scaler.bookMyShow.service;

import com.scaler.bookMyShow.exceptions.BookingExpiredException;
import com.scaler.bookMyShow.exceptions.BookingNotFoundException;
import com.scaler.bookMyShow.models.Booking;
import com.scaler.bookMyShow.models.Payment;
import com.scaler.bookMyShow.models.ShowSeat;
import com.scaler.bookMyShow.models.enums.*;
import com.scaler.bookMyShow.repository.BookingRepository;
import com.scaler.bookMyShow.repository.PaymentRepository;
import com.scaler.bookMyShow.repository.ShowSeatRepository;
import com.scaler.bookMyShow.strategies.PaymentGatewayStrategy;
import com.scaler.bookMyShow.strategies.PaymentGatewayStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private BookingRepository bookingRepository;
    private ShowSeatRepository showSeatRepository;
    private TicketService ticketService;
    private PaymentGatewayStrategyFactory paymentGatewayStrategyFactory;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          BookingRepository bookingRepository,
                          ShowSeatRepository showSeatRepository,
                          TicketService ticketService,
                          PaymentGatewayStrategyFactory paymentGatewayStrategyFactory){
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketService = ticketService;
        this.paymentGatewayStrategyFactory = paymentGatewayStrategyFactory;
    }

    @Transactional
    public Payment makePayment(int bookingId,
                               PaymentMode mode,
                               PaymentGateway gateway) throws BookingNotFoundException {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new BookingNotFoundException("Booking not found"));

        if(!booking.getBookingStatus().equals(BookingStatus.PENDING)){
            throw new RuntimeException("Booking already Processed");
        }

        // check expiry
        if(booking.getExpiresAt().before(new Date())){
            booking.setBookingStatus(BookingStatus.EXPIRED);
            releaseSeats(booking);
            bookingRepository.save(booking);
            throw new BookingExpiredException("Booking expired");
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentMode(mode);
        payment.setGateway(gateway);
        payment.setTotalAmount(booking.getTotalAmount());
        payment.setTransaction_id(UUID.randomUUID().toString());

        PaymentGatewayStrategy strategy = paymentGatewayStrategyFactory
                .getPaymentGateway(gateway);

        PaymentStatus paymentStatus = strategy.processPayment(booking.getTotalAmount());

        payment.setPaymentStatus(paymentStatus);
        Payment savedPayment = paymentRepository.save(payment);

        if(paymentStatus.equals(PaymentStatus.SUCCESS)) {
            //booking confirmed
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            System.out.println("Booking confirmed");

            for (ShowSeat showSeat : booking.getShowSeats()) {
                showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
                showSeatRepository.save(showSeat);
            }

            //generate tickets
            ticketService.generateTickets(booking);
        }
        else{
            booking.setBookingStatus(BookingStatus.FAILED);
            releaseSeats(booking);
        }

        booking.getPayments().add(savedPayment);
        bookingRepository.save(booking);

        return savedPayment;
    }

    public void releaseSeats(Booking booking){

        for (ShowSeat showSeat : booking.getShowSeats()){
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeat.setLockedAt(null);
        }

        showSeatRepository.saveAll(booking.getShowSeats());
    }

}
