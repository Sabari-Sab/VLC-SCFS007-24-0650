package com.ntuc.bankbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    @Column(name="user_name")
    private String userName;
    private String password;
    private String name;
    private String email;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="role_id")
    private Roles role;
    
    public BankingUser(String userName, String password, String name, String email, Roles role) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

}
