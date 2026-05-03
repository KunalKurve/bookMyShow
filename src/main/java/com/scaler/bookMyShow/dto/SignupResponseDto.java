package com.scaler.bookMyShow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {

    private ResponseStatus responseStatus;
    private int userId;
}
