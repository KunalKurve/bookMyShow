package com.scaler.bookMyShow.strategies;

import com.scaler.bookMyShow.models.Show;
import com.scaler.bookMyShow.models.ShowSeat;
import com.scaler.bookMyShow.models.ShowSeatType;
import com.scaler.bookMyShow.repository.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasePricingStrategy implements PricingStrategy{


    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public BasePricingStrategy(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    @Override
    public double calculateAmount(List<ShowSeat> savedShowSeats, Show show) {

        System.out.println("Calculating amount...");
        double total = 0;
        for(ShowSeat showSeat : savedShowSeats){

            System.out.println(showSeat.getSeat().getSeatType());

            ShowSeatType showSeatType = showSeatTypeRepository
                    .findByShowAndSeatType(show, showSeat.getSeat().getSeatType())
                    .orElseThrow(()-> new RuntimeException("No such seatType in Show"));

            System.out.println(showSeatType.getPrice());
            total += showSeatType.getPrice();
        }

        return total;
    }
}
