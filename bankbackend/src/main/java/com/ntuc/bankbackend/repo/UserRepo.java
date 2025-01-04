package com.ntuc.bankbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntuc.bankbackend.model.BankingUser;

public interface UserRepo extends JpaRepository<BankingUser, Long> {

    BankingUser findByUserName(String name);
}
