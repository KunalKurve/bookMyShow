package com.scaler.bookMyShow.dto.responses;

import com.scaler.bookMyShow.models.Payment;
import com.scaler.bookMyShow.models.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

    private String transactionId;
    private PaymentStatus paymentStatus;
    private ResponseStatus responseStatus;
}
