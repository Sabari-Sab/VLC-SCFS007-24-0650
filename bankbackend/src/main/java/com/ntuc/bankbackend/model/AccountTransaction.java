package com.ntuc.bankbackend.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="acc_trans")
@Entity
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trans_id")
    private int transId;
    @Column(name="trans_date")
    private Date transDate;
    @Enumerated(EnumType.STRING)
    @Column(name="trans_type", nullable = false)
    private TransactionType transactionType;
    @ManyToOne
    @JoinColumn(name="acc_id", nullable = false)
    private BankAccount bankAccount;
    
    public AccountTransaction(Date transDate, TransactionType transactionType) {
        this.transDate = transDate;
        this.transactionType = transactionType;
    }
}
