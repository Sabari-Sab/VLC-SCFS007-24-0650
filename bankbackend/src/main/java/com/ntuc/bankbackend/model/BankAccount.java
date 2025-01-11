package com.ntuc.bankbackend.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @Column(name="acc_status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    private GenderType gender;
    
    @Column(nullable = false)
    private double deposit;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountTransaction> accountTransaction;

    public void setBalance(double deposit){
           this.balance += deposit;
    }

    public BankAccount(String accHolderName, GenderType gender, AccountType accountType, Status status, double deposit, Customer customer,
            List<AccountTransaction> accountTransaction) {
        this.accHolderName = accHolderName;
        this.gender = gender;
        this.accountType = accountType;
        this.status = status;
        this.setBalance(deposit);
        this.deposit = deposit;
        this.customer = customer;
        this.accountTransaction = accountTransaction;
    }

    @Override
    public String toString() {
        return "BankAccount [id=" + id + ", accHolderName=" + accHolderName + ", balance=" + balance + ", accountType="
                + accountType + ", status=" + status + ", deposit=" + deposit + "]";
    }

    
}
