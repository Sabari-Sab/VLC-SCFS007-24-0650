package com.ntuc.bankbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntuc.bankbackend.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
