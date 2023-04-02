package com.fi.loanapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fi.loanapp.entity.Customer;
import com.fi.loanapp.entity.Loan;
import com.fi.loanapp.repository.CustomerRepository;
import com.fi.loanapp.repository.LoanRepository;
import com.fi.loanapp.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String saveLoan(Loan loan, String customerId) {
		loan.setFkCustomerId(customerId);
		loan.setLoanId(generateKey("LID-"));
		loanRepository.save(loan);
		return loan.getLoanId();
	}
	private String generateKey(String prefix) {
		String key = UUID.randomUUID().toString().split("-")[0];
		return prefix + key;
	}
	@Override
	public List<Loan> getByFkCustomerId(String fkCustomerId) {
			
		return loanRepository.findAllByFkCustomerId(fkCustomerId);
	}
	@Override
	public List<Loan> getPendingLoan() {
		 List<Loan> pendingLoans = loanRepository.findByLoanStatus("pending");
		    List<String> customerIdList = new ArrayList<>();
		    for (Loan loan : pendingLoans) {
		        customerIdList.add(loan.getFkCustomerId());
		    }
		    List<Customer> customers = customerRepository.findAllById(customerIdList);
		    Map<String, Customer> customerMap = new HashMap<>();
		    for (Customer customer : customers) {
		        customerMap.put(customer.getCustomerId(), customer);
		    }
		    List<Loan> loan2 = new ArrayList<>();
		    for (Loan loan : pendingLoans) {
		        Customer customer = customerMap.get(loan.getFkCustomerId());
		        loan2.add(new Loan(loan.getLoanId(), loan.getLoanAmount(), loan.getLoanType(), loan.getLoanReason(), loan.getLoanTenure(), loan.getLoanStatus(), customer));
		    }
		    return loan2;
	}
	@Override
	public String updateLoanStatus(String loanId, String loanStatus) {
		List<Loan> loanIds = loanRepository.findByLoanId(loanId);
		for(Loan loan : loanIds) {
			if(loan.getLoanId().equals(loanId)) {
				loan.setLoanStatus(loanStatus);
				loanRepository.save(loan);
				return loan.getLoanStatus();
			}
		}
		return null;
	}
	@Override
	public Loan getLoan(String loanId) {
		List<Loan> getList = loanRepository.findByLoanId(loanId);
		for(Loan loan : getList) {
			if(loan.getLoanId().equals(loanId)) {
				System.out.println(loan.getLoanReason());
				return loan;
			}
		}
		return null;
	}
	
}
