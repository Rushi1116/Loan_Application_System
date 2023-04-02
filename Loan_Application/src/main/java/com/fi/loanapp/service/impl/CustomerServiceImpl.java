package com.fi.loanapp.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fi.loanapp.entity.Customer;
import com.fi.loanapp.entity.Loan;
import com.fi.loanapp.repository.CustomerRepository;
import com.fi.loanapp.repository.LoanRepository;
import com.fi.loanapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Override
	public Customer registerCustomer(Customer customer) {
		customer.setCustomerId(generateKey("CID-"));
		return customerRepository.save(customer);
	}
	private String generateKey(String prefix) {
		String key = UUID.randomUUID().toString().split("-")[0];
		return prefix + key;
	}

	@Override
	public String getCustomerDetails(String email, String password) {
		List<Customer> getCustomerDetails = customerRepository.findByEmailAndPassword(email, password);
		Customer getMail = customerRepository.findByEmail(email);
		if (getCustomerDetails.isEmpty()) {
			return null;
		}
		return getMail.getCustomerId();
	}
	@Override
	public List<Customer> getCustomerProfile(String customerId) {
		return customerRepository.findByCustomerId(customerId);
	}
	@Override
	public Customer getCustomer(Loan loan) {
		List<Loan> getLoans = loanRepository.findByLoanId(loan.getLoanId());
		for(Loan loans : getLoans) {
			System.out.println("^^^^"+loan.getFkCustomerId());
			if(loans.getLoanId().equals(loan.getLoanId())) {
				System.out.println("****"+loan.getFkCustomerId());
				List<Customer> customers = customerRepository.findByCustomerId(loan.getFkCustomerId());
				for(Customer customer : customers) {
				System.out.println("!!!!!!"+customer.getAccno());
					return customer;
				}
				
			}
		}
		return null;
	}
}
