package io.github.knt5.web;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.knt5.domain.Customer;
import io.github.knt5.form.CustomerForm;
import io.github.knt5.service.CustomerService;

@Controller
@RequestMapping("customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private MessageSource messageSource;
	
	@ModelAttribute
	CustomerForm setUpCustomerForm() {
		// model.addAttribute(new CustomerForm()); --> ${customerForm} in template file
		return new CustomerForm();
	}
	
	@GetMapping
	public String list(Model model) {
		List<Customer> customers= customerService.findAll();
		model.addAttribute("customers", customers);
		
		// Check locale
		Locale locale = LocaleContextHolder.getLocale();
		System.out.println(locale);
		System.out.println(messageSource.getMessage("msg", null, locale));
		
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
	
	@GetMapping(path = "edit", params = "form")
	public String editForm(@RequestParam Integer id, CustomerForm customerForm) {
		Customer customer = customerService.findOne(id);
		BeanUtils.copyProperties(customer, customerForm);
		return "customers/edit";
	}
	
	@PostMapping(path = "edit")
	public String edit(
			@RequestParam Integer id,
			@Validated CustomerForm customerForm,
			BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			return editForm(id, customerForm);
		}
		
		// Update
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerForm, customer);
		customer.setId(id);
		customerService.update(customer);
		
		// Redirect
		return "redirect:/customers";
	}
	
	@PostMapping(path = "edit", params = "goToTop")
	public String goToTopFromEdit() {
		return "redirect:/customers";
	}
	
	@PostMapping(path = "delete")
	public String delete(@RequestParam Integer id) {
		customerService.delete(id);
		return "redirect:/customers";
	}
}
