package com.scaler.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    private Date start_time;
    private Date end_time;

    @ManyToOne
    @JoinColumn(name = "screen_d")
    private Screen screen;

    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypes;

}
