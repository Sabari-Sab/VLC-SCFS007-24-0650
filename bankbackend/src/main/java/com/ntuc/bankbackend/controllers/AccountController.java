package com.ntuc.bankbackend.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntuc.bankbackend.model.AccountTransaction;
import com.ntuc.bankbackend.model.AccountType;
import com.ntuc.bankbackend.model.BankAccount;
import com.ntuc.bankbackend.model.BankingUser;
import com.ntuc.bankbackend.model.Customer;
import com.ntuc.bankbackend.model.RoleType;
import com.ntuc.bankbackend.model.Roles;
import com.ntuc.bankbackend.model.Status;
import com.ntuc.bankbackend.model.TransactionType;
import com.ntuc.bankbackend.repo.AccountRepo;
import com.ntuc.bankbackend.repo.RoleRepo;
import com.ntuc.bankbackend.repo.UserRepo;

@Controller
public class AccountController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    // password encryption
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/home")
    public String homePage(){
        return "index";
    }

    @PostMapping("/home")
    public String homePage0(Model model){
        List<BankingUser> usernames = userRepo.findAll();
        model.addAttribute("users", usernames);
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

    @GetMapping("/newuser")
    public String showAdd() {
        return "newuser";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam("username") String username, @RequestParam("fullname") String fullname, @RequestParam("emailaddress") String emailaddress, @RequestParam("password") String password, @RequestParam("roletype") String roletype ) {
        
        Roles role = new Roles();
        RoleType roleType = RoleType.valueOf(roletype);
        role.setRoleType(roleType);

        BankingUser user = new BankingUser(username, password, fullname, emailaddress, role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/newuser";
    }
}
