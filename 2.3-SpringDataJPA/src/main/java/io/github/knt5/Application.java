package io.github.knt5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import io.github.knt5.domain.Customer;
import io.github.knt5.repository.CustomerRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... strings) throws Exception {
		// Add
		Customer createdCustomer = customerRepository.save(new Customer(null, "Jolyne", "Kujo"));
		System.out.println(createdCustomer + " is created.");
		
		// Set paging
		Pageable pageable = new PageRequest(1, 3);
		Page<Customer> page = customerRepository.findAll(pageable);
		
		// Show page data
		System.out.println("--------------------------------------------");
		System.out.println("Page size:      " + page.getSize());
		System.out.println("Page number:    " + page.getNumber());
		System.out.println("Total pages:    " + page.getTotalPages());
		System.out.println("Total elements: " + page.getTotalElements());
		System.out.println("--------------------------------------------");
		page.getContent().forEach(System.out::println);
		System.out.println("--------------------------------------------");
		customerRepository.findAllOrderByName(pageable).forEach(System.out::println);
		
		// Show all
		System.out.println("--------------------------------------------");
		customerRepository.findAllOrderByName().forEach(System.out::println);
	}
}
