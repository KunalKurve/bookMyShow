package com.scaler.bookMyShow.models;

import com.scaler.bookMyShow.models.enums.PaymentGateway;
import com.scaler.bookMyShow.models.enums.PaymentMode;
import com.scaler.bookMyShow.models.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "payments")
public class Payment extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String transaction_id;

    private double totalAmount;

    @Enumerated(value = EnumType.STRING)
    PaymentGateway gateway;

    @Enumerated(value = EnumType.STRING)
    PaymentMode paymentMode;


    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

}
