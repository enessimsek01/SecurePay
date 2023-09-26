package com.enessimsek.securepay.service;


import com.enessimsek.securepay.converter.CustomerConverter;
import com.enessimsek.securepay.dto.CustomerDto;
import com.enessimsek.securepay.dto.CustomerSaveRequestDto;
import com.enessimsek.securepay.entity.Customer;
import com.enessimsek.securepay.exception.*;
import com.enessimsek.securepay.repository.CustomerRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto newCustomer(CustomerSaveRequestDto customerSaveRequestDto) {

        Boolean isCustomerExist = customerRepository.findByEmail(customerSaveRequestDto.getEmail());

        if (BooleanUtils.isTrue(isCustomerExist)) {
            throw new DuplicatedEmailException(customerSaveRequestDto.getEmail());
        }

        if(!isValidEmail(customerSaveRequestDto.getEmail())){
            throw new EmailNotValidException(customerSaveRequestDto.getEmail());
        }

        if(isNameEmptyOrNull(customerSaveRequestDto.getFirstName()) || isNameEmptyOrNull(customerSaveRequestDto.getLastName())){
            throw new NameFieldsCanNotBeEmptyException();
        }

        if(isNameAvailableForSize(customerSaveRequestDto.getFirstName())||isNameAvailableForSize(customerSaveRequestDto.getLastName())){
            throw new NameSizesIsNotAvailableException();
        }

        Customer customer = CustomerConverter.INSTANCE.convertCustomerSaveRequestDtoToCustomer(customerSaveRequestDto);
        customer.setCustomerNumber(generateCustomerNumber());
        customer = customerRepository.save(customer);
        return CustomerConverter.INSTANCE.convertCustomerToCustomerDto(customer);

    }

    public CustomerDto findByCustomerNumber(String customerNumber) {
        Customer customer = customerRepository.findCustomerByCustomerNumber(customerNumber);

        if(Objects.isNull(customer)){
            throw new CustomerNotFoundException();
        }
        return CustomerConverter.INSTANCE.convertCustomerToCustomerDto(customer);
    }

    private String generateCustomerNumber() {

        UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();
        String customerNumber = Math.abs(mostSigBits) + String.valueOf(Math.abs(leastSigBits));
        customerNumber = customerNumber.substring(0, 6);
        return customerNumber;

    }

    private Boolean isValidEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);

        if(Objects.isNull(email)){
            return false;
        }

        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }

    private Boolean isNameEmptyOrNull(String name){
        return Objects.isNull(name) || name.trim().isEmpty() ;
    }

    private Boolean isNameAvailableForSize(String name){
        return name.length()<2 || name.length()>50;
    }



}
