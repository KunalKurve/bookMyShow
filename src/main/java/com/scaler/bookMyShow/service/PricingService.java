package com.scaler.bookMyShow.service;

import com.scaler.bookMyShow.models.Show;
import com.scaler.bookMyShow.models.ShowSeat;
import com.scaler.bookMyShow.strategies.PricingStrategy;
import com.scaler.bookMyShow.strategies.PricingStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    private final PricingStrategyFactory pricingStrategyFactory;

    @Autowired
    public PricingService(PricingStrategyFactory pricingStrategyFactory){
        this.pricingStrategyFactory = pricingStrategyFactory;
    }

    public double calculateBookingAmount(List<ShowSeat> showSeats, Show show){

        PricingStrategy pricingStrategy = pricingStrategyFactory.getPricingStrategy(show);
        return pricingStrategy.calculateAmount(showSeats, show);
    }
}
