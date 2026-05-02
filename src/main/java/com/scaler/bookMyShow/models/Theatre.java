package com.scaler.bookMyShow.models;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Theatre extends BaseModel{

    private String name;
    private String address;

    @OneToMany
    List<Screen> screens;
}
