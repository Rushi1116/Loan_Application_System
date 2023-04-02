package com.fi.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fi.loanapp.entity.Loan;
import com.fi.loanapp.service.AdminService;
import com.fi.loanapp.service.LoanService;

@Controller
@SessionAttributes({"admin_id","loan_id"})
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private LoanService loanService;
	
	
	@PostMapping("/validateAdmin")
	public String validateAdmin(Model model, @RequestParam("email") String email,
			@RequestParam("password") String password) {
		String adminId = adminService.validateAdmin(email, password);
		model.addAttribute("admin_id", adminId);
		if (adminId != null) {
			return "redirect:/AdminDashboard";
		} else
			return "redirect:/AdminLogin";	
	}
	@GetMapping("/AdminDashboard")
	public String getAdminDashboard(Model model) {
		model.addAttribute("loans", loanService.getPendingLoan());
		String adminId = (String) model.getAttribute("admin_id");
		model.addAttribute("firstName", adminService.getFirstName(adminId));
		model.addAttribute("lastName", adminService.getLastName(adminId));
		Loan loan = new Loan();
		model.addAttribute("emptyLoan",loan);
		return "AdminDashboard";	
	}
}
