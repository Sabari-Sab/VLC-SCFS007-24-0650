package com.ntuc.bankbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ntuc.bankbackend.model.BankingUser;
import com.ntuc.bankbackend.repo.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankingUser bankinguser = userRepo.findByUserName(username);

        if (bankinguser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserDetails(bankinguser);
    }
}
