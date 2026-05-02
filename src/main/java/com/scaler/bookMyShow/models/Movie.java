package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.Feature;
import com.scaler.bookMyShow.models.enums.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "movies")
public class Movie extends BaseModel{

    private String name;
    private int duration;

    @ManyToMany
    private List<Language> languageList;

    @ManyToMany
    private List<Feature> features;
}
