package com.scaler.bookMyShow.dto.requests;

import com.scaler.bookMyShow.models.City;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {

    private String name;

    private String password;

    private String email;

    private City city;
}
