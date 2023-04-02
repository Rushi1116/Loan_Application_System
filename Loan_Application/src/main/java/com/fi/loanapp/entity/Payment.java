package com.fi.loanapp.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    
    @Id
    private String paymentId;
    
    @Column(name = "fk_loan_id", nullable = false)
	private String fkloanId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_loan_id", referencedColumnName = "loan_id", insertable = false, updatable = false)
	private Loan loan;
    
    @Column(name = "paid_amount")
    private long paidAmount = 0;
    
    @Column(name = "due_date")
    private Date dueDate;
    
    @Column(name = "payment_date")
    private Date paymentDate;
    
    @Column(name = "payment_status")
    private String paymentStatus;
    
    @Column(name = "remain_cycles")
	private int remainCycles;
}
