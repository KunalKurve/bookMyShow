package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.Feature;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "screens")
public class Screen extends BaseModel{

    private int screen_no;

    @OneToMany
    private List<Feature> features;

    @ManyToOne
    private Theatre theatre;

    @OneToMany
    private List<Show> shows;

    @OneToMany
    private List<Seat> seats;

}
