package com.ntuc.bankbackend.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntuc.bankbackend.model.AccountTransaction;
import com.ntuc.bankbackend.model.AccountType;
import com.ntuc.bankbackend.model.BankAccount;
import com.ntuc.bankbackend.model.BankingUser;
import com.ntuc.bankbackend.model.Customer;
import com.ntuc.bankbackend.model.GenderType;
import com.ntuc.bankbackend.model.RoleType;
import com.ntuc.bankbackend.model.Roles;
import com.ntuc.bankbackend.model.Status;
import com.ntuc.bankbackend.model.TransactionType;
import com.ntuc.bankbackend.repo.AccountRepo;
import com.ntuc.bankbackend.repo.CustomerRepo;
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

    @Autowired
    CustomerRepo customerRepo;

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
    public String addAccount(@RequestParam("accholdername") String accholdername, @RequestParam("accholdernric") String accholdernric, @RequestParam("accholdergender") String accholdergender, @RequestParam("accholderemailadd") String accholderemailadd, @RequestParam("accholderphonenumber") String accholderphonenumber, @RequestParam("initialdepositamount") Double deposit, @RequestParam("bankaccounttype") String bankaccouunttype) {
        
        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);
        
        List<AccountTransaction> accountTransactionsList = new ArrayList<>();
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setTransDate(currentdate);
        accountTransaction.setTransactionType(TransactionType.ADD_ACC);
        accountTransactionsList.add(accountTransaction);

        BankAccount bankAccount = new BankAccount(accholdername, GenderType.valueOf(accholdergender), AccountType.valueOf(bankaccouunttype), Status.ACTIVE, deposit, new Customer(accholdername, accholderemailadd, accholderphonenumber, accholdernric), accountTransactionsList);
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

    // @GetMapping("/view-acc")
    // public String viewAcc(){

    //     return "viewaccount";
    // }

    @GetMapping("/view-accts")
    public String viewAccts(Model model){
        model.addAttribute("acclist", accountRepo.findAll());
        return "viewaccount";
    }

    @GetMapping("/withdraw/{id}")
    public String withdraw(@PathVariable Long id, Model model){

        BankAccount bankAccount = accountRepo.findById(id).get();
        model.addAttribute("acct", bankAccount);

        return "withdraw";
    }

    @GetMapping("/deposit/{id}")
    public String deposit(@PathVariable Long id, Model model){

        BankAccount bankAccount = accountRepo.findById(id).get();
        model.addAttribute("acct", bankAccount);

        return "deposit";
    }

    @PostMapping("/withdraw/{id}")
    public String processWithdraw(@PathVariable Long id, String amount){

        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);

        BankAccount bankAccount = accountRepo.findById(id).get();
        double withdrawAmount = Double.parseDouble(amount);
        if(bankAccount.getBalance() > 0 && withdrawAmount < bankAccount.getBalance()) { 
        bankAccount.withdraw(withdrawAmount);
        }
        List<AccountTransaction> trans = bankAccount.getAccountTransaction();
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setBankAccount(bankAccount);
        accountTransaction.setTransactionType(TransactionType.WITHDRAWAL);
        accountTransaction.setTransDate(currentdate);
        trans.add(accountTransaction);
        bankAccount.setAccountTransaction(trans);

        accountRepo.save(bankAccount);
    
        return "redirect:/view-accts";
    }

    @PostMapping("/deposit/{id}")
    public String processDeposit(@PathVariable Long id, String amount){

        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);

        BankAccount bankAccount = accountRepo.findById(id).get();
        double depositAmount = Double.parseDouble(amount);
        if(depositAmount > 0) { 
        bankAccount.setBalance(depositAmount);
        }
        List<AccountTransaction> trans = bankAccount.getAccountTransaction();
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setBankAccount(bankAccount);
        accountTransaction.setTransactionType(TransactionType.DEPOSIT);
        accountTransaction.setTransDate(currentdate);
        trans.add(accountTransaction);
        bankAccount.setAccountTransaction(trans);

        accountRepo.save(bankAccount);
    
        return "redirect:/view-accts";
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
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateCustomer(@RequestParam Long userid,@RequestParam("emailaddress") String emailAddress, @RequestParam("phonenumber") String phoneNumber, @RequestParam String nric, @RequestParam String address) {
        
        Customer customer = customerRepo.findById(userid).get();
        customer.setEmail(emailAddress);
        customer.setAddress(address);
        customer.setNRIC(nric);
        customer.setPhoneNumber(phoneNumber);

        customerRepo.save(customer);

        BankingUser bankingUser = userRepo.findByUserName(customer.getName());
        bankingUser.setEmail(emailAddress);
        userRepo.save(bankingUser);

        return "redirect:/home";
    }

    @GetMapping("/updateView")
    public String updateView(Model model, Principal principal) {
       
        Customer customer = customerRepo.findByName(principal.getName());
        model.addAttribute("custDetl", customer);
        
        return "updateacc";
    }

    @GetMapping("/viewTransaction")
    public String viewAccTrans(Principal principal, Model model){
        BankingUser user = userRepo.findByUserName(principal.getName());
        List<BankAccount> bankAccounts = accountRepo.findByAccHolderName(user.getName());
        BankAccount bankAccount = bankAccounts.get(0);
        List<AccountTransaction> accountTransactions = bankAccount.getAccountTransaction();
        model.addAttribute("transactions", accountTransactions);
        model.addAttribute("bankacct", bankAccount);
        return "viewtransaction";
    }

    @GetMapping("/closeaccount/{id}")
    public String closeacc(@PathVariable long id){
        BankAccount bankAccount = accountRepo.findById(id).get();
        accountRepo.delete(bankAccount);
        return "redirect:/view-accts";
    }

    @GetMapping("/transferfunds")
    public String transferFunds(Principal principal, Model model){
        BankingUser user = userRepo.findByUserName(principal.getName());
        List<BankAccount> bankAccounts = accountRepo.findByAccHolderName(user.getName());
        BankAccount bankAccount = bankAccounts.get(0);
        String accHolderName = bankAccount.getAccHolderName();
        model.addAttribute("accts", bankAccounts);
        model.addAttribute("accHolderName", accHolderName);

        return "transferFunds";
    }
}
