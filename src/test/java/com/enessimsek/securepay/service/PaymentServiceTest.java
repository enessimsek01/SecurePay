package com.enessimsek.securepay.service;

import com.enessimsek.securepay.dto.PaymentDto;
import com.enessimsek.securepay.dto.PaymentSaveRequestDto;
import com.enessimsek.securepay.entity.Customer;
import com.enessimsek.securepay.entity.Payment;
import com.enessimsek.securepay.exception.CustomerNotFoundException;
import com.enessimsek.securepay.repository.CustomerRepository;
import com.enessimsek.securepay.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PaymentServiceTest {


    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNewPayment() {

        PaymentSaveRequestDto paymentSaveRequestDto = new PaymentSaveRequestDto();
        paymentSaveRequestDto.setCustomerNumber("12345");

        Customer customer = new Customer();
        customer.setCustomerNumber(paymentSaveRequestDto.getCustomerNumber());

        Payment payment = new Payment();
        payment.setCustomer(customer);

        when(customerRepository.findCustomerByCustomerNumber(paymentSaveRequestDto.getCustomerNumber())).thenReturn(customer);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        PaymentDto paymentDto = paymentService.newPayment(paymentSaveRequestDto);

        assertNotNull(paymentDto);
        assertEquals(paymentSaveRequestDto.getCustomerNumber(), payment.getCustomer().getCustomerNumber());
    }

    @Test
    public void testNewPaymentWithCustomerNotFound() {

        PaymentSaveRequestDto requestDto = new PaymentSaveRequestDto();
        requestDto.setCustomerNumber("not available");

        when(customerRepository.findCustomerByCustomerNumber(requestDto.getCustomerNumber())).thenReturn(null);

        assertThrows(CustomerNotFoundException.class, () -> paymentService.newPayment(requestDto));
    }



}