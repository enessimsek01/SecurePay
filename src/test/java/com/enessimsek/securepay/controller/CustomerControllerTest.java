package com.enessimsek.securepay.controller;

import com.enessimsek.securepay.dto.CustomerDto;
import com.enessimsek.securepay.dto.CustomerSaveRequestDto;
import com.enessimsek.securepay.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void newCustomer() {

        CustomerSaveRequestDto customerSaveRequestDto = new CustomerSaveRequestDto();

        customerSaveRequestDto.setEmail("test@gmail.com");
        customerSaveRequestDto.setFirstName("test");
        customerSaveRequestDto.setLastName("test");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerNumber("111");
        customerDto.setEmail("test@gmail.com");
        customerDto.setFirstName("test");
        customerDto.setLastName("test");
        when(customerService.newCustomer(customerSaveRequestDto)).thenReturn(customerDto);

        ResponseEntity<CustomerDto> response = customerController.newCustomer(customerSaveRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void findAllPaymentsByCustomerNumber() {
        String customerNumber = "123456";

        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerNumber(customerNumber);;

        when(customerService.findByCustomerNumber(customerNumber)).thenReturn(customerDto);

        ResponseEntity<CustomerDto> response = customerController.findAllPaymentsByCustomerNumber(customerNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}