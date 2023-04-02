package com.fi.loanapp.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fi.loanapp.entity.Loan;
import com.fi.loanapp.entity.Payment;
import com.fi.loanapp.repository.LoanRepository;
import com.fi.loanapp.repository.PaymentRepository;
import com.fi.loanapp.service.PaymentService;

@Service
public class PaymentsServiceImpl implements PaymentService {

	@Autowired
    private PaymentRepository paymentRepository;

	@Autowired
    private LoanRepository loanRepository;

	
	@Override
	public Payment savePayment(Payment objPayment, String fkloanId) {
		List<Loan> loans = loanRepository.findByLoanId(fkloanId);
		for(Loan loan : loans) {
			if(objPayment.getRemainCycles() <= loan.getPaymentCycles()) {
				System.out.println("yaha tak pohoch gaye ho");
				int remainCycles = objPayment.getRemainCycles() + 1;	
				objPayment.setRemainCycles(remainCycles);
				System.out.println("success hua kya");
				objPayment.setFkloanId(fkloanId);
				objPayment.setPaymentId((generateKey("PID-")));
				paymentRepository.save(objPayment);
				
				List<Payment> payments = paymentRepository.findByfkloanId(fkloanId);
				for(Payment payment : payments) {
					if(payment.getFkloanId().equals(loan.getLoanId())) {
						Payment demo = paymentRepository.findByfkloanIdAndRemainCycles(fkloanId, payments.stream().mapToInt(Payment::getRemainCycles).max().orElse(0));
						if(demo != null) {
							loan.setPaidAmount(demo.getPaidAmount());
							loanRepository.save(loan);
						}
					}
				}
				return objPayment;
			}
		}		
		return null;
	}
	private String generateKey(String prefix) {
		String key = UUID.randomUUID().toString().split("-")[0];
		return prefix + key;
	}
	@Override
	public Payment getCycles(String loanId) {
		List<Payment> payments = paymentRepository.findByfkloanId(loanId);
		Payment payment = new Payment();
		List<Loan> loans = loanRepository.findByLoanId(loanId);
		if(payments.isEmpty()) {
			payment.setRemainCycles(0);
			payment.setPaidAmount(0);
			for(Loan loan : loans) {
				payment.setDueDate(loan.getStartDate());
			}
			payment.setPaymentStatus("success");
			return payment;
		}
		payment.setRemainCycles(payments.stream().mapToInt(Payment::getRemainCycles).max().orElse(0));
		
		Payment demo = paymentRepository.findByfkloanIdAndRemainCycles(loanId, payments.stream().mapToInt(Payment::getRemainCycles).max().orElse(0));
		
		if(demo != null) {
			payment.setPaidAmount(demo.getPaidAmount());
			payment.setDueDate(demo.getDueDate());
		}else {
			payment.setPaidAmount(0);
			
			for(Loan loan : loans) {
				payment.setDueDate(loan.getStartDate());
			}
		}
		payment.setPaymentStatus("success");
		return payment;
	}
	@Override
	public List<Payment> getPayment(List<Loan> loans) {
		 List<String> loanIds = loans.stream().map(Loan::getLoanId).collect(Collectors.toList());
		List<Payment> payments =  paymentRepository.findByfkloanIdIn(loanIds);
		 if (payments.isEmpty()) {
			 payments.forEach(payment -> {
				    payment.setPaidAmount(0);
				    payment.setRemainCycles(0);
				  });
			 return payments;
			  }
			  
		return payments;
	}

}
