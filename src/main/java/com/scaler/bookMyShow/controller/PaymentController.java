package com.scaler.bookMyShow.controller;

import com.scaler.bookMyShow.dto.requests.PaymentRequestDto;
import com.scaler.bookMyShow.dto.responses.PaymentResponseDto;
import com.scaler.bookMyShow.dto.responses.ResponseStatus;
import com.scaler.bookMyShow.models.Payment;
import com.scaler.bookMyShow.models.enums.PaymentStatus;
import com.scaler.bookMyShow.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public PaymentResponseDto makePayment(PaymentRequestDto requestDto){
        PaymentResponseDto responseDto = new PaymentResponseDto();
        try{
            Payment payment = paymentService.makePayment(
                    requestDto.getBookingId(),
                    requestDto.getPaymentMode(),
                    requestDto.getPaymentGateway()
            );
            responseDto.setTransactionId(payment.getTransaction_id());
            responseDto.setPaymentStatus(PaymentStatus.SUCCESS);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setPaymentStatus(PaymentStatus.FAILED);
            responseDto.setResponseStatus(ResponseStatus.FAILED);
        }
        return responseDto;
    }
}
