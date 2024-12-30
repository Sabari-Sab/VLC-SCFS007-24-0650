package com.ntuc.bankbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntuc.bankbackend.model.BankAccount;

public interface AccountRepo extends JpaRepository<BankAccount, Long>{

}
