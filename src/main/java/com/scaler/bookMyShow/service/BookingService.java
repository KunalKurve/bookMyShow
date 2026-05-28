package com.scaler.bookMyShow.service;

import com.scaler.bookMyShow.exceptions.ShowDoesNotExistException;
import com.scaler.bookMyShow.exceptions.UserNotFoundException;
import com.scaler.bookMyShow.models.Booking;
import com.scaler.bookMyShow.models.Show;
import com.scaler.bookMyShow.models.ShowSeat;
import com.scaler.bookMyShow.models.User;
import com.scaler.bookMyShow.models.enums.BookingStatus;
import com.scaler.bookMyShow.models.enums.ShowSeatStatus;
import com.scaler.bookMyShow.repository.BookingRepository;
import com.scaler.bookMyShow.repository.ShowRepository;
import com.scaler.bookMyShow.repository.ShowSeatRepository;
import com.scaler.bookMyShow.repository.UserRepository;
import com.scaler.bookMyShow.strategies.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PricingService pricingService;
    private PaymentService paymentService;

    @Autowired
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository,
                          PricingService pricingService,
                          PaymentService paymentService
    ){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.pricingService = pricingService;
        this.paymentService = paymentService;
    }

    public Booking bookTicket(int userId, int showId, List<Integer> showSeatIds) {

        //whenever coding any api or service logic - first code the validation logic

        //userId should exist
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOptional.get();

        // showId should exist
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowDoesNotExistException("Show does not Exist");
        }
        Show show = optionalShow.get();

        //showseats should exist and be Available not blocked or booked
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new RuntimeException("All requested seats are not available");
            }
        }
        // when all showseats are available then only mark them as blocked
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat: showSeats){

            //showseat status change from available to blocked
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setLockedAt(new Date());

            //save the showseats status in db
            ShowSeat savedShowSeat = showSeatRepository.save(showSeat);
            savedShowSeats.add(savedShowSeat);
        }

        return createBooking(user, show, savedShowSeats);
    }

    // import the correct annotation (from springframework.transaction.annotation)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(User user, Show show, List<ShowSeat> savedShowSeats){
        //create Booking then save and return
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSeats(savedShowSeats);
        booking.setPayments(new ArrayList<>());
        double amount = pricingService.calculateBookingAmount(savedShowSeats, show);
        booking.setTotalAmount(amount);
        booking.setShow(show);
        // set status to Pending because payment is Pending
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setTickets(new ArrayList<>());
        return bookingRepository.save(booking);
    }
}
