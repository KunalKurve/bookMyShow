package com.scaler.bookMyShow.dto.responses;

import com.scaler.bookMyShow.models.Show;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShowResponseDTO {

    private Show show;
    private ResponseStatus responseStatus;
}
