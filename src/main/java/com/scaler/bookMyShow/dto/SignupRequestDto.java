package com.scaler.bookMyShow.dto;

import com.scaler.bookMyShow.models.City;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {

    private String name;

    private String email;

    private String password;

    private String phone;

    private City city;
}
