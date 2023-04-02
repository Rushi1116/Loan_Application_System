package com.fi.loanapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fi.loanapp.entity.Admin;
import com.fi.loanapp.repository.AdminRepository;
import com.fi.loanapp.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public String validateAdmin(String email, String password) {
		List<Admin> getAdminDetails = adminRepository.findByEmailAndPassword(email, password);
		Admin objAdmin = adminRepository.findByEmail(email);
		if(getAdminDetails.isEmpty()) {
			return null;
		}
		return objAdmin.getAdminId();		
	}

	@Override
	public String getFirstName(String adminId) {
		Admin admin = adminRepository.findByAdminId(adminId);
		return admin.getFName();
	}

	@Override
	public String getLastName(String adminId) {
		Admin admin = adminRepository.findByAdminId(adminId);
		return admin.getLName();
	}

}
