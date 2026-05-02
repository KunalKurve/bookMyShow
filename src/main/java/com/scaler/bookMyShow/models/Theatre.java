package com.scaler.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

@Entity(name = "theatres")
public class Theatre extends BaseModel{

    private String name;
    private String address;

    @OneToMany(mappedBy = "theatre")
    List<Screen> screens;

    @ManyToOne
    @JoinColumn(name = "city_id") //db column
    private City city;  // java field
}
