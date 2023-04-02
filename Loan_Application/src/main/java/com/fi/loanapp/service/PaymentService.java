package com.fi.loanapp.service;

import java.util.List;

import com.fi.loanapp.entity.Loan;
import com.fi.loanapp.entity.Payment;

public interface PaymentService {
	
	Payment savePayment(Payment objPayment, String fkloanId);
	
	Payment getCycles(String loanId);
	
	List<Payment> getPayment(List<Loan> loans);
}
