package com.fi.loanapp.service;

public interface AdminService {

	public String validateAdmin(String email, String password);
	
	public String getFirstName(String adminId);
	
	public String getLastName(String adminId);
	
}
