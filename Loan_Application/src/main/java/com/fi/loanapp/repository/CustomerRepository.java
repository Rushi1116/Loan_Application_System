package com.fi.loanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fi.loanapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	List<Customer> findByEmailAndPassword(String email, String password);
	
	Customer findByEmail(String email);
	
	List<Customer> findByCustomerId(String customerId);
	
	
}
