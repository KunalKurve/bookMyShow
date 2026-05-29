package com.scaler.bookMyShow.dto.requests;

import com.scaler.bookMyShow.models.enums.PaymentGateway;
import com.scaler.bookMyShow.models.enums.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    private int bookingId;
    private PaymentGateway paymentGateway;
    private PaymentMode paymentMode;
}
