package com.enessimsek.securepay.controller;

import com.enessimsek.securepay.dto.PaymentDto;
import com.enessimsek.securepay.dto.PaymentSaveRequestDto;
import com.enessimsek.securepay.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PaymentControllerTest {
    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNewPayment() {
        PaymentSaveRequestDto paymentSaveRequestDto = new PaymentSaveRequestDto();
        paymentSaveRequestDto.setAmount(250.0);
        paymentSaveRequestDto.setCardNumber("111");
        paymentSaveRequestDto.setCustomerNumber("111");

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(250.0);
        paymentDto.setCardNumber("111");

        when(paymentService.newPayment(paymentSaveRequestDto)).thenReturn(paymentDto);

        ResponseEntity<PaymentDto> response = paymentController.newPayment(paymentSaveRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}