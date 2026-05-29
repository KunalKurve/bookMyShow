package com.scaler.bookMyShow.strategies;

import com.scaler.bookMyShow.models.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaytmGatewayStrategy implements PaymentGatewayStrategy{
    @Override
    public PaymentStatus processPayment(double amount) {
        return PaymentStatus.REFUNDED;
    }
}
