package com.ntuc.bankbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ntuc.bankbackend.model.BankingUser;

public interface UserRepo extends JpaRepository<BankingUser, Long> {
    
    @Query("Select bu from BankingUser bu where lower(userName)= ?1")
    BankingUser findByUserName(String name);
}
