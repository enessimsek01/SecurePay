package com.enessimsek.securepay.service;

import com.enessimsek.securepay.dto.CustomerDto;
import com.enessimsek.securepay.dto.CustomerSaveRequestDto;
import com.enessimsek.securepay.entity.Customer;
import com.enessimsek.securepay.exception.*;
import com.enessimsek.securepay.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {


    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNewCustomer() {
        CustomerSaveRequestDto requestDto = new CustomerSaveRequestDto();
        requestDto.setEmail("test@gmail.com");
        requestDto.setFirstName("test");
        requestDto.setLastName("test");

        Customer customer = new Customer();
        customer.setEmail(requestDto.getEmail());
        customer.setFirstName(requestDto.getFirstName());
        customer.setLastName(requestDto.getLastName());

        when(customerRepository.findByEmail(requestDto.getEmail())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto response = customerService.newCustomer(requestDto);

        assertNotNull(response);
        assertEquals(requestDto.getEmail(), response.getEmail());
        assertEquals(requestDto.getFirstName(), response.getFirstName());
        assertEquals(requestDto.getLastName(), response.getLastName());
    }

    @Test
    public void testNewCustomerWithDuplicatedEmail() {
        CustomerSaveRequestDto requestDto = new CustomerSaveRequestDto();
        requestDto.setEmail("test@gmail.com");

        when(customerRepository.findByEmail(requestDto.getEmail())).thenReturn(true);

        assertThrows(DuplicatedEmailException.class, () -> customerService.newCustomer(requestDto));
    }

    @Test
    public void testNewCustomerWithInvalidEmail() {
        CustomerSaveRequestDto requestDto = new CustomerSaveRequestDto();
        requestDto.setEmail("invalid-email");

        assertThrows(EmailNotValidException.class, () -> customerService.newCustomer(requestDto));
    }

}