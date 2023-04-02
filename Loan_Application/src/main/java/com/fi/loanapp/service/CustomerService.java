package com.fi.loanapp.service;

import java.util.List;

import com.fi.loanapp.entity.Customer;
import com.fi.loanapp.entity.Loan;

public interface CustomerService {
		
	public Customer registerCustomer(Customer customer);
	
	public String getCustomerDetails(String email, String password);
	
	public List<Customer> getCustomerProfile(String customerId);
	
	public Customer getCustomer(Loan loan);
	
}
