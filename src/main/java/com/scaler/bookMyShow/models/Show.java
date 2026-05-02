package com.scaler.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel{

    @ManyToOne
    private Movie movie;
    private Date start_time;
    private Date end_time;

}
