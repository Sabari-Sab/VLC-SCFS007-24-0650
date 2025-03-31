package com.ntuc.bankbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntuc.bankbackend.model.BankingUser;
import com.ntuc.bankbackend.model.RoleType;
import com.ntuc.bankbackend.model.Roles;
import com.ntuc.bankbackend.repo.UserRepo;

@Controller
public class UserController {

    @Autowired
    UserRepo userRepo;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

}
