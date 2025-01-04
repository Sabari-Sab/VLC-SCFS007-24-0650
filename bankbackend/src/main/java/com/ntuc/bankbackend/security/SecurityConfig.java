package com.ntuc.bankbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // Initialising password encryption service
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    } */

    // Created one admin user in the meantime 
    @Bean
    UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails uOne = User.withUsername("Admin").password(encoder.encode("admin")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(uOne);
    }

    // solely a login page now only for authentication of the admin user
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/logout").permitAll()
            .anyRequest().authenticated())
            .formLogin(fl -> fl.successForwardUrl("/home").loginPage("/login"))
            .logout(lo -> lo.logoutSuccessUrl("/login"))
            .csrf(c -> c.disable());
        
            return http.build();
    }
}