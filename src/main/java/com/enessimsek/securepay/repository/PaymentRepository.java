package com.enessimsek.securepay.repository;

import com.enessimsek.securepay.entity.Customer;
import com.enessimsek.securepay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
