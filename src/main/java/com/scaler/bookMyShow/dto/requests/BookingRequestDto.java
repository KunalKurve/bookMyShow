package com.scaler.bookMyShow.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDto {

    // userIds generally not sent in RequestDto's in Production
    // because spoofing of request can happen.
    // So we use JWT security tokens and we take out info from token.
    // but not implementing that so taking manually.
    private int userId;
    private int showId;
    private List<Integer> showSeatIds;
}
