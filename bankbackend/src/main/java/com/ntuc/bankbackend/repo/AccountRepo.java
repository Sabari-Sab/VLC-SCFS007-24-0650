package com.ntuc.bankbackend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ntuc.bankbackend.model.BankAccount;

public interface AccountRepo extends JpaRepository<BankAccount, Long>{

    @Query("Select ba from BankAccount ba where lower(accHolderName)= ?1")
    List<BankAccount> findByAccHolderName(String custName);

}
