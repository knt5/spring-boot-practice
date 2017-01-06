package io.github.knt5.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.knt5.domain.Customer;
import io.github.knt5.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
	@Autowired
	protected CustomerService customerService;
	
	/*@GetMapping
	public List<Customer> getCustomers() {
		return customerService.findAll();
	}*/
	
	@GetMapping
	public Page<Customer> getCustomers(@PageableDefault Pageable pageable) {
		return customerService.findAll(pageable);
	}
	
	@GetMapping(path = "{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return customerService.findOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer, UriComponentsBuilder uriBuilder) {
		// Add a created resource URI to POST response header
		Customer createdCustomer = customerService.create(customer);
		URI uri = uriBuilder
				.path("api/customers/{id}")
				.buildAndExpand(createdCustomer.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(createdCustomer);
	}
	
	@PutMapping(path = "{id}")
	public Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
		customer.setId(id);
		return customerService.update(customer);
	}
	
	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
	}
}
