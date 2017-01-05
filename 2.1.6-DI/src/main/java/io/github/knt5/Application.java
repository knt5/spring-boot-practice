package io.github.knt5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.knt5.domain.Customer;
import io.github.knt5.service.CustomerService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... strings) throws Exception {
		// Add data
		customerService.save(new Customer(1, "Joseph", "Joestar"));
		customerService.save(new Customer(2, "Jotaro", "Kujo"));
		customerService.save(new Customer(3, "Josuke", "Kujo"));
		
		// Show data
		customerService.findAll().forEach(System.out::println);
	}
}
