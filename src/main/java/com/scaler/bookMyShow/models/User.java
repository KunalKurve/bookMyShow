package com.scaler.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//can't just write @Entity (have to give name) because can't create table with name "user"
@Entity(name = "users")
public class User extends BaseModel{

    private String name;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

}
