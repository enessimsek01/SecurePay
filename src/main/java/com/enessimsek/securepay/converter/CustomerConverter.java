package com.enessimsek.securepay.converter;

import com.enessimsek.securepay.dto.CustomerDto;
import com.enessimsek.securepay.dto.CustomerSaveRequestDto;
import com.enessimsek.securepay.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerConverter {

    CustomerConverter INSTANCE = Mappers.getMapper(CustomerConverter.class);

    CustomerDto convertCustomerToCustomerDto(Customer customer);

    Customer convertCustomerSaveRequestDtoToCustomer(CustomerSaveRequestDto customerSaveRequestDto);
}
