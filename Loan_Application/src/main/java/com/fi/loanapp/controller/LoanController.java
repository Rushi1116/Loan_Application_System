package com.fi.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fi.loanapp.entity.Loan;
import com.fi.loanapp.service.LoanService;

@Controller
@SessionAttributes({"customer_id","loan_id"})
public class LoanController {
	
	@Autowired
	private LoanService loanService;

	@PostMapping("/saveLoan")
	public String saveLoan(Model model, @ModelAttribute("loan") Loan loan) {
		String customerId = (String) model.getAttribute("customer_id");
		String getLoanId = loanService.saveLoan(loan, customerId);
		System.out.println("this is loanid===> "+getLoanId);
		model.addAttribute("loan_id", getLoanId);
		if(getLoanId != null) {
			return "redirect:/savedLoan";	
		}
		return null;
	}
	
	@GetMapping("savedLoan")
	public String getsavedLoan() {
		return "savedLoan";
	}
	
	@GetMapping("/applyLoan")
	public String getApplyLoan(Model model) {
		Loan loan = new Loan();
		model.addAttribute("objLoan", loan);
		return "applyLoan";
	}
	
	@GetMapping("EmiCalculator")
	public String getEmiCalculator() {
		return "EmiCalculator";
	}
	
	@PostMapping("/approve")
	public String approveLoan(@RequestParam("loanId") String loanId) {
	    String loan = loanService.updateLoanStatus(loanId, "approved");
	    if (loan == null) {
	        return "error bhetla";
	    }
	    return "redirect:/AdminDashboard";
	}

	@PostMapping("/reject")
	public String rejectLoan(@RequestParam("loanId") String loanId) {
	    String loan = loanService.updateLoanStatus(loanId, "rejected");
	    if (loan == null) {
	        return "error bhetla";
	    }
	    return "redirect:/AdminDashboard";
	}
}