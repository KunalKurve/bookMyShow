package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.Feature;
import com.scaler.bookMyShow.models.enums.Language;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "movies")
public class Movie extends BaseModel {

    private String title;
    private double rating;
    private int duration;
    private String genre;
    private int year;
    private String director;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Language> availableInLanguages;

    @ElementCollection
    private List<Feature> features;

//    @ElementCollection -> works for Collection<primitives> (Primitives = String, int, float, enums)
//    private List<String> actors;
}
