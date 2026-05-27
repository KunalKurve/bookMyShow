package com.scaler.bookMyShow.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private boolean isLoggedIn;
    private ResponseStatus responseStatus;
}
