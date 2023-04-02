package com.fi.loanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fi.loanapp.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,String> {
	
	List<Payment> findByfkloanId(String loanId);
	
	 Payment findByfkloanIdAndRemainCycles(String loanId, Integer remainCycles);
	
	List<Payment> findByfkloanIdIn(List<String> loanIds);
}
