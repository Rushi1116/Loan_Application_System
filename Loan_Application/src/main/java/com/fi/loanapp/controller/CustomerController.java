package com.fi.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fi.loanapp.entity.Customer;
import com.fi.loanapp.entity.Loan;
import com.fi.loanapp.service.CustomerService;
import com.fi.loanapp.service.LoanService;
import com.fi.loanapp.service.PaymentService;

@Controller
@SessionAttributes("customer_id")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private PaymentService paymentService;

	// handle method to handle list of customers and return mode and view

	@PostMapping("/addcustomer")
	public String registerCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.registerCustomer(customer);
		return "redirect:/RegisterSuccess";
	}

	@GetMapping("/RegisterSuccess")
	public String registerSuccess() {
		return "RegisterSuccess";
	}

	@PostMapping("/verifycustomer")
	public String verifyCustomer(Model model, @RequestParam("email") String email,
			@RequestParam("password") String password) {
		String customerId = customerService.getCustomerDetails(email, password);
		model.addAttribute("customer_id", customerId);
		if (customerId != null) {
			return "redirect:/CustomerDashBoard";
		} else
			return "redirect:/Login";
	}

	@GetMapping("/CustomerDashBoard")
	public String getDashBoard(Model model) {
		String fkCustomerId = (String) model.getAttribute("customer_id");
		model.addAttribute("loans", loanService.getByFkCustomerId(fkCustomerId));
		model.addAttribute("payments", paymentService.getPayment(loanService.getByFkCustomerId(fkCustomerId)));
		Loan emptyLoan = new Loan();
		model.addAttribute("emptyLoan", emptyLoan);
		return "CustomerDashBoard";
	}

	@GetMapping("/CustomerProfile")
	public String getProfile(Model model) {
		String customerId = (String) model.getAttribute("customer_id");
		System.out.println("this --->"+customerId);
		System.out.println("See this => "+customerService.getCustomerProfile(customerId));
		model.addAttribute("profile", customerService.getCustomerProfile(customerId));
		return "CustomerProfile";
	}

}
