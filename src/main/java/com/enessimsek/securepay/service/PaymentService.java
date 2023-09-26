package com.enessimsek.securepay.service;

import com.enessimsek.securepay.converter.PaymentConverter;
import com.enessimsek.securepay.dto.PaymentDto;
import com.enessimsek.securepay.dto.PaymentSaveRequestDto;
import com.enessimsek.securepay.entity.Customer;
import com.enessimsek.securepay.entity.Payment;
import com.enessimsek.securepay.exception.CustomerNotFoundException;
import com.enessimsek.securepay.repository.CustomerRepository;
import com.enessimsek.securepay.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    private CustomerRepository customerRepository;

    public PaymentService(PaymentRepository paymentRepository, CustomerRepository customerRepository) {
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
    }

    public PaymentDto newPayment(PaymentSaveRequestDto paymentSaveRequestDto) {

        Customer customer = customerRepository
                .findCustomerByCustomerNumber(paymentSaveRequestDto.getCustomerNumber());

        if(Objects.isNull(customer)){
            throw new CustomerNotFoundException();
        }

        Payment payment = PaymentConverter.INSTANCE.convertPaymentSaveRequestDtoToPayment(paymentSaveRequestDto);
        payment.setCustomer(customer);
        payment = paymentRepository.save(payment);

        return PaymentConverter.INSTANCE.convertPaymentToPaymentDto(payment);

    }

}
