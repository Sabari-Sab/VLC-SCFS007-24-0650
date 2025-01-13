package com.ntuc.bankbackend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.ntuc.bankbackend.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("Select c from Customer c where lower(name)= ?1")
    List<Customer> findByName(String name);

}
