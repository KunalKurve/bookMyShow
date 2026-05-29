package com.scaler.bookMyShow.service;

import com.scaler.bookMyShow.exceptions.*;
import com.scaler.bookMyShow.models.*;
import com.scaler.bookMyShow.models.enums.Feature;
import com.scaler.bookMyShow.models.enums.SeatType;
import com.scaler.bookMyShow.models.enums.ShowSeatStatus;
import com.scaler.bookMyShow.models.enums.UserType;
import com.scaler.bookMyShow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowService {

    private MovieRepository movieRepository;
    private ScreenRepository screenRepository;
    private SeatsRepository seatsRepository;
    private ShowSeatTypeRepository showSeatTypeRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;

    @Autowired
    public ShowService(
            MovieRepository movieRepository,
            ScreenRepository screenRepository,
            SeatsRepository seatsRepository,
            ShowSeatTypeRepository showSeatTypeRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            UserRepository userRepository
    ){
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        this.seatsRepository = seatsRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    public List<ShowSeat> getShowSeats(int showId) {

        return showSeatRepository.findByShowId(showId);
    }

    public Show createShow(int userId, int movieId, int screenId, Date startTime, Date endTime,
                           List<Pair<SeatType, Double>> pricingConfig, List<Feature> features)
            throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, InvalidDateException,
            UserNotFoundException, UnAuthorizedAccessException {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        if(!user.getUserType().equals(UserType.ADMIN)){
            throw new UnAuthorizedAccessException("User is not authorized");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()-> new MovieNotFoundException("Movie not found"));

        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(()-> new ScreenNotFoundException("Screen not found"));

        Date now = new Date();

        // 1. Start should be in future
        if (startTime.before(now)) {
            throw new InvalidDateException("Show cannot start in the past");
        }

        // 2. End must be after start
        if (!endTime.after(startTime)) {
            throw new InvalidDateException("End time must be after start time");
        }

        List<Feature> supportedFeatures = screen.getFeatures();
        for(Feature feature: features){
            if(!supportedFeatures.contains(feature)){
                throw new FeatureNotSupportedByScreen("Feature not supported");
            }
        }

        Show show = new Show();
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setFeatures(features);
        show.setMovie(movie);
        show.setScreen(screen);
        show = showRepository.save(show);

        List<Seat> seats = seatsRepository.findAllByScreenId(screenId);

        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat: seats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShow(show);
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeats.add(showSeat);
        }

        showSeatRepository.saveAll(showSeats);

        List<ShowSeatType> pricingList = new ArrayList<>();
        for(Pair<SeatType, Double> pair: pricingConfig){
            ShowSeatType showSeatType = new ShowSeatType();
            showSeatType.setSeatType(pair.getFirst());
            showSeatType.setPrice(pair.getSecond());
            showSeatType.setShow(show);
            pricingList.add(showSeatType);
        }

        showSeatTypeRepository.saveAll(pricingList);

        return show;



    }
}
