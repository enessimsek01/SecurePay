package com.enessimsek.securepay.controller;

import com.enessimsek.securepay.dto.CustomerDto;
import com.enessimsek.securepay.dto.CustomerSaveRequestDto;
import com.enessimsek.securepay.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> newCustomer( @Valid @RequestBody CustomerSaveRequestDto customerSaveRequestDto){

        CustomerDto customerDto = customerService.newCustomer(customerSaveRequestDto);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerDto> findAllPaymentsByCustomerNumber(@RequestParam String customerNumber){
        CustomerDto customerDto = customerService.findByCustomerNumber(customerNumber);
        return ResponseEntity.ok(customerDto);
    }
}
