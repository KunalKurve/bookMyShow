package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.UserType;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//can't just write @Entity (have to give name) because can't create table with name "user"
@Entity(name = "users")
public class User extends BaseModel{

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

//    @OneToMany
//    private List<Booking> bookings;
    //@OneToMany is optional by default
    //No nullable = false
    //No foreign key in users table
    //So DB doesn't care if bookings are missing

}
