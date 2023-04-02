package com.fi.loanapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
	@Id
	@Column(name = "customer_id", nullable = false)
	private String customerId;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "accno", nullable = false)
	private String accno;
		
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role")
	private String role = "customer";
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_customer_id", referencedColumnName = "customer_id")
	private List<Loan> loan;
	
	public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
