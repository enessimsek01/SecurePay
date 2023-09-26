package com.enessimsek.securepay.repository;


import com.enessimsek.securepay.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Customer findCustomerByCustomerNumber(String customerNumber);

    @Query("SELECT CASE WHEN (COUNT(c)>0) THEN true ELSE false END FROM Customer c where c.email= :email")
    Boolean findByEmail(@Param("email") String email);

}
