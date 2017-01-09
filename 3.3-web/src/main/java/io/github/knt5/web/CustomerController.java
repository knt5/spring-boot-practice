package io.github.knt5.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.knt5.domain.Customer;
import io.github.knt5.form.CustomerForm;
import io.github.knt5.service.CustomerService;

@Controller
@RequestMapping("customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@ModelAttribute
	CustomerForm setUpCustomerForm() {
		// model.addAttribute(new CustomerForm()); --> ${customerForm} in template file
		return new CustomerForm();
	}
	
	@GetMapping
	public String list(Model model) {
		List<Customer> customers= customerService.findAll();
		model.addAttribute("customers", customers);
		return "customers/list";
	}
	
	@PostMapping(path = "create")
	public String create(@Validated CustomerForm customerForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return list(model);
		}
		
		// Copy properties
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerForm, customer);
		
		// Save to DB
		customerService.create(customer);
		
		// Redirect to list view
		return "redirect:/customers";
	}
}
