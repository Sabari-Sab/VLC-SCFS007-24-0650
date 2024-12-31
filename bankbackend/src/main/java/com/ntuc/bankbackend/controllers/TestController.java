package com.ntuc.bankbackend.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntuc.bankbackend.model.AccountTransaction;
import com.ntuc.bankbackend.model.BankAccount;
import com.ntuc.bankbackend.model.Customer;
import com.ntuc.bankbackend.model.TransactionType;
import com.ntuc.bankbackend.repo.AccountRepo;

@RestController
public class TestController {

    @Autowired
    AccountRepo accountRepo;


    @PostMapping("/manageaccount/update")
    public String updateAcc(@RequestParam String accHolderName, @RequestParam String accHolderEmail, @RequestParam String accHolderPhone){
        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);
        List<BankAccount> accs = accountRepo.findByAccHolderName(accHolderName);
        BankAccount bankAccount = new BankAccount();
        Optional<BankAccount> optionalbankAccount = accs.stream().filter(acc -> acc.getAccHolderName().equals(accHolderName)).findFirst();
        if(optionalbankAccount.isPresent()){
          bankAccount = optionalbankAccount.get();
          System.out.println(bankAccount.getCustomer().getName());
        } else {
            return "Account does not exist";
        }

        Customer customer = bankAccount.getCustomer();
        customer.setEmail(accHolderEmail);
        customer.setPhoneNumber(accHolderPhone);
        bankAccount.setCustomer(customer);
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setTransDate(currentdate);
        accountTransaction.setTransactionType(TransactionType.UPD_ACC);
        accountTransaction.setBankAccount(bankAccount);
        bankAccount.getAccountTransaction().add(accountTransaction);
        
        accountRepo.save(bankAccount);
    
        return "Account exists";
    }

    @PostMapping("/manageaccount/remove")
    public String updateAcc(@RequestParam String accHolderName){
        List<BankAccount> accs = accountRepo.findByAccHolderName(accHolderName);
        BankAccount bankAccount = new BankAccount();
        Optional<BankAccount> optionalbankAccount = accs.stream().filter(acc -> acc.getAccHolderName().equals(accHolderName)).findFirst();
        if(optionalbankAccount.isPresent()){
          bankAccount = optionalbankAccount.get();
          System.out.println(bankAccount.getCustomer().getName());
        } else {
            return "Account does not exist";
        }

        accountRepo.delete(bankAccount);
    
        return "Account removed";
    }

    @PostMapping("/viewacc")
    public String viewAcc(@RequestParam String accHolderName){
        List<BankAccount> accs = accountRepo.findByAccHolderName(accHolderName);
        BankAccount bankAccount = new BankAccount();
        Optional<BankAccount> optionalbankAccount = accs.stream().filter(acc -> acc.getAccHolderName().equals(accHolderName)).findFirst();
        if(optionalbankAccount.isPresent()){
          bankAccount = optionalbankAccount.get();
          System.out.println(bankAccount.getCustomer().getName());
        } else {
            return "Account does not exist";
        }
        
    
        return bankAccount.toString();
    }

}
