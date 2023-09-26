package com.enessimsek.securepay.controller;

import com.enessimsek.securepay.dto.PaymentDto;
import com.enessimsek.securepay.dto.PaymentSaveRequestDto;
import com.enessimsek.securepay.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<PaymentDto> newPayment(@RequestBody PaymentSaveRequestDto paymentSaveRequestDto){
        PaymentDto paymentDto = paymentService.newPayment(paymentSaveRequestDto);

        return ResponseEntity.ok(paymentDto);
    }

}
