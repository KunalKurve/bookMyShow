package com.scaler.bookMyShow.strategies;

import com.scaler.bookMyShow.models.enums.PaymentGateway;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayStrategyFactory {

    private final RazorpayGatewayStrategy razorpayGatewayStrategy;
    private final PaytmGatewayStrategy paytmGatewayStrategy;

    public PaymentGatewayStrategyFactory(
            RazorpayGatewayStrategy razorpayGatewayStrategy,
            PaytmGatewayStrategy paytmGatewayStrategy) {

        this.razorpayGatewayStrategy = razorpayGatewayStrategy;
        this.paytmGatewayStrategy = paytmGatewayStrategy;
    }

    public PaymentGatewayStrategy getPaymentGateway(PaymentGateway gateway) {

        return switch (gateway) {

            case RAZORPAY -> razorpayGatewayStrategy;

            case PAYTM -> paytmGatewayStrategy;

            default -> throw new RuntimeException("Unsupported gateway");
        };
    }
}