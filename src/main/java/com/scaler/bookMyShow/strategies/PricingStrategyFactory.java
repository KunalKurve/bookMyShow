package com.scaler.bookMyShow.strategies;

import com.scaler.bookMyShow.models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class PricingStrategyFactory {


    private final BasePricingStrategy basePricingStrategy;

    @Autowired
    public PricingStrategyFactory(BasePricingStrategy basePricingStrategy){
        this.basePricingStrategy = basePricingStrategy;
    }

    public PricingStrategy getPricingStrategy(Show show){

        PricingStrategy pricingStrategy = basePricingStrategy;

        if(isWeekend(show)){
            pricingStrategy = new WeekendDecorator(pricingStrategy);
        }

        return pricingStrategy;
    }

    public boolean isWeekend(Show show){

        DayOfWeek day = DayOfWeek.of(show.getStartTime().getDay());

        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;

    }


}
