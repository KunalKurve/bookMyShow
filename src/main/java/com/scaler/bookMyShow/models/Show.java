package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
//can't just write @Entity (have to give name) because can't create table with name "show"
@Entity(name = "shows")
public class Show extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Date startTime;
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Feature> features;

    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;

    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypes;

}
