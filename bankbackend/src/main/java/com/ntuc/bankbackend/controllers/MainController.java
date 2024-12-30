package com.ntuc.bankbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.ntuc.bankbackend.model.BankAccount;
// import com.ntuc.bankbackend.model.Status;
import com.ntuc.bankbackend.repo.AccountRepo;

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

}
