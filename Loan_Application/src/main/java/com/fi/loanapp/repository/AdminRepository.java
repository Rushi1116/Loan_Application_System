package com.fi.loanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fi.loanapp.entity.Admin;
import com.fi.loanapp.entity.Customer;

public interface AdminRepository extends JpaRepository<Admin, String>{
	List<Admin> findByEmailAndPassword(String email, String password);
	
	Admin findByEmail(String email);
	
	Admin findByAdminId(String adminId);
}
