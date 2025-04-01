package com.ntuc.bankbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // Initialising password encryption service
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /* // Created one admin user in the meantime 
    @Bean
    UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails uOne = User.withUsername("Admin").password(encoder.encode("admin")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(uOne);
    } */

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    // solely a login page now only for authentication of the admin user
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/logout", "/newuser", "/save").permitAll()
            .anyRequest().authenticated())
            .formLogin(fl -> fl.successForwardUrl("/home").loginPage("/login"))
            .logout(lo -> lo.logoutSuccessUrl("/login"))
            .csrf(c -> c.disable());
        
            return http.build();
    }
}