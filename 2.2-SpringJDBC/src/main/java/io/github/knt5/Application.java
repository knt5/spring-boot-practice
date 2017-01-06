package io.github.knt5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		
		// Show
		customerRepository.findAll().forEach(System.out::println);
	}
}
