package com.ntuc.bankbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntuc.bankbackend.model.AccountType;
import com.ntuc.bankbackend.model.BankAccount;
import com.ntuc.bankbackend.model.Customer;
import com.ntuc.bankbackend.model.Status;
// import com.ntuc.bankbackend.model.BankAccount;
// import com.ntuc.bankbackend.model.Status;
import com.ntuc.bankbackend.repo.AccountRepo;
import com.ntuc.bankbackend.repo.CustomerRepo;

// @RestController
@Controller
public class MainController {

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

    /* @PostMapping("/manageaccount/add")
    public String addAccount(@RequestParam("accholdername") String accholdername, @RequestParam("accholderemailadd") String accholderemailadd, @RequestParam("accholderphonenumber") String accholderphonenumber, @RequestParam("initialdepositamount") Double deposit, @RequestParam("bankaccounttype") String bankaccouunttype) {
        accountRepo.save(new BankAccount(0, null, null, 0, new Cu));
        return "redirect:/manageaccount/add";
    }

    @GetMapping("/manageaccount/add")
    public String addAccount0() {
        return "addbankaccount";
    } */

}