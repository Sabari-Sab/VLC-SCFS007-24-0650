package com.ntuc.bankbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntuc.bankbackend.model.Roles;

public interface RoleRepo extends JpaRepository<Roles, Long> {
   
}
