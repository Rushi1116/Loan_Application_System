package com.fi.loanapp.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "loans")
public class Loan {

	@Id
	@Column(name = "loan_id", nullable = false)
	private String loanId;

	@Column(name = "loan_type")
	private String loanType;

	@Column(name = "loan_reason")
	private String loanReason;

	@Column(name = "loan_amount", nullable = false)
	private long loanAmount;

	@Column(name = "interest_rate")
	private int interestRate = 13;

	@Column(name = "trade_date")
	private Date tradeDate;

	@Column(name = "start_Date")
	private Date startDate;

	@Column(name = "loan_tenure")
	private int loanTenure;

	@Column(name = "loan_terms")
	private int loanTerms;

	@Column(name = "payment_cycles")
	private int paymentCycles;

	@Column(name = "maturity_date")
	private String maturityDate;

	@Column(name = "emi")
	private long emi;

	@Column(name = "total_amount")
	private long totalAmount;
	
	@Column(name = "paid_amount")
	private long paidAmount = 0;
	
	@Column(name = "loan_status")
	private String loanStatus = "pending";
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_loan_id", referencedColumnName = "loan_id")
	private List<Payment> payment;
	
	@Column(name = "fk_customer_id", nullable = false)
	private String fkCustomerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
	private Customer customer;
	
	public Loan(String loanId, long loanAmount,String loanType, String loanReason, int loanTenure, String loanStatus, Customer customer) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.loanType = loanType;
        this.loanReason = loanReason;
        this.loanTenure = loanTenure;
        this.loanStatus = loanStatus;
        this.customer = customer;
    }
}
