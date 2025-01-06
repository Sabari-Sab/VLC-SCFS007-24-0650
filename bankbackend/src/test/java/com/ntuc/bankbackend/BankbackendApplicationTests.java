package com.ntuc.bankbackend;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ntuc.bankbackend.model.Roles;
import com.ntuc.bankbackend.repo.RoleRepo;

@SpringBootTest
class BankbackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	RoleRepo roleRepo;

	@Test
	void findAllMethods() {
        List<Roles> role = roleRepo.findAll();
        role.forEach((p) -> {
            System.out.println(p.getRoleType());
        });
    }
}
