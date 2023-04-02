package com.fi.loanapp.service;

import java.util.List;

import com.fi.loanapp.entity.Loan;

public interface LoanService {

	public String saveLoan(Loan loan, String customerId);
	
	public List<Loan> getByFkCustomerId(String fkCustomerId);
	
	public List<Loan> getPendingLoan();
	
	public Loan getLoan(String loanId);
	
	public String updateLoanStatus(String loanId, String loanStatus);
	
}
