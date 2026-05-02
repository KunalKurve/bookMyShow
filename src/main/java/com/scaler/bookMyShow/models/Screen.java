package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "screens")
public class Screen extends BaseModel{

    private int screen_no;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Feature> features;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @OneToMany(mappedBy = "screen")
    private List<Show> shows;

    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

}
