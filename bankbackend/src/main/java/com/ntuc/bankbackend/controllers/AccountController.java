package com.ntuc.bankbackend.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntuc.bankbackend.model.AccountTransaction;
import com.ntuc.bankbackend.model.AccountType;
import com.ntuc.bankbackend.model.BankAccount;
import com.ntuc.bankbackend.model.Customer;
import com.ntuc.bankbackend.model.Status;
import com.ntuc.bankbackend.model.TransactionType;
// import com.ntuc.bankbackend.model.BankAccount;
// import com.ntuc.bankbackend.model.Status;
import com.ntuc.bankbackend.repo.AccountRepo;

// @RestController
@Controller
public class AccountController {

    @Autowired
    AccountRepo accountRepo;

    // @GetMapping("/")
    // public String home(){
    //     return "Welcome, admin";
    // }

    // @PostMapping("/add-acc")
    // public String addAcc(@ModelAttribute BankAccount acc){

    //     acc.setStatus(Status.ACTIVE);
    //     if(acc.getDeposit() > 0) {
    //         acc.setBalance(acc.getDeposit());
    //     }
    //     accountRepo.save(acc);
        
    //     return acc.toString();

    // }

    @GetMapping("/home")
    public String homePage(){
        return "index";
    }

    @PostMapping("/home")
    public String homePage0(){
        return "index";
    }

    @PostMapping("/manageaccount/add")
    public String addAccount(@RequestParam("accholdername") String accholdername, @RequestParam("accholderemailadd") String accholderemailadd, @RequestParam("accholderphonenumber") String accholderphonenumber, @RequestParam("initialdepositamount") Double deposit, @RequestParam("bankaccounttype") String bankaccouunttype) {
        
        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);
        
        List<AccountTransaction> accountTransactionsList = new ArrayList<>();
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setTransDate(currentdate);
        accountTransaction.setTransactionType(TransactionType.ADD_ACC);
        accountTransactionsList.add(accountTransaction);

        BankAccount bankAccount = new BankAccount(accholdername, AccountType.valueOf(bankaccouunttype), Status.ACTIVE, deposit, new Customer(accholdername, accholderemailadd, accholderphonenumber), accountTransactionsList);
        accountTransaction.setBankAccount(bankAccount);

        accountRepo.save(bankAccount);
        
        return "redirect:/manageaccount/add";
    }

    @GetMapping("/manageaccount/add")
    public String addAccount0() {
        return "addbankaccount";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/view-acc")
    public String viewAcc(){

        return "viewaccount";
    }

    @GetMapping("/withdraw")
    public String withdraw(){

        return "withdraw";
    }

    @GetMapping("/deposit")
    public String deposit(){

        return "deposit";
    }
}
