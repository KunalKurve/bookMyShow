package com.scaler.bookMyShow.strategies;

import com.scaler.bookMyShow.models.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RazorpayGatewayStrategy implements PaymentGatewayStrategy{
    @Override
    public PaymentStatus processPayment(double amount) {

        Random random = new Random();

        int value = random.nextInt(100);

        if(value < 80){
            System.out.println("Payment SUCCESS");
            return PaymentStatus.SUCCESS;
        }
        System.out.println("Payment FAILED");
        return PaymentStatus.FAILED;
    }
}
