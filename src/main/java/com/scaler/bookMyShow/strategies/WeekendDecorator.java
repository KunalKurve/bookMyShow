package com.scaler.bookMyShow.strategies;

import com.scaler.bookMyShow.models.Show;
import com.scaler.bookMyShow.models.ShowSeat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeekendDecorator extends PricingDecorator{

    public WeekendDecorator(PricingStrategy pricingStrategy){
        super(pricingStrategy);
    }

    @Override
    public double calculateAmount(List<ShowSeat> savedShowSeats, Show show) {

        double amount = pricingStrategy.calculateAmount(savedShowSeats, show);

        return amount * 1.2;
    }
}
