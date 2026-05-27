package com.scaler.bookMyShow.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {

    private ResponseStatus responseStatus;
    private int userId;
}
