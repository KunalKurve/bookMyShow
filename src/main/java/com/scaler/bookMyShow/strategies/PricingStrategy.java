package com.scaler.bookMyShow.strategies;

import com.scaler.bookMyShow.models.Show;
import com.scaler.bookMyShow.models.ShowSeat;

import java.util.List;

public interface PricingStrategy {



    double calculateAmount(List<ShowSeat> savedShowSeats, Show show);
}
