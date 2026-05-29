package com.scaler.bookMyShow.strategies;

import com.scaler.bookMyShow.models.enums.PaymentStatus;

public interface PaymentGatewayStrategy {

    PaymentStatus processPayment(double amount);

}
