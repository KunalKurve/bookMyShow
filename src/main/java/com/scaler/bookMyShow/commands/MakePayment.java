package com.scaler.bookMyShow.commands;

import com.scaler.bookMyShow.controller.PaymentController;
import com.scaler.bookMyShow.dto.requests.PaymentRequestDto;
import com.scaler.bookMyShow.dto.responses.PaymentResponseDto;
import com.scaler.bookMyShow.models.enums.PaymentGateway;
import com.scaler.bookMyShow.models.enums.PaymentMode;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MakePayment implements Command{

    private String COMMAND_NAME = "MakePayment";
    private PaymentController paymentController;

    @Autowired
    private MakePayment(PaymentController paymentController){
        this.paymentController = paymentController;
    }

    @Override
    public boolean matches(String input) {
        String[] commandFragments = input.split(" ");
        if(commandFragments[0].equalsIgnoreCase(COMMAND_NAME)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        String[] commandFragments = input.split(" ");
        try{
            PaymentRequestDto paymentRequestDto = getPaymentRequestDto(commandFragments);

            PaymentResponseDto paymentResponseDto = paymentController.makePayment(paymentRequestDto);

            System.out.println("Payment created with ID: " + paymentResponseDto.getTransactionId());
            System.out.println(paymentResponseDto.getPaymentStatus());
            System.out.println(paymentResponseDto.getResponseStatus());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Nonnull
    private static PaymentRequestDto getPaymentRequestDto(String[] commandFragments) {
        int bookingId = Integer.parseInt(commandFragments[1]);
        PaymentMode mode = PaymentMode.valueOf(commandFragments[2]);
        PaymentGateway gateway = PaymentGateway.valueOf(commandFragments[3]);

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setBookingId(bookingId);
        paymentRequestDto.setPaymentMode(mode);
        paymentRequestDto.setPaymentGateway(gateway);
        return paymentRequestDto;
    }
}
