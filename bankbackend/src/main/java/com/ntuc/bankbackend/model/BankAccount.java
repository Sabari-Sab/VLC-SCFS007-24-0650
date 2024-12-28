package com.ntuc.bankbackend.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bank_acc")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="acc_id")
    private Long id;

    @Column(name="acc_holder_name", nullable = false)
    private String accHolderName;

    @Column(name="acc_bal", nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(name="acc_type", nullable = false)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name="acc_status")
    private Status status;
    
    @Column(nullable = false)
    private double deposit;

    public void setBalance(double deposit){
           this.balance += deposit;
    }

}
