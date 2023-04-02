package com.fi.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fi.loanapp.entity.Loan;
import com.fi.loanapp.entity.Payment;
import com.fi.loanapp.service.CustomerService;
import com.fi.loanapp.service.LoanService;
import com.fi.loanapp.service.PaymentService;

@Controller
@SessionAttributes({"loan_id","fkloanId"})
public class PaymentController {

	 @Autowired
	 private PaymentService paymentService;
	 
	 @Autowired
	 private LoanService loanService;
	 
	 @Autowired
	 private CustomerService customerService;
	 
		/*
		 * @PostMapping("/PayEmi") public String
		 * fetchPayment(@ModelAttribute("emptyLoan") Loan emptyLoan) {
		 * System.out.println("---->"+emptyLoan.getPaidAmount()); return "";
		 * 
		 * }
		 */
	 
	 @GetMapping("/PayEmi")
	 public String getPayEmi(Model model, @ModelAttribute("emptyLoan") Loan filledLoan) {
		 try {
			 model.addAttribute("fkloanId", filledLoan.getLoanId());
			 Loan loan = loanService.getLoan(filledLoan.getLoanId());
			 model.addAttribute("loan", loan);
			 model.addAttribute("customer", customerService.getCustomer(loan));
			 Payment cycle = paymentService.getCycles(filledLoan.getLoanId());
			 model.addAttribute("cycle", cycle);
			 Payment objPayment = new Payment();
			 model.addAttribute("objPayment", objPayment);
			 return"PayEmi";
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	 }
	 
	 @PostMapping("/savePayment")
	 public String savePayment(Model model, @ModelAttribute("objPayment") Payment objPayment) {
		 try {
			 String fkloanId = (String) model.getAttribute("fkloanId");
			 Payment payment = paymentService.savePayment(objPayment,fkloanId);
			 if(payment == null) {
				 return null;
			 } 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/savePayment";
	 }
	 
	 @GetMapping("savePayment")
	 public String savedPayment() {
		return "savePayment";
		 
	 }
}
