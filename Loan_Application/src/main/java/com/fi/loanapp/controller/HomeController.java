package com.fi.loanapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fi.loanapp.entity.Admin;
import com.fi.loanapp.entity.Customer;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHome() {
		return "Home";
	}
	
	@GetMapping("/AboutUs")
	public String getAboutUs() {
		return "AboutUs";
	}
	
	@GetMapping("/ContactUs")
	public String getContactUs() {
		return "ContactUs";
	}
	
	@GetMapping("/Login")
	public String getLogin(Model model) {
		//Creating loginData object
		Customer loginData = new Customer();
		model.addAttribute("loginData", loginData);
		return "Login";
	}
	
	@GetMapping("/Register")
	public String getRegister(Model model) {
		// creating a object to hold customer registration data
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "Register";	
	}
	
	@GetMapping("/AdminLogin")
	public String getAdminLogin(Model model) {
		//Creating loginData object 
		Admin loginData = new Admin();
		model.addAttribute("loginData", loginData);
		return "AdminLogin";
	}
}
