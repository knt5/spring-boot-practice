package io.github.knt5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.knt5.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
