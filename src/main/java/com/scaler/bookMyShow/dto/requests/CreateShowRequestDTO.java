package com.scaler.bookMyShow.dto.requests;

import com.scaler.bookMyShow.models.enums.Feature;
import com.scaler.bookMyShow.models.enums.SeatType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateShowRequestDTO {

    private int userId;
    private int movieId;
    private int screenId;
    private Date startTime;
    private Date endTime;
    private List<Pair<SeatType, Double>> pricingConfig;
    private List<Feature> features;

}
