package com.enessimsek.securepay.converter;

import com.enessimsek.securepay.dto.PaymentDto;
import com.enessimsek.securepay.dto.PaymentSaveRequestDto;
import com.enessimsek.securepay.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentConverter {

    PaymentConverter INSTANCE = Mappers.getMapper(PaymentConverter.class);

    PaymentDto convertPaymentToPaymentDto(Payment payment);
    @Mapping(target ="customer.customerNumber" ,source ="customerNumber" )
    Payment convertPaymentSaveRequestDtoToPayment(PaymentSaveRequestDto paymentSaveRequestDto);
}
